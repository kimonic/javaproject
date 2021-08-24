package com.dzx;

import com.dzx.bean.AnalyzeTimeConsumeBean;
import com.dzx.util.LUtils;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tools {

    public static void main(String[] args) {
//        splitUrlParam();
        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\冷启动方法耗时分析.txt");
        File file2 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\热启动方法耗时.txt");

        parseMethodConsume(file1);
        LUtils.i("\n\n\n\n\n\n");

        parseMethodConsume(file2);


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
        String url = " http://layout-launcher.hismarttv.com/api/activityResources?accessToken=09jdoMAAKSe5kge3ygC1d4Gnzvu6vV5Y9EFWVRS9Nslw2dw6hzsCymmFeIRCH4N2fCUTcdr_qc6jL9sv8Vr4u_H3TIbyaJ-vpMgwtV9ivhmU1A9erKt6trBiqS5sxUhUqbW21i75-BH-TbDRfATEg9SXZ7loxHY-tVPlwxVF26eh7mteBy_2l1QebA82KWAzph8n3nY4UT&appPackageName=com.hisense.smartimages&appVersion=tusou.01.100&appVersionCode=304000150&appVersionName=3.04.00.015.0&customerId=1515786421&deviceId=8610030090000030000007120c9fe306&languageId=0&license=1015&mac=7a:b3:7b:95:f8:e0&otaVersion=6.6&resourceType=1&sceneCode=TUSOURecNEW\n";
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


        Gson gson = new Gson();
        try {
            List<String> list = FileUtils.readLines(file);
            List<AnalyzeTimeConsumeBean> result = new ArrayList<>();
            for (String s : list) {
                if (!TextUtils.isEmpty(s)) {
                    String[] strings = s.split("##");
                    if (strings.length == 2) {
                        AnalyzeTimeConsumeBean bean = gson.fromJson(strings[1], AnalyzeTimeConsumeBean.class);
                        result.add(bean);
                    }
                }
            }
            result.sort(new Comparator<AnalyzeTimeConsumeBean>() {
                @Override
                public int compare(AnalyzeTimeConsumeBean o1, AnalyzeTimeConsumeBean o2) {
                    return (int) (o2.consumeTime-o1.consumeTime);
                }
            });

            for (AnalyzeTimeConsumeBean s : result) {
                if (s.isMainThread) {
                    LUtils.i(s.className, "    ",s.methodName,"     ",s.consumeTime);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
