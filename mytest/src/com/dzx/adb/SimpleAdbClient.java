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

// A very simple and limited client for the Android Debugger (ADB).
// This client communicates with the ADB via TCP and can be used to execute shell commands.
public class SimpleAdbClient {

    private static int abdProtocolVersion = 0x01000000;

    // Executes a shell command and returns it's output.
    public static String shell(String hostName, int port, String shellCommand) throws IOException {
        final int maxMessageSize = 0x4000;
        final int maxPayloadSize = maxMessageSize - AdbMessage.headerLen;
        final int localId = 1;
        AdbConnection conn = null;
        try {
            AdbMessage msg;
            conn = new AdbConnection();
            conn.open(hostName, port, maxMessageSize);
            conn.sendMessage(Command.connect, abdProtocolVersion, maxPayloadSize, "host::\u0000");
            msg = conn.receiveMessage();
            verifyCommand(msg, Command.connect);
            conn.sendMessage(Command.open, localId, 0, "shell:" + shellCommand + "\u0000");
            msg = conn.receiveMessage();
            verifyCommand(msg, Command.ready);
            int remoteId = msg.arg0;
            StringBuilder out = new StringBuilder();
            while (true) {
                msg = conn.receiveMessage();
                if (msg.command == Command.close) {
                    break;
                }
                verifyCommand(msg, Command.write);
                out.append(msg.getPayloadAsCharSequence());
                conn.sendMessage(Command.ready, localId, remoteId);
            }
            conn.sendMessage(Command.close, localId, remoteId);
            conn.close();
            return out.toString();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    private static void verifyCommand(AdbMessage msg, Command expectedCommand) throws IOException {
        if (msg.command != expectedCommand) {
            throw new IOException("Command \"" + msg.command + "\" received when \"" + expectedCommand + "\" was exptected.");
        }
    }

}
