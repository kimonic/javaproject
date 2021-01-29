package com.dzx.util;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 相关配置
 * 下载chromedriver的地址
 * http://chromedriver.storage.googleapis.com/
 * https://npm.taobao.org/mirrors/chromedriver 镜像地址
 * 下载完成后将chromedriver.exe放到Chrome.exe同一个文件夹内
 *
 * 博客文章(内含jar包下载)
 * https://www.cnblogs.com/Soy-technology/p/11344752.html
 *
 * selenium  下载地址
 * https://www.selenium.dev/downloads/
 *
 * 相关使用jar包
 * byte-buddy-1.8.15.jar
 * client-combined-3.141.59.jar
 * client-combined-3.141.59-sources.jar
 * commons-exec-1.3.jar
 * guava-25.0-jre.jar
 * okhttp-3.11.0.jar
 * okio-1.14.0.jar
 * jsoup-1.12.2.jar
 *
 */
@SuppressWarnings("DuplicatedCode")
public class ParseMediaSourceUtil {
    /**
     * 保存获取失败的url位置
     */
    private static List<Integer> failList = new ArrayList<>();
    /**
     * 保存成功的资源url
     */
    private static List<ResourceEntity> resultList = new ArrayList<>();

    public static void main(String[] args) {//C:\Program Files (x86)\Google\Chrome\Application
        getAllContentFromUrl();
    }

    /**
     * 获取完整的网页源码
     */
    private static void getAllContentFromUrl() {
        //设置启动google浏览器的驱动和位置,此处需要提前做好配置
        //chromedriver.exe文件与chrome.exe文件放置到同一个文件夹下
        //注意chromedriver.exe与chrome.exe的版本对应关系
        System.setProperty("webdriver.chrome.driver",
                "C:" + File.separator +
                        "Program Files (x86)" + File.separator +
                        "Google" + File.separator +
                        "Chrome" + File.separator +
                        "Application" + File.separator +
                        "chromedriver.exe");
        System.out.println("启动 ...");
        //ChromeOptions chromeOptions = new ChromeOptions();
        //配置Chrome浏览器启动时最大化,实测无效
        //chromeOptions.addArguments("-–start-maximized");
        //浏览器不弹出窗口,设置该选项后无法获取到js加载的源码内容
        //chromeOptions.setHeadless(true);
        //使用设置参数启动浏览器驱动
        //WebDriver driver = new ChromeDriver(chromeOptions);
        //设置要打开的页面
        //driver.get("https://ting55.com/book/11250-897");
        //设置默认搜索的关键字
        //driver.findElement(By.id("kw")).sendKeys("eclipse窗口集成浏览器");
        //搜索按钮点击
        //driver.findElement(By.id("su")).click();

        //获取谷歌浏览器驱动,使用默认参数配置
        WebDriver driver = new ChromeDriver();
        for (int i = 11; i < 20; i++) {
            driver.get("https://ting55.com/book/11250-" + i);
            try {
                //不休眠可能获取不到完整的html文件,此处应该视网速情况进行设置
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取网页源码,此时可以获取到js解析的内容
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

        //关闭单个浏览器,关闭重新使用driver.get()方法会异常退出
        //driver.close();
    }

    /**
     * 解析获取资源url
     */
    private static void parseSource(String html, int num) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("audio");
        int size = elements.size();
        System.out.println("num = " + num + " , elements.size() = " + size);
        if (size == 0) {
            failList.add(num);
        }
        for (int i = 0; i < size; i++) {
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

    /**
     * 设置打开手机网页,未验证
     */
    private static void phoneWeb() {
        //
        //System.setProperty("webdriver.chrome.driver", config.getProperty("chromePath"));
        //Map<String, String> mobileEmulation = new HashMap<String, String>();
        //mobileEmulation.put("deviceName", "Apple iPhone 6");
        //Map<String, Object> chromeOptions = new HashMap<String, Object>();
        //chromeOptions.put("mobileEmulation", mobileEmulation);
        //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        //capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        //
        //WebDriver driver=new ChromeDriver(capabilities);
    }

    /**
     * 通过网址直接下载并保存文件到本地
     */
    public static String downloadFromUrl() {
        System.out.println("开始下载");
        try {
            URL httpUrl = new URL("http://audio.xmcdn.com/group15/M05/75/F0/wKgDZVYBZZDhTyhiAJSb2zeGPMI302.m4a");
            File f = new File("C:\\Users\\dingzhixin.ex\\Desktop", "声音文件018");
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
        /**
         * 资源url
         */
        public String resourceUrl;
        /**
         * 资源名称,位置
         */
        public int resourcePosition;
    }

}
