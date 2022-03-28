package com.dzx;

import com.dzx.util.LUtils;
import com.dzx.util.UZipFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test9 {
    public static void main(String[] args) {
        unzipGradleLibJarFile();
    }

    private static void unzipGradleLibJarFile() {
        File file = new File("C:\\Users\\dingzhixin.ex\\.gradle\\wrapper\\dists\\gradle-5.6.4-all\\ankdp27end7byghfw1q2sw75f\\gradle-5.6.4\\lib");
        List<File> list=getAllFile(file);

        for (File file1 : list) {
            try {
                UZipFile.unZipFiles(file1,"C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件\\libs\\");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            LUtils.i(file1.getAbsolutePath());
        }


    }

    private static void getGradleJavaFile() {
        File file = new File("E:\\work\\app\\gradlesrcc\\src");
        File[] files = file.listFiles();
        List<File> list = new ArrayList<>();

        if (files != null) {
            for (File file1 : files) {
                list.addAll(getFiles(file1));
            }
        }

        List<File> result = new ArrayList<>();
        for (File file1 : list) {
            String path = file1.getAbsolutePath();
            if (path.contains("org")) {
                result.addAll(getAllFile(file1));
            }
        }

        for (File s : result) {
            String path = s.getAbsolutePath().substring(s.getAbsolutePath().indexOf("org"));
            LUtils.i(path);
            File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件", path);
            LUtils.i(file1.getAbsolutePath());
            try {
                FileUtils.copyFile(s, file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void getHierarchyFiles(File file, int deep) {
        List<File> list = new ArrayList<>();
        if (file.isDirectory()) {
            for (int i = 0; i < deep; i++) {

            }
        }

    }

    public static void getFiles(List<File> list) {
        List<File> result = new ArrayList<>();
        for (File file : list) {
            result.addAll(getFiles(file));
        }
    }

    /**
     * 获取文件夹的下一级的所有文件
     */
    public static List<File> getFiles(File file) {
        List<File> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                list.addAll(Arrays.asList(files));
            }
        }
        return list;
    }

    public static List<String> getFilePath(File file) {
        List<String> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    list.addAll(getFilePath(file1));
                }
            }
        } else {
            String path = file.getAbsolutePath();
//            LUtils.i(path);
            list.add(path);
        }
        return list;
    }

    public static List<File> getAllFile(File file) {
        List<File> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    list.addAll(getAllFile(file1));
                }
            }
        } else {
            String path = file.getAbsolutePath();
//            LUtils.i(path);
            list.add(file);
        }
        return list;
    }

}
