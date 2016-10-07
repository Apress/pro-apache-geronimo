package samples.connectors.inbound;


import javax.resource.spi.*;
import javax.resource.spi.endpoint.*;
import javax.resource.spi.work.*;
import javax.resource.cci.*;
import javax.resource.*;



import java.util.*;
import java.util.logging.*;


public class EndpointThread  implements Work
{

    private boolean                 active = false;
  private MessageEndpointFactory endpointFactory;
  private String msg;
  private int i=1;

    private static int              QUANTUM = 500;

    /**
     * Constructor.
     */

    public EndpointThread(MessageEndpointFactory endpointFactory,
                      ActivationSpec spec)
    {
        this.active      = true;
        this.endpointFactory=endpointFactory;
        ActivationSpecImpl specImpl=(ActivationSpecImpl)spec;
        this.msg=specImpl.getMsg();
        try{
          this.QUANTUM=Integer.getInteger(specImpl.getInterval()).intValue();
        }catch(Exception e){
          // ignore
        }
    }



    public void release()
    {
      active = false;
    }

    /**
     * run
     */

    public void run()
    {

        while (active)
        {
            try
            {
                Thread.sleep(QUANTUM);
                generateAndSendMessage();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }


    }



  private void generateAndSendMessage(){
    try {
      SampleMessage sampleMessage=new SampleMessage((i++) +":"+msg);
      MessageEndpoint endpoint=endpointFactory.createEndpoint(null);
      if(null!=endpoint){
        ((SampleMessageListener)endpoint).onMessage(sampleMessage);
      }
    }catch(Exception e){
      // handle exception
    }
  }


}
