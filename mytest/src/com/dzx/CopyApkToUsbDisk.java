package com.dzx;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopyApkToUsbDisk {


    public static void main(String[] arg) {
        String fileName = "tusou_016.apk";
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop", fileName);

        if (file.length() < 18000000) {
            System.out.println("文件大小异常  " + file.length());
            return;
        }

        File file1 = new File("F:\\Android\\old", fileName);
        try {
            FileUtils.copyFile(file, file1);
            System.out.println("文件复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
