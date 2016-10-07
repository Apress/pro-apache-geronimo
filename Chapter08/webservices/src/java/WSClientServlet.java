package samples.ws.web.client;

import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import javax.naming.*;
import javax.xml.rpc.*;
import javax.xml.namespace.*;

import samples.ws.web.*;

public class WSClientServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException{
		String nsURI="http://samples.ws.web";
		PrintWriter out=new PrintWriter(response.getWriter());
		try{
			InitialContext ic=new InitialContext();
			print(out,"Got initial context:"+ic);
			Service svc =(Service)ic.lookup("java:comp/env/service/GreetingSvc");
			print(out,"Got svc:"+svc);
			samples.usermgmt.Greeting g=(samples.usermgmt.Greeting)svc.getPort(new QName(nsURI,"GreetingPort"),samples.usermgmt.Greeting.class);
			print(out,"Got port"+g);
			print(out,"Got result : " + g.sayGreeting("Tom"));

		} catch(Exception e){
			out.println("Error MSG: "+e.getMessage());
			out.println("Error : "+e);
		}
    }

    private void print(PrintWriter out, String msg){
			System.out.println(msg);
			out.println(msg);
	}
}