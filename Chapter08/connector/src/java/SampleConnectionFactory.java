
package samples.connectors.outbound;

import javax.resource.*;



public interface SampleConnectionFactory
{

    public SampleConnection createConnection()
        throws ResourceException;

    public SampleConnection createConnection(String userName, String password)
        throws ResourceException;
}
