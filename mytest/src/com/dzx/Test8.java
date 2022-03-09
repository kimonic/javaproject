package com.dzx;

import com.dzx.util.LUtils;
import com.dzx.util.ThreadPoolUtil;
import info.monitorenter.cpdetector.io.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Test8 {
    public static volatile int s = 16 >> 4;
// xmlns:android="http://schemas.android.com/apk/res/android"
//    android:versionCode="1305"
//    android:versionName="10.6.1(1305)"
//    android:compileSdkVersion="28"
//    android:compileSdkVersionCodename="9"
//    package="com.hisense.kdweibo.client"
//    platformBuildVersionCode="28"
//    platformBuildVersionName="9"



    //android:sharedUserId="android.uid.system"
    //    android:versionCode="12100"
    //    android:versionName="1.2.1.0.0"
    //    package="com.audiocn.karaoke.micmanager">
    private static String exceptionSearch = "select *\n" +
            "FROM imagesearchlog.o_imagesearch_exception_json \n" +
            "where partitiondate >= '20220116' \n" +
            "and datecode = '20220116'\n" +
            "and appversioncode in ('304000230','304000231','304000250','304000251')\n" +
            "and ename = 'NullPointerException'";

    private static volatile AtomicInteger mCount = new AtomicInteger(0);

    public static void main(String args[]) throws IOException {

//        File file = new File("D:\\work\\app\\aggregation\\jarunzip\\com");


//        File orgFile = new File("D:\\work\\app\\aggregation\\jarunzip\\org");
        File orgFile = new File("D:\\work\\app\\aggregation\\jarunzip\\org\\jetbrains\\kotlin");
        File[] orgFiles = orgFile.listFiles();
        List<File> orgFileList = new ArrayList<>();
        for (File file1 : orgFiles) {
            File[] tempFiles = file1.listFiles();
            if (tempFiles != null) {
                orgFileList.addAll(Arrays.asList(tempFiles));
            }
        }

        LUtils.i("总文件夹数  = ", orgFileList.size());

//        File[] files = file.listFiles();379,230  131,926
        List<String> list = new CopyOnWriteArrayList<>();
        int count = 0;

        for (File file1 : orgFileList) {
            count++;
            int finalCount = count;
            ThreadPoolUtil.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<String> temp = getAllFilePath(file1);
                    list.addAll(temp);
                    try {
                        FileUtils.writeLines(new File("C:\\Users\\dingzhixin.ex\\Desktop\\unzipTest\\文件orgkotlin目录表", finalCount + ".txt"), temp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mCount.getAndAdd(1);
                    LUtils.i(file1.getAbsolutePath(), "   统计完成   ", temp.size(), "    ", list.size(), "  ",mCount.get());
                }
            });

        }


//        for (String s : list) {
//            try {
//                File file1 = new File(s);
//                if (file1.length()==0){
//                    LUtils.i("文件大小为0,跳过  ",s);
//                    continue;
//                }
//                String fileName = file1.getName();
//                File zipFileDir = new File(file1.getParentFile().getAbsolutePath().replaceAll("maven","jarunzip"), fileName.replaceAll("\\.", "-"));
//                String zipFileDirPath = zipFileDir.getAbsolutePath() + "\\";
//
//
//                if (s.endsWith(".jar")) {
//                    LUtils.i("解压jar路径  ",s);
//                    UZipFile.unZipFiles(file1, zipFileDirPath);
//                } else if (s.endsWith(".aar")) {
//                    LUtils.i("解压aar路径  ",s);
//                    UZipFile.unZipFiles(file1, zipFileDirPath);
//                    File aarFile = new File(zipFileDirPath, "classes.jar");
//                    if (aarFile.exists()) {
//                        String aarJarDirPath = aarFile.getParentFile().getAbsolutePath() + "\\classFileDir\\";
//                        UZipFile.unZipFiles(aarFile, aarJarDirPath);
//                    }
//                } else {
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }


        LUtils.i("文件解压结束 ===");
