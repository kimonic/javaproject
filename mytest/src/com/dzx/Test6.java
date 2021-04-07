package com.dzx;

import javafx.scene.paint.Color;

import java.util.*;

public class Test6 {
    public static void main(String[] args) {
        // 相似度从0到1
//        System.out.println(getColorSemblance(Color.rgb(0, 0, 0), Color.rgb(255, 255, 255)));
//        System.out.println(getColorSemblance(Color.rgb(255, 255, 255), Color.rgb(200, 200, 200)));
//        System.out.println(getColorSemblance(Color.rgb(255, 255, 255), Color.rgb(255, 255, 255)));
//        testFilter();
//        showStringLength("{\"header\":{\"messageId\":\"fd0f20bf-b4cb-407d-8845-c31d3856fb1a\",\"payloadVersion\":\"1\"},\"payload\":{\"status\":\"SUCCESS\",\"partner\":[{\"id\":\"3000\",\"name\":\"OPPLE\",\"introduction\":\"欧普照明: 专注光的价值，感受光的魅力\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/opple2.jpg\",\"manufacturer\":\"欧普照明股份有限公司，作为中国照明行业标杆的整体照明解决方案提供者，不仅致力于研究光的合理运用，提供贴心产品，还为消费者提供差异化整体照明解决方案等专业的配套服务，全面提升用户体验。针对不同场合，欧普提供的照明方案能满足人在不同时间、不同空间生理需求和心理需求的灯光。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/opple2.jpg\"},{\"id\":\"3004\",\"name\":\"tuya\",\"introduction\":\"涂鸦成立于2014年6月,致力于智能产品APP开发,智能化APP开发以及产品智能化解决方案，是一个全球化智能平台，在AI与大数据驱动下，独创IOT平台服务模式，连接消费者、制造品牌、OEM厂商的智能化需求，为客户提供一站式人工智能物联网的解决方案。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/tuya_small.jpg\",\"manufacturer\":\"涂鸦智能：全球化智能家居家电照明安防全屋智能升级平台\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/tuya_small.jpg\"},{\"id\":\"3008\",\"name\":\"小京鱼\",\"introduction\":\"小京鱼：为您提供简单,快乐的智能生活体验!\",\"smallIcon\":\"http://download.hismarttv.com/epgdata/hiaiot/jd_logo_small.png\",\"manufacturer\":\"2018年12月4日，京东推出IoT新品牌京鱼座，并发布智能助手小京鱼。 小京鱼智能平台整合了原有的京东Alpha平台，并引入了京东的人工智能与大数据能力，不仅聚焦原有的智能硬件、智能家居、智慧出行方案，更把其物联网能力拓展至更多场景。\",\"status\":\"unlinked\",\"small_icon\":\"http://download.hismarttv.com/epgdata/hiaiot/jd_logo_small.png\"},{\"id\":\"3013\",\"name\":\"Aqara\",\"introduction\":\"绿米科技（Aqara）：致力于构建以智能设备、大数据和增值服务为核心的智慧生活服务平台。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/aqara_small.jpg\",\"manufacturer\":\"拥有超550项专利的行业内知名品牌，Aqara将其在超低功耗无线传感、Zigbee、AI、大数据等技术领域的深厚积累融入到领先的全屋智能解决方案，产品广泛应用于家居、酒店、地产、办公、农业、养老等垂直领域，并覆盖192个国家、超9000个城市，服务全球超200万用户。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/aqara_small.jpg\"},{\"id\":\"3014\",\"name\":\"美的美居\",\"introduction\":\"美的美居是美的智慧生活统一用户入口，支持美的集团旗下所有品牌的智能家电和智能设备。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/midea_small.jpg\",\"manufacturer\":\"美的IoT为消费者带来了全新的数字化的智能生活体验，关注不同用户群体需求，结合大数据、AI、云服务技术，集成手机、语音等能力，打造了安全的家、健康的家、个性的家，为用户提供安全、健康、便捷、愉悦的智慧生活服务。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/midea_small.jpg\"},{\"id\":\"3015\",\"name\":\"石头智能\",\"introduction\":\"石头科技：用创新简化生活\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/roborock_small.png\",\"manufacturer\":\"石头科技（roborock，全称北京石头世纪科技股份有限公司）成立于2014年7月，是一家专注于家用智能清洁机器人及其他智能电器研发和生产的公司。公司旗下产品有石头扫地机器人、米家扫地机器人、米家手持吸尘器、小瓦扫地机器人。在全球激光导航类扫地机器人领域，石头科技出品的产品占据大部分市场份额。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/roborock_small.png\"}]}}\n");
//        showStringLength("AbsRequestBean: |url = https://iot-sys.hismarttv.com/1.0/iot/partners/list, content = {\"header\":{\"messageId\":\"b6017ab0-6061-458c-a8d2-f068ca21f570\",\"payloadVersion\":\"1\"},\"payload\":{\"status\":\"SUCCESS\",\"partner\":[{\"id\":\"3000\",\"name\":\"OPPLE\",\"introduction\":\"欧普照明: 专注光的价值，感受光的魅力\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/opple2.jpg\",\"manufacturer\":\"欧普照明股份有限公司，作为中国照明行业标杆的整体照明解决方案提供者，不仅致力于研究光的合理运用，提供贴心产品，还为消费者提供差异化整体照明解决方案等专业的配套服务，全面提升用户体验。针对不同场合，欧普提供的照明方案能满足人在不同时间、不同空间生理需求和心理需求的灯光。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/opple2.jpg\"},{\"id\":\"3004\",\"name\":\"涂鸦\",\"introduction\":\"涂鸦智能：全球化智能家居家电照明安防全屋智能升级平台。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/tuya_small.jpg\",\"manufacturer\":\"涂鸦成立于2014年6月,致力于智能产品APP开发,智能化APP开发以及产品智能化解决方案，是一个全球化智能平台，在AI与大数据驱动下，独创IOT平台服务模式，连接消费者、制造品牌、OEM厂商的智能化需求，为客户提供一站式人工智能物联网的解决方案。\",\"status\":\"linked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/tuya_small.jpg\"},{\"id\":\"3013\",\"name\":\"Aqara\",\"introduction\":\"绿米科技（Aqara）：致力于构建以智能设备、大数据和增值服务为核心的智慧生活服务平台。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/aqara_small.jpg\",\"manufacturer\":\"拥有超550项专利的行业内知名品牌，Aqara将其在超低功耗无线传感、Zigbee、AI、大数据等技术领域的深厚积累融入到领先的全屋智能解决方案，产品广泛应用于家居、酒店、地产、办公、农业、养老等垂直领域，并覆盖192个国家、超9000个城市，服务全球超200万用户。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/aqara_small.jpg\"},{\"id\":\"3014\",\"name\":\"美的美居\",\"introduction\":\"美的美居是美的智慧生活统一用户入口，支持美的集团旗下所有品牌的智能家电和智能设备。\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/midea_small.jpg\",\"manufacturer\":\"美的IoT为消费者带来了全新的数字化的智能生活体验，关注不同用户群体需求，结合大数据、AI、云服务技术，集成手机、语音等能力，打造了安全的家、健康的家、个性的家，为用户提供安全、健康、便捷、愉悦的智慧生活服务。\",\"status\":\"unlinked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/midea_small.jpg\"},{\"id\":\"3015\",\"name\":\"石头智能\",\"introduction\":\"石头科技：用创新简化生活\",\"smallIcon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/roborock_small.png\",\"manufacturer\":\"石头科技（roborock，全称北京石头世纪科技股份有限公司）成立于2014年7月，是一家专注于家用智能清洁机器人及其他智能电器研发和生产的公司。公司旗下产品有石头扫地机器人、米家扫地机器人、米家手持吸尘器、小瓦扫地机器人。在全球激光导航类扫地机器人领域，石头科技出品的产品占据大部分市场份额。\",\"status\":\"linked\",\"small_icon\":\"https://img-download-iot.hismarttv.com/epgdata/hiaiot/roborock_small.png\"}]}}\n");
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
