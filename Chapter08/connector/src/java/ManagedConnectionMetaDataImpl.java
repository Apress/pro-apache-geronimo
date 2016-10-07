package samples.connectors.outbound;


import javax.resource.spi.*;
import javax.resource.ResourceException;
import javax.resource.spi.IllegalStateException;
import java.util.*;



public class ManagedConnectionMetaDataImpl implements ManagedConnectionMetaData
{
    private ManagedConnectionImpl mc;

    public ManagedConnectionMetaDataImpl(ManagedConnectionImpl mc)
    {

        this.mc = mc;
    }



    public String getEISProductName()
  throws ResourceException
    {
        String productName = null;

        // ToDo: Add service specific code here

        return  productName;
    }


    public String getEISProductVersion()
  throws ResourceException
    {
         String productVersion = null;
         // ToDo: Add service specific code here

         return  productVersion;
    }


    public int getMaxConnections()
  throws ResourceException
    {
        int  maxConnections = 0;

        // ToDo: Add service specific code here

        return  maxConnections;
    }


    public String getUserName()
  throws ResourceException
    {
        if (mc.isDestroyed())
        {
            throw new IllegalStateException("DESTROYED_CONNECTION");
        }
        return "sampleUser";
    }
}
