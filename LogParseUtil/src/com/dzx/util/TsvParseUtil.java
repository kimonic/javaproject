package com.dzx.util;

import com.dzx.bean.ExceptionResultEntity;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

public class TsvParseUtil {

    public static void main(String[] args) {


//        try {
//            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\空指针异常1.txt"), "", false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        readTsv("C:\\Users\\dingzhixin.ex\\Desktop\\data_2021-06-10 09_33_55 AM.tsv");

//        filterException();
        parseFile();
    }

    private static List<String[]> getCsvList() {
        return null;
    }


    private static void parseFile() {
        try {
            // 创建tsv解析器settings配置对象
            TsvParserSettings settings = new TsvParserSettings();
            settings.setMaxCharsPerColumn(20000);
            settings.getFormat().setLineSeparator("\n");
            TsvParser parser = new TsvParser(settings);
            DataInputStream in = new DataInputStream(new FileInputStream(new File("C:\\Users\\dingzhixin.ex\\Desktop\\data_2021-08-24 09_11_01 AM.tsv")));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));//这里如果csv文件编码格式是utf-8,改成utf-8即可
            List<String[]> allRows = parser.parseAll(br);

            LUtils.i("异常总次数 = ", allRows.size(), " 次");

            Map<String, List<String[]>> errorFilterMap = new HashMap<>();
            for (String[] strings : allRows) {
                if (errorFilterMap.get(strings[21]) == null) {
                    List<String[]> list = new ArrayList<>();
                    list.add(strings);
                    errorFilterMap.put(strings[21], list);
                } else {
                    errorFilterMap.get(strings[21]).add(strings);
                }
            }
            List<ExceptionResultEntity> exceptionResultEntities = new ArrayList<>();
            for (String s : errorFilterMap.keySet()) {
                ExceptionResultEntity exceptionResultEntity = new ExceptionResultEntity();
                exceptionResultEntity.exceptionInfo = s;
                exceptionResultEntity.sameInfoList = errorFilterMap.get(s);
                exceptionResultEntity.count = errorFilterMap.get(s).size();
                exceptionResultEntities.add(exceptionResultEntity);
            }
            exceptionResultEntities.sort(new Comparator<ExceptionResultEntity>() {
                @Override
                public int compare(ExceptionResultEntity o1, ExceptionResultEntity o2) {
                    if (o1.count > o2.count) {
                        return -1;
                    } else if (o1.count < o2.count) {
                        return 1;
                    }
                    return 0;
                }
            });