//        String filePath = "C:\\Users\\dingzhixin.ex\\Desktop\\unzipTest\\recyclerview-v7-28.0.0.aar";
//
//        File file = new File(filePath);
//        String dirPath = "C:\\Users\\dingzhixin.ex\\Desktop\\unzipTest\\" + file.getName().replaceAll("\\.", "-") + "\\";
//        try {
//            UZipFile.unZipFiles(file, dirPath);
//
//            File file1 = new File(dirPath, "classes.jar");
//            if (file1.exists()) {
//                UZipFile.unZipFiles(file1, dirPath + "classesDir\\");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    private static List<String> getAllFilePath(File file) {
        List<String> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File file1 : files) {
                    list.addAll(getAllFilePath(file1));
                }
            }
        } else {
//            LUtils.i(file.getAbsolutePath());
            list.add(file.getAbsolutePath());
        }
        return list;
    }

    /**
     * 修改ffmpeg输出的文件名
     */
    private static void changeFileName() throws IOException {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\视频测试\\ff");
        File[] files = file.listFiles();
        int length = files.length;
        List<File> files1 = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (files[i].getName().contains("jpeg")) {
                files1.add(files[i]);
            }
        }

        int size = files1.size();
        LUtils.i("总文件数  ", size);

        for (int i = 0; i < size; i++) {
            LUtils.i(files1.get(i).getName());
            LUtils.i();
            FileUtils.copyFile(files1.get(i),
                    new File("C:\\Users\\dingzhixin.ex\\Desktop\\视频测试\\ff\\re\\" +
                            handleInt(files1.get(i).getName().split("-")[1].substring(0, 3))
                            + ".jpeg"));
        }
    }

    private static String handleInt(String target) {
        String result = "0000";

        int num = Integer.parseInt(target);
        int next = 403 - num;

        if (next < 10) {
            result = "000" + next;
        } else if (next < 100) {
            result = "00" + next;
        } else if (next < 1000) {
            result = "0" + next;
        } else {
            result = "" + next;
        }


        return result;
    }

    /**
     * long转化为byte数组
     */
    public static byte[] longToBytes(long x) {

        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

        buffer.putLong(x);

        return buffer.array();

    }

    /**
     * byte数组转化为long
     */
    public static long bytesToLong(byte[] bytes) {

        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

        buffer.put(bytes);
        // 将Buffer从写模式切换到读模式（必须调用这个方法）,读取从0到当前位置的数据
        buffer.flip();//need flip

        return buffer.getLong();

    }

    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset - 1] & 0xFF) << 8)
                | ((src[offset - 2] & 0xFF) << 16)
                | ((src[offset - 3] & 0xFF) << 24));
        return value;
    }

    public static int reverseBytesToInt(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset + 1] & 0xFF) << 8)
                | ((src[offset + 2] & 0xFF) << 16)
                | ((src[offset + 3] & 0xFF) << 24));
        return value;
    }

    public static String byteToHex(byte b) {

        String hex = Integer.toHexString(b & 0xFF);

        if (hex.length() < 2) {
            hex = "0" + hex;
        }

        return hex;

    }

    public static void write() throws IOException {
        //以读写的方式来访问该文件
        RandomAccessFile raf = new RandomAccessFile("D:\\test.txt", "rw");
        raf.writeBytes("Hello World!");
        raf.writeBoolean(true);
        raf.writeInt(30);
        raf.writeDouble(3.56);
        raf.close();
    }

    public static void read() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            builder.append(" ");
        }
        File file = new File("E:\\迅雷云盘\\tongtianzhiti.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        LUtils.i(randomAccessFile.getFilePointer());
        LUtils.i(file.length());
//        long start = (long) (file.length() * 0.934f)-1024*10;
        long start = 14388015;
        //13813437
        //13846049
        //13877866
        //13909033
        //13940861
        //13971418
        //14002584
        //14354419
        //14388015

        //15349131
        //1535694
        randomAccessFile.seek(start);

//        byte[] bytes = new byte[1024];
        StringBuilder builder1 = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            String line = randomAccessFile.readLine();
            if (line != null) {
                //此种方式科技以解决使用byte转码导致的部分中文乱码问题
                builder1.append(new String(line.getBytes(StandardCharsets.ISO_8859_1), "GB18030"));
            }
        }

        String result = builder1.toString().replaceAll(" ", "").replaceAll("\n", "");
        LUtils.i("读取的总字符数  ", randomAccessFile.getFilePointer());
        int length = result.length();
        int lineCount = 39;

        int total = 0;
        while (total < length) {
            if (total + lineCount < length) {
                LUtils.i(builder.toString() + result.substring(total, total + lineCount).replaceAll("\r", ""));
            } else {
                LUtils.i(builder.toString() + result.substring(total));
            }
            total += lineCount;
        }


