package com.dzx.util;

import com.dzx.bean.ExceptionResultEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

public class TsvParseUtil {

    public static void main(String[] args) {
        String parseFileName = "\\data_2021-09-14 09_48_50 AM.tsv";
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE) - 1;
        //文件路径,必改
        String targetFilePath = "C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\" + "0" + month + day + parseFileName;
        //异常去重后保存的文件名,必改
        String exceptionFileName = "0" + month + day + "异常信息";
//        firstRunThisMethodToParse(parseFileName, 15);


        //logfilename  0,      linenum  1,      createddate  2,      logfiletime  3,      version  4,      logtime  5,
        // timecode  6,      hourcode  7,      mincode  8,      datecode  9,      weekcode  10,      moncode  11,
        // yearcode  12,      ip  13,      province  14,      city  15,      areaid  16,      featurecode  17,
        // issysrepo  18,      appversionname  19,      sessionid  20,      etrace  21,      logstamp  22,
        // subscriberid  23,      ename  24,      devicemsg  25,      eventpos  26,      eventtype  27,      eventcode  28,
        // deviceid  29,      customerid  30,      appversioncode  31,      oip  32,      time  33,      partitiondate  34

//        //----------------------------------------------------------------------------------------------------------------
//
//
//        //解析获取所有的tsv 每行的信息
//        List<String[]> list = getAllTsvRows(targetFilePath);
//        //移除首行标签
//        list.remove(0);
//
//
//        //tsv 总行数
//        LUtils.i("小聚识图" + month + "月" + day + "日, HiSmartImage_3.04.00.015.0版本累计上报异常 ", list.size(), "次");
//
//        //根据eventCode过滤出对应的tsv行,000001--崩溃异常,000002--淘宝百科等异常
//        List<String[]> eventCodeList = filterWithEventCode(list, "000001");
//        LUtils.i("其中运行崩溃异常EventCode  000001 累计发生" + eventCodeList.size() + "  次,分布如下");
//        //筛选异常类型输出异常发生次数
//        filterWithENameAll(eventCodeList);
//
//        //UnsatisfiedLinkError异常每个设备的输出次数
//        filterWithENameSpecial(eventCodeList, "UnsatisfiedLinkError");
//
//
//        //根据eventCode过滤出对应的tsv行,000001--崩溃异常,000002--淘宝百科等异常
//        List<String[]> eventCodeList2 = filterWithEventCode(list, "000002");
//        LUtils.i("EventCode  000002 异常累计发生" + eventCodeList2.size() + "  次,分布如下");
//        //筛选异常类型输出异常发生次数
//        filterWithENameAll(eventCodeList2);
//
//        //获取每种异常信息发生的次数
//        filterWithErrorInfo(eventCodeList, 21);
//
//
//        //异常去重并保存到文件
//        filterAndSaveErrorMessage(eventCodeList, exceptionFileName);
//
//        //----------------------------------------------------------------------------------------------------------------

        //读取文件中的异常总数
//        getExceptionNum("0830前全部异常");
        //筛选新增异常,保存所有异常
        parseNewlyException("0912前全部异常", exceptionFileName, "0913前全部异常");
         

