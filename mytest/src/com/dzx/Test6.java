package com.dzx;

import com.dzx.util.LUtils;
import com.google.gson.Gson;
import javafx.scene.paint.Color;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Test6 {
    private static volatile String mTest;
    static CountDownLatch mCountDownLatch = new CountDownLatch(8);
    static ReentrantLock mReentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {


        File file=new File("D:\\Program Files\\2345Soft\\2345SafeCenter\\CommonFiles\\SdEngine");
        file.setExecutable(true);
        file.setWritable(true);
        LUtils.i(file.canExecute());
        LUtils.i(file.delete());

//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\汇总文件列表");
//        List<String> list = getAllFilePath(file);
//
//        List<String>  contents=new ArrayList<>();
//
//        for (String s:list){
//            try {
//                contents.addAll(FileUtils.readLines(new File(s)));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try {
//            FileUtils.writeLines(new File("C:\\Users\\dingzhixin.ex\\Desktop\\汇总文件列表","aarjar所有文件目录.txt"),contents);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        LUtils.i("汇总完成");

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

    private static void changeJson() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\BaikeInfoData");
        try {
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            BaikeBean.ContentMyBean bean = new Gson().fromJson(content, BaikeBean.ContentMyBean.class);

            bean.mBaikeDefaultLemmaBean = new Gson().fromJson(bean.baikeDefaultLemma, BaikeBean.ContentMyBean.BaikeDefaultLemmaBean.class);
            bean.mBaikeTagsBean = new Gson().fromJson(bean.baikeTags, BaikeBean.ContentMyBean.BaikeTagsBean.class);
            bean.baikeDefaultLemma = "";
            bean.baikeTags = "";
            String s = new Gson().toJson(bean);

            LUtils.i(s);

//            LUtils.i(bean.tag_value);
//            LUtils.i(bean.baikeTags);
//            LUtils.i(bean.baikeDefaultLemma);
//            LUtils.i(bean.getLemmaDesc());
//            LUtils.i(bean.getCard());
//            LUtils.i(bean.getCatalogContentStructured());
//            LUtils.i(bean.getErrmsg());
//            LUtils.i(bean.getErrno());
//            LUtils.i(bean.getLemmaTitle());


//            BaikeBean baikeBean = new Gson().fromJson(content, BaikeBean.class);
//            LUtils.i(baikeBean.signatureServer);
//            LUtils.i(new Gson().fromJson(baikeBean.vca_tag.get(0).baikeDefaultLemma, BaikeBean.ContentMyBean.BaikeDefaultLemmaBean.class).getLemmaDesc());
//            LUtils.i(baikeBean.vca_tag.get(0).baikeTags);
//            LUtils.i(baikeBean.vca_tag.get(0).tag_value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    private static boolean needReturn = false;

    private static void test(String s) {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\新建文本文档.txt");
        try {
            List<String> list = FileUtils.readLines(file);
//507297
            LUtils.i((char) -128);
            LUtils.i(Integer.toBinaryString(65279));
            LUtils.i((int) (list.get(0).charAt(0)));
            if (needReturn) {
                return;
            }

            Set<String> strings = new HashSet<>();
            Map<String, Integer> map = new HashMap<>();
            for (String s1 : list) {
                if (s1.length() < 32) {
                    continue;
                }

                String result1 = s1.replaceAll("\"", "").replaceAll(" ", "");
                String result2 = result1.substring(0, 32);
                String result3 = result1.substring(32).replaceAll("\\(", "").replaceAll("\\)", "")
                        .replaceAll(" ", "")
                        .replaceAll("次", "");
                LUtils.i(result1);

                if (map.get(result2) == null) {
                    map.put(result2, Integer.parseInt(result3));
                } else {
                    map.put(result2, map.get(result2) + Integer.parseInt(result3));
                }

//                LUtils.i("=",result1,"=",s1.length());
//                LUtils.i("=",result2,"=",result2.length());
//                for (int i=0;i<result2.length();i++) {
//                    LUtils.i("=",result2.charAt(0),"=");
//                }

                strings.add(result2);
            }


            LUtils.i("\n\n\n");

            for (String s1 : strings) {
                LUtils.i(s1);
            }
            LUtils.i("\n\n\n");


            for (String s1 : map.keySet()) {
                LUtils.i(s1, "  累计异常  ", map.get(s1), "次");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static AtomicInteger mAtomicInteger = new AtomicInteger(10);

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private static void sortAndFilterLog() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\session - 副本.log");
        try {
            List<String> list = FileUtils.readLines(file);
            int size = list.size();
            for (int i = size - 1; i > -1; i--) {
                if (TextUtils.isEmpty(list.get(i))) {
                    list.remove(i);
                } else if (list.get(i).contains("JuBao KeyEvent is STOP")
                        || list.get(i).contains("StartJuBao, with package")) {
                    list.remove(i);
                }

            }

            int size1 = list.size();
            List<SortLogEntity> entities = new ArrayList<>();
            for (int i = 0; i < size1; i++) {

                SortLogEntity entity = new SortLogEntity();
                entity.content = list.get(i);
                entity.sortFlg = Long.parseLong(list.get(i).substring(0, 18).replaceAll(" ", "")
                        .replaceAll(":", "")
                        .replaceAll("-", "")
                        .replaceAll("\\.", ""));
                entities.add(entity);
            }

            entities.sort(new Comparator<SortLogEntity>() {
                @Override
                public int compare(SortLogEntity o1, SortLogEntity o2) {
                    return (int) (o1.sortFlg - o2.sortFlg);
                }
            });

            StringBuilder builder = new StringBuilder();
            for (SortLogEntity entity : entities) {
                LUtils.i(entity.content);
                builder.append(entity.content).append("\n");
            }

            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\处理后日志.txt"), builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class SortLogEntity {
        public long sortFlg;
        public String content;
    }


    private static void calculateConsumeTime() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\处理后日志.txt");
        try {
            List<String> list = FileUtils.readLines(file);
            int size = list.size();
            for (int i = size - 1; i > -1; i--) {
                if (TextUtils.isEmpty(list.get(i))) {
                    list.remove(i);
                }
            }

            int size1 = list.size();
            int subFirst = 12;
            List<ConsumeEntity> list1 = new ArrayList<>();
            int count = 0;
            int total = 0;
            for (int i = 0; i < size1; i++) {
//                LUtils.i(list.get(i).contains("KEYCODE_JU_BAO_APP"),"           ",i);
                if (list.get(i).contains("KEYCODE_JU_BAO_APP")) {
                    if (
                            list.get(i + 1).contains("JuBaoKeyEventReceiver: ")
//                            list.get(i + 1).contains("showCaptureTest")
                                    && list.get(i + 2).contains("mCenterImageLayout")) {
                        ConsumeEntity entity = new ConsumeEntity();
                        List<String> list2 = getResultString(list.get(i).substring(subFirst, 18),
                                list.get(i + 1).substring(subFirst, 18),
                                list.get(i + 2).substring(subFirst, 18));
                        entity.first = list2.get(0).replaceAll("\\.", "");
                        ;
                        entity.second = list2.get(1).replaceAll("\\.", "");
                        ;
                        entity.third = list2.get(2).replaceAll("\\.", "");
                        ;

//                        entity.first = list.get(i).substring(subFirst, 18).replaceAll("\\.", "");
//                        entity.second = list.get(i + 1).substring(subFirst, 18).replaceAll("\\.", "");
//                        entity.third = list.get(i + 2).substring(subFirst, 18).replaceAll("\\.", "");

                        LUtils.i(list.get(i));
                        LUtils.i(list.get(i + 1));
                        LUtils.i(list.get(i + 2));
                        count++;
                        total += (Integer.parseInt(entity.third) - Integer.parseInt(entity.second));
                        LUtils.i("接收广播前耗时:", (Integer.parseInt(entity.second) - Integer.parseInt(entity.first)),
                                "ms, CardBar截图耗时:", (Integer.parseInt(entity.third) - Integer.parseInt(entity.second)), "ms\n\n");

                    } else {
                        continue;
                    }
                }

//                if (i > 0 && i % 3 == 0) {
//                    ConsumeEntity entity = new ConsumeEntity();
//                    entity.first = list.get(i - 3).substring(subFirst, 18).replaceAll("\\.", "");
//                    entity.second = list.get(i - 2).substring(subFirst, 18).replaceAll("\\.", "");
//                    entity.third = list.get(i - 1).substring(subFirst, 18).replaceAll("\\.", "");
//
//                    LUtils.i(list.get(i - 3));
//                    LUtils.i(list.get(i - 2));
//                    LUtils.i(list.get(i - 1));
//                    LUtils.i("接收广播前耗时:", (Integer.parseInt(entity.second) - Integer.parseInt(entity.first)),
//                            ", CardBar截图耗时:", (Integer.parseInt(entity.third) - Integer.parseInt(entity.second)));
//
//                    list1.add(entity);
////                    LUtils.i(new Gson().toJson(entity));
//                }
            }
            int size2 = list1.size();

//            for (int i = 0; i < size2; i++) {
//                LUtils.i("接收广播前耗时:",(Integer.parseInt(list1.get(i).second)-Integer.parseInt(list1.get(i).first)),
//                        ", CardBar截图耗时:",(Integer.parseInt(list1.get(i).third)-Integer.parseInt(list1.get(i).second)));
//            }

            LUtils.i("count = ", count, ", 平均耗时 = ", (total / 1.0f / count));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static List<String> getResultString(String target1, String target2, String target3) {
        List<String> list = new ArrayList<>();
        String[] s1 = target1.split("\\.");
        String[] s2 = target2.split("\\.");
        String[] s3 = target3.split("\\.");
        if (Integer.parseInt(s1[0]) > Integer.parseInt(s2[0])
                || Integer.parseInt(s2[0]) > Integer.parseInt(s3[0])) {

            if (Integer.parseInt(s1[0]) > Integer.parseInt(s2[0])) {
                list.add(target1);
                list.add(String.valueOf(Integer.parseInt(s2[0]) + 60) + "." + s2[1]);
                list.add(String.valueOf(Integer.parseInt(s3[0]) + 60) + "." + s3[1]);

            } else if (Integer.parseInt(s2[0]) > Integer.parseInt(s3[0])) {
                list.add(target1);
                list.add(target2);
                list.add(String.valueOf(Integer.parseInt(s3[0]) + 60) + "." + s3[1]);
            }
        } else {
            list.add(target1);
            list.add(target2);
            list.add(target3);
        }

        return list;
    }


    private static class ConsumeEntity {
        public String first;
        public String second;
        public String third;
    }

    private static void generateDimens() {
        String target = "<dimen name=\"custom_10px\">5dp</dimen>";
        for (int i = 0; i < 1921; i++) {
            System.out.println("<dimen name=\"custom_" + i + "px\">" + i / 1.5f + "dp</dimen>");
        }
    }

    private static void copyApkToUsb() {
        String fileName = "HiSmartImage_3.03.01.003.1.apk";
//
        try {
//            FileUtils.copyFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\widget调试04091149\\" + fileName),
            FileUtils.copyFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\" + fileName),
                    new File("F:\\Android\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 扫描目录中是否有文件名包含java的文件
     */
    private static void checkFile() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\JUUI_dev-s3-widget_047_vidaa6\\JUUI_dev-s3-widget_047_vidaa6");
        filterFileName(file);

    }

    /**
     * 过滤文件名中是否包含某个字符串
     */
    private static void filterFileName(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                filterFileName(file1);
            }

        } else {
            String fileName = file.getName();
            if (fileName.contains("java")) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }


    private static void splitUrlParam(String url) {
        //专题  contentType
//        String url="  http://vipcloud-launcher.hismarttv.com/vipcloud/specialfavorite/download?appVersionName=2020.5.0.0.13.0&deviceId=86100300000101000000071218006808&languageId=0&appVersionCode=2000000000&mac=00%3A63%3A18%3A00%3A68%3A08&accessToken=1ayD8BAFA4vJuJOwq-7M7PLemt-KKosaGaOl2Nm10iZovxRWz_j7vahUJtqhpRWy1Q7cInevo505E7RX3pZzCjPO2jljEX73eYDFPzhY_5IB6-xfGdv30uqFerWw-CR-mQda0VGe6zZ0uRHcFc1HvTHsfxv5tOVD7vFds-vVXdIlqU0AjrWwdgD5imMQ70LbYVcDjzAs1v&serialNo=c495585c5a121d9f8e1368f29591f9f2&license=1015&sourceType=1&appPackageName=com.jamdeo.tv.vod&timeStamp=1607594333&commonRandomId=cd5a7fe0-d8be-40f1-8116-e084a869a939&appVersion=01.102.136&productCode=HIKID&customerId=32822477&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&version=2020.5.0.0.13.0&subscriberId=203627496&otaVersion=0001AI0930&deviceExt=MSD648\n";
        //绘本  contentType=21002
//        String url="http://vipcloud-launcher.hismarttv.com/vipcloud/favorite/download?appVersionName=2020.5.0.0.13.0&deviceId=86100300000101000000071218006808&languageId=0&appVersionCode=2000000000&mac=00%3A63%3A18%3A00%3A68%3A08&accessToken=1ayD8BAFA4vJuJOwq-7M7PLemt-KKosaGaOl2Nm10iZovxRWz_j7vahUJtqhpRWy1Q7cInevo505E7RX3pZzCjPO2jljEX73eYDFPzhY_5IB6-xfGdv30uqFerWw-CR-mQda0VGe6zZ0uRHcFc1HvTHsfxv5tOVD7vFds-vVXdIlqU0AjrWwdgD5imMQ70LbYVcDjzAs1v&contentType=21002&serialNo=c495585c5a121d9f8e1368f29591f9f2&license=1015&sourceType=1&appPackageName=com.jamdeo.tv.vod&timeStamp=1607594393&commonRandomId=2734f330-d55b-4f48-a3a6-0638e6fcea8b&appVersion=01.102.136&productCode=HIKID&customerId=32822477&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&version=2020.5.0.0.13.0&subscriberId=203627496&otaVersion=0001AI0930&deviceExt=MSD648\n";
        //听学  contentType=19006
//        String url="http://vipcloud-launcher.hismarttv.com/vipcloud/favorite/download?appVersionName=2020.5.0.0.13.0&deviceId=86100300000101000000071218006808&languageId=0&appVersionCode=2000000000&mac=00%3A63%3A18%3A00%3A68%3A08&accessToken=1ayD8BAFA4vJuJOwq-7M7PLemt-KKosaGaOl2Nm10iZovxRWz_j7vahUJtqhpRWy1Q7cInevo505E7RX3pZzCjPO2jljEX73eYDFPzhY_5IB6-xfGdv30uqFerWw-CR-mQda0VGe6zZ0uRHcFc1HvTHsfxv5tOVD7vFds-vVXdIlqU0AjrWwdgD5imMQ70LbYVcDjzAs1v&contentType=19006&serialNo=c495585c5a121d9f8e1368f29591f9f2&license=1015&sourceType=1&appPackageName=com.jamdeo.tv.vod&timeStamp=1607594483&commonRandomId=e33875f5-ca5f-4385-8616-fb0a40df77a3&appVersion=01.102.136&productCode=HIKID&customerId=32822477&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&version=2020.5.0.0.13.0&subscriberId=203627496&otaVersion=0001AI0930&deviceExt=MSD648\n";
        //课程 contentType=2001,1001
        String[] result = url.split("\\?")[1].split("&");
        Map<String, String> map = new HashMap<>();
        for (String s : result) {
            LUtils.i(s);
            String[] result1 = s.split("=");
            if (result1.length > 0) {
                if (result1.length > 1) {
                    map.put(result1[0], result1[1]);
                } else {
                    map.put(result1[0], "");
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (String s : map.keySet()) {
            builder.append(s).append(",").append(map.get(s)).append("\n");
        }

        try {
            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\分离后的url.tsv"), builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static final List<String> NUMBERS = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

    public static void testFilter() {
        List<String> list = new ArrayList<>(Arrays.asList("3", "0", "b", "a", "d", "c"));

        List<String> letters = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        for (String s : list) {
            if (NUMBERS.contains(s)) {
                numbers.add(s);
            } else {
                letters.add(s);
            }
        }

        Collections.sort(letters);
        Collections.sort(numbers);
        list.clear();
        list.addAll(letters);
        list.addAll(numbers);

        System.out.println(list.toString());

    }


    public static void showStringLength(String text) {
        System.out.println(text.length());

    }

    public static double getColorSemblance(Color color1, Color color2) {
        // 此处Color为javafx.scene.paint.Color，getRed()为红色通道的程度，getRed() * 255为红色通道的值
        double semblance = (255 - (
                Math.abs(color1.getRed() - color2.getRed()) * 255 * 0.297
                        + Math.abs(color1.getGreen() - color2.getGreen()) * 255 * 0.593
                        + Math.abs(color1.getBlue() - color2.getBlue()) * 255 * 11.0 / 100)
        ) / 255;
        return semblance;
    }


}
