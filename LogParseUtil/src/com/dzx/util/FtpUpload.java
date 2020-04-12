package com.dzx.util;

import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 上传文件到ftp
 * 需要org.apache.commons.net包
 * 在https://mvnrepository.com/search?q=javaws中搜索org.apache.commons net即可
 * 当前使用版本3.6
 */
@SuppressWarnings({"Duplicates", "ResultOfMethodCallIgnored", "FieldCanBeLocal", "unused", "StatementWithEmptyBody"})
public class FtpUpload {

    //ftp对象
    private FTPClient ftp;
    //需要连接到的ftp端的ip
    private String ip;
    //连接端口，默认21
    private int port;
    //要连接到的ftp端的名字
    private String name;
    //要连接到的ftp端的对应得密码
    private String pwd;
    //生成的apk的文件名称

    //调用此方法，输入对应得ip，端口，要连接到的ftp端的名字，要连接到的ftp端的对应得密码。连接到ftp对象，并验证登录进入fto
    public FtpUpload(String ip, int port, String name, String pwd) {
        ftp = new FTPClient();
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.pwd = pwd;
        //验证登录
        try {
            ftp.connect(ip, port);
            ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlEncoding("UTF-8");
            ftp.enterLocalPassiveMode();
            if (ftp.login(name, pwd)) {
                System.out.println("Ftp登录成功!");
            } else {
                System.out.println("Ftp登录失败!");
            }
            //必须先登录ftp后设置文件类型,否则上传文件的大小会出现不一致
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

//            int reply;
//            reply = ftp.getReplyCode();
            System.out.println("ftp登录响应=" + ftp.getReplyString() + "=");
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                ftp.disconnect();
//                System.err.println("FTP server refused connection.");
//                System.exit(1);
//            }


        } catch (IOException ex) {
            System.out.println("登录FTP异常:<br>" + getExceptionInfo(ex));
        }

    }


    //上传文件
    public void putFile() {
        try {
            //将本地的"D:/1.zip"文件上传到ftp的根目录文件夹下面，并重命名为"aaa.zip"
            System.out.println(ftp.storeFile("014.apk", new FileInputStream(new File("C:\\\\Users\\\\chennana\\\\Desktop\\\\014.apk"))));
        } catch (IOException e) {
            System.out.println("finally中异常:<br>" + getExceptionInfo(e));
        }
    }