        //过滤指定设备发生了多少次异常
//        List<String[]> specialDevice = filterWithDeviceId(eventCodeList, 29, "8610030000010110000007120cd3170b");
//        filterWithErrorInfo(specialDevice, 21);
//        List<String[]> specialDevice1 = filterWithDeviceId(eventCodeList, 29, "86100300000105000000071200d238fb");
//        filterWithErrorInfo(specialDevice1, 21);
//        List<String[]> specialDevice1 = filterWithDeviceId(eventCodeList, 29, "86100300000104700000071200e74126");
//        filterWithErrorInfo(specialDevice1, 21);

    }

    private static void firstRunThisMethodToParse(String parseFileName, int sotFlag) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE) - 1;
        //文件路径,必改
        String targetFilePath = "C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\" + "0" + month + day + parseFileName;
        //异常去重后保存的文件名,必改
        String exceptionFileName = "0" + month + day + "异常信息";
        //排序flag,必改

        //设备出现异常日期,必改
        String appearDate = "(0" + month + day + "日新增)";

        //首先单独执行该方法,然后注释掉,重新生成设备id发生UnsatisfiedLinkError异常时的对应列表
        execute(targetFilePath, appearDate, sotFlag);
    }

    private static List<String[]> getCsvList() {
        return null;
    }


    private static void filterAndSaveErrorMessage(List<String[]> list, String fileName) {
        HashSet set = new HashSet();
        for (String[] strings : list) {
            try {
                set.add(strings[21]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String content = new Gson().toJson(set);
        try {
            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析", fileName), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getExceptionNum(String fileName) {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析", fileName);

        try {
            List<String> list = new Gson().fromJson(FileUtils.readFileToString(file), new TypeToken<List<String>>() {
            }.getType());
            LUtils.i("文件中异常次数 = ", list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void parseNewlyException(String fileNameOld, String fileNameNew, String saveFileName) {
        File fileOld = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析", fileNameOld);
        File fileNew = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析", fileNameNew);

        try {
            List<String> listOld = new Gson().fromJson(FileUtils.readFileToString(fileOld), new TypeToken<List<String>>() {
            }.getType());
            List<String> listNew = new Gson().fromJson(FileUtils.readFileToString(fileNew), new TypeToken<List<String>>() {
            }.getType());

            LUtils.i("老文件异常数 = ", listOld.size(), ",新文件异常数 = ", listNew.size());

            List<String> newlyList = new ArrayList<>();
            for (String s : listNew) {
                if (!listOld.contains(s)) {
                    newlyList.add(s);
                }
            }
            LUtils.i("差异异常数 = ", newlyList.size());
            for (String s : newlyList) {
                LUtils.i(s + "\n\n");
            }
            listOld.addAll(newlyList);
            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析", saveFileName), new Gson().toJson(listOld));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 29 deviceId
     */
    private static List<String[]> filterWithDeviceId(List<String[]> list, int deviceIdPosition, String specialDeviceId) {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, List<String[]>> deviceMap = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        for (String[] strings : list) {
            try {
                String deviceid = strings[deviceIdPosition];
                if (set.contains(deviceid)) {
                    map.put(deviceid, map.get(deviceid) + 1);
                    deviceMap.get(deviceid).add(strings);
                } else {
                    List<String[]> list1 = new ArrayList<>();
                    list1.add(strings);
                    deviceMap.put(deviceid, list1);
                    map.put(deviceid, 1);
                    set.add(deviceid);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String s : map.keySet()) {
            LUtils.i("设备 ", s, " 异常发生了  " + map.get(s) + "  次\n");
        }

        return deviceMap.get(specialDeviceId);
    }


    /**
     * 21  异常信息
     */
    private static List<String[]> filterWithErrorInfo(List<String[]> list, int eTracePosition) {
        List<String[]> list1 = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, List<String[]>> errorInfoMap = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        for (String[] strings : list) {
            try {
                String eMessage = strings[eTracePosition];
                if (set.contains(eMessage)) {
                    map.put(eMessage, map.get(eMessage) + 1);
                    errorInfoMap.get(eMessage).add(strings);
                } else {
                    List<String[]> list2 = new ArrayList<>();
                    list2.add(strings);
                    errorInfoMap.put(eMessage, list2);
                    map.put(eMessage, 1);
                    set.add(eMessage);
                    list1.add(strings);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        for (String s : map.keySet()) {
//            LUtils.i("以下异常发生了  " + map.get(s) + "  次\n" + s + "\n\n\n");
//        }
        for (String s : errorInfoMap.keySet()) {
            List<String[]> list2 = errorInfoMap.get(s);
            HashSet<String> deviceIdGroup = new HashSet<>();
            for (String[] s1 : list2) {
                deviceIdGroup.add(s1[29]);
            }
            LUtils.i("以下异常发生了  " + list2.size() + "  次", " 发生在设备", deviceIdGroup, "上\n", s, "\n\n\n");

        }

        return list1;
    }


    private static List<String[]> filterWithEventCode(List<String[]> list, String eventCode) {
        List<String[]> list1 = new ArrayList<>();
        HashSet<String> eventCodeSet = new HashSet<>();
        for (String[] strings : list) {
            try {
                eventCodeSet.add(strings[28]);
                if (eventCode.equals(strings[28])) {
                    list1.add(strings);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LUtils.i("包含的eventCode 类型   ", eventCodeSet);
        return list1;
    }

    private static List<String[]> filterWithEName(List<String[]> list, String eName) {
        List<String[]> list1 = new ArrayList<>();
        HashSet<String> eventNameSet = new HashSet<>();
        for (String[] strings : list) {
            try {
                eventNameSet.add(strings[24]);
                if (eName.equals(strings[24])) {
                    list1.add(strings);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LUtils.i("包含的eName 类型   ", eventNameSet);
        return list1;
    }


    private static List<String[]> filterWithENameAll(List<String[]> list) {
        List<String[]> list1 = new ArrayList<>();
        HashSet<String> eventNameSet = new HashSet<>();
        HashMap<String, List<String[]>> eNameMap = new HashMap<>();

        for (String[] strings : list) {
            try {
                String eNameCurrent = strings[24];
                if (eventNameSet.contains(eNameCurrent)) {
                    eNameMap.get(eNameCurrent).add(strings);
                } else {
                    eventNameSet.add(eNameCurrent);
                    List<String[]> list2 = new ArrayList<>();
                    list2.add(strings);
                    eNameMap.put(eNameCurrent, list2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LUtils.i("包含的eName 类型   ", eventNameSet);


        for (String s : eNameMap.keySet()) {
            LUtils.i(s, "  类型异常发生了 ", eNameMap.get(s).size(), "  次!");
        }
        return list1;
    }


    private static List<String[]> filterWithENameSpecial(List<String[]> list, String specialEName) {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\UnsatisfiedLinkError报错设备记录");
        List<UnsatisfiedLinkErrorRecordEntity> unsatisfiedLinkErrorRecordEntities = null;
        try {
            unsatisfiedLinkErrorRecordEntities = new Gson().fromJson(FileUtils.readFileToString(file), new TypeToken<List<UnsatisfiedLinkErrorRecordEntity>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, UnsatisfiedLinkErrorRecordEntity> map = new HashMap<>();

        if (unsatisfiedLinkErrorRecordEntities != null) {
            for (UnsatisfiedLinkErrorRecordEntity entity : unsatisfiedLinkErrorRecordEntities) {
                map.put(entity.deviceId, entity);
            }
        }

        List<String[]> list1 = new ArrayList<>();

        for (String[] strings : list) {
            try {
                String eNameCurrent = strings[24];
                if (specialEName.equals(eNameCurrent)) {
                    list1.add(strings);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LUtils.i("\n\n", specialEName, "  异常类型发生了  ", list1.size(), "  次!");

        HashSet<String> eventNameSet = new HashSet<>();
        HashMap<String, List<String[]>> eNameMap = new HashMap<>();

        for (String[] s : list1) {
            try {

                String eNameCurrent = s[29];
                if (eventNameSet.contains(eNameCurrent)) {
                    eNameMap.get(eNameCurrent).add(s);
                } else {
                    eventNameSet.add(eNameCurrent);
                    List<String[]> list2 = new ArrayList<>();
                    list2.add(s);
                    eNameMap.put(eNameCurrent, list2);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        List<UnsatisfiedLinkErrorOutEntity> linkErrorOutEntities = new ArrayList<>();
        for (String s : eNameMap.keySet()) {
            try {
                UnsatisfiedLinkErrorOutEntity entity = new UnsatisfiedLinkErrorOutEntity();
                entity.appearDate = map.get(s).appearDate;

                entity.sortFlg = map.get(s).sortFlag;
                entity.deviceId = s;
                entity.count = eNameMap.get(s).size();
                entity.specialEName = specialEName;
                linkErrorOutEntities.add(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        linkErrorOutEntities.sort(new Comparator<UnsatisfiedLinkErrorOutEntity>() {
            @Override
            public int compare(UnsatisfiedLinkErrorOutEntity o1, UnsatisfiedLinkErrorOutEntity o2) {
                return o1.sortFlg - o2.sortFlg;
            }
        });

        for (UnsatisfiedLinkErrorOutEntity entity : linkErrorOutEntities) {
            LUtils.i(entity.deviceId, entity.appearDate, "累计发生异常", entity.count,
                    "次(", entity.specialEName, "高频异常设备,主要异常为so异常)");

        }

        LUtils.i("以上单列高频异常设备均为so相关异常,累计so相关异常", list1.size(), "次 (UnsatisfiedLinkError)\n\n\n");
        return list1;
    }


    private static List<String[]> getAllTsvRows(String filePath) {
        List<String[]> allRows = new ArrayList<>();
        try {
            TsvParserSettings settings = new TsvParserSettings();
            settings.setMaxCharsPerColumn(20000);
            settings.getFormat().setLineSeparator("\n");
            TsvParser parser = new TsvParser(settings);
            DataInputStream in = new DataInputStream(new FileInputStream(new File(filePath)));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));//这里如果csv文件编码格式是utf-8,改成utf-8即可
            allRows = parser.parseAll(br);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRows;

    }


    private static void parseFile() {
        try {
            // 创建tsv解析器settings配置对象
            TsvParserSettings settings = new TsvParserSettings();
            settings.setMaxCharsPerColumn(20000);
            settings.getFormat().setLineSeparator("\n");
            TsvParser parser = new TsvParser(settings);
            DataInputStream in = new DataInputStream(new FileInputStream(new File("C:\\Users\\dingzhixin.ex\\Desktop\\data_2021-08-26 10_06_36 AM.tsv")));
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
            versionSet.merge(strings[17] + "***" + strings[19], 1, Integer::sum);
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

    private static void execute(String filePath, String appearDate, int sortFlag) {
//        record("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\0825\\data_2021-08-26 10_06_36 AM.tsv", "(25日新增)", 1);
//        record("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\0826\\data_2021-08-27 09_25_58 AM.tsv", "(26日新增)", 2);
//        record("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\0827\\data_2021-08-30 09_20_45 AM.tsv", "(27日新增)", 3);
//        record("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\0830\\data_2021-08-31 10_16_46 AM.tsv", "(30日新增)", 4);
//        record("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\0831\\data_2021-09-01 09_22_25 AM.tsv", "(31日新增)", 5);
        record(filePath, appearDate, sortFlag);
    }


    private static void record(String filePath, String appearData, int sortFlag) {

        List<String[]> list = getAllTsvRows(filePath);
        //移除首行标签
        list.remove(0);
//        //根据eventCode过滤出对应的tsv行,000001--崩溃异常,000002--淘宝百科等异常
        List<String[]> eventCodeList = filterWithEventCode(list, "000001");
        saveUnsatisfiedLinkErrorData(filterWithENameSpecial(eventCodeList, "UnsatisfiedLinkError"), appearData, sortFlag);

    }

    private static void saveUnsatisfiedLinkErrorData(List<String[]> list, String appearData, int sortFlag) {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\UnsatisfiedLinkError报错设备记录");
        List<UnsatisfiedLinkErrorRecordEntity> unsatisfiedLinkErrorRecordEntities = null;
        try {
            unsatisfiedLinkErrorRecordEntities = new Gson().fromJson(FileUtils.readFileToString(file), new TypeToken<List<UnsatisfiedLinkErrorRecordEntity>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (unsatisfiedLinkErrorRecordEntities != null) {
            LUtils.i("原纪录设备数量为 ", unsatisfiedLinkErrorRecordEntities.size());
        } else {
            LUtils.i("读取记录文件异常");
        }

        List<String> conditionList = new ArrayList<>();
        if (unsatisfiedLinkErrorRecordEntities != null) {
            for (UnsatisfiedLinkErrorRecordEntity entity : unsatisfiedLinkErrorRecordEntities) {
                if (!TextUtils.isEmpty(entity.deviceId)) {
                    conditionList.add(entity.deviceId);
                }
            }
        }


        HashSet<String> deviceIds = new HashSet<>();

        for (String[] strings : list) {
            try {
                if (conditionList.contains(strings[29])) {

                } else {
                    deviceIds.add(strings[29]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LUtils.i("相较记录文件中新增设备id为   ", deviceIds);

        List<UnsatisfiedLinkErrorRecordEntity> result = new ArrayList<>();

        for (String s : deviceIds) {
            UnsatisfiedLinkErrorRecordEntity UnsatisfiedLinkErrorRecordEntity = new UnsatisfiedLinkErrorRecordEntity();
            UnsatisfiedLinkErrorRecordEntity.deviceId = s;
            UnsatisfiedLinkErrorRecordEntity.appearDate = appearData;
            UnsatisfiedLinkErrorRecordEntity.sortFlag = sortFlag;
            result.add(UnsatisfiedLinkErrorRecordEntity);
        }

        LUtils.i("新增设备生成数据  ", new Gson().toJson(result));

        if (result.size() > 0) {
            if (unsatisfiedLinkErrorRecordEntities == null) {
                unsatisfiedLinkErrorRecordEntities = new ArrayList<>();
            }

            unsatisfiedLinkErrorRecordEntities.addAll(result);
            File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图异常分析\\UnsatisfiedLinkError报错设备记录");
            try {
                String content = new Gson().toJson(unsatisfiedLinkErrorRecordEntities);
                FileUtils.write(file1, content);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            LUtils.i("UnsatisfiedLinkError  没有新增异常设备");
        }


    }

    public static class UnsatisfiedLinkErrorRecordEntity {
        public String deviceId;
        public String appearDate;
        public int sortFlag;
    }

    public static class UnsatisfiedLinkErrorOutEntity {
        public String deviceId;
        public int sortFlg;
        public String appearDate;
        public int count;
        public String specialEName;

    }
}

