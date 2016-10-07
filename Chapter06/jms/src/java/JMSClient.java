package samples.jms.client;

import java.io.*;
import javax.servlet.*;
import javax.naming.*;
import javax.servlet.http.*;
import javax.jms.*;
import javax.rmi.*;


public class JMSClient extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException{

    PrintWriter out=new PrintWriter(response.getWriter());
    out.println("JMS Client<br>");
    QueueConnection qConnection=null;
    QueueSession qSession=null;

      try{
        InitialContext ic=new InitialContext();
        out.println("Got context:"+ic+":<br>");

        Object obj=ic.lookup("java:comp/env/jms/myJMSConnectionFactory");
        QueueConnectionFactory qcf=( QueueConnectionFactory)PortableRemoteObject.narrow(obj, QueueConnectionFactory.class);
        out.println("Got JMS Connection factory ... <br>");

        qConnection=qcf.createQueueConnection();
        out.println("Got Queue Connection... <br>");

        qSession=qConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        out.println("Got Queue Session... <br>");



        Object object = ic.lookup("java:comp/env/queue/myQueue");
        Queue queue=(Queue)PortableRemoteObject.narrow(object, Queue.class) ;
        out.println("Got queue ... <br>");

        QueueSender sender=qSession.createSender(queue);

        TextMessage message=qSession.createTextMessage();
        String msg=request.getParameter("msg");
        if(null==msg){msg="sample message";}
        message.setText(msg);
        sender.send(message);

        out.println("Message:"+msg+": sent ... <br>");

      }catch(Exception e){
        out.println("Error:"+e+":<br>");
      }
      finally {
        closeConnection(qConnection);
        closeSession(qSession);

      }



    }


    private void closeConnection(Connection conn){
    try {
      if(null!=conn){
        conn.close();
      }
    }catch(Exception e){
      // ignore
    }
  }

    private void closeSession(Session session){
    try {
      if(null!=session){
        session.close();
      }
    }catch(Exception e){
      // ignore
    }
  }

}


