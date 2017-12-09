/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package mdr.sam.adapter.channel;


public class ClientInfo {
	private String listenerKey;
	private String serviceDelegate;
	private boolean isSynchronousMode;
	private ChannelName channelName;
	private String channelHeader;
	private String schema;

	public ClientInfo(String key, String serviceName, boolean isSyncMode, ChannelName channel, String header,
			String schema) {
		this.listenerKey = key;
		this.serviceDelegate = serviceName;
		this.isSynchronousMode = isSyncMode;
		this.channelName = channel;
		this.channelHeader = header;
		this.schema = schema;
	}

	public String getListenerKey() {
		return this.listenerKey;
	}

	public String getServiceDelegate() {
		return this.serviceDelegate;
	}

	public boolean isSynchronousMode() {
		return this.isSynchronousMode;
	}

	public ChannelName getChannelName() {
		return this.channelName;
	}

	public String getChannelHeader() {
		return this.channelHeader;
	}

	public String getSchema() {
		return this.schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
}