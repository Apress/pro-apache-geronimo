

import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;



public class FrontController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException{
    String commandName=request.getParameter("command");
    Command command=null;
    if("welcome".equals(commandName)){
      command=new WelcomeCommand();
    } else {
      // handle this case - may be use a default command
    }
    Object msg=command.execute(request);
    request.setAttribute("message",msg);
    String nextView=command.getNextView(request,msg);
    RequestDispatcher rd=getServletContext().getRequestDispatcher(nextView);
    rd.forward(request,response);
    }
}