    /**
     * 获取apk文件的名称
     */
    private String getApkName() {
        File file = new File("C:\\Users\\chennana\\work\\apk\\localv7" +
                "\\aviview_v7\\app\\build\\outputs\\apk\\debug\\output.json");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] strbuf = new byte[(int) file.length()];
            fileInputStream.read(strbuf);
            fileInputStream.close();
            String s = new String(strbuf);
            int position = s.indexOf("outputFile");
            int position1 = s.indexOf(".apk");
            String currentNum = s.substring(position + 13, position1);
            if (currentNum.length() > 2) {
//                return currentNum;
            } else if (currentNum.length() == 2) {
                currentNum += "0";
            } else if (currentNum.length() == 1) {
                currentNum += "00";
            }
            System.out.println("当前要上传的文件名称是==" + currentNum + "==");
            return currentNum;
        } catch (Exception e) {
            System.out.println("finally中异常:<br>" + getExceptionInfo(e));
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("finally中异常:<br>" + getExceptionInfo(e));
            }
        }

        return "";
    }

    private String getApkNameByPath(String path) {
        File file = new File(path + "output.json");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] strbuf = new byte[(int) file.length()];
            fileInputStream.read(strbuf);
            fileInputStream.close();
            String s = new String(strbuf);
            int position = s.indexOf("outputFile");
            int position1 = s.indexOf(".apk");
            String currentNum = s.substring(position + 13, position1);
            if (currentNum.length() > 2) {
//                return currentNum;
            } else if (currentNum.length() == 2) {
                currentNum += "0";
            } else if (currentNum.length() == 1) {
                currentNum += "00";
            }
            System.out.println("当前要上传的文件名称是==" + currentNum + "==");
            return currentNum;
        } catch (Exception e) {
            System.out.println("finally中异常:<br>" + getExceptionInfo(e));
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("finally中异常:<br>" + getExceptionInfo(e));
            }
        }

        return "";
    }

    //上传文件的第二种方法，优化了传输速度
    private void putFileToFTpFast() {
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            //权限问题可能会导致os=null,进而上传失败
            //上传ftp文件需要扫描,扫描不成功禁止上传同样会导致os=null
            //异常信息:java.net.SocketException: Connection reset
            os = ftp.storeFileStream(getApkName() + ".apk");
            System.out.println("获ftp的输出流的响应=" + ftp.getReplyString() + "=获ftp的输出流的响应");
            System.out.println("获取到的ftp的输出流=" + os);
            fis = new FileInputStream(new File("C:\\Users\\chennana\\work\\apk\\localv7\\aviview_v7" +
                    "\\app\\build\\outputs\\apk\\debug\\" +
                    getApkName() + ".apk"));
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
            }
            System.out.println("上传文件到ftp成功!");
        } catch (Exception ex) {
            System.out.println("FTP上传异常:<br>" + getExceptionInfo(ex));
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }

                ftp.logout();
                if (ftp.isConnected()) {
                    ftp.disconnect();
                }
                System.out.println("finally块执行成功!");
            } catch (IOException e) {
                System.out.println("finally中异常:<br>" + getExceptionInfo(e));
                e.printStackTrace();
            }
        }
    }

    public void putFileToFTpFastByPath(String path) {
        OutputStream os = null;
        InputStream fis = null;
        try {
            os = ftp.storeFileStream(getApkNameByPath(path) + ".apk");
            System.out.println("获ftp的输出流的响应=" + ftp.getReplyString() + "=");
            System.out.println("获取到的ftp的输出流=" + os);
//            fis = new FileInputStream(new File("E:\\008.apk"));
            File file = new File(path +
                    getApkNameByPath(path) + ".apk");
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
            }
            System.out.println("上传文件到ftp成功!");
        } catch (Exception ex) {
            System.out.println("上传文件到ftp失败:<br>" + getExceptionInfo(ex));
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }

                ftp.logout();
                if (ftp.isConnected()) {
                    ftp.disconnect();
                }

            } catch (IOException e) {
                System.out.println("finally中异常:<br>" + getExceptionInfo(e));
            }
        }
    }

    /**
     * 获取异常输出信息
     */
    private String getExceptionInfo(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        ex.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }

    /**
     * 通过命令行向主函数传递参数
     * <p>
     * java -jar   PutFileToFtp.jar   E:\ideademo\out\artifacts\PutFileToFtp_jar(需要传递的参数)
     * <p>
     * cmd命令行传递参数说明
     * 第一个参数   output.json文件所在路径
     * 第二个参数   ftp的ip地址
     * 第三个参数   ftp端口号
     * 第四个参数   用户名
     * 第五个参数   密码
     */
    public static void main(String[] args) {
        FtpUpload ftpUpload;
        String path = "";
        if (args.length == 1) {
            System.out.println("命令行传递的参数" + args[0]);
            path = args[0];
            System.out.println("传递的文件路径==" + path + "==");
            ftpUpload = new FtpUpload("192.168.0.18", 21, "yintenglong", "yintenglong");
        } else if (args.length == 5) {
            path = args[0];
            ftpUpload = new FtpUpload(args[1], Integer.parseInt(args[2]), args[3], args[4]);
            System.out.println("ftp相关参数,ip=" + args[1] + ",port=" + args[2] + ",name=" + args[3] + ",pwd=" + args[4] + ",=");
        } else {
            System.out.println("命令行没有传递的参数");
            ftpUpload = new FtpUpload("192.168.0.18", 21, "yintenglong", "yintenglong");
        }
        if (path.length() > 0) {
            ftpUpload.putFileToFTpFastByPath(path);
        } else {
            System.out.println("未传递文件路径,使用默认路径");
            ftpUpload.putFileToFTpFast();
        }
    }




    public void putFileToFTpFastByPath(String apkPath,String ftpPath) {
        OutputStream os = null;
        InputStream fis = null;
        try {
            os = ftp.storeFileStream(ftpPath);
            System.out.println("获ftp的输出流的响应=" + ftp.getReplyString() + "=");
            System.out.println("获取到的ftp的输出流=" + os);
//            fis = new FileInputStream(new File("E:\\008.apk"));
            File file = new File(apkPath);
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
            }
            System.out.println("上传文件到ftp成功!");
        } catch (Exception ex) {
            System.out.println("上传文件到ftp失败:<br>" + getExceptionInfo(ex));
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }

                ftp.logout();
                if (ftp.isConnected()) {
                    ftp.disconnect();
                }

            } catch (IOException e) {
                System.out.println("finally中异常:<br>" + getExceptionInfo(e));
            }
        }
    }
}
