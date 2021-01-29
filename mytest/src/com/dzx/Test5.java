package com.dzx;

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
import java.util.ArrayList;
import java.util.List;

public class Test5 {
    private static List<Integer> failList = new ArrayList<>();
    private static List<ResourceEntity> resultList = new ArrayList<>();

    public static void main(String[] args) {//C:\Program Files (x86)\Google\Chrome\Application
//        getAllContentFromUrl();
        downloadFromUrl();
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