            StringBuilder builder = new StringBuilder();
            builder.append("总计发生异常  ").append(allRows.size()).append("    次\n");
            for (ExceptionResultEntity s : exceptionResultEntities) {
//                以下异常87次 3.03.00.020.0发生5次, 3.00.01.046.0发生64次, 3.03.00.029.0发生18次
                builder.append("以下异常发生了   ")
                        .append(s.count)
                        .append("   次     ")
                        .append(filterVersion(s.sameInfoList))
                        .append("\n")
                        .append(filterFeatureCode(s.sameInfoList))
                        .append("\n")
                        .append("=======================================\n")
                        .append(filterFeatureCodeAndVersion(s.sameInfoList))
                        .append("\n=======================================\n")
                        .append(s.exceptionInfo)
                        .append("\n\n");

            }
            try {
                FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\异常结果统计.txt"), builder.toString(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }

            LUtils.i(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String filterVersion(List<String[]> list) {
        String result = "";
        Map<String, Integer> versionSet = new HashMap<>();

        for (String[] strings : list) {
            versionSet.merge(strings[19], 1, Integer::sum);
        }
        StringBuilder builder = new StringBuilder();
        for (String s : versionSet.keySet()) {
            builder.append(s).append("发生").append(versionSet.get(s)).append("次").append(", ");
        }
        result = builder.toString();
        return result;


    }

    private static String filterFeatureCode(List<String[]> list) {
        String result = "";
        Map<String, Integer> versionSet = new HashMap<>();

        for (String[] strings : list) {
            versionSet.merge(strings[17], 1, Integer::sum);
        }
        StringBuilder builder = new StringBuilder();
        for (String s : versionSet.keySet()) {
            builder.append(s).append("发生").append(versionSet.get(s)).append("次").append(", ");
        }
        result = builder.toString();
        return result;


    }


    private static String filterFeatureCodeAndVersion(List<String[]> list) {
        String result = "";
        Map<String, Integer> versionSet = new HashMap<>();

        for (String[] strings : list) {
            versionSet.merge(strings[17]+"***"+strings[19], 1, Integer::sum);
        }
        StringBuilder builder = new StringBuilder();
        for (String s : versionSet.keySet()) {
            builder.append(s).append("发生").append(versionSet.get(s)).append("次").append(", ");
        }
        result = builder.toString();
        return result;


    }




    private static void filterException() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\空指针异常1.txt");
        Set<String> strings = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        try {
            List<String> list = FileUtils.readLines(file);
            int count = 0;
            for (String string : list) {
                map.merge(string, 1, Integer::sum);
            }


            for (String s : map.keySet()) {
                StringBuilder builder = new StringBuilder();
                LUtils.i("以下异常发生  ", map.get(s), "  次      \n", s);
            }
            System.out.println(strings.size());
            System.out.println(list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readTsv(String url) {


        System.out.println(new java.util.Date().toString());
        List<Object> list = new ArrayList<Object>();
        try {
            // 创建tsv解析器settings配置对象
            TsvParserSettings settings = new TsvParserSettings();
            settings.setMaxCharsPerColumn(20000);
            settings.getFormat().setLineSeparator("\n");
            TsvParser parser = new TsvParser(settings);
            DataInputStream in = new DataInputStream(new FileInputStream(new File(url)));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));//这里如果csv文件编码格式是utf-8,改成utf-8即可
            List<String[]> allRows = parser.parseAll(br);
            String safeInitError = "AlibcTradeInitCallback is failed. errCode = 1; errMsg = 安全初始化失败";
            int safeInitErrorCount = 0;
            String serviceError = "errInfo服务竟然出错了";
            int serviceErrorCount = 0;
            String UnsatisfiedLinkError = "java.lang.UnsatisfiedLinkError";
            int UnsatisfiedLinkErrorCount = 0;
            String serviceRetry = "errInfo很抱歉，服务异常，请稍后重试！";
            int serviceRetryCount = 0;

            String checkError = "errInfo文件MD5校检失败";
            int checkErrorCount = 0;

            String netError = "errInfo网络竟然崩溃了";
            int netErrorCount = 0;

            String baiKeError = "parse HiCloudBaikeContentBean is null";
            int baiKeErrorCount = 0;

            String baiKeError1 = "baike lemmaDesc is null";
            int baiKeErrorCount1 = 0;

            String uploadFailError = "errInfoconnect error, errCode=PICTURE_UPLOAD_FAIL";
            int uploadFailErrorCount = 0;

            String noGo0dsError = "errInfo没有搜索到商品";
            int noGo0dsErrorCount = 0;

            String noGo0dsError1 = "errInfo未搜索到任何商品";
            int noGo0dsErrorCount1 = 0;

            String OutOfMemoryError = "java.lang.OutOfMemoryError";
            int OutOfMemoryErrorCount = 0;

            String ConcurrentModificationException = "ConcurrentModificationException";
            int ConcurrentModificationExceptionCount = 0;


            String NullPointerException = "NullPointerException";
            int NullPointerExceptionCount = 0;

            String NoClassDefFoundError = "NoClassDefFoundError";
            int NoClassDefFoundErrorCount = 0;

            String JSONException = "JSONException";
            int JSONExceptionCount = 0;

            String IndexOutOfBoundsException = "IndexOutOfBoundsException";
            int IndexOutOfBoundsExceptionCount = 0;


            String timeOutError = "errInfodata send or receive timeout";
            int timeOutErrorCount = 0;

            String fileLengthZeroError = "errInfofile.length() == 0";
            int fileLengthZeroErrorCount = 0;

            String UnsupportedOperationException = "UnsupportedOperationException";
            int UnsupportedOperationExceptionCount = 0;

            String errInfoError2003 = "errInfoerror=-2003";
            int errInfoError2003Count = 0;

            String pictureUploadFail = "PICTURE_UPLOAD_FAIL";
            int pictureUploadFailCount = 0;


            for (int i = 0; i < allRows.size(); i++) {    //忽略第一行
                //21  异常信息
                //17 featurecode
                //19 版本号
//                if (i < 10) {
                String errorInfo = allRows.get(i)[21];
                if (!TextUtils.isEmpty(errorInfo)) {
                    if (errorInfo.contains(safeInitError)) {
                        safeInitErrorCount++;
                    } else if (errorInfo.contains(serviceError)) {
                        serviceErrorCount++;
                    } else if (errorInfo.contains(pictureUploadFail)) {
                        pictureUploadFailCount++;
                    } else if (errorInfo.contains(errInfoError2003)) {
                        errInfoError2003Count++;
                    } else if (errorInfo.contains(UnsupportedOperationException)) {
                        UnsupportedOperationExceptionCount++;
                    } else if (errorInfo.contains(fileLengthZeroError)) {
                        fileLengthZeroErrorCount++;
                    } else if (errorInfo.contains(timeOutError)) {
                        timeOutErrorCount++;
                    } else if (errorInfo.contains(IndexOutOfBoundsException)) {
                        IndexOutOfBoundsExceptionCount++;
                    } else if (errorInfo.contains(JSONException)) {
                        JSONExceptionCount++;
                    } else if (errorInfo.contains(NoClassDefFoundError)) {
                        NoClassDefFoundErrorCount++;
                    } else if (errorInfo.contains(NullPointerException)) {
                        handleNullPointerException(allRows.get(i));

                        NullPointerExceptionCount++;
                    } else if (errorInfo.contains(ConcurrentModificationException)) {
                        ConcurrentModificationExceptionCount++;
                    } else if (errorInfo.contains(UnsatisfiedLinkError)) {
                        UnsatisfiedLinkErrorCount++;
                    } else if (errorInfo.contains(serviceRetry)) {
                        serviceRetryCount++;
                    } else if (errorInfo.contains(checkError)) {
                        checkErrorCount++;
                    } else if (errorInfo.contains(netError)) {
                        netErrorCount++;
                    } else if (errorInfo.contains(baiKeError)) {
                        baiKeErrorCount++;
                    } else if (errorInfo.contains(baiKeError1)) {
                        baiKeErrorCount1++;
                    } else if (errorInfo.contains(uploadFailError)) {
                        uploadFailErrorCount++;
                    } else if (errorInfo.contains(noGo0dsError)) {
                        noGo0dsErrorCount++;
                    } else if (errorInfo.contains(noGo0dsError1)) {
                        noGo0dsErrorCount1++;
                    } else if (errorInfo.contains(OutOfMemoryError)) {
                        OutOfMemoryErrorCount++;
                    } else {


//                        for (int j = 0; j < allRows.get(i).length; j++) {
//                            if (j == 17 || j == 19 || j == 21) {
//                                System.out.println(j + "  |=|  " + allRows.get(i)[j]);
//                            }
//                        }
                    }
                }


//                    System.out.println(allRows.get(i).length);
//                    System.out.println(Arrays.toString(allRows.get(i)));
//                }
            }
            LUtils.i("safeInitErrorCount = ", safeInitErrorCount,
                    " ,\n serviceErrorCount = ", serviceErrorCount,
                    " ,\n UnsatisfiedLinkErrorCount = ", UnsatisfiedLinkErrorCount,
                    " ,\n serviceRetryCount = ", serviceRetryCount,
                    " ,\n checkErrorCount = ", checkErrorCount,
                    " ,\n netErrorCount = ", netErrorCount,
                    " ,\n baiKeErrorCount = ", baiKeErrorCount,
                    " ,\n baiKeErrorCount1 = ", baiKeErrorCount1,
                    " ,\n uploadFailErrorCount = ", uploadFailErrorCount,
                    " ,\n noGo0dsErrorCount = ", noGo0dsErrorCount,
                    " ,\n NoClassDefFoundErrorCount = ", NoClassDefFoundErrorCount,
                    " ,\n ConcurrentModificationExceptionCount = ", ConcurrentModificationExceptionCount,
                    " ,\n OutOfMemoryErrorCount = ", OutOfMemoryErrorCount,
                    " ,\n noGo0dsErrorCount1 = ", noGo0dsErrorCount1,
                    " ,\n IndexOutOfBoundsExceptionCount = ", IndexOutOfBoundsExceptionCount,
                    " ,\n UnsupportedOperationExceptionCount = ", UnsupportedOperationExceptionCount,
                    " ,\n errInfoError2003Count = ", errInfoError2003Count,
                    " ,\n pictureUploadFailCount = ", pictureUploadFailCount,
                    " ,\n fileLengthZeroErrorCount = ", fileLengthZeroErrorCount,
                    " ,\n NullPointerExceptionCount = ", NullPointerExceptionCount,
                    " ,\n JSONExceptionCount = ", JSONExceptionCount,
                    " ,\n timeOutErrorCount = ", timeOutErrorCount,
                    " ,\n allRows.size() = ", allRows.size(),
                    " ,\n 剩余异常 = ", (allRows.size() - safeInitErrorCount - serviceErrorCount - UnsatisfiedLinkErrorCount
                            - serviceRetryCount - checkErrorCount - netErrorCount - baiKeErrorCount - uploadFailErrorCount
                            - baiKeErrorCount1 - noGo0dsErrorCount - noGo0dsErrorCount1 - ConcurrentModificationExceptionCount
                            - NullPointerExceptionCount - OutOfMemoryErrorCount - NoClassDefFoundErrorCount - JSONExceptionCount
                            - timeOutErrorCount - IndexOutOfBoundsExceptionCount - fileLengthZeroErrorCount - errInfoError2003Count
                            - UnsupportedOperationExceptionCount - pictureUploadFailCount)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(versionNameSet);
        StringBuilder builder = new StringBuilder();

        for (String s : countMap.keySet()) {
            builder.append(s).append("发生").append(countMap.get(s)).append("次").append(", ");
        }
//        System.out.println(builder.toString());

        LUtils.i("以下异常发生  ", infoNull2Count, "  次      ", builder.toString());
        LUtils.i("累计排查异常  ", other);
        return list;
    }

    private static void saveSpecialExceptionToFile(String[] result, String fileName) {
        if (result == null || result.length < 22) {
            LUtils.i("result is null or length is error");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder
//                .append(result[17])
//                .append("\n")
//                .append(result[19])
//                .append("\n")
                .append(result[21])
                .append("\n");
        try {
            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\" + fileName + ".txt"), builder.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static String infoNull1 = "com.hisense.smartimages.capture.models.CaptureModelA.getScreenCapture(CaptureModelA.java:76)";
    private static String infoNull2 = "com.hisense.smartimages.internal.f.a.a(TaoBaoManager.java:826)  at ";
    private static String infoNull3 = "com.hisense.smartimages.AIView.b$5.onKey(FullScreenShotWindow.java:871)  ";
    private static String infoNull4 = "com.hisense.smartimages.internal.Shopping.b.a(JuShoppingWindow.java:259)  at ";
    private static String infoNull5 = "com.hisense.smartimages.AIView.b$4.onKey(FullScreenShotWindow.java:871)  at ";
    private static String infoNull6 = "com.hisense.smartimages.internal.i$i.run(WorkAroundPool.java:2577)  at ";
    private static String infoNull7 = "com.hisense.smartimages.internal.g.n(SmartImageManager.java:510)  at";
    private static String infoNull8 = "on a null object reference  at com.hisense.smartimages.internal.i$i.run(WorkAroundPool.java:1419)  at ";
    private static String infoNull9 = "com.hisense.smartimages.internal.SmartImageManager.handleOnReportRecognitionResult(SmartImageManager" +
            ".java:1255)  at ";
    private static String infoNull10 = "com.hisense.smartimages.internal.g.d(SmartImageManager.java:433)  at ";
    private static String infoNull11 = "com.hisense.smartimages.internal.WorkAroundPool$FetchFullHiCloudDataTask.run(WorkAroundPool.java:1095)  at";
    private static String infoNull12 = "com.hisense.smartimages.internal.f.a.a(TaoBaoManager.java:821)  at";
    private static String infoNull13 = "com.hisense.smartimages.internal.WorkAroundPool$FetchChannelEPGTask.run(WorkAroundPool.java:2236) ";
    private static String infoNull14 = "com.hisense.smartimages.internal.i$h.run(WorkAroundPool.java:2482)";
    private static String infoNull15 = "com.hisense.smartimages.AIView.FullScreenShotWindow$4.onKey(FullScreenShotWindow.java:784)";
    private static String infoNull16 = "com.hisense.smartimages.AIView.b$5.onKey(FullScreenShotWindow.java:839) ";
    private static String infoNull17 = "com.hisense.smartimages.internal.WorkAroundPool$FetchBClassHiCloudDataTask.run(WorkAroundPool.java:1381)";
    private static String infoNull18 = "com.hisense.smartimages.internal.WorkAroundPool$FetchFullHiCloudDataTask.run(WorkAroundPool.java:1106)";
    private static String infoNull19 = "com.hisense.smartimages.capture.a.b(CaptureManager.java:186) ";
    private static String infoNull20 = "com.hisense.smartimages.AIView.FullScreenShotWindow$4.onKey(FullScreenShotWindow.java:773)";
    private static String infoNull21 = "on a null object reference  at com.hisense.smartimages.internal.i$x.run(WorkAroundPool.java:2804) ";
    private static String infoNull22 = "on a null object reference  at com.hisense.smartimages.internal.i$ab.run(WorkAroundPool.java:2804)";
    private static String infoNull23 = "";
    private static String infoNull24 = "";
    private static String infoNull25 = "";
    private static String infoNull26 = "";
    private static int infoNull2Count = 0;
    private static int other = 0;
    private static Set<String> versionNameSet = new HashSet<>();

    private static Map<String, Integer> countMap = new HashMap<>();

    //    private String  infoNull1="com.hisense.smartimages.capture.models.CaptureModelA.getScreenCapture(CaptureModelA.java:76)";
    private static void handleNullPointerException(String[] target) {
        if (target[21].contains(infoNull22)) {
            infoNull2Count++;
            versionNameSet.add(target[19]);
            countMap.merge(target[19], 1, Integer::sum);
            other++;
        } else if (target[21].contains(infoNull1)
                || target[21].contains(infoNull2)
                || target[21].contains(infoNull3)
                || target[21].contains(infoNull4)
                || target[21].contains(infoNull5)
                || target[21].contains(infoNull6)
                || target[21].contains(infoNull7)
                || target[21].contains(infoNull8)
                || target[21].contains(infoNull9)
                || target[21].contains(infoNull10)
                || target[21].contains(infoNull11)
                || target[21].contains(infoNull12)
                || target[21].contains(infoNull13)
                || target[21].contains(infoNull14)

                || target[21].contains(infoNull15)
                || target[21].contains(infoNull16)
                || target[21].contains(infoNull17)
                || target[21].contains(infoNull18)
                || target[21].contains(infoNull19)
                || target[21].contains(infoNull20)

                || target[21].contains(infoNull21)
//                || target[21].contains(infoNull22)
//                || target[21].contains(infoNull23)
//                || target[21].contains(infoNull24)
//                || target[21].contains(infoNull25)
//                || target[21].contains(infoNull26)
//                || target[21].contains(infoNull27)
//                || target[21].contains(infoNull28)
        ) {
            other++;

        } else {
            saveSpecialExceptionToFile(target, "空指针异常1");
        }
    }
}
