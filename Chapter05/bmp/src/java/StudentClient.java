package samples.bmp.student;


import java.io.*;
import java.util.*;

import javax.rmi.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.naming.*;



public class StudentClient extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException{

    PrintWriter out=response.getWriter();
    out.println("Student BMP-EJB Web Client ...<br> <br>");
    try {
      InitialContext context=new InitialContext();
      StudentHome home=(StudentHome)PortableRemoteObject.narrow(
        context.lookup("java:comp/env/ejb/bmpStudent"),StudentHome.class);

      Random generator =new Random();
      Student student=null;
      // create students
      for (int i=1;i<=10;i++){
        student=(Student)PortableRemoteObject.narrow(home.create("f"+i,"l"+i),
                  Student.class);
        int marks=generator.nextInt(100);
        student.setMarks(marks);
        out.println("Student [f"+i+"][l"+i+"]["+marks+"] created ...<br>");

      }

      out.println("<br><br>");

      out.println("Using findByPrimaryKey ...<br>");
      Object obj = home.findByPrimaryKey(new StudentPKey("f1"));
      student=(Student)PortableRemoteObject.narrow(obj,Student.class);
      out.println("Got Student ["+student.getFirstName()+
            "]["+student.getLastName()+"]["+student.getMarks()+"] ...<br>");
      out.println("Using Business Method ...<br>");
      out.println("Student Passed:"+student.passed()+": <br>");

      out.println("<br><br>");

      out.println("Using Home Business Method...<br>");
      int passPercentage=home.getPassPercentage();
      out.println("EJB Home Pass Percentage:"+passPercentage+":<br>");

      out.println("<br><br>");
      out.println("Using findAll ...<br>");
      Collection list = home.findAll();
      for (Iterator it=list.iterator();it.hasNext();){
        student=(Student)PortableRemoteObject.narrow(it.next(),Student.class);
        out.println("Got Student ["+student.getFirstName()+
            "]["+student.getLastName()+"]["+student.getMarks()+"][passed:"+
            student.passed()+"]...<br>");


        student.remove();
        out.println("Student Removed... <br>");
      }

    }catch(Exception e){
      out.println("Error:"+e.getMessage());
    }
    out.close();
    }
}
