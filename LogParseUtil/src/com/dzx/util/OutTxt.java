package com.dzx.util;

import com.dzx.watch.dir.DirWatcher;
import com.dzx.watch.dir.WatcherResultHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.parser.HTMLParserListener;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.util.List;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class OutTxt {


    public static void main(String args[]) throws IOException {
        //过滤异常及下载数据
//        List<String> list = new ArrayList<>(Arrays.asList("HXJ","DemoCrashHandler","RequestBean"));
        //过滤是否已经配置下载连接
//        List<String> list = new ArrayList<>(Arrays.asList("getAppUpgradeInfoByPackageName"));

//        List<String> list = new ArrayList<>(Arrays.asList("android.widget.ImageView.","android.view.View.","android.graphics"));
//        List<String> list = new ArrayList<>(Arrays.asList("anr ","ANR ","HXJ","HomeActivity","am_"));
//        List<String> list = new ArrayList<>(Arrays.asList("打开香薰机 ","关闭香薰机 ","onReceive, deviceStatusChanged","HomeActivity: |HXJ showStatus",
//                "getHomeId"));
//        List<String> list = new ArrayList<>(Arrays.asList("IotHomeCardViewStrategy","main"));
//        List<String> list = new ArrayList<>(Arrays.asList("java.lang.","java.util","android."));
//        List<String> list = new ArrayList<>(Arrays.asList("ActivityManager: START u0", "HXJ"));
//        List<String> list = new ArrayList<>(Arrays.asList("HisensePhoneWindowHelper", "HXJ"));
//        List<String> list = new ArrayList<>(Arrays.asList("FATAL","AiotCrashHandler","Exception"));
//        根据条件过滤内容
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\cb67339a059e9f40b6061b933e6ec3ba\\log.txt", list);
        //过滤异常统计信息
//        List<String> list = new ArrayList<>(Arrays.asList("8610030000010100000007120c9c6a3b"));
//        List<String> list = new ArrayList<>(Arrays.asList("speech_CoreService: sort:chat;title","HXJ onReceive, deviceStatusChanged, msgContent"));
//        List<String> list = new ArrayList<>(Arrays.asList("VLauncherPresenter","MainPageNetworkModule","NetworkAccess","AbstractCacheHandler"));
//        List<String> list = new ArrayList<>(Arrays.asList("getAppUpgradeInfoByPackageName", "AppLoadingActivity"));
//        List<String> list1 = new ArrayList<>(Arrays.asList("SurfaceFlinger","LBStateManager2","com.hisense.base.activity.AppLoadingActivity"));
//        List<String> list = new ArrayList<>(Arrays.asList("FamilyCircleTwoTwoWidget: | WLFC  doJump ", "WidgetTools: getIntentFromJumpUrl: jumpUrl"));
//        List<String> list = new ArrayList<>(Arrays.asList("ProgramActionRouter: ProgramActionRouter",
//                "MVPBaseActivity","ThirdJumpUtils","ThirdPartnerActivity));
//        List<String> list1 = new ArrayList<>(Arrays.asList("SurfaceFlinger","LBStateManager2","com.hisense.base.activity.AppLoadingActivity"));
//        List<String> list = new ArrayList<>(Arrays.asList("ThirdJumpUtils","ThirdPartnerActivity","START u0","VContentDetailActivity"));
//        List<String> list = new ArrayList<>(Arrays.asList("SmartImageApplication: mExceptionHandler"));
//        List<String> list1=new ArrayList<>(Arrays.asList("Activity@","dispatchTouchEvent","AppSwitchRequest",
//                "BufferQueueProducer","MVPBaseActivity","HisensePhoneWindowHelper"));
//       List<String> list1=new ArrayList<>(Arrays.asList(
//                "BufferQueueProducer","ViewUtils","AccountLogicModuleHelpUtil","LBStateManager2","MVPBaseActivity","I am"));
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\0519bug\\log.txt.4", list);
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\0519bug\\log.txt.3", list);
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\0519bug\\log.txt.2", list);
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\log.txt", list);
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\log.txt.1", list,list1);
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\log.txt", list,list1);

//        List<String> list = new ArrayList<>(Arrays.asList("ActivityManager: Process com.hisense.smartimages", "lowmemorykiller: Killing 'nse" +
//                ".smartimages'", "SmartImageApplication XJST| : application create","JuBao KeyEvent is START","showCaptureTest capture set end",
//                "Sending non-protected broadcast com.jamdeo.tv.START_JU_BAO"));
//        List<String> list = new ArrayList<>(Arrays.asList("setContentList", "requestHotWords"));
//        List<String> list = new ArrayList<>(Arrays.asList("checkCondition()", "FetchPresentationHiCloudDataTask()","HiCloudTokenManager"
//        ,"SmartImageApplication","MSG_FETCH_PRESENTATION_DATA","isDataArrivedAndValidate","PresentationData","notifyPresentationHiCloudDataData",
//                "PresentationActivity"));
        //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
//        List<String> list = new ArrayList<>(Arrays.asList("XJST|isVideoMedia()", "XJST|start new thread to exec","JuBao KeyEvent",
//                "duration of video record","Failed to execute"));
////        List<String> list = new ArrayList<>(Arrays.asList("lowmemorykiller: Kill 'com.hisense.smartimages'", "onReceive(), JuBao KeyEvent"));
//        List<String> list=new ArrayList<>(Arrays.asList("showToastDialog: show success","JuBao KeyEvent is START.  showCaptureTest",
//                "showToastDialog  reset  mDialogLayout null","showToastDialog layout init end"));
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\A65E10：07bug\\log.txt", list);

//        String filePath = "C:\\Users\\dingzhixin.ex\\Desktop\\TV日志，2021年09月24日14时01分26秒开始记录.log";
//        String filePath = "C:\\Users\\dingzhixin.ex\\Desktop\\A55截图，出现弹窗图片不显示，显示黑屏画面 14：33.log";
//        String filePath = "C:\\Users\\dingzhixin.ex\\Desktop\\TV日志，2021年09月24日14时48分35秒开始记录.log";
//        String filePath = "C:\\Users\\dingzhixin.ex\\Desktop\\TV日志，2021年09月24日15时42分46秒开始记录.log";

        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\monkey");
        File[] files = file1.listFiles();

//        String filePath = "C:\\Users\\dingzhixin.ex\\Desktop\\";
        List<String> list = new ArrayList<>(Arrays.asList("ANR","XJST"));
//        List<String> list = new ArrayList<>(Arrays.asList("lowmemorykiller: Killing 'nse.smartimages", "onReceive(), JuBao KeyEvent"));
        for (File file11 : files) {
            LUtils.i(file11.getAbsolutePath(),"\n\n\n");
            filerFileAndOut(file11.getAbsolutePath(), list);

        }
//        System.out.println("===========================================间隔1=================================================================");
//        filerFileAndOut(filePath + "log.txt.1", list);
//        System.out.println("===========================================间隔2=================================================================");
//        filerFileAndOut(filePath + "log.txt.2", list);
//        System.out.println("===========================================间隔3=================================================================");
//        filerFileAndOut(filePath + "log.txt.3", list);
//        System.out.println("===========================================间隔4=================================================================");
//        filerFileAndOut(filePath + "log.txt.4", list);
//        System.out.println("===========================================间隔1=================================================================");
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\log.txt.1", list);
//        System.out.println("===========================================间隔2=================================================================");
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\log.txt.2", list);
//        System.out.println("===========================================间隔3=================================================================");
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\log.txt.3", list);
//        System.out.println("===========================================间隔4=================================================================");
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\log.txt.4", list);
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\A65E截图，焦点到搜索上，截图右侧展示内容识别.log", list);
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\A65E截图，首页cardbar页面出现闪退现象.log", list);


        //分析内存泄漏
//        analysisMemInfo();

//        monitor("C:\\Users\\dingzhixin.ex\\Desktop\\1.txt", 500);

//        Utils.runtimeCommand("arp -a 192.168.137.172");
//        Utils.runtimeCommand("nbtstat -a  192.168.137.172");
//        handleJavaFile();
//        encryption();
//        File loadFile = new File("C:\\Users\\dingzhixin.ex\\Desktop\\00001.txt");
//        File encryptionFile = new File("C:\\Users\\dingzhixin.ex\\Desktop\\乱码001.txt");
//        File decryptFile = new File("C:\\Users\\dingzhixin.ex\\Desktop\\乱码002.txt");
//        File convergence = new File("C:\\Users\\dingzhixin.ex\\Desktop\\乱码003.txt");
//        encryption(200,loadFile,encryptionFile);
//        decrypt(200, encryptionFile, decryptFile);
//        interval( encryptionFile, decryptFile);
//        convergence(decryptFile,convergence);

//        Document document = Jsoup.connect("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=Android%20%20%20&rsv_spt=1&oq=Android&rsv_pq=ab2a70100027a7f2&rsv_t=f1cayg5tN5WI1FODPZv%2FHTUcr0DvR8TlNhypj9FUM74dtsnn%2BVqI3b%2FojNajfQhEbVxt&rqlang=cn&rsv_enter=0&rsv_dl=tb&rsv_sug3=4&rsv_sug1=4&rsv_sug7=100&rsv_btype=t&inputT=1639&rsv_sug4=10793").get();
//        System.out.println(document.body());
//        parseHtml();

//        parseLayout();
//        parseRunMethod();
//        splitUrlParam();
//        testRobot();
//        textCompare();
//        renameFile("E:\\work\\resource\\aiot资料\\慧享家\\Aiot 12月份资料\\12.31风扇和空气净化器\\12.31风扇和空气净化器\\03 Res\\空气净化器\\序列帧",
//                "aircleaner_anim_");
//        outFilenameAppend("C:\\Users\\dingzhixin.ex\\Desktop\\验证温度\\风扇");
//        renameImg("E:\\work\\resource\\aiot资料\\慧享家\\Aiot 12月份资料\\12.31风扇和空气净化器\\12.31风扇和空气净化器\\03 Res\\空气净化器\\序列帧");

    }

    /**
     * 解析运行稳定率文件
     * datecode	errorname	exceptionmessage	randomid	featurecode	deviceid	versionCode	versionName	_col8	times
     */
    private static void parseRunText() throws IOException {
        List<String> list1 = FileUtils.readLines(new File("C:\\Users\\dingzhixin.ex\\Desktop\\data_2021-05-08 09_14_18 " +
                "AM.tsv"));
        Set<String> eorror = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        for (String s : list1) {
//            System.out.println(s);
            StringBuilder builder = new StringBuilder();
            String[] strings = s.split("\t");

            System.out.println(strings[1] + "\t\t" + strings[4] + "\t\t" + strings[6]);
            if (eorror.contains(strings[1])) {
                map.put(strings[1], map.get(strings[1]) + 1);
            } else {
                eorror.add(strings[1]);
                map.put(strings[1], 1);
                map2.put(strings[1], strings[4] + "  " + strings[6]);
                map1.put(strings[1], s);
            }


//            for (int i = 0; i < strings.length; i++) {
//                if (i == 2||i==3||i==8||i==9) {
////                    try {
////                        System.out.println(strings[i].indexOf("at"));
////                        builder.append(strings[i].substring(0,strings[i].indexOf("at"))).append("\t");
////
////                    } catch (Exception e) {
////                        builder.append(strings[i]).append("\t");
////
////                    }
//                }else {
//                    builder.append(strings[i]).append("\t\t\t\t\t\t\t");
//                }
//
//            }
//            System.out.println(builder.toString());

//            for (String s1 : strings) {
//
//                System.out.println(s1);
//            }
//            System.out.println(strings.length);
        }

        for (String s : map.keySet()) {
            System.out.println(s + "     " + map.get(s));
        }
//        System.out.println(list1.size() + "行");
//        System.out.println(eorror.size() + "行");
    }


    private static void renameImg(String filePath) {
        File file = new File(filePath);
        String[] files = file.list();
        int length = files.length;
        for (int i = 0; i < length; i++) {
            String result = files[i].replaceAll("air_on_", "").replaceAll(".png", "");
            System.out.println(result);
            if (result.length() == 4) {
                result = "0" + result;
            } else if (result.length() == 3) {
                result = "00" + result;
            } else if (result.length() == 2) {
                result = "000" + result;
            } else {

            }
            File file1 = new File(filePath, files[i]);
            File file2 = new File(filePath, "air_on_" + result + ".png");
            System.out.println(files[i] + "  =======  " + file1.renameTo(file2));


        }

    }

    private static void textCompare() {
        String s1 = "{\"startupType\":4,\"startupUrl\":[{\"key\":\"startupType\",\"value\":1,\"type\":\"int\"},{\"key\":\"packageName\",\"value\":\"com.jamdeo.tv.vod\",\"type\":\"String\"},{\"key\":\"className\",\"value\":\"com.jamdeo.tv.vod.detail.ContentDetailActivity\",\"type\":\"String\"},{\"key\":\"uri\",\"value\":\"content://com.jamdeo.data.vod/content_detail?source=JAMDEO_CLOUD&programSeriesId=%s\",\"type\":\"String\"},{\"key\":\"com.jamdeo.UiInterpreter.EXTRA_DETAIL_VIEW_TRIGGER\",\"value\":0,\"type\":\"int\"},{\"key\":\"srcPackageName\",\"value\":\"%s\",\"type\":\"String\"},{\"key\":\"realClass\",\"value\":\"com.jamdeo.tv.vod.detail.ContentDetailActivity\",\"type\":\"String\"},{\"key\":\"typecode\",\"value\":1002,\"type\":\"int\"},{\"key\":\"superAppParam\",\"value\":{\"productCode\":\"8\",\"id\":\"6567\",\"name\":\"\",\"typeCode\":\"8001\",\"subTypeCode\":\"\",\"packageVipId\":\"\",\"albumType\":\"2\",\"navigationId\":\"\"},\"type\":\"String\"}]}";
        String s2 = "{\"startupType\":4,\"startupUrl\":[{\"key\":\"startupType\",\"value\":1,\"type\":\"int\"},{\"key\":\"packageName\",\"value\":\"com.jamdeo.tv.vod\",\"type\":\"String\"},{\"key\":\"className\",\"value\":\"com.jamdeo.tv.vod.detail.ContentDetailActivity\",\"type\":\"String\"},{\"key\":\"uri\",\"value\":\"content://com.jamdeo.data.vod/content_detail?source=JAMDEO_CLOUD&programSeriesId=%s\",\"type\":\"String\"},{\"key\":\"com.jamdeo.UiInterpreter.EXTRA_DETAIL_VIEW_TRIGGER\",\"value\":0,\"type\":\"int\"},{\"key\":\"srcPackageName\",\"value\":\"%s\",\"type\":\"String\"},{\"key\":\"realClass\",\"value\":\"com.jamdeo.tv.vod.detail.ContentDetailActivity\",\"type\":\"String\"},{\"key\":\"typecode\",\"value\":1002,\"type\":\"int\"},{\"key\":\"superAppParam\",\"value\":{\"productCode\":\"8\",\"id\":\"4844\",\"name\":\"\",\"typeCode\":\"8001\",\"subTypeCode\":\"\",\"packageVipId\":\"\",\"albumType\":\"2\",\"navigationId\":\"\"},\"type\":\"String\"}]}";
        LUtils.i("s1.length = ", s1.length(), " , s2.length = ", s2.length());
        int size = Math.min(s1.length(), s2.length());
        for (int i = 0; i < size; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                LUtils.i(s1.charAt(i), " === ", s2.charAt(i));
                LUtils.i("i = ", i);
            }
        }

        LUtils.i(s1.substring(650, 750));
        LUtils.i(s2.substring(650, 750));


    }

    private static void testRobot() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(1800, 1000);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解析Android studio 抓取的方法运行文件
     */
    private static void parseRunMethod() throws IOException {
        List<String> list = FileUtils.readLines(new File("C:\\Users\\dingzhixin.ex\\Desktop\\runmethod.txt"));
        int count = 0;
        for (String s : list) {
            if (s.contains("main") && s.contains("ju.")) {
                count++;
                System.out.println(s);
            }
        }

        System.out.println("总行数  =  " + count);
    }

    /**
     * 解析adb dumpsys activity top输出内容
     */
    private static void parseLayout() throws IOException {
        List<String> list = FileUtils.readLines(new File("C:\\Users\\dingzhixin.ex\\Desktop\\superbaby.txt"));
        String start = "View Hierarchy";
        List<DataParseBean> result = new ArrayList<>();
        boolean isStart = false;
        int count = 0;
        for (String string : list) {
            if (string.contains(start)) {
                isStart = true;
                continue;
            }
            count++;
            if (isStart && !TextUtils.isEmpty(string)) {
                result.add(new DataParseBean(getSpaceCount(string), count, string));
                System.out.println(getSpaceCount(string) + "  =  " + string);

            }
        }
//        int first = 0;
//        int second = 0;
//        Map<Integer, List<DataParseBean>> map = new TreeMap<>();
//        int size = result.size();
//
//
//        for (int i = 0; i < size; i++) {
//            DataParseBean dataParseBean = result.get(i);
//            if (dataParseBean.spaceCount == 0) {
//                continue;
//            }
//            if (map.get(dataParseBean.spaceCount) == null) {
//                List<DataParseBean> list1 = new ArrayList<>();
//                list1.add(dataParseBean);
//                map.put(dataParseBean.spaceCount, list1);
//            } else {
//                map.get(dataParseBean.spaceCount).add(dataParseBean);
//            }
//        }
//        for (Integer integer : map.keySet()) {
//            List<DataParseBean> list1 = map.get(integer);
//            for (DataParseBean dataParseBean : list1) {
//                System.out.println(dataParseBean.position + "  =  " + dataParseBean.spaceCount + "  =  " + dataParseBean.content);
//            }
//        }
    }

    public static class DataParseBean {//2708-3004  2830-3006  2854-2920
        public int spaceCount;
        public int position;
        public String content;

        public DataParseBean(int spaceCount, String content) {
            this.spaceCount = spaceCount;
            this.content = content;
        }

        public DataParseBean(int spaceCount, int position, String content) {
            this.spaceCount = spaceCount;
            this.position = position;
            this.content = content;
        }
    }

    private static int getSpaceCount(String s) {
        if (TextUtils.isEmpty(s)) {
            return 0;
        }
        String temp = s + "A";
        int start = temp.length();
        int end = temp.trim().length();


        return start - end;
    }

    public static void parseHtml() throws IOException {
        //Htmlunit模拟的浏览器，设置css,js等支持及其它的一些简单设置
        WebClient browser = new WebClient();
        browser.getOptions().setCssEnabled(false);
        browser.getOptions().setJavaScriptEnabled(true);
        browser.getOptions().setThrowExceptionOnScriptError(false);

        //获取页面
        HtmlPage htmlPage = browser.getPage("https://fanyi.baidu.com/?aldtype=16047#en/zh/top");
        //设置等待js的加载时间
        browser.waitForBackgroundJavaScript(30000);
        browser.setHTMLParserListener(new HTMLParserListener() {
            @Override
            public void error(String s, URL url, String s1, int i, int i1, String s2) {

            }

            @Override
            public void warning(String s, URL url, String s1, int i, int i1, String s2) {

            }
        });
        //使用xml的方式解析获取到jsoup的document对象
        Document doc = Jsoup.parse(htmlPage.asXml());
        System.out.println(doc.body());

    }

    private static void interval(File loadFile, File saveFile) throws IOException {
        String content = FileUtil.getFileContent(loadFile);
        StringBuilder builder = new StringBuilder();
        char[] chars = content.toCharArray();
        int length = chars.length;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(chars[i]).append((char) (random.nextInt('z') % ('z' - 'a' + 1) + 'a'));
        }
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
        FileUtil.outFileContent(saveFile, builder.toString());
    }

    private static void convergence(File loadFile, File saveFile) throws IOException {
        String content = FileUtil.getFileContent(loadFile);
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        char[] chars = content.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                builder1.append(chars[i]);
            } else {
                builder2.append(chars[i]);
            }
        }
        System.out.println(builder1.toString());
        System.out.println(builder2.toString());
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
        FileUtil.outFileContent(saveFile, builder1.toString());
    }

    private static void decrypt(int subLength, File loadFile, File saveFile) throws IOException {
        String content = FileUtil.getFileContent(loadFile);
        int size = content.length();
        int surplus = size % subLength;
        int count;
        if (surplus == 0) {
            count = size / subLength;
        } else {
            count = size / subLength + 1;
        }
        StringBuilder builder = new StringBuilder();

        int position = 0;
        List<List<String>> lists = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < subLength; i++) {
            if (i < surplus - 1) {
                if (position + count < size) {
                    list.add(content.substring(position, position + count));
                    position += count;
                } else {
                    list.add(content.substring(position, size));
                    position = size;
                }
            } else {
                if (position + count < size) {
                    list.add(content.substring(position, position + count - 1));
                    position += count - 1;
                } else {
                    list.add(content.substring(position, size));
                    position = size;
                }
            }
        }

        int resultSize = list.size();
        int resultLength = list.get(0).length();
        for (int i = 0; i < resultLength; i++) {
            for (int j = 0; j < resultSize; j++) {
                if (list.get(j).length() > i) {
                    builder.append(list.get(j).substring(i, i + 1));
                }
            }
        }
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
        FileUtil.outFileContent(saveFile, builder.toString());
    }

    private static void encryption(int subLength, File loadFile, File saveFile) throws IOException {
        String content = FileUtil.getFileContent(loadFile);
        StringBuilder builder = new StringBuilder();
        int size = content.length();
        for (int i = 0; i < subLength; i++) {
            for (int j = 0; j < size; j++) {
                if (j % subLength == i) {
                    builder.append(content.substring(j, j + 1));
                }
            }
        }
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
        FileUtil.outFileContent(saveFile, builder.toString());
    }


    /**
     * 将Java类的方法全部添加日志输出,自动添加方法名
     */
    private static void handleJavaFile() throws IOException {
        String result = FileUtil.getFileContent(new File("C:\\Users\\dingzhixin.ex\\Desktop\\12315.txt"));
        int size = result.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String s = result.substring(i, i + 1);
            builder.append(s);
            if ("{".equals(s)) {
                builder.append("\n").append("    LUtils.e(TAG, \" = \" );\n");
            }
        }
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\55555.java");
        file.createNewFile();
        FileUtil.outFileContent(file, builder.toString());


    }


    /**
     * 根据条件过滤文件并输出内容到文件
     */
    public static void filerFileAndOut(String targetFilePath, String outFilePath, List<String> conditions) {
        ParseLogUtil.parseLog(targetFilePath, outFilePath, conditions);
    }

    public static void filerFileAndOut(String targetFilePath, String outFilePath, List<String> conditions,
                                       List<String> excludeConditions) {
        ParseLogUtil.parseLog(targetFilePath, outFilePath, conditions, excludeConditions);
    }

    public static void filerFileAndOut(String targetFilePath, List<String> conditions, List<String> excludeConditions) {
        filerFileAndOut(targetFilePath, "C:\\Users\\dingzhixin.ex\\Desktop\\解析结果1.log", conditions, excludeConditions);
    }

    public static void filerFileAndOut(String targetFilePath, List<String> conditions) {
        filerFileAndOut(targetFilePath, "C:\\Users\\dingzhixin.ex\\Desktop\\解析结果1.log", conditions);
    }

    /**
     * 持续输出内存使用情况内容,用于分析内存泄漏
     */
    public static void analysisMemInfo() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Utils.runtimeCommand("adb -s 192.168.137.172 shell \"dumpsys meminfo com.hisense.smartimages " +
                            "|grep -E \'Views|Activities|TOTAL\' \"");

                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    public static void watchFileChange() {
        final DirWatcher dw;
        try {
            dw = new DirWatcher(true);
            dw.registerAllEvents(Paths.get("C:\\Users\\dingzhixin.ex\\Desktop")).addHandler(new WatcherResultHandler() {
                @Override
                public void handleResult(Path path, WatchEvent.Kind<?> event) {
                    System.out.println(path.toString() + ": " + event.toString());
                }
            }).processEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 2  * @param inputFile  监控文件
     * 3  * @param sleepInterval  当文件没有日志时sleep间隔
     * 监控文件改变并输出最新内容
     */
    private static void monitor(String inputFile, int sleepInterval) {
//        List<String> list = Arrays.asList("InflateException", "HXJ", "OutOfMemoryError", "IllegalArgumentException", "FATAL", "AiotCrashHandler", "manager error", "has detached", "Invalid item position");
//        List<String> list = Arrays.asList("InflateException", "OutOfMemoryError", "IllegalArgumentException", "FATAL", "DemoCrashHandler", "manager error", "HXJ");
//        List<String> list = Arrays.asList("SingleCardDeviceManger", "BluetoothVoiceTipFragment","IotManagerService","RequestBean");
//        List<String> list = Arrays.asList("init CardBar", "JuBao KeyEvent is START","com.jamdeo.tv.START_JU_BAO"/*,"lowmemorykiller"*/);
        List<String> list = Arrays.asList("TaoBaoManager: XJST| OnUIStatusListener(), onDismiss(), isBackPressed",
                "addCardBarToWindow");
//        List<String> list = Arrays.asList("updateSceneView()", "updateByPartnerStatus","mAssistView.isFocusable()","firstRequestFocus");
        TailerListener listener = new TailerListenerAdapter() {
            @Override
            public void handle(String line) {
                boolean needOut = false;
                boolean needOut1 = false;
                int count = 0;
                for (String s : list) {
                    if (line.contains(s)) {
//                        count++;
                        needOut = true;
                        if (line.contains("HXJ") && !line.contains("AiotCrashHandler")) {
                            needOut = false;
                        }
                        break;
                    }

                }
                if (needOut) {
                    System.out.println(line + "\n");

//                    if (!line.contains("HXJ") || line.contains("has detached")) {
//                        System.out.println(line + "\n");
//                    }
//                if (count == list.size()) {
                    FileUtil.outFileContentAppend(new File("C:\\Users\\dingzhixin.ex\\Desktop\\异常结果.txt"), line + "\n\n");
                }

            }
        };
        MyTailer tailer = new MyTailer(new File(inputFile), listener, sleepInterval, true);
        tailer.run();
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
                String s = encode;
                return s; //是的话，返回“GB2312“，以下代码同理
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "不确定"; //如果都不是，说明输入的内容不属于常见的编码格式。
    }

    /**
     * 重命名文件名
     */
    public static void renameFile(String filePath, String pre) {
        File file = new File(filePath);

        String[] list = file.list();

        int count = 0;
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
//            System.out.println(list[i].replace("01_",""));
            String fileName;
            if (count < 10) {
                fileName = pre + "00" + count + ".png";
            } else if (count < 100) {
                fileName = pre + "0" + count + ".png";
            } else {
                fileName = pre + count + ".png";
            }
            File file1 = new File(filePath, list[i]);
            try {
                System.out.println(list[i] + "  ===========  " + fileName);
                FileUtils.copyFile(file1, new File("C:\\Users\\dingzhixin.ex\\Desktop\\空气", fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println("==========   " + file1.renameTo(new File("C:\\Users\\dingzhixin.ex\\Desktop\\处理后图片", fileName)));
            count++;
        }

    }

    /**
     * 输出文件名组合
     */
    private static void outFilenameAppend(String filePath) {
        //R.drawable.
        File file = new File(filePath);
        String[] list = file.list();
        int length = list.length;
        for (int i = 0; i < length; i++) {
            System.out.println("R.drawable." + list[i].replaceAll(".png", "") + ",");
        }

    }


    private static long mBeforeTime;
    private static int INTERVAL = 500;
    private static int count = 0;
    private static CountDownLatch start = new CountDownLatch(1);
    private static CountDownLatch end = new CountDownLatch(1000);

    private static void testRequest() {

        for (int i = 0; i < 1000; i++) {
            start = new CountDownLatch(1);
            end = new CountDownLatch(2);
            new Thread() {
                @Override
                public void run() {
                    try {
                        start.await();
                        request();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        start.await();
                        request();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            }.start();


            start.countDown();
            try {
                Thread.sleep(600);
                end.await();
                System.out.println("结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }


    private static int mRunCount = 0;

    private static void request() {
        System.out.println("执行时间 = " + System.currentTimeMillis());
        if (System.currentTimeMillis() - mBeforeTime < INTERVAL) {
            count++;
            System.out.println("请求被拦截     " + count);
            return;
        } else {
            mBeforeTime = System.currentTimeMillis();
        }
        mRunCount++;
        System.out.println("发起了请求    =  " + mRunCount);
    }


    private static void test() {
        //        Desktop.getDesktop().open(new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm"));
//        OutputStream inputStream = Runtime.getRuntime().exec("explorer E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm").getOutputStream();
//
//        ArrayList<String> columnList = new ArrayList<String>();
////        File file = new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm");
////        File file = new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\IOT平台指令协议-20200514(addvirtualcenter) (1).xlsx");
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\AIOT3.0.5总的冒烟用例.xlsx");
////        FileWriter filewriter = new FileWriter("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm");
//        try {
//            FileInputStream in = new FileInputStream(file);
//
//            XSSFWorkbook wb = new XSSFWorkbook(in);
//
//            Sheet sheet = wb.getSheetAt(0); //取得“测试.xlsx”中的第一个表单
//            int firstRowNum = sheet.getFirstRowNum();
//            int lastRowNum = sheet.getLastRowNum();
//
//            Row row = null;
//            Cell cell_a = null;
//            for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
//                row = sheet.getRow(i); //取得第i行 （从第二行开始取，因为第一行是表头）
//                cell_a = row.getCell(1); //取得i行的第一列
//                String cellValue = cell_a.getStringCellValue().trim();
//                //System.out.println(cellValue);
////                filewriter.write(cellValue + "\r\n");   //将取出的.xlsx表中的数据写入到txt文件中
//                System.out.println(cellValue);
//                columnList.add(cellValue);
//            }
////            filewriter.flush();
////            filewriter.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        renameFile("C:\\Users\\dingzhixin.ex\\Desktop\\01_ok_button");
//        System.out.println("dsds".toString());
//        monitor("C:\\Users\\dingzhixin.ex\\Desktop\\1.txt", 1000);
//        FileUtil.getFileContent(new File("C:\\Users\\dingzhixin.ex\\Desktop\\统计异常.txt"));


    }

    public static void getDeskTopPath() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        System.out.println("desktop path = " + com.getPath());
    }
}