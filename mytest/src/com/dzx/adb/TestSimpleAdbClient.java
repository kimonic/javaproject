package com.dzx.adb;

// Test program for the SimpleAdbClient class.
public class TestSimpleAdbClient {

    private static final int port = 5555;

    public static void main(String[] args) throws Exception {
//        if (args.length == 0) {
//            System.out.println("Arguments: hostname command");
//            System.out.println("E.g.: java -cp simpleAdbClient.jar TestSimpleAdbClient 192.168.1.2 \"ls -l\"");
//            return;
//        }
//        if (args.length != 2) {
//            System.out.println("Invalid number of command line arguments.");
//            return;
//        }
//        String hostname = args[0];
        String hostname = "192.168.137.172";
        String shellCommand = "logcat";
        String out = SimpleAdbClient.shell(hostname, port, shellCommand);
        System.out.println(out);
    }

}
