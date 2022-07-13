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

import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

// A description of the protocol can be found in the Android source in system/core/adb/*.txt.
public class AdbMessage {

    @SuppressWarnings("serial")
    public static class MalformedMessageException extends ProtocolException {
        public MalformedMessageException(String msg) {
            super(msg);
        }
    }

    public static final int headerLen = 24;
    private static final Charset charset = StandardCharsets.UTF_8;

    public Command command;
    public int arg0;
    public int arg1;
    public ByteBuffer payload;            // may be null

    public enum Command {
        connect(0x4e584e43),
        open(0x4e45504f),
        ready(0x59414b4f),                                   // = Okay
        write(0x45545257),
        close(0x45534c43);
        private int code;
        private static HashMap<Integer, Command> codeMap;

        Command(int code) {
            this.code = code;
        }

        private static Command lookup(int code) {
            return codeMap.get(code);
        }

        static {
            codeMap = new HashMap<Integer, Command>(values().length);
            for (Command command : values()) {
                codeMap.put(command.code, command);
            }
        }
    }

    public AdbMessage() {
    }

    public AdbMessage(Command command, int arg0, int arg1, ByteBuffer payload) {
        this.command = command;
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.payload = payload;
    }

    public AdbMessage(Command command, int arg0, int arg1, String payload) {
        this(command, arg0, arg1, charset.encode(payload));
    }

    public AdbMessage(Command command, int arg0, int arg1) {
        this(command, arg0, arg1, (ByteBuffer) null);
    }

    public ByteBuffer pack() {
        int payloadLen = (payload == null) ? 0 : payload.remaining();
        ByteBuffer buf = ByteBuffer.allocate(headerLen + payloadLen);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        buf.putInt(command.code);
        buf.putInt(arg0);
        buf.putInt(arg1);
        buf.putInt(payloadLen);
        buf.putInt(computeChecksum(payload));
        buf.putInt(~command.code);
        if (payload != null) {
            buf.put(payload.array(), payload.position(), payload.remaining());
        }
        // We don't use ByteBuffer.put(ByteBuffer) here, because we don't want to change payload.position.
        buf.flip();
        return buf;
    }

    // If the payload length field has not yet been received, the minimum message length is returned.
    public static int estimateMessageLength(ByteBuffer buf) throws MalformedMessageException {
        if (buf == null || buf.remaining() < headerLen) {
            return headerLen;
        }
        MessageHeader hdr = unpackHeader(buf);
        return headerLen + hdr.payloadLen;
    }

    public static AdbMessage unpack(ByteBuffer buf) throws MalformedMessageException {
        MessageHeader hdr = unpackHeader(buf);
        if (buf.remaining() != headerLen + hdr.payloadLen) {
            throw new MalformedMessageException("Invalid message size.");
        }
        AdbMessage msg = new AdbMessage();
        msg.command = hdr.command;
        msg.arg0 = hdr.arg0;
        msg.arg1 = hdr.arg1;
        if (hdr.payloadLen > 0) {
            msg.payload = ByteBuffer.allocate(hdr.payloadLen);
            msg.payload.put(buf.array(), buf.position() + headerLen, hdr.payloadLen);
            msg.payload.flip();
            int checksum = computeChecksum(msg.payload);
            if (checksum != hdr.payloadChecksum) {
                throw new MalformedMessageException("Checksum error for payload data.");
            }
        }
        return msg;
    }

    private static class MessageHeader {
        Command command;
        int arg0;
        int arg1;
        int payloadLen;
        int payloadChecksum;
    }

    private static MessageHeader unpackHeader(ByteBuffer buf0) throws MalformedMessageException {
        ByteBuffer buf = buf0.duplicate();                      // we don't want to modify the original
        if (buf.remaining() < headerLen) {
            throw new MalformedMessageException("Message shorted than header length.");
        }
        buf.order(ByteOrder.LITTLE_ENDIAN);
        MessageHeader hdr = new MessageHeader();
        int commandCode = buf.getInt();
        hdr.arg0 = buf.getInt();
        hdr.arg1 = buf.getInt();
        hdr.payloadLen = buf.getInt();
        hdr.payloadChecksum = buf.getInt();
        int magic = buf.getInt();
        if (magic != ~commandCode) {
            throw new MalformedMessageException("Invalid message frame structure received (wrong magic field).");
        }
        hdr.command = Command.lookup(commandCode);
        if (hdr.command == null) {
            throw new MalformedMessageException("Unknown command code 0x" + Integer.toHexString(commandCode) + " received.");
        }
        if (hdr.payloadLen < 0) {
            throw new MalformedMessageException("Negative payload length.");
        }
        return hdr;
    }

    // The documentation in system/core/adb/protocol.txt states that the checksum is CRC32,
// but the program code in transport.cpp computes just a simple byte sum.
    private static int computeChecksum(ByteBuffer buf) {
        if (buf == null) {
            return 0;
        }
        int sum = 0;
        for (int p = buf.position(); p < buf.limit(); p++) {
            sum += buf.get(p) & 0xFF;
        }
        return sum;
    }

    public CharSequence getPayloadAsCharSequence() {
        if (payload == null) {
            return "";
        }
        return charset.decode(payload);
    }

}
