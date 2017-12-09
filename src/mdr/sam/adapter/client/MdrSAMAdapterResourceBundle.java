package mdr.sam.adapter.client;

import java.util.ListResourceBundle;

public class MdrSAMAdapterResourceBundle extends ListResourceBundle implements MdrSAMConstants {

    static final Object[][] _contents = {
        {"MdrSAMAdapter.DISPLAYNAME", "SAM Adapter"},
        {"MdrSAMAdapter.DESCRIPTION", "SAM Server Adapter"},
        {"MdrSAMAdapter.VENDORNAME", "EMI. Appliction Development Group. Bank Mandiri"},
        {"mdr.sam.adapter.client.connection.MdrSAMConnectionFactory.DISPLAYNAME", "SAM Server Connection"},
        {"mdr.sam.adapter.client.connection.MdrSAMConnectionFactory.DESCRIPTION", "To connect to Mandiri SAM Server"},
        {"SocketServerConnection.GROUP", "Mandiri SAM Server Connection"},
        {"SocketServerConnection.DESCRIPTION", "Connection to SAM Server Bank Mandiri"},
        {"serverHostName.DISPLAYNAME", "SAM Server"}, {"serverHostName.DESCRIPTION", "SAM Server"},
        {"serverPortNumber.DISPLAYNAME", "SAM Port"}, {"serverPortNumber.DESCRIPTION", "SAM  Port"},
        {"timeout.DISPLAYNAME", "Connection Timeout (msec)"}, {"timeout.DESCRIPTION", "connection timeout (msec)"},
        {"otherParameter.DISPLAYNAME", "Other Parameters"},
        {"otherParameter.DESCRIPTION", "Other parameters specific to the socket."},
        //{"channel.DISPLAYNAME", "Channel"}, {"channel.DESCRIPTION", "Channel"},
        //{"header.DISPLAYNAME", "Channel Header (optional)"}, {"header.DESCRIPTION", "Channel Header"},
        //{"connectionType.DISPLAYNAME", "Connection Type"}, {"connectionType.DESCRIPTION", "Connection Type"},
        {"MdrSAMAdapter.LISTRESOURCES.HELPURL", "MdrSAMAdapter/doc/OnlineHelp/MdrSAMAdapter.html"},
        {"MdrSAMAdapter.ABOUT.HELPURL", "MdrSAMAdapter/doc/OnlineHelp/MdrSAMAdapterObout.html"},
        {"MdrSAMAdapter.LISTCONNECTIONTYPES.HELPURL", "MdrSAMAdapter/doc/OnlineHelp/MdrSAMAdapterLH.html"},
        {"ackTimeout.DISPLAYNAME", "Response Timeout (msec)"},
        {"ackTimeout.DESCRIPTION", "Response Timeout (msec). -1: no wait, 0: infinite wait, >0: millis to wait"},
        {"101", "Initializing SAM Connection: {0}"},
        {"102", "Using ackTimeout as zero, interpreted as waiting for infinite timeout for receiving acknowledgement."},
        {"103", "ACK read timed-out after: {0} ms"},
        {"104", "Connection could not be establised timed-out after: {0} ms"},
        {"105", "Connection Established with Remote Host: {0} on port: {1}"},
        {"106", "Connection Pool : {0}"},
        {"111", "Closing SAM Connection: {0}"},
        {"112", "Found Connection ID : {0}, Action -> Reused"},
        {"113", "Not Found Connection ID : {0}, Action -> Created New"},
        {"201", "SAM Client is attempting to write data of size {0} byte(s)"},
        {"202", "SAM Client has successfully completed writing data to channel."},
        {"203", "SAM Client has completed writing data of size {0} byte(s)"},
        {"301", "Finished reading. Read size: {0}"},
        {"302", "End of Stream encountered. Total number of bytes read: {0}"},
        {"303", "SAM Header found at Index: {0}"}, {"304", "ISO_8583 Footer found at Index: {0}"},
        {"305", "SAM Client is awaiting response"}, {"306", "ACK read time-out: {0}"},
        {"310", "Send Message ({0}) to {1}"}, 
        {"311", "Received Message ({0}) from {1}"},
        {"401", "Missing value for mandatory parameter: {0}"}, {"1001", "Resource Connection Exception: "}
    
    };

    protected Object[][] getContents() {
        return _contents;
    }
}
