package com.dzx.util;

import java.io.*;

public class FileUtil {
    /**
     * 从文件中读取内容
     */
    public static String getFileContent(File positionFile) {
        String result = "";

        if (positionFile.exists()) {
            FileInputStream fileInputStream = null;
            BufferedReader bufferedReader = null;
            try {
                fileInputStream = new FileInputStream(positionFile);
                StringBuilder builder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                result = builder.toString();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("读取的文件内容    " + result);
        return result;
    }


    /**
     * 向文件中写入内容覆盖
     */
    public static boolean outFileContent(File positionFile, String content) {
        boolean result = false;

        if (positionFile.exists()) {
            FileOutputStream fileOutputStream = null;
            BufferedWriter bufferedWriter = null;
            try {
                fileOutputStream = new FileOutputStream(positionFile);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                bufferedWriter.write(content);
                bufferedWriter.flush();
                result = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
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
        System.out.println("写入文件成功  =" + result);
        return result;
    }

    /**
     * 向文件中写入内容 追加
     */
    public static boolean outFileContentAppend(File positionFile, String content) {
        boolean result = false;

        if (positionFile != null && !positionFile.exists()) {
            try {
                positionFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (positionFile.exists()) {
            FileOutputStream fileOutputStream = null;
            BufferedWriter bufferedWriter = null;
            try {
                fileOutputStream = new FileOutputStream(positionFile, true);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                bufferedWriter.write(content);
                bufferedWriter.flush();
                result = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
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
        System.out.println("写入文件成功  =" + result);
        return result;
    }
}
