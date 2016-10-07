package samples.connectors.outbound;

import javax.resource.cci.*;
import javax.resource.ResourceException;
import javax.resource.NotSupportedException;
import javax.resource.spi.ConnectionEvent;
import java.util.*;
import java.util.logging.*;
import javax.mail.*;


public class SampleConnectionImpl implements SampleConnection
{

    private ManagedConnectionImpl mc;


    public SampleConnectionImpl(ManagedConnectionImpl mc)
    {
        this.mc = mc;

    }



    public ManagedConnectionImpl getManagedConnection()
    {

        return mc;
    }


    public javax.resource.cci.LocalTransaction getLocalTransaction()
    throws ResourceException
    {
        throw new ResourceException("NO_TRANSACTION");
    }




    public void close()
    throws ResourceException
    {

        if (mc == null)
        return;
        mc.setConnection(null);
        mc.sendEvent(ConnectionEvent.CONNECTION_CLOSED, null, this);
        mc = null;
    }


    public void associateConnection(ManagedConnectionImpl newMc)
        throws ResourceException
    {
        checkIfValid();
        // associate handle with new managed connection
        newMc.setConnection(this);
        mc = newMc;
    }

    /**
     * Checks the validity of the physical connection to the EIS.
     */

    public void checkIfValid()
    throws ResourceException
    {

        if (mc == null)
        {
            throw new ResourceException("INVALID_CONNECTION");
        }
    }


    public void invalidate()
    {

        mc = null;
    }



    public String doSomething(String text)
      throws ResourceException
    {
        checkIfValid();
        return "[Sample Connection]"+text;
    }


}
