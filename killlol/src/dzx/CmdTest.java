package dzx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CmdTest {

    public static void main(String[] args) {
        System.out.println("========================================");
        String lolName = "LeagueClient.exe";
        String lolName1 = "LeagueClientUx.exe";
        String lolName2 = "LeagueClientUxRender.exe";
        String lolName3 = "LeagueClientUxRender.exe";
        String lolName4 = "GameBarPresenceWriter.exe";
        String lolName5 = "League of Legends.exe";
        //获取所有程序的进程号
        String cmdCommand = "tasklist";
        //根据进程号杀死进程
        String killCommand1 = "taskkill /im ";
        String killCommand2 = "";
        String killCommand3 = " /f ";
        List<String> list = new ArrayList<>();

        try {
            Process process = Runtime.getRuntime().exec(cmdCommand);
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(lolName)
                        || line.contains(lolName1)
                        || line.contains(lolName2)
                        || line.contains(lolName3)
                        || line.contains(lolName4)) {
                    //\\s+匹配一个或者多个空格
                    String[] contents = line.split("\\s+");
                    killCommand2 = contents[1];
                    list.add(contents[1]);
                    System.out.println("匹配上的进程号==========" + contents[1]);
                } else if (line.contains(lolName5)) {
                    String[] contents = line.split("\\s+");
                    list.add(contents[3]);
                    System.out.println("匹配上的进程号==========" + contents[3]);
                }
            }
            //nircmd可以使用管理员权限执行cmd命令
            for (int i = 0; i < list.size(); i++) {
                Process process1 = Runtime.getRuntime().exec("D:\\nircmd-x64\\nircmd.exe elevate " + killCommand1 + list.get(i) + killCommand3);
                InputStream inputStream1 = process1.getInputStream();
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1, "GB2312"));
                String line1 = null;
                while ((line1 = bufferedReader1.readLine()) != null) {
                    System.out.println(line1);
                }
            }

            //获取管理员权限cmd脚本,执行会弹出黑色窗口
            //            Process process1 = Runtime.getRuntime().exec("\n" +
//                    "@echo off\n"+
//                    "mode con lines=0 cols=0\n" +
//                    "%1 mshta vbscript:CreateObject(\"Shell.Application\").ShellExecute(\"cmd.exe\",\"/k %~s0 ::\",\"\",\"runas\",1)\n" +
//                    "cd /d \"%~dp0\"\n" +
//                    "rem  "+killCommand1 + killCommand2 + killCommand3);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("========================================");
    }
}
