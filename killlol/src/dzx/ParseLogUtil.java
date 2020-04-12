package dzx;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ParseLogUtil {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\20313\\Desktop\\bug\\bug\\log.txt");
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
            fileOutputStream = new FileOutputStream("C:\\Users\\20313\\Desktop\\bug\\bug\\筛选好的日志.txt");
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("RequestBean") || line.contains("onReceive, deviceStatusChanged")
                        || line.contains("click,controlView")) {
                    System.out.println(line);
                    bufferedWriter.write(line);
                    bufferedWriter.write("\n\n");
                    System.out.println("\n");
                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }

                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