//        raf.seek(12);//设置指针的位置
//        boolean booleanValue = raf.readBoolean();
//        int intValue = raf.readInt();
//        double doubleValue = raf.readDouble();
//        raf.seek(0);//设置指针的位置为文件的开始部分
//        byte[] bytes = new byte[12];
//        for (int i=0; i<bytes.length; i++)
//            bytes[i] = raf.readByte();//每次读一个字节，并把它赋值给字节bytes[i]
//        String stringValue = new String(bytes);
//        raf.skipBytes(1);//指针跳过一个字节
//        int intValue2 = raf.readInt();
//        raf.close();
//        System.out.println(booleanValue);
//        System.out.println(intValue);
//        System.out.println(doubleValue);
//        System.out.println(stringValue);
//        System.out.println(intValue2);
    }


    /**
     * 利用第三方开源包cpdetector获取文件编码格式
     *
     * @param path 要判断文件编码格式的源文件的路径
     * @author huanglei
     * @version 2012-7-12 14:05
     */
    public static String getFileEncode(String path) {
        /*
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
         * JChardetFacade、ASCIIDetector、UnicodeDetector。
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
         * cpDetector是基于统计学原理的，不保证完全正确。
         */
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
         * 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        /*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         */
        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        File f = new File(path);
        try {
            charset = detector.detectCodepage(f.toURI().toURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null)
            return charset.name();
        else
            return null;
    }


    //  /* Table of CRCs of all 8-bit messages. */
    //   unsigned long crc_table[256];
    //
    //   /* Flag: has the table been computed? Initially false. */
    //   int crc_table_computed = 0;
    //
    //   /* Make the table for a fast CRC. */
    //   void make_crc_table(void)
    //   {
    //     unsigned long c;
    //     int n, k;
    //
    //     for (n = 0; n < 256; n++) {
    //       c = (unsigned long) n;
    //       for (k = 0; k < 8; k++) {
    //         if (c & 1)
    //           c = 0xedb88320L ^ (c >> 1);
    //         else
    //           c = c >> 1;
    //       }
    //       crc_table[n] = c;
    //     }
    //     crc_table_computed = 1;
    //   }
    //
    //   /* Update a running CRC with the bytes buf[0..len-1]--the CRC
    //      should be initialized to all 1's, and the transmitted value
    //      is the 1's complement of the final running CRC (see the
    //      crc() routine below)). */
    //
    //   unsigned long update_crc(unsigned long crc, unsigned char *buf,
    //                            int len)
    //   {
    //     unsigned long c = crc;
    //     int n;
    //
    //     if (!crc_table_computed)
    //       make_crc_table();
    //     for (n = 0; n < len; n++) {
    //       c = crc_table[(c ^ buf[n]) & 0xff] ^ (c >> 8);
    //     }
    //     return c;
    //   }
    //
    //   /* Return the CRC of the bytes buf[0..len-1]. */
    //   unsigned long crc(unsigned char *buf, int len)
    //   {
    //     return update_crc(0xffffffffL, buf, len) ^ 0xffffffffL;
    //   }
}
