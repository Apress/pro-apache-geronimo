package samples.connectors.outbound;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.resource.Referenceable;
import javax.resource.cci.*;
import javax.resource.spi.*;
import javax.resource.ResourceException;
import javax.naming.Reference;



public class SampleConnectionFactoryImpl implements
    SampleConnectionFactory, Serializable, Referenceable
{

    private ManagedConnectionFactory mcf;
    private ConnectionManager cm;

    // Local variables

    private Reference reference;


    public SampleConnectionFactoryImpl() { }


    public SampleConnectionFactoryImpl(ManagedConnectionFactory mcf,
                                         ConnectionManager cm)
    {

        this.mcf = mcf;
        if (cm == null)
        {
            this.cm = new ConnectionManagerImpl();
        } else {
            this.cm = cm;
        }
    }


    public SampleConnection createConnection()
  throws ResourceException
    {
        SampleConnection con = null;

        con = (SampleConnection) cm.allocateConnection(mcf, null);

        return con;
    }


    public SampleConnection createConnection(String userName, String password)
  throws ResourceException
    {
        SampleConnection con = null;

        ConnectionRequestInfoImpl info =
            new ConnectionRequestInfoImpl(userName, password);

        con = (SampleConnection)cm.allocateConnection(mcf, info);

      return con;
    }

    /**
     * This method is declared in the javax.resource.Referenceable interface
     * and should be implemented in order to support JNDI registration.
     *
     * @param reference  a Reference instance
     */

    public void setReference(Reference reference)
    {
        this.reference = reference;
    }


    /**
     * This method is declared in the javax.naming.Referenceable interface
     * and should be implemented in order to support JNDI registration.
     *
     * @return  a Reference instance
     */

    public Reference getReference()
    {

        return reference;
    }
}
