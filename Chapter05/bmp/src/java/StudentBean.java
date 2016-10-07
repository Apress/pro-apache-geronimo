package samples.bmp.student;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;

public class StudentBean implements EntityBean{

    protected EntityContext context;

    private static final int PASS_MARK=40;

    // BEAN MANAGED PERSISTENT FIELDS
    private String firstName; // primary key field
    private String lastName;
    private int marks;

    public StudentBean(){ }

    // CREATE METHODS
    public StudentPKey ejbCreate(String firstName) throws CreateException {

        insertStudent(firstName,null);
        this.firstName=firstName;
        this.lastName=null;
        StudentPKey pk=new StudentPKey(firstName);
        return pk;

    }

    public void ejbPostCreate(String firstName){}

    public StudentPKey ejbCreate(String firstName, String lastName) throws CreateException{

        insertStudent(firstName,lastName);
        this.firstName=firstName;
        this.lastName=lastName;
        StudentPKey pk=new StudentPKey(firstName);
        return pk;
    }

    public void ejbPostCreate(String firstName, String lastName){}

    // GETTERS FOR PERSISTENT FIELDS
    public String getFirstName(){return firstName;}
    public String getLastName() {return lastName;}
    public int getMarks(){return marks;}

    // SETTERS FOR PERSISTENT FIELD
    public void setLastName(String name){lastName=name;}
    public void setMarks(int marks){this.marks=marks;}

    // BUSINESS METHOD
    public boolean passed(){return (marks>=PASS_MARK);}

    // HOME BUSINESS METHODS
    public int ejbHomeGetPassPercentage()throws EJBException{
      Connection conn=null;
      try {
        conn=getConnection();
        int totalCount=getTotalCount(conn);
        int passCount=getPassCount(conn);
        return (int)(((float)passCount/(float)totalCount)*100);
      }catch (Exception e){
        throw new EJBException("Failed:"+e.getMessage());
      }finally {
        close(conn);
      }
    }


    // FINDER METHOD IMPLEMENTATIONS
    public StudentPKey ejbFindByPrimaryKey(StudentPKey pk) throws FinderException{

      Connection conn=null;
      PreparedStatement ps=null;
      ResultSet rs=null;

      try{
        conn=getConnection();
        ps=conn.prepareStatement("select fname,lname,marks from stud where fname=?");
        ps.setString(1,pk.firstName);
        rs=ps.executeQuery();
        rs.next();
        // no eror return the primary key
        return pk;

      }catch(Exception e){
        throw new FinderException("Find failed:"+e.getMessage());
      }finally{
        close(rs);
        close(ps);
        close(conn);
      }

    }

    public Collection ejbFindAll() throws FinderException{
      Connection conn=null;
      PreparedStatement ps=null;
      ResultSet rs=null;

      try{
        conn=getConnection();
        ps=conn.prepareStatement("select fname from stud");
        rs=ps.executeQuery();
        Collection list=new ArrayList();
        while(rs.next()){
          list.add(new StudentPKey(rs.getString(1)));
        }
        return list;

      }catch(Exception e){
        throw new FinderException("Find failed:"+e.getMessage());
      }finally{
        close(rs);
        close(ps);
        close(conn);
      }


    }

    // EJB REQUIRED METHODS
    public void ejbPassivate() {

    }

    public void ejbActivate() {
    }

    public void ejbRemove() throws RemoveException{
      StudentPKey pk=(StudentPKey)context.getPrimaryKey();
      deleteStudent(pk.firstName);
    }

    public void ejbLoad(){

      Connection conn=null;
      PreparedStatement ps=null;
      ResultSet rs=null;


      try{
        StudentPKey pk=(StudentPKey)context.getPrimaryKey();
        conn=getConnection();
        ps=conn.prepareStatement("select fname,lname,marks from stud where fname=?");
        ps.setString(1,pk.firstName);
        rs=ps.executeQuery();
        while(rs.next()){
          this.firstName=rs.getString(1);
          this.lastName=rs.getString(2);
          this.marks=rs.getInt(3);
        }
      }catch(Exception e){
        throw new EJBException("Load FAILED:"+e);
      }finally{
        close(rs);
        close(ps);
            close(conn);
      }

    }

    public void ejbStore(){

      updateStudent();
    }

    public void setEntityContext(EntityContext context){
      this.context=context;
    }

    public void unsetEntityContext(){
    
      this.context=null;
    }

    private Connection getConnection() throws Exception{
      InitialContext ic=new InitialContext();
      DataSource ds=(DataSource)ic.lookup("java:comp/env/jdbc/mydbpool");
      return ds.getConnection();
    }

    private void insertStudent(Connection connection,String firstName)
                throws Exception {

      insertStudent(firstName,null);
    }

    private void insertStudent(String firstName, String lastName)
                throws CreateException {

      Connection conn=null;
      PreparedStatement ps=null;

      try {
        conn=getConnection();
        ps=conn.prepareStatement(
          "INSERT INTO stud (fname,lname) values (?,?)");
        ps.setString(1,firstName);
        ps.setString(2,lastName);
        ps.executeUpdate();
      } catch(Exception e){
        throw new CreateException("Create Failed:"+e.getMessage());
      }finally {
        close(ps);
        close(conn);
      }

    }

    private void updateStudent()
                throws EJBException {

      Connection conn=null;
      PreparedStatement ps=null;

      try {
        conn=getConnection();
        ps=conn.prepareStatement("update stud set lname=?, marks=? where fname=?");
        ps.setString(1,lastName);
        ps.setInt(2,marks);
        ps.setString(3,firstName);
        ps.executeUpdate();
      } catch(Exception e){
        throw new EJBException("Update Failed:"+e.getMessage());
      }finally {
        close(ps);
        close(conn);
      }

    }

    private void deleteStudent(String firstName)
                throws RemoveException {

      Connection conn=null;
      PreparedStatement ps=null;

      try {
        conn=getConnection();
        ps=conn.prepareStatement("delete from stud where fname=?");
        ps.setString(1,firstName);
        ps.executeUpdate();
      } catch(Exception e){
        throw new RemoveException("Remove Failed:"+e.getMessage());
      }finally {
        close(ps);
        close(conn);
      }

    }
    
    private int getPassCount(Connection connection)throws Exception{
      Statement st=null;
      ResultSet rs=null;
      try {
        int count=0;
        st=connection.createStatement();
        rs=st.executeQuery("Select count(*) from stud where marks >="+PASS_MARK);
        if(rs.next()){
          count=rs.getInt(1);
        }
        return count;
      }finally{
        close(rs);
        close(st);
      }

    }


    private int getTotalCount(Connection connection)throws Exception{
      Statement st=null;
      ResultSet rs=null;
      try {
        int count=0;
        st=connection.createStatement();
        rs=st.executeQuery("Select count(*) from stud");
        if(rs.next()){
          count=rs.getInt(1);
        }
        return count;
      }finally{
        close(rs);
        close(st);
      }

    }

    private void close(ResultSet rs){
      try {
        if (null!=rs){rs.close();}
      }catch(Exception e){}
    }

    private void close(Statement st){
      try {
        if (null!=st){st.close();}
      }catch(Exception e){}
    }

    private void close(Connection conn){
      try {
        if (null!=conn){conn.close();}
      }catch(Exception e){}
    }


}
