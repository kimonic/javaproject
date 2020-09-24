package com.dzx.util;

import java.io.*;
import java.util.List;

public class FileUtil {
    /**
     * 从文件中读取内容
     */
    public static String getFileContent(File positionFile) {
        String result = "";
        int count = 0;

        if (positionFile.exists()) {
            FileInputStream fileInputStream = null;
            BufferedReader bufferedReader = null;
            try {
                fileInputStream = new FileInputStream(positionFile);
                StringBuilder builder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = null;
                System.out.println("==============");
                while ((line = bufferedReader.readLine()) != null) {
                    if (
//                            line.contains("IndexOutOfBoundsException")
//                            || line.contains("UnsatisfiedLinkError")
//                             line.contains("NullPointerException")
                            line.contains("value = {\"additional\":\"\",\"area\"")
                    ) {
                        count++;
                        System.out.println(line + "\n\n");
                    } else {
                    }
                    builder.append(line).append("\n");
                }
                result = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileInputStream != null) {
                        //UnsatisfiedLinkError   92次
                        //IndexOutOfBoundsException   32次
                        //NullPointerException   2次
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
//        System.out.println("读取的文件内容    " + result);
//        System.out.println("读取的文件内容  总行数   " + count);
        return result;
    }

    public static String getFileContentFiler(File positionFile, List<String> list) {
        String result = "";
        int count = 0;

        if (positionFile.exists()) {
            FileInputStream fileInputStream = null;
            BufferedReader bufferedReader = null;
            try {
                fileInputStream = new FileInputStream(positionFile);
                StringBuilder builder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    if (
//                            line.contains("IndexOutOfBoundsException")
//                            || line.contains("UnsatisfiedLinkError")
                            line.contains("NullPointerException")
                    ) {
                        count++;
                        System.out.println(line);
                    } else {
                    }
                    builder.append(line);
                }
                result = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileInputStream != null) {
                        //UnsatisfiedLinkError   92次
                        //IndexOutOfBoundsException   32次
                        //NullPointerException   2次
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
//        System.out.println("读取的文件内容  总行数   " + count);
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

    public static void randomAccessFileReadFile(File  file){
//        try {
//            RandomAccessFile reader=new RandomAccessFile(file, "r");
//            reader.read()
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
