package samples.usermgmt.sf;


import java.io.*;
import java.util.*;

import javax.rmi.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.naming.*;



public class UserMgmtClient extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException{

		PrintWriter out=response.getWriter();
		out.println("Here in UserMgmt Client ...<br> <br>");
		try {
			InitialContext context=new InitialContext();
			UserManagementHome home=(UserManagementHome)PortableRemoteObject.narrow(
				context.lookup("java:comp/env/ejb/sfUserMgmt"),UserManagementHome.class);
			UserManagement userMgmt=home.create();

			userMgmt.addUser("user1");
			userMgmt.addUser("user2");
			userMgmt.addUser("user3");

			out.println("Added 3 users <br><br>");
			List users=userMgmt.listUser("user.");
			out.println("Searching for - 'user.' <br><br>");
			if(null!=users){
				for(int i=0;i<users.size();i++){
					User user=(User)users.get(i);
					out.println("Search Returned:"+user.getName()+"<br>");
				}
			}


		}catch(Exception e){
			out.println("Error:"+e.getMessage());
		}
		out.close();
    }
}
