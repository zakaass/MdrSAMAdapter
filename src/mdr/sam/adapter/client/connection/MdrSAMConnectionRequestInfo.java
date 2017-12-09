/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdr.sam.adapter.client.connection;

import com.wm.adk.connection.WmConnectionRequestInfo;
import javax.resource.cci.ConnectionSpec;
import mdr.sam.adapter.log.Util;

/**
 *
 * @author lt17-zakaria
 */
public class MdrSAMConnectionRequestInfo extends WmConnectionRequestInfo {

    private String connectionId;
    private String connectionName;

    public MdrSAMConnectionRequestInfo(ConnectionSpec connectionSpec) {

        MdrSAMConnectionSpec connSpec = (MdrSAMConnectionSpec) connectionSpec;
        this.connectionId = connSpec.getConnectionId();
        this.connectionName = connSpec.getConnectionName();
    }

    public String getConnectionId() {
        return connectionId;
    }

    public String getConnectionName() {
        return connectionName;
    }

    @Override
    public String getLoggableName() {
        return this.connectionId; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return this.connectionId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        MdrSAMConnectionRequestInfo infoObj = (MdrSAMConnectionRequestInfo) obj;
        boolean isBoolean = true;
        if ((this.connectionId != null)) {
            isBoolean = (this.connectionId.equals(infoObj.getConnectionId()));
        }
        if (isBoolean) {
            Util.logDebug4(112, new String[]{connectionId});
        } else {
            Util.logDebug4(113, new String[]{connectionId});
        }
        return isBoolean;
    }

}
