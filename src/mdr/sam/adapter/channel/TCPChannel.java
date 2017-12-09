/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package mdr.sam.adapter.channel;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;

public abstract class TCPChannel {
	protected SocketChannel channel;
	ClientInfo clientInfo;
	private boolean firstTime;

	//public abstract void send(ISOMessage paramISOMessage) throws Exception;

	public abstract void send(byte[] paramArrayOfByte) throws Exception;

	public abstract byte[] receive() throws Exception;

	public abstract TCPChannel newInstance();

	public void setSocketChannel(SocketChannel channel) {
		this.channel = channel;
	}

	public SocketChannel getSocketChannel() {
		return this.channel;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public abstract boolean hasHeader();

	protected void checkIsFirstTime() throws ExecutionException {
		//if (this.firstTime)
			//throw new ExecutionException(new ClientClosedExcetion("Connection is closed"));
	}

	public void setFirstTime(boolean val) {
		this.firstTime = val;
	}
}