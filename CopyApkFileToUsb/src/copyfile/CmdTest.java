package copyfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CmdTest {

    public static void main(String[] args) {
        System.out.println("========================================");
        String lolName = "LeagueClient.exe ";
        //获取所有程序的进程号
        String cmdCommand = "tasklist";
        //根据进程号杀死进程
        String killCommand1 = "taskkill /im ";
        String killCommand2 = "";
        String killCommand3 = " /f ";

        try {
            Process process = Runtime.getRuntime().exec(cmdCommand);
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(lolName)) {
                    //\\s+匹配一个或者多个空格
                    String[] contents = line.split("\\s+");
                    killCommand2 = contents[1];
                    System.out.println(contents[1]);
                }
            }
            //nircmd可以使用管理员权限执行cmd命令
            Process process1 = Runtime.getRuntime().exec("D:\\nircmd-x64\\nircmd.exe elevate "+killCommand1 + killCommand2 + killCommand3);
            InputStream inputStream1 = process1.getInputStream();
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1, "GB2312"));
            String line1 = null;
            while ((line1 = bufferedReader1.readLine()) != null) {
                System.out.println(line1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("========================================");
    }
}
