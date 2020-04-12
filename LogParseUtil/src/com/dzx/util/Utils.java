package com.dzx.util;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**工具类文件*/
@SuppressWarnings({"WeakerAccess", "ResultOfMethodCallIgnored"})
public class Utils {
    /**
     * 获取本地磁盘,可以获取U盘的盘符
     */
    public static String getLocalDisk() {
        File[] _files = File.listRoots();//全部盘符 临时变量
        //过滤掉非"本地磁盘"类型的磁盘 by xdj 20121016
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();// 获取FileSystemView对象
        for (File file : _files) {
            // 获取磁盘的类型描述信息
            String diskType = fileSystemView.getSystemTypeDescription(file);

//            本地磁盘||可移动磁盘
//            System.out.println("系统盘符类型1="+diskType);
//            ZHANGDAZHAO (H:)
//            System.out.println("系统盘符类型2="+fileSystemView.getSystemDisplayName(file));
//            C:\Users\dingzhixin.ex\Documents
//            System.out.println("系统盘符类型3="+fileSystemView.getDefaultDirectory());
//            C:\Users\dingzhixin.ex\Desktop
//            System.out.println("系统盘符类型4="+fileSystemView.getHomeDirectory());
//            计算机
//            System.out.println("系统盘符类型5="+fileSystemView.getParentDirectory(file));
            //盘符类型包括：本地磁盘、可移动磁盘、CD 驱动器等
            if (diskType.equals("可移动磁盘")) {
                //            H:\
                System.out.println("获取U盘盘符=" + file.getAbsolutePath());
                return file.getAbsolutePath();
            }
        }
        return "没有找到U盘,请先插入U盘!";
    }

    /**
     * 获取异常输出信息
     */
    public static String getExceptionInfo(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        ex.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }

    /**
     * Java执行cmd命令
     */
    public static String runtimeCommand(String command) {
        Process process;
        try {//  '/c'执行完后关闭cmd窗口,最后的cmd命令有异常时会出现问题
            //  '/k'执行完命令后不关闭窗口,手动exit则不会出现异常引发的问题
            process = Runtime.getRuntime().exec("cmd.exe /c " + command);
            int status = process.waitFor();

            System.out.println(status);
            InputStream in = process.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            return "<br>移除U盘成功";
        } catch (Exception e) {
            return "<br><br>执行cmd命令出错<br>" + getExceptionInfo(e);
        }

    }

    /**
     * 获取当前jar包所在路径
     */
    public static String getJarPath(Class clz) {
        String jarPath = clz.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println("当前jar包所在的路径=" + jarPath);
        return jarPath.substring(1, jarPath.lastIndexOf("/"));
    }

    /**
     * 保存内容到文件
     */
    public static void saveFile(String path, String content) {
        System.out.println("当前文件的保存路径=" + path);
        File file = new File(path);
        if (file.isDirectory()) {
            System.out.println("当前传递的文件路径是目录!");
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            if (file.isFile()) {
                if (!file.exists()) {
                    System.out.println("当前文件不存在,创建新文件!");
                    file.createNewFile();
                }
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            System.out.println(path + "文件保存成功!");
        } catch (Exception e) {
            System.out.println("保存文件异常=" + getExceptionInfo(e));
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean strIsEmpty(String target) {
        return target == null || "".equals(target);
    }

    /**
     * 获取字符串int值,异常时返回0
     */
    public static int getStrInt(String target) {
        if (strIsEmpty(target)) {
            return 0;
        }
        try {
            return Integer.parseInt(target);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String readFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            System.out.println("当前的文件路径是目录=" + path);
            return "";
        }
        if (!file.exists()) {
            System.out.println("当前的文件不存在=" + path);
            return "";
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp);
            }
            System.out.println("当前读取的文件内容是=" + stringBuilder.toString());
            return stringBuilder.toString();
        } catch (Exception e) {
            System.out.println("读取文件内容出错="+getExceptionInfo(e));
        }
        return "";
    }
}