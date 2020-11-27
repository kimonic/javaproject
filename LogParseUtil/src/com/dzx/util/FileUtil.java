package com.dzx.util;


import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        System.out.println("写入文件成功  =" + result);
        return result;
    }

    public static void randomAccessFileReadFile(File file) {
//        try {
//            RandomAccessFile reader=new RandomAccessFile(file, "r");
//            reader.read()
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 通过字节流读取文件
     */
    public static void readFileByBytes(File file) {
        if (file == null || !file.exists()) {
            System.out.println("file is not exist");
            return;
        }
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                System.out.println("size = " + size);
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilder1 = new StringBuilder();
                int count = temp.length;
                System.out.println(new String(temp));
                System.out.println(new String(temp,"utf-8"));
                for (int i = 0; i < size; i++) {
                    stringBuilder.append(byteToBinaryString(temp[i])).append(",");
                    stringBuilder1.append(temp[i]).append(",");
                }//97,98,99,100,104,102,102,104,106,107,102,104,32,-60,-29,-70,-61,
                System.out.println(stringBuilder.toString());
                System.out.println(stringBuilder1.toString());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将byte字符转化为二进制字符串
     */
    public static String byteToBinaryString(Byte b) {
        if (b == null) {
            return "0";
        }
        return Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
    }




    /**

     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        try {
            Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
            Matcher m = p.matcher(strName);
            String after = m.replaceAll("");
            String temp = after.replaceAll("\\p{P}", "");
            char[] ch = temp.trim().toCharArray();

            int length = (ch != null) ? ch.length : 0;
            for (int i = 0; i < length; i++) {
                char c = ch[i];
                if (!Character.isLetterOrDigit(c)) {
                    String str = "" + ch[i];
                    if (!str.matches("[\u4e00-\u9fa5]+")) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
}
