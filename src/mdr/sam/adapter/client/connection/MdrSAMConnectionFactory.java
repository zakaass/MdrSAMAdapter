/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a>
 * plugin, Copyright (c) 2017 Chen Chao. *
 */
package mdr.sam.adapter.client.connection;

import com.wm.adk.connection.WmManagedConnection;
import com.wm.adk.connection.WmManagedConnectionFactory;
import com.wm.adk.error.AdapterException;
import com.wm.adk.info.ResourceAdapterMetadataInfo;
import com.wm.adk.metadata.WmDescriptor;
import java.util.Locale;
import javax.resource.ResourceException;
import javax.resource.cci.ConnectionSpec;
import javax.resource.spi.ConnectionRequestInfo;
import javax.security.auth.Subject;
import mdr.sam.adapter.client.MdrSAMAdapter;
import mdr.sam.adapter.client.MdrSAMConstants;

public class MdrSAMConnectionFactory extends WmManagedConnectionFactory implements MdrSAMConstants {

    private static final long serialVersionUID = -5565576419299845862L;
    private String serverHostName;
    private int serverPortNumber;
    private int timeout;
    private int ackTimeout;
    private String connectionId;
    private String connectionName;

    public MdrSAMConnectionFactory() {
        this.serverHostName = "";
        this.timeout = 20000;
        this.ackTimeout = 30000;
        this.connectionId="_Default_Connection_ID";
        this.connectionName="_Default_Connection_Name";
    }

    public WmManagedConnection createManagedConnectionObject(Subject arg0, ConnectionRequestInfo arg1)
            throws ResourceException {
        return new MdrSAMConnection();
    }

    public void fillResourceAdapterMetadataInfo(ResourceAdapterMetadataInfo info, Locale locale)
            throws AdapterException {
        MdrSAMAdapter.getInstance().fillResourceAdapterMetadataInfo(info, locale);
        info.addServiceTemplate("mdr.sam.adapter.service.Send");
    }

    public void fillWmDescriptor(WmDescriptor d, Locale l) throws AdapterException {
        d.createGroup("SocketServerConnection", new String[]{
            /**
             * "connectionType", "channel", "header", *
             */
            "serverHostName",
            "serverPortNumber",
            "timeout",
            "ackTimeout",
            "connectionId",
            "connectionName"
        });

        d.setMinStringLength("serverHostName", 1);

        d.setRequired("serverPortNumber");
        d.setRequired("serverHostName");

        d.setHidden("connectionId");
        d.setHidden("connectionName");
        d.setDescriptions(MdrSAMAdapter.getInstance().getAdapterResourceBundleManager(), l);
    }

    public String getServerHostName() {
        return this.serverHostName;
    }

    public void setServerHostName(String serverHostName) {
        this.serverHostName = serverHostName;
    }

    public int getServerPortNumber() {
        return this.serverPortNumber;
    }

    public void setServerPortNumber(int serverPortNumber) {
        this.serverPortNumber = serverPortNumber;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public int getAckTimeout() {
        return this.ackTimeout;
    }

    public void setAckTimeout(int ackTimeout) {
        this.ackTimeout = ackTimeout;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getConnectionId() {
        return connectionId;
    }    

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }
    
    

    @Override
    public ConnectionRequestInfo getConnectionRequestInfo(ConnectionSpec spec) {
        MdrSAMConnectionRequestInfo partitionDef=null;
        if(spec != null && spec instanceof MdrSAMConnectionSpec){
            synchronized(this){
                try{
                    partitionDef=new MdrSAMConnectionRequestInfo(spec);
                }catch(Exception e){
                    
                }
            }
        }
        return partitionDef; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    

}
