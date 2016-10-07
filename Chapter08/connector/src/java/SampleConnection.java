
package samples.connectors.outbound;

import javax.resource.*;


public interface SampleConnection
{

    public String doSomething(String text)
        throws ResourceException;

    public void close() 
	throws ResourceException;
}
