package samples.connectors.outbound;
import javax.mail.*;
import javax.mail.internet.*;

import javax.resource.spi.*;
import javax.resource.ResourceException;
import javax.transaction.xa.XAResource;
import javax.security.auth.Subject;
import java.io.*;
import java.util.*;
import javax.resource.cci.*;
import javax.resource.spi.security.PasswordCredential;
import javax.resource.spi.SecurityException;
import javax.resource.spi.IllegalStateException;
import javax.resource.NotSupportedException;
import java.net.*;


public class ManagedConnectionImpl implements ManagedConnection
{
    private ManagedConnectionFactoryImpl     mcf;
    private SampleConnectionEventListener  eventListener;

  private SampleConnection connection;
  private PrintWriter logWriter;
    private boolean                  destroyed;

    private PasswordCredential passCred = null;

    ManagedConnectionImpl(ManagedConnectionFactoryImpl mcf,
        Subject subject,
        ConnectionRequestInfo cxRequestInfo)
        throws ResourceException
    {
        this.mcf = mcf;

       eventListener = new SampleConnectionEventListener(this);
    }


    public Object getConnection(Subject subject,
                                ConnectionRequestInfo cxRequestInfo)
  throws ResourceException
    {

        checkIfDestroyed();


        connection = new SampleConnectionImpl(this);

        return connection;
    }



    public void destroy()
  throws ResourceException
    {
        if (destroyed)
        return;


        destroyed = true;

      ((SampleConnectionImpl)connection).invalidate();
      connection=null;

    }


    public void cleanup()
  throws ResourceException
    {
        checkIfDestroyed();

        ((SampleConnectionImpl)connection).invalidate();
        connection=null;
    }




    /**
     * Used by the container to change the association of an application-level
     * connection handle with a ManagedConnection instance. The container
     * should find the right ManagedConnection instance and call the
     * associateConnection method.
     *
     * @param connection  application-level connection handle
     *
     * @exception ResourceException  if the attempt to change the association
     *                               fails
     */

    public void associateConnection(Object connection)
  throws ResourceException
    {
        checkIfDestroyed();

        if (connection instanceof SampleConnection)
        {
            SampleConnectionImpl con =
                (SampleConnectionImpl) connection;
            con.associateConnection(this);
        } else {
            throw new IllegalStateException("INVALID_CONNECTION");
        }
    }

  public void setConnection(SampleConnection con){
    this.connection=con;
  }


    public void addConnectionEventListener(ConnectionEventListener listener)
    {
        eventListener.addConnectorListener(listener);
    }


    public void removeConnectionEventListener(ConnectionEventListener listener)
    {
        eventListener.removeConnectorListener(listener);
    }



    public XAResource getXAResource()
  throws ResourceException
    {
        throw new NotSupportedException("NO_XATRANSACTION");
    }



    public javax.resource.spi.LocalTransaction getLocalTransaction()
  throws ResourceException
    {
        throw new NotSupportedException("NO_TRANSACTION");
    }



    public ManagedConnectionMetaData getMetaData()
  throws ResourceException
    {
        checkIfDestroyed();
        return new ManagedConnectionMetaDataImpl(this);
    }


    public void setLogWriter(PrintWriter out)
  throws ResourceException
    {
        this.logWriter = out;
    }


    /**
     * Gets the log writer for this ManagedConnection instance.
     *
     * @return   the character output stream associated with this
     *           ManagedConnection instance
     *
     * @exception ResourceException  if the method fails
     */

    public PrintWriter getLogWriter()
  throws ResourceException
    {
        return logWriter;
    }



    /**
     * Check validation of the physical connection.
     *
     * @exception ResourceException  if the connection has been destroyed
     */

    private void checkIfDestroyed()
  throws ResourceException
    {
        if (destroyed) {
            throw new IllegalStateException("DESTROYED_CONNECTION");
        }
    }


    boolean isDestroyed()
    {
        return destroyed;
    }


    public ManagedConnectionFactoryImpl getManagedConnectionFactory()
    {
        return this.mcf;
    }


    public void sendEvent(int eventType, Exception ex)
    {
        eventListener.sendEvent(eventType, ex, null);
    }


    public void sendEvent(int eventType, Exception ex, Object connectionHandle)
    {
        eventListener.sendEvent(eventType, ex, connectionHandle);
    }


}
