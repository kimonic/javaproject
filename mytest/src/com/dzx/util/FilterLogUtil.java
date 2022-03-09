package com.dzx.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterLogUtil {

    private static AtomicInteger mCount = new AtomicInteger(0);
    private static CountDownLatch mCountDownLatch;

    public static void main(String[] args) {
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\输出anr分段日志\\smartimageAnr\\logcat_20220302225600.txt_8_smartAnr");
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\输出anr分段日志\\smartimageAnr\\logcat_20220302225600.txt_3_smartAnr");


        //筛选出小聚识图相关anr日志
//        filterSmartImageAnrFile();

//        filterAnrReason();

//        filterPackageAnr();
        //根据tag过滤日志
//        outSpecialTagLog(file);

        //根据时间过滤日志
//        outSpecialTimeLog(getLineOrderLong("03-02 23:07:20.789 27864 27864 I Choreographer:"),
//                getLineOrderLong("03-02 23:07:22.789 27864 27864 I Choreographer:"), file);


        //        test();


        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\百科日志");
        File[] files = file.listFiles();
        List<String> list=new ArrayList<>(Arrays.asList("FATAL","Unity   :"));

        for (File s : files) {
            filterTag(s,list);
        }
    }


    private static void filterTag(File file, List<String> list) {
        try {
            LUtils.i("\n\n", file.getAbsolutePath(), "\n\n");
            List<String> list1 = FileUtils.readLines(file);

            int count = 0;
            for (String s : list1) {
                for (String l : list) {
                    if (s.contains(l)) {
                        count++;
                        LUtils.i(s, "\n");
                        break;
                    }
                }
            }
            LUtils.i("累计行数  =  ", count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 过滤出每个应用anr的次数
     */
    private static void filterPackageAnr() {
        String start = "ActivityManager: ANR in ";
        String end = " \\(";
        String rex = "(?<=" + start + ").*?(?=" + end + ")";


        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\输出anr分段日志\\monkey异常分析\\monkey日志中的总异常.txt");
        try {
            List<String> list = FileUtils.readLines(file);
            Pattern pattern = Pattern.compile(rex);
            LUtils.i(list.size());
            List<String> result = new ArrayList<>();
            for (String s : list) {
                Matcher matcher = pattern.matcher(s);
                while (matcher.find()) {
                    result.add(matcher.group());
//                    System.out.println(matcher.group());
                }
            }

            LUtils.i(result.size());
            Map<String, Integer> map = new HashMap<>();
            Set<String> set = new HashSet<>();


            for (String s : result) {
                if (set.contains(s)) {
                    map.put(s, map.get(s) + 1);
                } else {
                    set.add(s);
                    map.put(s, 1);
                }
            }

            int statistics = 0;
            for (String s : map.keySet()) {
                statistics += map.get(s);
                LUtils.i("应用  ", fillFixSpace(s, 36), "  Anr ", fillFixSpace("" + map.get(s), 3), "  次");
            }

            LUtils.i(statistics);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String fillFixSpace(String target, int maxNum) {
        StringBuilder result = new StringBuilder();
        result.append(target);
        for (int i = target.length(); i < maxNum; i++) {
            result.append(" ");
        }
        return result.toString();
    }

    /**
     * 根据字符串相似度筛选anr异常原因类型
     */
    private static void filterAnrReason() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\输出anr分段日志\\monkey异常分析\\小聚识图anr发生原因.txt");


        try {
            List<String> list = FileUtils.readLines(file);
            List<String> result = new ArrayList<>();
            for (String s : list) {
                if (s.contains("Reason")) {
                    result.add(s);
                }
            }
            LUtils.i(result.size());
            List<String> similarity = new ArrayList<>();
            for (String s : result) {
                boolean temp = false;
                for (String s1 : similarity) {
                    if (StringUtil.getSimilarityRatio(s, s1) > 90) {
                        temp = true;
                    }
                }

                if (!temp) {
                    similarity.add(s);
                }
            }

            LUtils.i(similarity.size());

            for (String s : similarity) {
                LUtils.i(s, "\n\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 过滤anr log
     */
    private static void filterSmartImageAnrFile() {
        File smartImageAnrDir = new File("C:\\Users\\dingzhixin.ex\\Desktop\\输出anr分段日志\\smartimageAnr");
        File anrFileDir = new File("C:\\Users\\dingzhixin.ex\\Desktop\\monkey");

        File[] anrFiles = anrFileDir.listFiles();
        List<String> result = new ArrayList<>();

        for (File file1 : anrFiles) {
            LUtils.i(file1.getAbsolutePath(), "\n\n");
            try {
                List<String> list = FileUtils.readLines(file1);
                int count = 0;
                for (String s : list) {
                    count++;
//                    if ((s.contains("ANR in ") && !s.contains("Monkey")) || s.contains("lowmemorykiller: Killing")) {
                    if ((s.contains("ActivityManager: Process com.hisense.smartimages"))) {
                        LUtils.i(s + "\n\n");
                        result.add(s);
//                        LUtils.i(list.get(count));
//                        LUtils.i(list.get(count + 1));
//                        if (s.contains("ANR in com.hisense.smartimages")) {
//                            LUtils.i(s);
//                            LUtils.i(list.get(count));
//                            LUtils.i(list.get(count + 1) + "\n\n");
////                            File file = new File(smartImageAnrDir, file1.getName() + "_smartAnr");
////                            FileUtils.copyFile(file1, file);
//                            break;
//                        }
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        LUtils.i(result.size());
//        saveStringListToFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\低内存与aar.txt"),result);
    }

    /**
     * 获取日志目录中所有日志中的anr出现前后1分钟的日志并输出到独立文件中
     */
    private static void getLogDirAnrLog() {
        File dir = new File("C:\\Users\\dingzhixin.ex\\Desktop\\monkey");
        File[] dirList = dir.listFiles();
        for (File file1 : dirList) {
            //过滤anr发生日志前后一分钟日志并输出到文件
            cutOutOneMinuteAnrLogAndOutFile(file1, "ActivityManager: ANR in ");
        }
    }

    private static void test() {
        String temp = "03-03 04:23:56.870 22107 22113 I ense.nasservic: ";
        LUtils.i(temp.substring(0, 18).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "")
                .replaceAll("\\.", ""));
    }

    /**
     * 根据tag过滤输出日志中的内容
     */
    private static void outSpecialTagLog(File file) {
        long start = System.currentTimeMillis();
        File[] files = file.listFiles();
        List<String> list = new ArrayList<>(Arrays.asList(
                "ANR in",
//                "lowmemorykiller: Killing",
//                "signal",
//                "abort",
//                "mem",
//                "cpu",
//                "skip",
//                "HisensePhoneWindowHelper",
//                "started dumping process",
//                "done dumping process",
//                "",
                "DetailPageActivity",
                "Choreographer",//03-02 23:08:19.730  3636
                "Monkey  : :Sending Touch (ACTION_UP):",
                "ActivityManager: Reason",
                "HisensePhoneWindowHelper: interceptKey",
                "Monkey  : :Sending Key",
                "InputDispatcher: Application is not responding",
                "Outbound queue length",
//                "received crash request for pid",
                "HisensePhoneWindowHelper: dispatching keycode"
//                ""
        ));
        List<String> result = new ArrayList<>();
//        List<String> list = new ArrayList<>(Arrays.asList("ANR in com.hisense.smartimages"));
//        mCountDownLatch = new CountDownLatch(files.length);
        int count = 0;
//        for (File file1 : files) {
//            ThreadPoolUtil.getInstance().execute(new Runnable() {
//                @Override
//                public void run() {
        count++;
        try {
            LUtils.i("\n\n\n", file.getAbsolutePath(), "    count = ", count, "\n\n\n");
            filter(list, file, result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
//                    mCountDownLatch.countDown();
//                }
//            });
//
//        }
//        try {
//            mCountDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        saveStringListToFile(result);
        LUtils.i("\n\n\n总计输出行数  = ", mCount.get(), "   耗时   ", (System.currentTimeMillis() - start));


    }

    /**
     * 获取每行时间构成的long型数据
     */
    private static long getLineOrderLong(String target) {
        try {
            return Long.parseLong(target.substring(0, 18).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "")
                    .replaceAll("\\.", ""));
        } catch (Throwable e) {
//            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 输出某个时间段的所有日志
     */
    public static void outSpecialTimeLog(long start, long end, File file) {
        int count = 0;
        try {
            List<String> list = FileUtils.readLines(file);
            for (String s : list) {
                long order = getLineOrderLong(s);
                if ((order >= start && order <= end) || order == 0) {
                    if (s.contains("hpplay-java")) {
                        continue;
                    }
                    count++;
                    LUtils.i(s, "\n\n");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        LUtils.i("输出总行数  =  ", count);
    }

    /**
     * 根据条件过滤结果到指定list中
     */
    private static void filter(List<String> conditions, File target, List<String> result) {

        try {
            List<String> list = FileUtils.readLines(target);
            for (String s : list) {
//                long timeOrder = getLineOrderLong(s);
//                if (timeOrder > 302182345873L) {
//                    break;
//                } else if (timeOrder < 302182245873L) {
//                    continue;
//                }

                for (String l : conditions) {
                    if (s.contains(l)) {
//                        if (s.contains("Monkey")) {
//                            break;
//                        }
                        result.add(s + "\n\n");
                        LUtils.i(s, "\n\n");
                        mCount.addAndGet(1);
                        break;
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存list string集合到文件中
     */
    private static void saveStringListToFile(List<String> list) {
        try {
            FileUtils.writeLines(new File("C:\\Users\\dingzhixin.ex\\Desktop\\临时解析结果.txt"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存list string集合到文件中
     */
    private static void saveStringListToFile(File file, List<String> list) {
        try {
            FileUtils.writeLines(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 截取anr输出日志前后一分钟的日志并输出为文件
     */
    private static void cutOutOneMinuteAnrLogAndOutFile(File file, String anrTimeTag) {

        try {
            List<String> list = FileUtils.readLines(file);
            List<Long> list1 = new ArrayList<>();
            List<PointLong> pointLongs = new ArrayList<>();

            for (String s : list) {
                if (s.contains(anrTimeTag)) {
                    list1.add(getLineOrderLong(s));
                }
            }

            int count = 0;
            for (Long l : list1) {
                count++;
                PointLong pointLong = new PointLong();
                pointLong.start = l - 100000;
                pointLong.end = l + 100000;
                pointLong.order = count;
                pointLongs.add(pointLong);
                LUtils.i(pointLong.toString());
            }

            int size = pointLongs.size();
            if (size < 1) {
                LUtils.i("该日志中不含anr信息");
                return;
            }

            int startNum = 0;
            PointLong first = pointLongs.get(0);
            File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\输出anr分段日志", file.getName() + "_" + (first.order));

            List<String> result = new ArrayList<>();
            for (String s : list) {
                long sTimeNum = getLineOrderLong(s);
                if (sTimeNum < first.start) {
                    continue;
                }
                result.add(s);
                if (sTimeNum > first.end) {
                    if (startNum < size - 1) {
                        saveStringListToFile(file1, result);
                        result.clear();
                        startNum++;
                        first = pointLongs.get(startNum);
                        file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\输出anr分段日志", file.getName() + "_" + (first.order));
                    } else {
                        if (result.size() > 0) {
                            saveStringListToFile(file1, result);
                        }
                        LUtils.i(file.getName() + "  anr 日志过滤已结束");
                        break;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static class PointLong {
        public long start;
        public long end;
        public int order;

        @Override
        public String toString() {
            return start + "  " + end + "  " + order;
        }
    }

}
