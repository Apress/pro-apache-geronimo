package samples.cmp.student;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;

public abstract class StudentBean implements EntityBean{

    protected EntityContext context;

    private static final int PASS_MARK=40;

    // NO DECLARATION OF PERSISTENT FIELDS


    public StudentBean(){ }

    // CREATE METHODS - INITIALIZE USING SETTERS AND RETURN NULL
    public StudentPKey ejbCreate(String firstName) throws CreateException {

        setFirstName(firstName);
        setLastName(null);
        setMarks(0);
        return null;

    }

    public void ejbPostCreate(String firstName){}

    public StudentPKey ejbCreate(String firstName, String lastName) throws CreateException{

        setFirstName(firstName);
        setLastName(lastName);
        setMarks(0);
        return null;
    }

    public void ejbPostCreate(String firstName, String lastName){}

    // ABSTRACT GETTERS FOR PERSISTENT FIELDS
    public abstract String getFirstName();
    public abstract String getLastName();
    public abstract int getMarks();

    // ABSTRACT SETTERS FOR PERSISTENT FIELD
    public abstract void setFirstName(String firstName);
    public abstract void setLastName(String lastName);
    public abstract void setMarks(int marks);

    // BUSINESS METHOD
    public boolean passed(){return (getMarks()>=PASS_MARK);}

    // HOME BUSINESS METHODS
    public int ejbHomeGetPassPercentage(){
      try{
        int totalCount=ejbSelectTotalCount().intValue();
        int passCount=ejbSelectPassCount().intValue();
        return (int)(((float)passCount/(float)totalCount)*100);
      } catch(Exception e){
        System.out.println("Exception:"+e.getMessage()+":"+e);
      }
      return 0;

    }


    // NO FINDER METHOD IMPLEMENTATIONS


    // EJB REQUIRED METHODS
    public void ejbPassivate() {}

    public void ejbActivate() {}

    public void ejbRemove() throws RemoveException{ }


  // EMPTY EJB LOAD and EJB STORE
    public void ejbLoad(){}

    public void ejbStore(){}

    public void setEntityContext(EntityContext context){
      this.context=context;
    }

    public void unsetEntityContext(){
      this.context=null;
    }


  public abstract Integer ejbSelectPassCount() throws FinderException;

  public abstract Integer ejbSelectTotalCount() throws FinderException;

}
