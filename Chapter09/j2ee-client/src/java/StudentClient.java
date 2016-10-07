package samples.client.student;

import java.util.*;
import javax.naming.*;
import javax.rmi.*;

import samples.cmp.student.*;

public class StudentClient{

public static void main(String[] args){


  try {

    System.out.println("Student EJB Standalone Client ...");

    InitialContext context=getInitialContext();
    StudentHome home=(StudentHome)PortableRemoteObject.narrow(
                context.lookup("java:comp/env/ejb/cmpStudent"),StudentHome.class);

    Random generator =new Random();
    Student student=null;
    // create students
    for (int i=1;i<=10;i++){
      student=(Student)PortableRemoteObject.narrow(home.create("f"+i,"l"+i),
              Student.class);
      int marks=generator.nextInt(101);
      student.setMarks(marks);
      System.out.println("Student [f"+i+"][l"+i+"]["+marks+"] created ...<br>");

    }


    //System.out.println("<br><br>");

    System.out.println("Using findByPrimaryKey ...<br>");

    Object obj = home.findByPrimaryKey("f1");
    student=(Student)PortableRemoteObject.narrow(obj,Student.class);
    System.out.println("Got Student ["+student.getFirstName()+
        "]["+student.getLastName()+"]["+student.getMarks()+"] ...<br>");
    System.out.println("Using Business Method ...<br>");
    System.out.println("Student Passed:"+student.passed()+": <br>");

    System.out.println("Using Home Business Method...<br>");
    int passPercentage=home.getPassPercentage();
    System.out.println("EJB Home Pass Percentage:"+passPercentage+":<br>");

    System.out.println("<br><br>");
    System.out.println("Using findAll ...<br>");
    Collection list = home.findAll();
    for (Iterator it=list.iterator();it.hasNext();){
      student=(Student)PortableRemoteObject.narrow(it.next(),Student.class);

      System.out.println("Got Student ["+student.getFirstName()+
        "]["+student.getLastName()+"]["+student.getMarks()+"][passed:"+
        student.passed()+"]...<br>");

      student.remove();
      System.out.println("Student Removed... <br>");
    }


  }catch(Exception e){
    System.out.println("Error:"+e.getMessage());
    System.out.println("Error:"+e);
  }

}


private static InitialContext getInitialContext()throws Exception{
  //Properties props = new Properties();

  //props.setProperty("java.naming.factory.initial",
  //          "org.openejb.client.RemoteInitialContextFactory");
  //props.setProperty("java.naming.provider.url", "localhost:4201");
  //props.setProperty("java.naming.security.principal", "username");
  //props.setProperty("java.naming.security.credentials", "passwd");

  //return new InitialContext(props);
  return new InitialContext();

}

}
