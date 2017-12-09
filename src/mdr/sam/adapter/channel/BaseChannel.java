/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdr.sam.adapter.channel;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 * @author lt17-zakaria
 */
public class BaseChannel extends TCPChannel {


    protected void sendMessage(byte[] msg) throws Exception {
        if (msg != null) {
            ByteBuffer buffer = getByteBuffer(msg.length);
            buffer.put(msg);
            buffer.flip();
            while (buffer.hasRemaining()) {
                getSocketChannel().write(buffer);
            }
        }
    }

    protected ByteBuffer getByteBuffer(int length) throws Exception {
        if (length >= 0) {
            return ByteBuffer.allocate(length);
        }
        throw new Exception("message seems strange");
    }

    public void send(byte[] b) throws Exception {
        sendMessage(b);
    }

    @Override
    public byte[] receive() throws Exception {
        ByteBuffer buffer = getByteBuffer(1024);
        int numRead = getSocketChannel().read(buffer);
        byte[] b = null;
        if ((numRead != -1) && (numRead != 0)) {
            byte[] header = new byte[numRead];
            System.arraycopy(buffer.array(), 0, header, 0, numRead);
            int l = 0;
            for (byte i : header) {
                if (i <= 0) {
                    break;
                }
                l++;
            }
            b = Arrays.copyOf(header, l);

        }
        

        return b;
    }

    @Override
    public TCPChannel newInstance() {
        return new BaseChannel();
    }

    @Override
    public boolean hasHeader() {
        return false;
    }
}
