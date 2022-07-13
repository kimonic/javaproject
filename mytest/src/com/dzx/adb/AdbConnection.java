// Copyright 2015 Christian d'Heureuse, Inventec Informatik AG, Zurich, Switzerland
// www.source-code.biz, www.inventec.ch/chdh
//
// This module is multi-licensed and may be used under the terms of any of the following licenses:
//
//  LGPL, GNU Lesser General Public License, V2.1 or later, http://www.gnu.org/licenses/lgpl.html
//  EPL, Eclipse Public License, V1.0 or later, http://www.eclipse.org/legal
//
// Please contact the author if you need another license.
// This module is provided "as is", without warranties of any kind.
//
// Home page: http://www.source-code.biz/snippets/java/SimpleAdbClient

package com.dzx.adb;


import com.dzx.adb.AdbMessage.Command;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class AdbConnection {

    private SocketChannel socketChannel;
    private ByteBuffer rxBuf;

    public void open(String hostName, int port, int maxMessageSize) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
        socketChannel = SocketChannel.open(socketAddress);
        socketChannel.configureBlocking(true);  // is default?
        rxBuf = ByteBuffer.allocate(maxMessageSize);
    }

    public void close() throws IOException {
        if (socketChannel != null) {
            socketChannel.close();
            socketChannel = null;
        }
    }

    // Blocks until the message is written to the channel.
    public void sendMessage(AdbMessage msg) throws IOException {
        ByteBuffer buf = msg.pack();
        socketChannel.write(buf);
        if (buf.remaining() != 0) {
            throw new IOException("Transmitter blocked.");
        }
    }

    public void sendMessage(Command command, int arg0, int arg1, String payload) throws IOException {
        sendMessage(new AdbMessage(command, arg0, arg1, payload));
    }

    public void sendMessage(Command command, int arg0, int arg1) throws IOException {
        sendMessage(new AdbMessage(command, arg0, arg1));
    }

    // Blocks until a message is received.
    public AdbMessage receiveMessage() throws IOException {
        rxBuf.clear();
        while (true) {
            rxBuf.flip();
            int msgLen = AdbMessage.estimateMessageLength(rxBuf);
            if (msgLen > rxBuf.capacity()) {
                throw new IOException("RX buffer overflow.");
            }
            rxBuf.position(rxBuf.limit());
            rxBuf.limit(msgLen);
            if (rxBuf.remaining() == 0) {
                break;
            }
            int trLen = socketChannel.read(rxBuf);
            if (trLen <= 0) {
                throw new IOException("End of Stream.");
            }
        }
        rxBuf.flip();
        AdbMessage msg = AdbMessage.unpack(rxBuf);
        return msg;
    }

}
