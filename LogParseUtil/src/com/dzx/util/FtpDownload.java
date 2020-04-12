package com.dzx.util;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * ftp下载文件
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue", "FieldCanBeLocal", "unused", "DuplicatedCode"})
public class FtpDownload {

    //ftp登录下载相关信息
    private String errorInfo;
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

    private PhaseListener phaseListener;

    public PhaseListener getPhaseListener() {
        return phaseListener;
    }

    public void setPhaseListener(PhaseListener phaseListener) {
        this.phaseListener = phaseListener;
    }

    //调用此方法，输入对应得ip，端口，要连接到的ftp端的名字，要连接到的ftp端的对应得密码。连接到ftp对象，并验证登录进入fto
    public FtpDownload(String ip, int port, String name, String pwd) {
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
            //不加本行打jar包后会在ftp.retrieveFileStream(fileName + ".apk");阻塞,无法继续运行
            ftp.enterLocalPassiveMode();
            if (ftp.login(name, pwd)) {
                System.out.println("Ftp登录成功!");
            } else {
                System.out.println("Ftp登录失败!");
            }
        } catch (IOException ex) {
            System.out.println("登录FTP异常:<br>" + Utils.getExceptionInfo(ex));
        }
    }
//    //下载文件
//    public void getFile() {
//        try {
//            //将ftp根目录下的"jsoup-1.10.2.jar"文件下载到本地目录文件夹下面，并重命名为"1.jar"
//            ftp.retrieveFile("jsoup-1.10.2.jar", new FileOutputStream(new File("D:/1.jar")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private boolean copySuccess = false;

    public boolean isCopySuccess() {
        return copySuccess;
    }

    //下载文件的第二种方法，优化了传输速度
    public String downFileFromFtpToUDisk(String fileName) {
        if (phaseListener != null) {
            phaseListener.start("<br>开始从ftp复制文件" + fileName + ".apk!<br>");
        }
        copySuccess = false;
        String diskName = Utils.getLocalDisk();
        if ("没有找到U盘,请先插入U盘!".equals(diskName)) {
            System.out.println("没有找到U盘,请先插入U盘!");
            if (phaseListener != null) {
                phaseListener.error("<br>没有找到U盘,请先插入U盘!<br>");
            }
            return "没有找到U盘,请先插入U盘!";
        }
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = ftp.retrieveFileStream(fileName + ".apk");
            fos = new FileOutputStream(new File(diskName + fileName + ".apk"));
            System.out.println("is=" + is + ",fos=" + fos);
            int count = 0;
            long current = System.currentTimeMillis();
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                count++;
                fos.write(b, 0, len);
                if (phaseListener != null && System.currentTimeMillis() - current > 1000) {
                    current = System.currentTimeMillis();
                    phaseListener.progress("<br>已复制" + String.format("%.2f", count / 1024f) + "M");
                }
            }
            System.out.println(fileName + ".apk  文件保存到  " + diskName + "  成功!");
            copySuccess = true;
            if (phaseListener != null) {
                phaseListener.finish("<br>" + fileName + ".apk  文件保存到  " + diskName + "  成功!<br>");
            }
            return fileName + ".apk  文件保存到  " + diskName + "  成功!";
        } catch (Exception e) {
            System.out.println("下载文件到U盘出错:<br>" + Utils.getExceptionInfo(e));
            if (phaseListener != null) {
                for (int i = 0; i < 10; i++) {
                    phaseListener.error("<br>下载文件到U盘出错:<br>" + Utils.getExceptionInfo(e).replaceAll("\n", "<br>"));
                }
            }
            return "下载文件到U盘出错:<br>" + Utils.getExceptionInfo(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }

                ftp.logout();
                if (ftp.isConnected()) {
                    ftp.disconnect();
                }

                System.out.println("finally块执行成功!");
            } catch (IOException e) {
                System.out.println("finally中异常:<br>" + Utils.getExceptionInfo(e));
            }
        }
    }


    //下载文件的第二种方法，优化了传输速度
    public String downFileFromFtp(String fileName,String savePath) {
        if (phaseListener != null) {
            phaseListener.start("<br>开始从ftp复制文件" + fileName+"<br>");
        }
        copySuccess = false;
        File outputFile=new File(savePath);

        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = ftp.retrieveFileStream(fileName);
            fos = new FileOutputStream(outputFile);
            System.out.println("is=" + is + ",fos=" + fos);
            int count = 0;
            long current = System.currentTimeMillis();
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                count++;
                fos.write(b, 0, len);
                if (phaseListener != null && System.currentTimeMillis() - current > 1000) {
                    current = System.currentTimeMillis();
                    phaseListener.progress("<br>已复制" + String.format("%.2f", count / 1024f) + "M");
                }
            }
            System.out.println(fileName + ".apk  文件保存到  " + savePath + "  成功!");
            copySuccess = true;
            if (phaseListener != null) {
                phaseListener.finish("<br>" + fileName + "  文件保存到  " + savePath + "  成功!<br>");
            }
            return fileName + ".apk  文件保存到  " + savePath + "  成功!";
        } catch (Exception e) {
            System.out.println("下载文件到U盘出错:<br>" + Utils.getExceptionInfo(e));
            if (phaseListener != null) {
                for (int i = 0; i < 10; i++) {
                    phaseListener.error("<br>下载文件到U盘出错:<br>" + Utils.getExceptionInfo(e).replaceAll("\n", "<br>"));
                }
            }
            return "从ftp下载文件出错:<br>" + Utils.getExceptionInfo(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }

                ftp.logout();
                if (ftp.isConnected()) {
                    ftp.disconnect();
                }

                System.out.println("finally块执行成功!");
            } catch (IOException e) {
                System.out.println("finally中异常:<br>" + Utils.getExceptionInfo(e));
            }
        }
    }
}
