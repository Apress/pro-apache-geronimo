
package samples.connectors.inbound;

import javax.resource.*;
import javax.resource.spi.*;



public class ActivationSpecImpl implements javax.resource.spi.ActivationSpec,
                                           java.io.Serializable
{
    private ResourceAdapter resourceAdapter = null;


    private String msg= new String("SAMPLE MESSAGE FROM CONNECTOR");
    private String interval=new String("500");


    public ActivationSpecImpl() { }

    public String getMsg()
    {
        return this.msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getInterval()
    {
        return this.interval;
    }

    public void setInterval(String interval)
    {
        this.interval = interval;
    }


    public void validate()
	throws InvalidPropertyException
    { }


    public void setResourceAdapter(ResourceAdapter ra)
        throws ResourceException
    {
        this.resourceAdapter = ra;
    }

    public ResourceAdapter getResourceAdapter()
    {
        return resourceAdapter;
    }

}
