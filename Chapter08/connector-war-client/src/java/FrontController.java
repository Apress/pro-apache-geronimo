

import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.naming.*;

import samples.connectors.outbound.*;


public class FrontController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException{

    PrintWriter out=new PrintWriter(response.getWriter());
    out.println("Hi - there <br>");

    try {
      InitialContext ic=new InitialContext();
      out.println("Got context:"+ic+":<br>");


      try{
        SampleConnectionFactory cf=(SampleConnectionFactory)ic.lookup("java:comp/env/mySampleConnectionFactory");
        out.println("Got Connection factory:"+cf+":<br>");
        SampleConnection c=cf.createConnection();
        out.println("Got Connection :"+c+":<br>");
        String output=c.doSomething("Got Connected");
        out.println("doSomething(\"Got Connected\") returns:"+output+":<br>");
      }catch(Exception e){
        out.println("Error:"+e+":<br>");
      }


    }
    catch(Exception e){
      out.println("Error:"+e);
      out.println("Error MSG:"+e.getMessage());
    }
    }


}
