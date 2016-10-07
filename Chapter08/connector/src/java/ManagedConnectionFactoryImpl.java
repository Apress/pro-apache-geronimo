
package samples.connectors.outbound;

import javax.resource.spi.*;
import javax.resource.ResourceException;
import javax.security.auth.Subject;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;
import java.io.*;


public class ManagedConnectionFactoryImpl implements
    ManagedConnectionFactory, Serializable
{
    private transient PrintWriter out;

    public ManagedConnectionFactoryImpl()
    {

    }

    public Object createConnectionFactory(ConnectionManager conManager)
  throws ResourceException
    {
      SampleConnectionFactoryImpl cf = null;
      try
      {
            cf = new SampleConnectionFactoryImpl(this, conManager);
      } catch(Exception e) {
            throw new ResourceException(e.getMessage());
      }
      return cf;
    }


    public Object createConnectionFactory()
  throws ResourceException
    {
        return new SampleConnectionFactoryImpl(this, null);
    }



    public ManagedConnection createManagedConnection(Subject subject,
        ConnectionRequestInfo cxRequestInfo)
  throws ResourceException
    {
        ManagedConnectionImpl mc = null;

        mc = new ManagedConnectionImpl(this, subject, cxRequestInfo);

        return mc;
    }


    public ManagedConnection matchManagedConnections(Set connectionSet,
            Subject subject,
            ConnectionRequestInfo cxRequestInfo)
  throws ResourceException
    {
        return null;
    }




    public void setLogWriter(PrintWriter out)
  throws ResourceException
    {
        this.out = out;
    }


    public PrintWriter getLogWriter()
  throws ResourceException
    {
        return this.out;
    }


    public int hashCode()
    {

       int hashcode = new String("").hashCode();

       if (userName != null)
          hashcode += userName.hashCode();

       if (password != null)
          hashcode += password.hashCode();

       return hashcode;
    }


    public boolean equals(Object obj)
    {
        if (obj != null)
        {
            if (obj instanceof ManagedConnectionFactoryImpl)
            {
                ManagedConnectionFactoryImpl other = (ManagedConnectionFactoryImpl)obj;

                if ( !userName.equals(other.getUserName()) )
                    return false;
                if ( !password.equals(other.getPassword()) )
                    return false;

                return true;
            }
        }
        return false;
    }


    // userName property value
    private String userName = new String("unknownUserName");

    // password property value
    private String password = new String("unknownPassword");


    public String getUserName()
    {
        return this.userName;
    }


    public void setUserName(String userName)
    {
        this.userName = userName;
    }


    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
