/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdr.sam.adapter.service;

import com.wm.adk.cci.interaction.WmAdapterService;
import com.wm.adk.cci.record.WmRecord;
import com.wm.adk.cci.record.WmRecordFactory;
import com.wm.adk.connection.WmConnectionSpec;
import com.wm.adk.connection.WmManagedConnection;
import com.wm.adk.metadata.WmTemplateDescriptor;
import com.wm.data.IData;
import com.wm.data.ValuesEmulator;
import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.Set;
import javax.resource.ResourceException;
import mdr.sam.adapter.channel.BaseChannel;
import mdr.sam.adapter.channel.TCPChannel;
import mdr.sam.adapter.client.MdrSAMAdapter;
import mdr.sam.adapter.client.connection.MdrSAMConnection;
import mdr.sam.adapter.client.connection.MdrSAMConnectionFactory;
import mdr.sam.adapter.client.connection.MdrSAMConnectionSpec;
import mdr.sam.adapter.log.Util;

/**
 *
 * @author lt17-zakaria
 */
public class Send extends WmAdapterService {

    protected String[] inputParameterNames;
    protected String[] inputFieldNames;
    protected String[] inputFieldTypes;
    protected String[] outputParameterNames;
    protected String[] outputFieldNames;
    protected String[] outputFieldTypes;

    @Override
    public WmConnectionSpec getConnectionSpec(IData pipeline) throws ResourceException {
        Object connetionId = ValuesEmulator.get(pipeline, "$connectionId");
        if (connetionId == null) {
            return null;
        }
        return new MdrSAMConnectionSpec(connetionId.toString(), "_Default_Connection_Name");
    }

    public String[] getInputFieldNames() {
        return this.inputFieldNames;
    }

    public void setInputFieldNames(String[] inputFieldNames) {
        this.inputFieldNames = inputFieldNames;
    }

    public String[] getInputParameterNames() {
        return this.inputParameterNames;
    }

    public void setInputParameterNames(String[] inputParameterNames) {
        this.inputParameterNames = inputParameterNames;
    }

    public String[] getInputFieldTypes() {
        return this.inputFieldTypes;
    }

    public void setInputFieldTypes(String[] inputFieldTypes) {
        this.inputFieldTypes = inputFieldTypes;
    }

    public String[] getOutputParameterNames() {
        return this.outputParameterNames;
    }

    public void setOutputParameterNames(String[] outputParameterNames) {
        this.outputParameterNames = outputParameterNames;
    }

    public String[] getOutputFieldNames() {
        return this.outputFieldNames;
    }

    public void setOutputFieldNames(String[] outputFieldNames) {
        this.outputFieldNames = outputFieldNames;
    }

    public String[] getOutputFieldTypes() {
        return this.outputFieldTypes;
    }

    public void setOutputFieldTypes(String[] outputFieldTypes) {
        this.outputFieldTypes = outputFieldTypes;
    }

    @Override
    public WmRecord execute(WmManagedConnection wmc, WmRecord wr) throws ResourceException {
        WmRecord output = WmRecordFactory.getFactory().createWmRecord("output");
        Object requestString = ValuesEmulator.get(wr, "requestString");

        byte[] msgByte = null;
        try {
            if (requestString instanceof byte[]) {
                msgByte = (byte[]) (byte[]) requestString;
            } else if (requestString instanceof String) {
                msgByte = ((String) requestString).getBytes();
            }
        } catch (Exception ex) {
        }

        MdrSAMConnection conn = (MdrSAMConnection) wmc;

        conn.getServerOut();
        synchronized (conn) {
            SocketChannel socketChannel = conn.getClientChannel();
            TCPChannel isoChannel = new BaseChannel();
            int ackTimeout = getAckTimeOut(conn);
            try {
                isoChannel.setSocketChannel(socketChannel);

                isoChannel.send(msgByte);
                Util.logDebug4(310, new String[]{requestString.toString(), getSamServer(conn)});
                if (ackTimeout >= 0) {
                    readResponse(conn, output, isoChannel, socketChannel, ackTimeout);
                }
            } catch (IOException ie) {
                Util.logDebug4(101, ie.getMessage());
            } catch (Exception e) {
                Util.logDebug4(101, e.getMessage());
            } finally {
                //closeIfTransientConnection(conn, socketChannel);
            }
        }
        return output;
    }

    private void readResponse(MdrSAMConnection conn, WmRecord output, TCPChannel isoChannel, SocketChannel socketChannel, int ackTimeout)
            throws Exception {
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, 1);
        selector.select(ackTimeout);
        Set selectedKeys = selector.selectedKeys();

        if (selectedKeys.isEmpty()) {
            return;
        }
        byte[] receivedMessage = isoChannel.receive();
        String responseString = new String(receivedMessage);
        Util.logDebug4(311, new String[]{responseString, getSamServer(conn)});
        output.put("responseString", responseString);
    }

    @Override
    public void fillWmTemplateDescriptor(WmTemplateDescriptor d, Locale l) throws ResourceException {
        d.createGroup("SEND", new String[]{"inputFieldNames", "inputFieldTypes", "inputParameterNames",
            "outputFieldNames", "outputFieldTypes", "outputParameterNames"});

        d.createFieldMap(new String[]{"inputFieldNames", "inputFieldTypes", "inputParameterNames"}, false);

        d.createTuple(new String[]{"inputFieldNames", "inputFieldTypes"});

        d.setResourceDomain("inputFieldNames", "inputSend", null);

        d.setResourceDomain("inputFieldTypes", "inputSendType", null);

        d.createFieldMap(new String[]{"outputFieldNames", "outputFieldTypes", "outputParameterNames"}, false);

        d.createTuple(new String[]{"outputFieldNames", "outputFieldTypes"});

        d.setResourceDomain("outputFieldNames", "outputSend", null);

        d.setResourceDomain("outputFieldTypes", "outputSendType", null);

        d.setResourceDomain("inputParameterNames", "inputFieldNames",
                new String[]{"inputFieldNames", "inputFieldTypes"});

        d.setResourceDomain("outputParameterNames", "outputFieldNames",
                new String[]{"outputFieldNames", "outputFieldTypes"});

        d.setDescriptions(MdrSAMAdapter.getInstance().getAdapterResourceBundleManager(), l);
    }

    private int getAckTimeOut(MdrSAMConnection conn) {
        return ((MdrSAMConnectionFactory) conn.getFactory()).getAckTimeout();
    }

    private String getSamServer(MdrSAMConnection conn) {
        return ((MdrSAMConnectionFactory) conn.getFactory()).getServerHostName();
    }

}
