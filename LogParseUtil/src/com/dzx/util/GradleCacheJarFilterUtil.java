package com.dzx.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradleCacheJarFilterUtil {
    private static List<String> filePathList = new ArrayList<>();
    private static List<String> targetFilePathList = new ArrayList<>();

    public static void main(String[] a) {
        fileHandle("C:\\Users\\dingzhixin.ex\\.gradle\\caches\\modules-2\\files-2.1", "D:\\work\\app\\aggregation\\maven");

    }

    private static void copyFile(String inDir, String outDir) {
        File in = new File(inDir);
        if (!in.exists() && !in.isDirectory()) {
            LUtils.i("file path is not dir");
            return;
        }

        File[] files = in.listFiles();
        if (files == null || files.length == 0) {
            LUtils.i("files is ", files == null ? "null" : files.length);
            return;
        }

        File file = new File(outDir);
        if (!file.exists()) {
            boolean createResult = file.mkdir();
            LUtils.i("create dir result = ", createResult);
        }

    }

    private static void fileHandle(String filePath, String saveFilePath) {
        File file = new File(filePath);
        File[] files = file.listFiles();
        int startPosition = filePath.split("\\\\").length;
        LUtils.i("startPosition = ", startPosition);
        for (int i = 0; i < files.length; i++) {
            getFile(files[i], startPosition);
        }
        for (int i = 0; i < filePathList.size(); i++) {
            File dest = new File(saveFilePath, filePathList.get(i));
            File src = new File(targetFilePathList.get(i));
            try {
                FileUtils.copyFile(src, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getFile(File file, int startPosition) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                getFile(files[i], startPosition);
            }
        } else {
            String filePath = file.getAbsolutePath();
            targetFilePathList.add(filePath);
            String[] total = filePath.split("\\\\");

//            LUtils.i(new Gson().toJson(total));

            List<String> list = new ArrayList<>();

            int size = total.length;
            for (int i = size - 1; i > -1; i--) {
                if (i == size - 1) {
                    list.add(total[i]);
                    continue;
                }
                if (i == size - 2) {
                    continue;
                }
                if (i < startPosition) {
//                    list.add(total[i]);
                } else {
                    if (i != size - 3) {
                        if (total[i].contains(".")) {
//                            LUtils.i(total[i]);
                            String[] second = total[i].split("\\.");
                            int length = second.length;
                            for (int j = length - 1; j > -1; j--) {
                                list.add(second[j]);
                            }
                        } else {
                            list.add(total[i]);
                        }
                    } else {
                        list.add(total[i]);
                    }
                }
            }

            int resultSize = list.size();
            String result = "";
            for (int i = resultSize - 1; i > -1; i--) {
                if (i == 0) {
                    result += list.get(i);
                } else {
                    result += list.get(i) + "\\";
                }
            }

//            LUtils.i(result);
//
//            String first = total[2];
//            String third = "";
//            if (first.contains(".")) {
//                String[] second = first.split("\\.");
//                for (int i = 0; i < second.length; i++) {
//                    third += second[i] + "\\";
//                }
//            } else {
//                third = first + "\\";
//            }
//            String result = third;
//            for (int i = 3; i < total.length; i++) {
//                if (total[i].length() == 40 && !total[i].contains("-")) {
//                    continue;
//                }
//                if (i < total.length - 1) {
//                    result += total[i] + "\\";
//
//                } else {
//                    result += total[i];
//                }
//            }
            filePathList.add(result);
        }
    }

}
