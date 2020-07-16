package com.dzx.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ParseLogUtil {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("HXJ");
//        list.add("deviceId");
//        list.add("InitPresenter");
//        list.add("HomePresenter");
//        list.add("RequestBean");
//        list.add("Connection: Route");
//        list.add("gslbDns");
        list.add("ActivityManager: Process com.hisense.aiot");

        String result = parseLog("C:\\Users\\dingzhixin.ex\\Desktop\\新建文件夹\\log.txt", "C:\\Users\\dingzhixin.ex\\Desktop\\新建文件夹\\过滤.txt", list);
        System.out.println(result);
    }

    /**
     * @param targetFilePath 解析的文件路径
     * @param outputFilePath 输出的文件路径
     *                       line.contains("RequestBean") || line.contains("onReceive, deviceStatusChanged")
     *                       || line.contains("click,controlView")
     */
    public static String parseLog(String targetFilePath, String outputFilePath, List<String> filterConditions) {
        String result = "";
        File targetFile = new File(targetFilePath);
        if (!targetFile.exists()) {
            result = "要解析的文件不存在!";
            return result;
        }
        System.out.println("文件是否存在" + targetFile.exists());
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileInputStream = new FileInputStream(targetFile);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
            fileOutputStream = new FileOutputStream(outputFilePath);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                //包含过滤条件则输出到文件中
                if (accordWithCondition(line, filterConditions)) {
                    bufferedWriter.write(line);
                    System.out.println(line);
                    bufferedWriter.write("\n\n");
                }
            }
            bufferedWriter.flush();
            result = "文件解析成功!";
        } catch (IOException e) {
            e.printStackTrace();
            result = "文件解析失败!";
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

        return result;
    }

    /**
     * 判断指定文本中是否包含给定条件
     */
    private static boolean accordWithCondition(String target, List<String> conditions) {
        if (conditions != null && TextUtils.nonEmpty(target)) {
            for (String condition : conditions) {
                if (target.contains(condition)) {
                    return true;
                }
            }
        }
        return false;
    }
}
