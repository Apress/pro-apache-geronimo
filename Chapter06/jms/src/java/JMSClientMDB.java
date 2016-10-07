
package samples.jms.client.mdb;

import javax.ejb.*;

import javax.jms.*;


public class JMSClientMDB implements MessageDrivenBean,
                                            MessageListener
{
    private transient MessageDrivenContext mdc = null;

    /**
     * Default constructor. Creates a bean.
     */

    public JMSClientMDB() {
    }


    public void setMessageDrivenContext(MessageDrivenContext mdc)
    {
    this.mdc = mdc;
    }

    /**
     * Creates a bean. Required by EJB spec.
     */

    public void ejbCreate()
    {
    }


    public void onMessage(Message message)
    {
      try{
        if(message instanceof TextMessage){

          System.out.println("Message is :"+((TextMessage)message).getText());
        }
      }catch(Exception e){
        System.out.println("Error receiving message:"+e.getMessage());
      }

    }  // onMessage


    public void ejbRemove()
    {
    }

} // class
