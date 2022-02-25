package com.dzx;

import com.dzx.bean.PositionBean;
import com.dzx.util.LUtils;
import com.dzx.util.ThreadPoolUtil;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Test5 {
    private static List<Integer> failList = new ArrayList<>();
    private static List<ResourceEntity> resultList = new ArrayList<>();
    private static PositionBean mPositionBean = new PositionBean(0, 0, 0);

    public static void main(String[] args) {//C:\Program Files (x86)\Google\Chrome\Application
        for (int i = 0; i < 50; i++) {
            ThreadPoolUtil.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (3000*Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LUtils.i("最终活跃数   ",ThreadPoolUtil.getInstance().getActiveCount());
            }
        }.start();
    }

    private synchronized void method1() {
        LUtils.i("======================= method1 ==============");
    }

    private synchronized void method2() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LUtils.i("======================= method2 ==============");
        method3();
    }

    private synchronized void method3() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LUtils.i("======================= method3 ==============");
    }

    private static int mFlags = 0;
    private static int FIRST_FLAG = 0x1;
    private static int SECOND_FLAG = 0x2;
    private static int THIRD_FLAG = 0x4;

    private static void bitOperation() {

        mFlags |= FIRST_FLAG;
        mFlags |= SECOND_FLAG;
        mFlags |= THIRD_FLAG;

        LUtils.i(Integer.toBinaryString(mFlags));
        LUtils.i(((mFlags & FIRST_FLAG) == FIRST_FLAG));
        LUtils.i(Integer.toBinaryString(mFlags));
        mFlags &= ~FIRST_FLAG;
        LUtils.i(((mFlags & FIRST_FLAG) == FIRST_FLAG));
        LUtils.i(Integer.toBinaryString(mFlags));
        LUtils.i(Integer.toBinaryString(THIRD_FLAG), "    4");

        mFlags &= ~FIRST_FLAG;
        mFlags &= ~SECOND_FLAG;
        mFlags &= ~THIRD_FLAG;

        LUtils.i(Integer.toBinaryString(mFlags));


    }

    private void splitMethod() {
        File file3 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\rv执行比对\\新建文本文档.txt");

        try {
            List<String> list = FileUtils.readLines(file3);

            StringBuilder builder1 = new StringBuilder();
            StringBuilder builder2 = new StringBuilder();
            StringBuilder builder3 = new StringBuilder();

            int size = list.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (count == 1) {
                    builder1.append(list.get(i).substring(list.get(i).indexOf("android"))).append("\n");

                } else if (count == 2) {
                    builder2.append(list.get(i).substring(list.get(i).indexOf("android"))).append("\n");
                } else if (count == 3) {
                    builder3.append(list.get(i).substring(list.get(i).indexOf("android"))).append("\n");
                }
                if (list.get(i).contains("android.support.v7.widget.RecyclerView.setAdapter(Landroid/support/v7/widget/RecyclerView$Adapter;)")) {

                    count++;
                }
            }


            File file31 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\rv执行比对\\新建文本文档31.txt");
            File file32 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\rv执行比对\\新建文本文档32.txt");
            File file33 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\rv执行比对\\新建文本文档33.txt");

            FileUtils.write(file31, builder1.toString());
            FileUtils.write(file32, builder2.toString());
            FileUtils.write(file33, builder3.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void methodCompare() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\rv执行比对\\新建文本文档31.txt");
        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\rv执行比对\\新建文本文档32.txt");
        File file2 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\rv执行比对\\新建文本文档33.txt");

        try {
            List<String> list1 = FileUtils.readLines(file);
            List<String> list2 = FileUtils.readLines(file1);
            List<String> list3 = FileUtils.readLines(file2);

//            StringBuilder builder=new StringBuilder();
//            for (int i = 0; i < list2.size(); i++) {
//                if (list2.get(i).contains("com.kimonik.drawui")){
//                    continue;
//                }
//                builder.append(list2.get(i)).append("\n");
//            }
//
//            FileUtils.write(file11,builder.toString());

            LUtils.i(list1.size(), "   ", list2.size(), "    ", list3.size());

            for (int i = 0; i < 5714; i++) {
//                if (
////                        list1.get(i).equals(list2.get(i))
//                         list2.get(i).equals(list3.get(i))) {
//
//                }else {
//                    LUtils.i(list1.get(i),"  ===  ",i);
                LUtils.i(list2.get(i), "  ===  ", i, "   ", list2.get(i).equals(list3.get(i)));
                LUtils.i(list3.get(i), "  ===  ", i);
                LUtils.i("\n");
//                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static final int STEP_START = 1;
    static final int STEP_LAYOUT = 1 << 1;
    static final int STEP_ANIMATIONS = 1 << 2;

    private static AtomicBoolean mAtomicBoolean = new AtomicBoolean(false);

    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String monthS = "";
        if (month < 10) {
            monthS = "0" + month;
        } else {
            monthS = "" + month;
        }
        String dayS = "";
        if (day < 10) {
            dayS = "0" + day;
        } else {
            dayS = "" + day;
        }
        LUtils.i(monthS + dayS);

        return monthS + dayS;

    }

    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String monthS = "";

        if (day == 1) {
            if (month == 1) {
                monthS = "12";
            } else if (month - 1 > 9) {
                monthS = "" + (month - 1);
            } else {
                monthS = "0" + (month - 1);
            }
        } else {
            if (month < 10) {
                monthS = "0" + month;
            } else {
                monthS = "" + month;
            }
        }

        String dayS = "";
        Set<String> months = new HashSet<>(Arrays.asList("1", "3", "5", "7", "8", "10", "12"));
        int lastMonth = month - 1;
        if (lastMonth == 0) {
            lastMonth = 12;
        }

        if (day == 1) {
            if (months.contains("" + lastMonth)) {
                dayS = "31";
            } else {
                if ("2".equals("" + lastMonth)) {
                    dayS = "28";
                } else {
                    dayS = "30";
                }
            }
        } else {
            if (day < 10) {
                dayS = "0" + (day - 1);
            } else {
                dayS = "" + (day - 1);
            }
        }

        LUtils.i(monthS + dayS);
        return monthS + dayS;

    }

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static void testLock() {
        for (int i = 0; i < 10; i++) {
//            reentrantLock.lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try {
//                reentrantLock.unlock();
//            } catch (Exception e) {
//                LUtils.i("跑出异常");
//            }

            System.out.println("================== " + i + "  " + Thread.currentThread().getName());
        }
    }


    private static List<String> list = new ArrayList<>();

    private static void testSyc() {
        new Thread() {
            @Override
            public void run() {
                synchronized (list) {
                    LUtils.i("已执行");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    list.add("11111");
                    LUtils.i(" ===", list);
                }

            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add("5555");
        LUtils.i(list);


    }

    private static void addTest(List<PositionBean> list) {
        list.get(0).lineNumber = 999;
    }

    private static void parseSource(String html, int num) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("audio");
        System.out.println("num = " + num + " , elements.size() = " + elements.size());
        for (int i = 0; i < elements.size(); i++) {
            Attributes attributes = elements.get(i).attributes();
            String url = attributes.get("src");
            if (TextUtils.isEmpty(url)) {
                failList.add(num);
            } else {
                ResourceEntity resourceEntity = new ResourceEntity();
                resourceEntity.resourcePosition = num;
                resourceEntity.resourceUrl = url;
                resultList.add(resourceEntity);
            }
            System.out.println(url + "--" + num);
        }
    }

    private static void getAllContentFromUrl() {
        //设置启动google浏览器的驱动和位置
        System.setProperty("webdriver.chrome.driver",
                "C:" + File.separator +
                        "Program Files (x86)" + File.separator +
                        "Google" + File.separator +
                        "Chrome" + File.separator +
                        "Application" + File.separator +
                        "chromedriver.exe");
        System.out.println("启动 ...");
        //获取谷歌浏览器驱动
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("-–start-maximized");
//        chromeOptions.setHeadless(true);
//        WebDriver driver = new ChromeDriver(chromeOptions);
        //设置默认打开的页面
//        driver.get("https://ting55.com/book/11250-897");
        //设置默认搜索的关键字
//        driver.findElement(By.id("kw")).sendKeys("eclipse窗口集成浏览器");
        //搜索按钮点击
//        driver.findElement(By.id("su")).click();
//        parseSource(driver.getPageSource(), 897);
        //关闭浏览器

        WebDriver driver = new ChromeDriver();
        for (int i = 11; i < 20; i++) {
            driver.get("https://ting55.com/book/11250-" + i);
            try {
                //不休眠可能获取不到完整的html文件
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String html = driver.getPageSource();
            parseSource(html, i);

        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Gson().toJson(failList));
        System.out.println(new Gson().toJson(resultList));
//        driver.close();
    }

    private static void phoneWeb() {
//
//        System.setProperty("webdriver.chrome.driver", config.getProperty("chromePath"));
//        Map<String, String> mobileEmulation = new HashMap<String, String>();
//        mobileEmulation.put("deviceName", "Apple iPhone 6");
//        Map<String, Object> chromeOptions = new HashMap<String, Object>();
//        chromeOptions.put("mobileEmulation", mobileEmulation);
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//
//        WebDriver driver=new ChromeDriver(capabilities);
    }

    public static String downloadFromUrl() {
        System.out.println("开始下载");
        try {
            URL httpUrl = new URL("https://selenium-release.storage.googleapis.com/3.141/IEDriverServer_Win32_3.141.59.zip");
            File f = new File("C:\\Users\\dingzhixin.ex\\Desktop", "IEDriverServer_Win32_3.141.59.zip");
            FileUtils.copyURLToFile(httpUrl, f);
            System.out.println("下载结束");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("下载异常");
            return "Fault!";
        }
        return "Successful!";

    }

    public static class ResourceEntity {
        public String resourceUrl;
        public int resourcePosition;
    }

    public static void playM4a() {
        FileInputStream fis;
        AudioStream as;
        try {
            fis = new FileInputStream("C:\\Users\\dingzhixin.ex\\Desktop\\声音文件22.wav");                                                                     //似乎只能是AU格式的。反正mp3格式的不行。可以先下载下来mp3格式的，再用ESFAudioConventer这款小工具来转换
            as = new AudioStream(fis);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
