
package samples.connectors.outbound;

import javax.resource.spi.*;
import javax.resource.*;
import java.io.Serializable;


public class ConnectionManagerImpl implements ConnectionManager, Serializable
{

    public ConnectionManagerImpl()
    {
    }

    public Object allocateConnection(ManagedConnectionFactory mcf,
             ConnectionRequestInfo conReqInfo) throws ResourceException
    {
        ManagedConnection mc = mcf.createManagedConnection(null, conReqInfo);
        return mc.getConnection(null, conReqInfo);
    }
}
