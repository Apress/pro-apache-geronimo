package samples.connectors;

import javax.resource.NotSupportedException;
import javax.resource.spi.*;
import javax.resource.spi.endpoint.*;
import javax.resource.spi.work.*;
import javax.resource.cci.*;
import javax.resource.*;

import javax.naming.*;
import java.lang.reflect.*;
import java.util.*;

import samples.connectors.outbound.*;
import samples.connectors.inbound.*;

import org.apache.commons.logging.*;



public class ResourceAdapterImpl
    implements ResourceAdapter, java.io.Serializable
{
    private static final Log log = LogFactory.getLog(ResourceAdapterImpl.class);

    protected transient BootstrapContext    bootCtx;
    protected transient WorkManager         workManager;
    public transient    Context             jndiContext  = null;
    private             Work                worker;

    public Method onMessage = null;

    /**
     *  Constructor.
     */

    public ResourceAdapterImpl () {
    log.info("#### ResourceAdapterImpl created ####");
    }



    public void start(BootstrapContext ctx)
        throws ResourceAdapterInternalException
    {
        log.info("#### ResourceAdapterImpl Start ####");

        this.bootCtx = ctx;

        try
        {

            // Get Work Manager
            this.workManager = ctx.getWorkManager();
        } catch(Exception ex) {

            ex.printStackTrace();
            throw new ResourceAdapterInternalException(
                "resourceadapterimpl.noservice");
        }

        log.info("#### ResourceAdapterImpl Started ####");



    }


    public void stop()
    {
      log.info("#### ResourceAdapterImpl stop ####");
      if(worker!=null){
        worker.release();
      }
      log.info("#### ResourceAdapterImpl stopped ####");
    }



    public void endpointActivation (MessageEndpointFactory endpointFactory,
                      ActivationSpec spec)
        throws NotSupportedException
    {
      log.info("#### ResourceAdapterImpl endpoint Activation ####");
      try {
        worker = new EndpointThread(endpointFactory,spec);
        workManager.scheduleWork(worker);
      }catch(Exception e){
        log.error("#### Error scheduling work ####"+e);
      }

    }


    public void endpointDeactivation (MessageEndpointFactory endpointFactory,
                        ActivationSpec spec)
    {
      log.info("#### ResourceAdapterImpl endpoint deactivation ####");
      worker.release();
      worker=null;

    }


    public javax.transaction.xa.XAResource[] getXAResources(ActivationSpec[] specs)
                                                 throws ResourceException
    {
        log.info("#### get XA Resources ####");

        return null;
    }


}
