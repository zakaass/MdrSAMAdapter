/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a>
 * plugin, Copyright (c) 2017 Chen Chao. *
 */
package mdr.sam.adapter.client.connection;

import com.wm.adk.connection.WmManagedConnection;
import com.wm.adk.error.AdapterException;
import com.wm.adk.metadata.ResourceDomainValues;
import com.wm.adk.metadata.WmAdapterAccess;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.security.auth.Subject;
import mdr.sam.adapter.client.MdrSAMAdapter;
import mdr.sam.adapter.log.Util;
import mdr.sam.adapter.client.MdrSAMConstants;

public class MdrSAMConnection extends WmManagedConnection implements MdrSAMConstants {

    private SocketChannel clientChannel = null;
    protected DataOutputStream serverOut;

    @Override
    public void cleanup() throws ResourceException {
        Util.logDebug4(106, "cleanup");
    }

    @Override
    protected void destroyConnection() throws javax.resource.ResourceException {
        try {
            this.clientChannel.close();
            Util.logDebug4(106, new String[]{"Destroyed"});
        } catch (IOException ex) {
            throw new ResourceException(ex.getMessage());
        }

    }

    @Override
    public void registerResourceDomain(WmAdapterAccess access) throws AdapterException {
        
        access.addResourceDomain(
                new ResourceDomainValues[]{
                    new ResourceDomainValues("inputSend", new String[]{"requestString"}), new ResourceDomainValues("inputSendType", new String[]{"java.lang.String"})
                });

        access.addResourceDomain(
                new ResourceDomainValues[]{new ResourceDomainValues("outputSend", new String[]{"responseString"}),
                    new ResourceDomainValues("outputSendType", new String[]{"java.lang.String"})});
    }

    @Override
    public ResourceDomainValues[] adapterResourceDomainLookup(String string, String string1, String[][] strings) throws AdapterException {
        return null;
    }

    @Override
    public Boolean adapterCheckValue(String string, String string1, String[][] strings, String string2) throws AdapterException {
        return Boolean.TRUE;
    }

    @Override
    protected boolean initializationRequired() {
        return true;
    }

    @Override
    protected boolean cleanupRequiredAfterConnectionUse() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean setupRequiredBeforeConnectionUse(Subject subject, ConnectionRequestInfo cxRequestInfo) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    @Override
    protected void initializeConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException, InterruptedException {
        MdrSAMConnectionFactory connFactory = (MdrSAMConnectionFactory) this._factory;
        int timeout = connFactory.getTimeout();
        try {
            this.clientChannel = SocketChannel.open();
            this.clientChannel.configureBlocking(true);

            this.clientChannel.socket().setSoTimeout(timeout);
            String host = connFactory.getServerHostName();
            int port = connFactory.getServerPortNumber();

            boolean established = this.clientChannel.connect(new InetSocketAddress(host, port));

            if (established) {
                Util.logDebug(105, new String[]{host, String.valueOf(port)});
                this.serverOut = new DataOutputStream(
                        new BufferedOutputStream(this.clientChannel.socket().getOutputStream(), 2048));
            }
        } catch (SocketTimeoutException ste) {
            throw MdrSAMAdapter.getInstance().createAdapterException(101, ste);
        } catch (IOException ioe) {
            throw MdrSAMAdapter.getInstance().createAdapterException(101, ioe);
        }
    }

    public DataOutputStream getServerOut() {
        return this.serverOut;
    }

    public void setServerOut(DataOutputStream serverOut) {
        this.serverOut = serverOut;
    }

    public SocketChannel getClientChannel() {
        return this.clientChannel;
    }
    
    

    public void setClientChannel(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

}
