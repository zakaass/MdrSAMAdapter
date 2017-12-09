/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdr.sam.adapter.client.connection;

import com.wm.adk.connection.WmConnectionSpec;

/**
 *
 * @author lt17-zakaria
 */
public class MdrSAMConnectionSpec extends WmConnectionSpec {

    private String connectionId;
    private String connectionName;

    public MdrSAMConnectionSpec(String connectionNodeName) {
        super(connectionNodeName);
    }

    public MdrSAMConnectionSpec(String connectionId, String connectionName) {
        this.connectionId = connectionId;
        this.connectionName = connectionName;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

}
