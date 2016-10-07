
package samples.connectors.client.mdb;

import javax.ejb.*;

import samples.connectors.inbound.*;
import org.apache.commons.logging.*;


public class SampleMDB implements MessageDrivenBean,
                                            SampleMessageListener
{
    private transient MessageDrivenContext mdc = null;
    private static final Log log = LogFactory.getLog(SampleMDB.class);


    /**
     * Default constructor. Creates a bean.
     */

    public SampleMDB() {
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


    public void onMessage(SampleMessage message)
    {
      log.info("RECEIVED MSG:"+message.getMsg());

    }  // onMessage


    public void ejbRemove()
    {
    }

} // class
