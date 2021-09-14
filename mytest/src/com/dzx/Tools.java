package com.dzx;

import com.dzx.bean.AnalyzeTimeConsumeBean;
import com.dzx.util.LUtils;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Tools {

    private static List<AnalyzeTimeConsumeBean> mAllResult = new ArrayList<>();


    public static void main(String[] args) {

        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\降层级后启动耗时.txt");
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\16版本启动耗时.txt");
        showFilterInfo(file);
        contrastData();
//        splitUrlParam();
//
//        Calendar calendar = Calendar.getInstance();
//        int month = calendar.get(Calendar.MONTH) + 1;
//        int day = calendar.get(Calendar.DATE) + 1;
//        LUtils.i("month = ", month, "day = ", day);
    }

    private static void contrastData() {
        //        splitUrlParam();
        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本冷启动.txt");
        File file2 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动01.txt");
        File file3 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动02.txt");
        File file4 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动03.txt");
        File file5 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动04.txt");
        File file6 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动05.txt");
        File file7 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动06.txt");
        File file8 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动07.txt");
        File file9 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动08.txt");
        File file10 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动09.txt");
        File file11 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本热启动10.txt");

        File file12 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化冷启动.txt");

        File file13 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化热启动01.txt");
        File file14 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化热启动02.txt");
        File file15 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化热启动03.txt");
        File file16 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化热启动04.txt");
        File file17 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化热启动05.txt");


        File file18 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化冷启动02.txt");
        File file19 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化冷启动03.txt");
        File file20 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化冷启动04.txt");
        File file21 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化冷启动05.txt");
        File file22 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15优化冷启动06.txt");
        File file23 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\小聚识图性能分析\\15版本冷启动01.txt");


//        showFilterInfo(file1);
//        showFilterInfo(file2);
//        showFilterInfo(file3);
//        showFilterInfo(file4);
//        showFilterInfo(file5);
//
//        showFilterInfo(file6);
//        showFilterInfo(file7);
//        showFilterInfo(file8);
//        showFilterInfo(file9);
//        showFilterInfo(file10);
//
//        showFilterInfo(file11);
//        showFilterInfo(file12);
//        showFilterInfo(file13);
//        showFilterInfo(file14);
//        showFilterInfo(file15);
//
//        showFilterInfo(file16);
//        showFilterInfo(file17);


//        showFilterInfo(file18);
//        showFilterInfo(file19);
//        showFilterInfo(file20);
//
//        showFilterInfo(file21);
//        showFilterInfo(file22);
//        showFilterInfo(file23);

        mAllResult.sort(new Comparator<AnalyzeTimeConsumeBean>() {
            @Override
            public int compare(AnalyzeTimeConsumeBean o1, AnalyzeTimeConsumeBean o2) {
                return (int) (o2.consumeTime - o1.consumeTime);
            }
        });


        Set<String> strings = new HashSet<>();
        HashMap<String, List<Integer>> map = new HashMap<>();
        int count = 0;
        List<String> order = new ArrayList<>();
        for (AnalyzeTimeConsumeBean analyzeTimeConsumeBean : mAllResult) {
            String classMethod = analyzeTimeConsumeBean.className + analyzeTimeConsumeBean.methodName;
            if (strings.contains(classMethod)) {
                map.get(classMethod).add(count);
            } else {
                strings.add(classMethod);
                order.add(classMethod);
                List<Integer> list = new ArrayList<>();
                list.add(count);
                map.put(classMethod, list);
            }
            count++;
        }


        for (String s : order) {
            List<Integer> list = map.get(s);
            for (Integer integer : list) {
                AnalyzeTimeConsumeBean bean = mAllResult.get(integer);
                if (bean.isMainThread
                        && bean.consumeTime > 50
                ) {
                    LUtils.i(bean.className, "    ", bean.methodName, "     ",
                            bean.consumeTime, "    ", bean.fileName);

                }
            }
        }
    }

    private static void showFilterInfo(File file) {
//        LUtils.i("\n\n\n" + file.getAbsolutePath() + "\n\n\n");
        parseMethodConsume(file);
    }


    /**
     * 分离HTTP请求参数
     */
    private static void splitUrlParam() {
        //专题  contentType
//        String url="  http://vipcloud-launcher.hismarttv.com/vipcloud/specialfavorite/download?appVersionName=2020.5.0.0.13.0&deviceId=86100300000101000000071218006808&languageId=0&appVersionCode=2000000000&mac=00%3A63%3A18%3A00%3A68%3A08&accessToken=1ayD8BAFA4vJuJOwq-7M7PLemt-KKosaGaOl2Nm10iZovxRWz_j7vahUJtqhpRWy1Q7cInevo505E7RX3pZzCjPO2jljEX73eYDFPzhY_5IB6-xfGdv30uqFerWw-CR-mQda0VGe6zZ0uRHcFc1HvTHsfxv5tOVD7vFds-vVXdIlqU0AjrWwdgD5imMQ70LbYVcDjzAs1v&serialNo=c495585c5a121d9f8e1368f29591f9f2&license=1015&sourceType=1&appPackageName=com.jamdeo.tv.vod&timeStamp=1607594333&commonRandomId=cd5a7fe0-d8be-40f1-8116-e084a869a939&appVersion=01.102.136&productCode=HIKID&customerId=32822477&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&version=2020.5.0.0.13.0&subscriberId=203627496&otaVersion=0001AI0930&deviceExt=MSD648\n";
        //绘本  contentType=21002
//        String url="http://vipcloud-launcher.hismarttv.com/vipcloud/favorite/download?appVersionName=2020.5.0.0.13.0&deviceId=86100300000101000000071218006808&languageId=0&appVersionCode=2000000000&mac=00%3A63%3A18%3A00%3A68%3A08&accessToken=1ayD8BAFA4vJuJOwq-7M7PLemt-KKosaGaOl2Nm10iZovxRWz_j7vahUJtqhpRWy1Q7cInevo505E7RX3pZzCjPO2jljEX73eYDFPzhY_5IB6-xfGdv30uqFerWw-CR-mQda0VGe6zZ0uRHcFc1HvTHsfxv5tOVD7vFds-vVXdIlqU0AjrWwdgD5imMQ70LbYVcDjzAs1v&contentType=21002&serialNo=c495585c5a121d9f8e1368f29591f9f2&license=1015&sourceType=1&appPackageName=com.jamdeo.tv.vod&timeStamp=1607594393&commonRandomId=2734f330-d55b-4f48-a3a6-0638e6fcea8b&appVersion=01.102.136&productCode=HIKID&customerId=32822477&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&version=2020.5.0.0.13.0&subscriberId=203627496&otaVersion=0001AI0930&deviceExt=MSD648\n";
        //听学  contentType=19006
//        String url="http://vipcloud-launcher.hismarttv.com/vipcloud/favorite/download?appVersionName=2020.5.0.0.13.0&deviceId=86100300000101000000071218006808&languageId=0&appVersionCode=2000000000&mac=00%3A63%3A18%3A00%3A68%3A08&accessToken=1ayD8BAFA4vJuJOwq-7M7PLemt-KKosaGaOl2Nm10iZovxRWz_j7vahUJtqhpRWy1Q7cInevo505E7RX3pZzCjPO2jljEX73eYDFPzhY_5IB6-xfGdv30uqFerWw-CR-mQda0VGe6zZ0uRHcFc1HvTHsfxv5tOVD7vFds-vVXdIlqU0AjrWwdgD5imMQ70LbYVcDjzAs1v&contentType=19006&serialNo=c495585c5a121d9f8e1368f29591f9f2&license=1015&sourceType=1&appPackageName=com.jamdeo.tv.vod&timeStamp=1607594483&commonRandomId=e33875f5-ca5f-4385-8616-fb0a40df77a3&appVersion=01.102.136&productCode=HIKID&customerId=32822477&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&version=2020.5.0.0.13.0&subscriberId=203627496&otaVersion=0001AI0930&deviceExt=MSD648\n";
        //课程 contentType=2001,1001
//        String url = "http://vipcloud-launcher.hismarttv.com/vipcloud/favorite/download?appVersionName=2020.5.0.0.13.0&deviceId=86100300000101000000071218006808&languageId=0&appVersionCode=2000000000&mac=00%3A63%3A18%3A00%3A68%3A08&accessToken=1ayD8BAFA4vJuJOwq-7M7PLemt-KKosaGaOl2Nm10iZovxRWz_j7vahUJtqhpRWy1Q7cInevo505E7RX3pZzCjPO2jljEX73eYDFPzhY_5IB6-xfGdv30uqFerWw-CR-mQda0VGe6zZ0uRHcFc1HvTHsfxv5tOVD7vFds-vVXdIlqU0AjrWwdgD5imMQ70LbYVcDjzAs1v&contentType=2001%2C1001&serialNo=c495585c5a121d9f8e1368f29591f9f2&license=1015&sourceType=1&appPackageName=com.jamdeo.tv.vod&timeStamp=1607594562&commonRandomId=10a639d0-ed43-43ec-b94b-edce95397203&appVersion=01.102.136&productCode=HIKID&customerId=32822477&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&version=2020.5.0.0.13.0&subscriberId=203627496&otaVersion=0001AI0930&deviceExt=MSD648\n";
        String url = "http://recommender-launcher.hismarttv.com/api/v1.1.0/recommendApi/relatedRecommendation?appVersion=01.102.105&otaVersion=0001AK0813&venderId=0&operatorCode=Hisense&deviceId=861003000001003000000615f14d96c2&appVersionCode=1500009249&mac=64%3Aae%3Af1%3A4d%3A96%3Ac2&sceneCode=vodRelatedActivity&commonRandomId=8201c23b-f8b9-4e11-863d-1c41915a1d6d&appType=1&customerId=62845054&id=11039123101&areaGroup=0&deviceType=1&softwareType=1&languageId=0&format=1&subscriberId=224047693&deviceExt=HZ55U8E&accessToken=1aheoKAP8dVySd9OSuMeY5al1f9TdIoSboarviiOHplnXkIOcl0k5yYEa0JWWAUh7VMgZVFmy3SvmJmmXbVzpGKIodtt2Y-d0WWC9-oO4zlimgOC_dS6RABIhiZKLZN5pqyb31vjyOgqVnvPkUgFUDfdf2ADCIkE3fVbpUun7YCfWHf2ViCpZqYnDH4nLoctz-VK_ArEWe&appVersionName=2020.6.2.20.85.0&version=1.0&timeStamp=1630495108&license=1007&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%221%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%221%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&productCode=1&sourceType=1&appPackageName=com.jamdeo.tv.vod&category=0";
        String[] result = url.split("[?]");
        String[] result1 = result[1].split("&");
        for (String s : result1) {
            LUtils.i(s.replaceAll("=", "      "));
        }

    }


    /**
     * 解析方法耗时
     */
    public static void parseMethodConsume(File file) {
        String fileName = file.getName();

        Gson gson = new Gson();
        try {
            List<String> list = FileUtils.readLines(file, StandardCharsets.UTF_8);
            LUtils.i("日志文件总行数 = ", list.size());
            List<AnalyzeTimeConsumeBean> result = new ArrayList<>();
            for (String s : list) {
                if (!TextUtils.isEmpty(s)) {
                    String[] strings = s.split("##");
                    if (strings.length == 2) {
                        try {
                            AnalyzeTimeConsumeBean bean = gson.fromJson(strings[1], AnalyzeTimeConsumeBean.class);
                            bean.fileName = fileName;
                            result.add(bean);
                        } catch (Exception e) {

                        }

                    }
                }
            }
            LUtils.i("筛选出的结果数 = ", result.size());
//            result.sort(new Comparator<AnalyzeTimeConsumeBean>() {
//                @Override
//                public int compare(AnalyzeTimeConsumeBean o1, AnalyzeTimeConsumeBean o2) {
//                    return (int) (o2.consumeTime - o1.consumeTime);
//                }
//            });

            mAllResult.addAll(result);

//            for (AnalyzeTimeConsumeBean s : result) {
//                if (s.consumeTime > 20) {
//                    LUtils.i(s.className, "    ", s.methodName, "     ", s.consumeTime, "    ", s.isMainThread);
//                }
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
