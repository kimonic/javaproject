package com.dzx;

import com.dzx.util.LUtils;
import javafx.scene.paint.Color;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test6 {
    public static void main(String[] args) {

        // 相似度从0到1
//        System.out.println(getColorSemblance(Color.rgb(0, 0, 0), Color.rgb(255, 255, 255)));
//        System.out.println(getColorSemblance(Color.rgb(255, 255, 255), Color.rgb(200, 200, 200)));
//        System.out.println(getColorSemblance(Color.rgb(255, 255, 255), Color.rgb(255, 255, 255)));
//        testFilter();
//        showStringLength("{\"header\":{\"messageId\":\"fd0f20bf-b4cb-407d-8845-c31d3856fb1a\",\"payloadVersion\":\"1\"},\"payload\":{\"status\":\"SUCCESS\",\"partner\":[{\"id\":\"3000\",\"name\":\"OPPLE\",\"introduction\":\"欧普照明: 专注光的价值，感受光的魅力\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/opple2.jpg\",\"manufacturer\":\"欧普照明股份有限公司，作为中国照明行业标杆的整体照明解决方案提供者，不仅致力于研究光的合理运用，提供贴心产品，还为消费者提供差异化整体照明解决方案等专业的配套服务，全面提升用户体验。针对不同场合，欧普提供的照明方案能满足人在不同时间、不同空间生理需求和心理需求的灯光。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/opple2.jpg\"},{\"id\":\"3004\",\"name\":\"tuya\",\"introduction\":\"涂鸦成立于2014年6月,致力于智能产品APP开发,智能化APP开发以及产品智能化解决方案，是一个全球化智能平台，在AI与大数据驱动下，独创IOT平台服务模式，连接消费者、制造品牌、OEM厂商的智能化需求，为客户提供一站式人工智能物联网的解决方案。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/tuya_small.jpg\",\"manufacturer\":\"涂鸦智能：全球化智能家居家电照明安防全屋智能升级平台\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/tuya_small.jpg\"},{\"id\":\"3008\",\"name\":\"小京鱼\",\"introduction\":\"小京鱼：为您提供简单,快乐的智能生活体验!\",\"smallIcon\":\"http://download.hismarttv.com/epgdata/hiaiot/jd_logo_small.png\",\"manufacturer\":\"2018年12月4日，京东推出IoT新品牌京鱼座，并发布智能助手小京鱼。 小京鱼智能平台整合了原有的京东Alpha平台，并引入了京东的人工智能与大数据能力，不仅聚焦原有的智能硬件、智能家居、智慧出行方案，更把其物联网能力拓展至更多场景。\",\"status\":\"unlinked\",\"small_icon\":\"http://download.hismarttv.com/epgdata/hiaiot/jd_logo_small.png\"},{\"id\":\"3013\",\"name\":\"Aqara\",\"introduction\":\"绿米科技（Aqara）：致力于构建以智能设备、大数据和增值服务为核心的智慧生活服务平台。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/aqara_small.jpg\",\"manufacturer\":\"拥有超550项专利的行业内知名品牌，Aqara将其在超低功耗无线传感、Zigbee、AI、大数据等技术领域的深厚积累融入到领先的全屋智能解决方案，产品广泛应用于家居、酒店、地产、办公、农业、养老等垂直领域，并覆盖192个国家、超9000个城市，服务全球超200万用户。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/aqara_small.jpg\"},{\"id\":\"3014\",\"name\":\"美的美居\",\"introduction\":\"美的美居是美的智慧生活统一用户入口，支持美的集团旗下所有品牌的智能家电和智能设备。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/midea_small.jpg\",\"manufacturer\":\"美的IoT为消费者带来了全新的数字化的智能生活体验，关注不同用户群体需求，结合大数据、AI、云服务技术，集成手机、语音等能力，打造了安全的家、健康的家、个性的家，为用户提供安全、健康、便捷、愉悦的智慧生活服务。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/midea_small.jpg\"},{\"id\":\"3015\",\"name\":\"石头智能\",\"introduction\":\"石头科技：用创新简化生活\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/roborock_small.png\",\"manufacturer\":\"石头科技（roborock，全称北京石头世纪科技股份有限公司）成立于2014年7月，是一家专注于家用智能清洁机器人及其他智能电器研发和生产的公司。公司旗下产品有石头扫地机器人、米家扫地机器人、米家手持吸尘器、小瓦扫地机器人。在全球激光导航类扫地机器人领域，石头科技出品的产品占据大部分市场份额。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/roborock_small.png\"}]}}\n");
//        showStringLength("AbsRequestBean: |url = https://iot-sys.hismarttv.com/1.0/iot/partners/list, content = {\"header\":{\"messageId\":\"b6017ab0-6061-458c-a8d2-f068ca21f570\",\"payloadVersion\":\"1\"},\"payload\":{\"status\":\"SUCCESS\",\"partner\":[{\"id\":\"3000\",\"name\":\"OPPLE\",\"introduction\":\"欧普照明: 专注光的价值，感受光的魅力\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/opple2.jpg\",\"manufacturer\":\"欧普照明股份有限公司，作为中国照明行业标杆的整体照明解决方案提供者，不仅致力于研究光的合理运用，提供贴心产品，还为消费者提供差异化整体照明解决方案等专业的配套服务，全面提升用户体验。针对不同场合，欧普提供的照明方案能满足人在不同时间、不同空间生理需求和心理需求的灯光。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/opple2.jpg\"},{\"id\":\"3004\",\"name\":\"涂鸦\",\"introduction\":\"涂鸦智能：全球化智能家居家电照明安防全屋智能升级平台。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/tuya_small.jpg\",\"manufacturer\":\"涂鸦成立于2014年6月,致力于智能产品APP开发,智能化APP开发以及产品智能化解决方案，是一个全球化智能平台，在AI与大数据驱动下，独创IOT平台服务模式，连接消费者、制造品牌、OEM厂商的智能化需求，为客户提供一站式人工智能物联网的解决方案。\",\"status\":\"linked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/tuya_small.jpg\"},{\"id\":\"3013\",\"name\":\"Aqara\",\"introduction\":\"绿米科技（Aqara）：致力于构建以智能设备、大数据和增值服务为核心的智慧生活服务平台。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/aqara_small.jpg\",\"manufacturer\":\"拥有超550项专利的行业内知名品牌，Aqara将其在超低功耗无线传感、Zigbee、AI、大数据等技术领域的深厚积累融入到领先的全屋智能解决方案，产品广泛应用于家居、酒店、地产、办公、农业、养老等垂直领域，并覆盖192个国家、超9000个城市，服务全球超200万用户。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/aqara_small.jpg\"},{\"id\":\"3014\",\"name\":\"美的美居\",\"introduction\":\"美的美居是美的智慧生活统一用户入口，支持美的集团旗下所有品牌的智能家电和智能设备。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/midea_small.jpg\",\"manufacturer\":\"美的IoT为消费者带来了全新的数字化的智能生活体验，关注不同用户群体需求，结合大数据、AI、云服务技术，集成手机、语音等能力，打造了安全的家、健康的家、个性的家，为用户提供安全、健康、便捷、愉悦的智慧生活服务。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/midea_small.jpg\"},{\"id\":\"3015\",\"name\":\"石头智能\",\"introduction\":\"石头科技：用创新简化生活\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/roborock_small.png\",\"manufacturer\":\"石头科技（roborock，全称北京石头世纪科技股份有限公司）成立于2014年7月，是一家专注于家用智能清洁机器人及其他智能电器研发和生产的公司。公司旗下产品有石头扫地机器人、米家扫地机器人、米家手持吸尘器、小瓦扫地机器人。在全球激光导航类扫地机器人领域，石头科技出品的产品占据大部分市场份额。\",\"status\":\"linked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/roborock_small.png\"}]}}\n");
//        splitUrlParam("http://home-launcher.hismarttv.com/api/mainPage?area=%25E5%25B1%25B1%25E4%25B8%259C-%25E9%259D%2592%25E5%25B2%259B&deviceType=0&appVersion=01.102.121&otaVersion=0001AL0329&languageId=0&subscriberId=216527432&deviceExt=55S7&model_id=0&accessToken=1aG2kBALssSBX_m_bGBD3Ua3M0Ym3Y5NLmPFK6n9rpFbgO_MdiPaT2c5U5qLlBpUVFh7y00kPF1puDnognw62VJ2KOVnlCGxAaLwehsq1_Kpf2ywjb7Dfs1THdkKAP3f4TF0A5APJA01W7iUir-giIwZCI9Ux1iFc1iD6hlXocOlgYtmBfzsinKUHUq7uBFTMX4kKGDo-n&appVersionName=2021.5.0.0.13.0&appVersionCode=2000000999&deviceId=8610030000010070000007124f770f98&mac=ca%3A2c%3A4f%3A77%3A0f%3A98&license=1015&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%222%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%222%22%7D%5D&commonRandomId=607a6596-1364-463b-a284-2600079b9d1f&customerId=32822477&appPackageName=com.jamdeo.tv.vod&logParams=%7B%22eventcode%22%3A%22200000%22%2C%22productcode%22%3A%228%22%2C%22ip%22%3A%22-1%22%2C%22requesttime%22%3A%221618301051833%22%2C%22subscriberid%22%3A%22216527432%22%2C%22eventPos%22%3A%22000%22%2C%22appversioncode%22%3A%222000000999%22%2C%22apiversion%22%3A%2201.102.121%22%2C%22devicemsg%22%3A%2255S7%22%2C%22sessionid%22%3A%22db9c42f56569402e92aa3ef83f98d0d5%22%2C%22eventType%22%3A%22200%22%2C%22deviceid%22%3A%228610030000010070000007124f770f98%22%2C%22version%22%3A%223.0%22%2C%22appversionname%22%3A%222021.5.0.0.13.0%22%2C%22license%22%3A%22wasu%22%2C%22customerid%22%3A%2232822477%22%2C%22logstamp%22%3A%2262%22%7D&timestamp=0");
//        splitUrlParam("http://layout-launcher.hismarttv.com/api/v1.1.0/layoutApi/secondParams?appVersion=01.102.141&otaVersion=&timeStampMS=1618465785244&languageId=0&subscriberId=0&deviceExt=55S7&accessToken=&appVersionName=5.0.0.13.0&deviceId=8610030000010070000007124f770f98&appVersionCode=2000000000&mac=ca%3A2c%3A4f%3A77%3A0f%3A98&timeStamp=1618465785&sceneCodeFV=WIDGET_JUMP_JUUI_FV%2CWIDGET_JUMP_SCREEN_SAVER_FV%2CWIDGET_JUMP_SYS_TV_SETTING_FV%2CWIDGET_JUMP_SYS_MEDIA_CENTER_FV%2CWIDGET_JUMP_SYS_PRIVATE_CLOUD_FV%2CWIDGET_JUMP_SYS_SIGNAL_SOURCE_FV%2CWIDGET_JUMP_SYS_AIOT_FV%2CWIDGET_JUMP_SYS_VIDAA_ASSISTANT_FV%2CWIDGET_JUMP_SYS_TUSOU_FV%2CWIDGET_JUMP_SYS_NFC_FV%2CWIDGET_JUMP_SYS_CAMERA_REMINDER_FV%2CWIDGET_JUMP_VOICE_ASSISTANT_FV%2CWIDGET_JUMP_SOCIALTV_VEDIO_CALL_FV%2CVEDIO_MEETING%2CWIDGET_JUMP_SOCIALTV_PHOTO_FV%2CWIDGET_JUMP_SOCIALTV_MIRROR_FV%2CMESSAGE_BOARD%2CAI_POPRIOCEPTIVE%2CWIDGET_JUMP_APP_HI_PORJECTION_FV%2CWIDGET_JUMP_APP_HI_MUSIC_FV%2CWIDGET_JUMP_APP_INTELLIGENT_BOARD_FV%2CWIDGET_JUMP_APP_CHILD_PANEL_FV&license=1011&productCode=21&sceneCode=8003108%2Cchild_common_new%2CHOME_THEATER_SLOGAN&customerId=5989126&appPackageName=com.hisense.widget.launcher");
//        splitUrlParam("");
//        splitUrlParam(" http://search-launcher.hismarttv.com/searchApi/mamSearch/hotword?appVersion=tusou.01.100&otaVersion=6.0&languageId=0&pageSize=10&index=0&subscriberId=216717686&deviceExt=&appVersionName=3.03.00.032.0&accessToken=1a0ZIDAMBRq5HW-TWK8BO-xTkzh17VUS_r80NxpcEw4GOBBy0bTtRppKV4WDJjVlpVn_QVfttzFRzXfQLO35NlHN_DxLjFG6Uk_72D_HRJzAK-cd7HLUm5_P2M9KdHOc1Y316mmHoTfZoV9pFK8MRyfvC5wd9AZq4iErIntFX0bYiOJV2io21YwWNB3mUzN2jntRXUo40P&deviceId=8610030000010060000007184f770f98&appVersionCode=303000320&mac=16%3Ac7%3A53%3Ad7%3Af2%3A7c&keyWord=&license=1015&sequence=1622100351244&sceneCode=TUSOURecNEW&appType=10&appPackageName=com.hisense.smartimages&customerId=32822477&inputType=1&page=1&resourceType=1\n");
//        String s=" {        \"startupType\":4,        \"startupUrl\":[{               \"key\":\"startupType\",            \"value\":1,              \"type\":\"int\"    },{             \"key\":\"packageName\",          \"value\":\"com.jamdeo.tv.vod\",            \"type\":\"String\"         },{\"key\":\"orientation\",\"value\":2,\"type\":int},{                \"key\":\"superAppParam\",          \"value\":{                       \"albumType\":\"22\",    \"typeCode\":\"8001\",                       \"tab\":{                                 \"index\":2,                           \"id\":530,                                \"name\":\"漫画\",                          \"type\":38,                           \"tabModifiedTime\":1610434200,                            \"bgColor\":\"\",                           \"bgPic\":\"https://testjhk-cdn-mampic.hismarttv.com/epgdata/mamPic/10/109/202003300306394022.png\",                              \"sideBarVisible\":1,                           \"hasSideBar\":1,                                 \"naviHeaderColour\":\"#212121\",        \"typeName\":\"spintv\"                      }               },              \"type\":\"String\"         }] }";
//        System.out.println(s.replaceAll("\n","").replaceAll(" ",""));


        checkFile();

//        splitUrlParam("http://search-launcher.hismarttv.com/searchApi/mamSearch/hotsearch?appVersion=01.102.139&modelId=0&otaVersion=0001JL0510&pageSize=30&isSuperApp=1&source=1&appVersionCode=2000000000&deviceId=86100300000100600000071820304050&mac=00%3A10%3A20%3A30%3A40%3A50&wechatVersionCode=308002005&appType=1&commonRandomId=17c21e18-8e5b-4e6c-be0f-d6c3fea99e40&customerId=1550000048&contentType=80042&languageId=0&index=0&subscriberId=1550073782&sessionId=48f6d46136e441a9a609ef0ab5c6ecec&deviceExt=55E3F&srcPackageName=com.jamdeo.tv.vod&accessToken=19g_YIAHnzK2lzRcpUmRfZ39LwY2tYUjXI25JTxBlTL-TveTE-7Un4SzgKPTQ3m7euGlMTnIxP-JJWCE4_y-eD_XFEmQNYQoiSanjs9le1CD4Fnrp31sGXnFAbZ-KTxiYeZFIT-8kzc8kQMJkLG0WP4npKCBARKhl8wzNGayFTWgkc2OiMMRRvOskTWXRbeJ6Z_wmROiPB&appVersionName=2021.5.0.0.13.0&keyWord=%E8%87%AA%E5%8A%A8%E5%8C%96%E6%B5%8B%E8%AF%95%E6%90%9C%E7%B4%A2&sequence=1622545128649&license=1015&vipRight=%5B%7B%22rightProduct%22%3A%22vod%22%2C%22rightValue%22%3A%221%22%7D%2C%7B%22rightProduct%22%3A%22edu%22%2C%22rightValue%22%3A%221%22%7D%2C%7B%22rightProduct%22%3A%22educ%22%2C%22rightValue%22%3A%221%22%7D%5D&appPackageName=com.jamdeo.tv.vod&page=1&searchVersion=1.4");
//        copyApkToUsb();
    }

    private static void generateDimens() {
        String target = "<dimen name=\"custom_10px\">5dp</dimen>";
        for (int i = 0; i < 1921; i++) {
            System.out.println("<dimen name=\"custom_" + i + "px\">" + i / 1.5f + "dp</dimen>");
        }
    }

    private static void copyApkToUsb() {
        String fileName = "dev-s3-ck-sp_010.apk";
//
        try {
//            FileUtils.copyFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\widget调试04091149\\" + fileName),
            FileUtils.copyFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\dev-s3-ck-sp_010\\" + fileName),
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
        String[] result = url.split("&");
        for (String s : result) {
            LUtils.i(s);
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
