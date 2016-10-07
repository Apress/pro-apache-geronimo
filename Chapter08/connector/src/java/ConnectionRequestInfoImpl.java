package samples.connectors.outbound;

import javax.resource.spi.*;
import java.lang.Object;


/**
 * This class implments the ConnectionRequestInfo interface, which enables a
 * resource adapter to pass its own request-specific data structure across the
 * connection request flow.
 */

public class ConnectionRequestInfoImpl implements ConnectionRequestInfo
{
    private String userName   = null;
    private String password   = null;

    /**
     * Constructor.
     *
     * @param userName  user name
     * @param password  user password
     * @param folderName  folder name
     * @param serverName  server name
     * @param protocol  protocol
     */

    public ConnectionRequestInfoImpl(String userName,String password)
    {
        this.userName   = userName;
        this.password   = password;
    }

    //
    //	Getter methods
    //

    /**
     * Returns the user name value.
     *
     * @return   String containing the user name
     */

    public String getUserName()
    {
        return userName;
    }

    /**
     * Returns the password value.
     *
     * @return   String containing the password
     */

    public String getPassword()
    {
        return password;
    }


    public boolean equals(Object obj)
    {
        boolean equal = false;

        if ((obj != null)&&(obj instanceof ConnectionRequestInfoImpl))
		{

         	ConnectionRequestInfoImpl other = (ConnectionRequestInfoImpl) obj;

            equal = (this.userName).equals(other.userName) &&
                        (this.password).equals(other.password);
        }
        return equal;

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
}
