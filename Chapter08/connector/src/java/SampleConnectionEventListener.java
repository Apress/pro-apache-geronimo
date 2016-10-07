
package samples.connectors.outbound;

import javax.resource.spi.*;
import java.util.*;



public class SampleConnectionEventListener
{
    private Vector listeners;
    private ManagedConnection mcon;



    public SampleConnectionEventListener(ManagedConnection mcon)
    {
        listeners = new Vector();
        this.mcon = mcon;
    }



    public void sendEvent(int eventType, Exception ex,
                          Object connectionHandle)
    {
        Vector list = (Vector) listeners.clone();
        ConnectionEvent ce = null;
        if (ex == null) {
            ce = new ConnectionEvent(mcon, eventType);
        } else {
            ce = new ConnectionEvent(mcon, eventType, ex);
        }
        if (connectionHandle != null) {
            ce.setConnectionHandle(connectionHandle);
        }

        for (int i = 0; i < list.size(); i++)
        {
            ConnectionEventListener l =
                (ConnectionEventListener) list.elementAt(i);

            switch (eventType)
            {
                case ConnectionEvent.CONNECTION_CLOSED:
                    l.connectionClosed(ce);
                    break;
                case ConnectionEvent.LOCAL_TRANSACTION_STARTED:
                    l.localTransactionStarted(ce);
                    break;
                case ConnectionEvent.LOCAL_TRANSACTION_COMMITTED:
                    l.localTransactionCommitted(ce);
                    break;
                case ConnectionEvent.LOCAL_TRANSACTION_ROLLEDBACK:
                    l.localTransactionRolledback(ce);
                    break;
                case ConnectionEvent.CONNECTION_ERROR_OCCURRED:
                    l.connectionErrorOccurred(ce);
                    break;
                default:
                    throw new IllegalArgumentException("ILLEGAL_EVENT_TYPE" + eventType);
            }
        }
    }


    public void addConnectorListener(ConnectionEventListener listener)
    {
        listeners.addElement(listener);
    }


    public void removeConnectorListener(ConnectionEventListener listener)
    {
        listeners.removeElement(listener);
    }
}
