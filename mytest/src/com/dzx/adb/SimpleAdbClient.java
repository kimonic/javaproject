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
import com.dzx.util.LUtils;

import java.io.IOException;

// A very simple and limited client for the Android Debugger (ADB).
// This client communicates with the ADB via TCP and can be used to execute shell commands.
public class SimpleAdbClient {

    private static int abdProtocolVersion = 0x01000000;
    private static String rsaPass ="QAAAAKlhARZnLW93I9UDd6RlYhluFm+99NzjrpkS5Jqz9PYQ0uOK7xJHohHVnpj13C9RXdGvPSlgxT1LOAQ0PaT/l3lOQB2InM6r3VagLgne46Oj276LBraWxCsV0VgLen/dwE3JnD9MqLzUxRe+kkGtQOPtxE+0+DH3Tr1YAfjEma22+pi5bDr2VzgCk0zvLvue0ZzXHVBqtmuQoDn1HI/sc7sj0yFs6brKFm36WClnmfpmKka8lNO2MuPHMG/qPPEehJ209di12UUW+DP2eucB/sxK6fhUh04rvgsSX4XOF+qAYyrZDaJ0k28yenMruDdSvLByYa0JM6OrDRJXuTztoGQognW5koWe/yl05rFw60gjLVLANIPP+KMp/oUA9lRlj2Mttz/8pxanCpa3Z93hvoR46FsL4d5Atu1Kfz0SYYd5TsHgWv0zesIHX/4FeU6hDTPk00nIXLBMKa2NoyqPl80cwCuJJvXk4UfDJFr98ilZnuixc2Kd/Egq7s8FLXJ5mibYchfeq97GduE/1/uSAj1BcTvJCEkQ5JHUkwqqOs1uaDM6pKKzHTLlE9ia3uR1mRKrE9yP160LgShXKN6oah6y2Mmkx665q1htG1x/k34R2U9GJDtRpHz1ibRXmdmYTkS/2a20QtePCqENWja4gwsS4byB1Fa7NV/tVEmxet9CBGLjLQEAAQA= dingzhixin.ex@DINGZHIXIN-EX";  // Executes a shell command and returns it's output.

    public static String shell(String hostName, int port, String shellCommand) throws IOException {
        final int maxMessageSize = 0x4000;
        final int maxPayloadSize = maxMessageSize - AdbMessage.headerLen;
        final int localId = 1;
        AdbConnection conn = null;
        try {
            AdbMessage msg;
            conn = new AdbConnection();
            conn.open(hostName, port, maxMessageSize);
            LUtils.i("开始请求授权");
            //3的时候需要每次都授权,1,2会阻塞无响应,目前不清楚如何处理
            conn.sendMessage(Command.auth, 3, maxPayloadSize, "host::\u0000");
//            conn.sendMessage(Command.auth, 3, maxPayloadSize,  "host::" +rsaPass + "\u0000");
            msg = conn.receiveMessage();
            verifyCommand(msg, Command.connect);


            LUtils.i("授权返回结束  ============================");
            conn.sendMessage(Command.connect, abdProtocolVersion, maxPayloadSize, "host::" + rsaPass + "\u0000");
//            conn.sendMessage(Command.connect, abdProtocolVersion, maxPayloadSize, "host::\u0000");
            LUtils.i("步骤1");
            msg = conn.receiveMessage();
            LUtils.i("步骤2");
            verifyCommand(msg, Command.auth);
            LUtils.i("步骤3");

            conn.sendMessage(Command.open, localId, 0, "shell:" + shellCommand + "\u0000");
            LUtils.i("步骤4");
            msg = conn.receiveMessage();
            LUtils.i("步骤5");
            verifyCommand(msg, Command.ready);

            LUtils.i("就绪");
            int remoteId = msg.arg0;
            StringBuilder out = new StringBuilder();
            while (true) {
                msg = conn.receiveMessage();
                LUtils.i(msg.command);
                if (msg.command == Command.close) {
                    break;
                }
                verifyCommand(msg, Command.write);
                LUtils.i("输出信息 = ",msg.getPayloadAsCharSequence(),"结束");
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
            LUtils.i(msg.arg0);
            throw new IOException("Command \"" + msg.command + "\" received when \"" + expectedCommand + "\" was exptected.");
        }
    }

}
