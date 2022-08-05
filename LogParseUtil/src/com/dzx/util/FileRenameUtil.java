package com.dzx.util;

import com.dzx.bean.ApkFileSaveNameBean;
import com.google.gson.Gson;

import java.io.File;

public class FileRenameUtil {
    public static void main(String[] args) {
        renameApkFile("D:\\work\\app\\BaseDemo\\app\\build\\outputs\\apk\\debug", "tusou_.apk", "存储当前apk序号.txt");
    }

    /**
     * 重命名apk
     */
    public static boolean renameApkFile(String apkPath, String apkName, String jsonName) {
        boolean success = false;
        //从文件中读取出上次的名称位置
        String positionContent = FileUtil.getFileContent(new File(apkPath, jsonName));
        Gson gson = new Gson();
        ApkFileSaveNameBean apkFileSaveNameBean = gson.fromJson(positionContent, ApkFileSaveNameBean.class);
        int position = 1;
        if (apkFileSaveNameBean != null) {
            position = apkFileSaveNameBean.getPosition() + 1;
        } else {
            apkFileSaveNameBean = new ApkFileSaveNameBean();
        }
        if (TextUtils.nonEmpty(apkPath)) {
            //重命名apk文件
            String[] splits = apkName.split("\\.");
            String resultName = splits[0] + getThreeStr(position) + "." + splits[1];
            File file = new File(apkPath, apkName);
            File renameFile = new File(apkPath, resultName);
            if (file.renameTo(renameFile)) {
                //将当前位置保存到文件中
                apkFileSaveNameBean.setPosition(position);
                apkFileSaveNameBean.setUploadPath(renameFile.getAbsolutePath());
                System.out.println("改名后的文件路径" + renameFile.getAbsolutePath());
                String content = gson.toJson(apkFileSaveNameBean);
                FileUtil.outFileContent(new File(apkPath, jsonName), content);
                success = true;
            }
        }
        System.out.println("重命名apk是否成功   =" + success);
        return success;
    }

    public static String renameApkFileAndReturnPath(String apkPath, String apkName, String jsonName) {
        String renamePath = "异常";
        boolean success = false;
        //从文件中读取出上次的名称位置
        String positionContent = FileUtil.getFileContent(new File(apkPath, jsonName));
        Gson gson = new Gson();
        ApkFileSaveNameBean apkFileSaveNameBean = gson.fromJson(positionContent, ApkFileSaveNameBean.class);
        int position = 1;
        if (apkFileSaveNameBean != null) {
            position = apkFileSaveNameBean.getPosition() + 1;
        } else {
            apkFileSaveNameBean = new ApkFileSaveNameBean();
        }
        if (TextUtils.nonEmpty(apkPath)) {
            //重命名apk文件
            String[] splits = apkName.split("\\.");
            String resultName = splits[0] + getThreeStr(position) + "." + splits[1];
            File file = new File(apkPath, apkName);
            File renameFile = new File(apkPath, resultName);
            if (file.renameTo(renameFile)) {
                //将当前位置保存到文件中
                apkFileSaveNameBean.setPosition(position);
                apkFileSaveNameBean.setUploadPath(renameFile.getAbsolutePath());
                System.out.println("改名后的文件路径" + renameFile.getAbsolutePath());
                renamePath = renameFile.getAbsolutePath();
                String content = gson.toJson(apkFileSaveNameBean);
                FileUtil.outFileContent(new File(apkPath, jsonName), content);
                success = true;
            }
        }
        System.out.println("重命名apk是否成功   =" + success);
        return renamePath;
    }


    /**
     * 重命名上传失败的文件
     */
    public static boolean renameFailApkFile(String apkPath, String apkName, String jsonName) {
        boolean success = false;
        //从文件中读取出上次的名称位置
        String positionContent = FileUtil.getFileContent(new File(apkPath, jsonName));
        Gson gson = new Gson();
        ApkFileSaveNameBean apkFileSaveNameBean = gson.fromJson(positionContent, ApkFileSaveNameBean.class);
        if (TextUtils.nonEmpty(apkPath)) {
            //重命名apk文件
            File file = new File(apkFileSaveNameBean.getUploadPath());
            File renameFile = new File(apkPath, apkName);
            if (file.renameTo(renameFile)) {
                success = true;
            }
        }
        System.out.println("重命名apk是否成功   =" + success);
        return success;
    }

    /**
     * 获取三位形式的数字字符串
     */
    public static String getThreeStr(int target) {
        String result = "";
        if (target < 10) {
            result = "00" + target;
        } else if (target < 100) {
            result = "0" + target;
        } else {
            result = "" + target;
        }
        return result;
    }
}
