package com.dzx.util;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 相关配置
 * 下载chromedriver的地址
 * http://chromedriver.storage.googleapis.com/
 * https://npm.taobao.org/mirrors/chromedriver 镜像地址
 * 下载完成后将chromedriver.exe放到Chrome.exe同一个文件夹内
 * <p>
 * 博客文章(内含jar包下载)
 * https://www.cnblogs.com/Soy-technology/p/11344752.html
 * <p>
 * selenium  下载地址
 * https://www.selenium.dev/downloads/
 * <p>
 * 相关使用jar包
 * byte-buddy-1.8.15.jar
 * client-combined-3.141.59.jar
 * client-combined-3.141.59-sources.jar
 * commons-exec-1.3.jar
 * guava-25.0-jre.jar
 * okhttp-3.11.0.jar
 * okio-1.14.0.jar
 * jsoup-1.12.2.jar
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

    private static int count = 0;
    private static int mCurrentNum = 716;


    /**
     * http://audio.xmcdn.com/group9/M0B/21/2D/wKgDZlbmxDihkQxIAJs4uQOBiKs061.m4a--403
     */
    public static void main(String[] args) {//C:\Program Files (x86)\Google\Chrome\Application

//        parseSource("",0);
        startFind();
//        filterFile();
    }

    private static void startFind() {
        System.out.println("已开始,等待中......." + new SimpleDateFormat("HH:mm:ss").format(new Date()));
//        try {
//            Thread.sleep(50 * 60 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("开始,等待结束......." + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        while (true) {
            try {
                getAllContentFromUrl(mCurrentNum, 930);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void filterFile() {
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\设备配网坐标\\下载资源.txt");
        File file = new File("C:\\Users\\20313\\Desktop\\唐砖\\处理文本.txt");
        try {
            List<String> list = FileUtils.readLines(file);
            List<ResourceEntity> resourceEntities = new ArrayList<>();
            for (String s : list) {
                if (s.contains("http") || s.contains("https")) {
                    ResourceEntity resourceEntity = new Gson().fromJson(s, ResourceEntity.class);
                    resourceEntities.add(resourceEntity);
                }
            }
            List<Integer> list1 = new ArrayList<>();
            for (int i = 0; i < 716; i++) {
                list1.add(i);
            }

            for (ResourceEntity entity : resourceEntities) {
                list1.remove((Integer) (entity.resourcePosition));
            }
            System.out.println(list1.toString());


            resourceEntities.sort(new Comparator<ResourceEntity>() {
                @Override
                public int compare(ResourceEntity o1, ResourceEntity o2) {
                    if (o1.resourcePosition > o2.resourcePosition) {
                        return 1;
                    } else if (o1.resourcePosition < o2.resourcePosition) {
                        return -1;
                    }
                    return 0;
                }
            });
            Set<Integer> set = new HashSet<>();
            for (ResourceEntity entity : resourceEntities) {
//                if (!set.contains(entity.resourcePosition)) {
//                    set.add(entity.resourcePosition);
                    if (!entity.resourceUrl.contains("https")) {
                        entity.resourceUrl = entity.resourceUrl.replaceAll("http", "https");
                    }
                    System.out.println(new Gson().toJson(entity));
//                }

            }

            int size = resourceEntities.size();
            set.clear();
            for (int i = size - 1; i > -1; i--) {
                ResourceEntity resourceEntity = resourceEntities.get(i);
                if (set.contains(resourceEntity.resourcePosition)) {
                    resourceEntities.remove(i);
                } else {
                    set.add(resourceEntity.resourcePosition);
                }

            }


            System.out.println(new Gson().toJson(resourceEntities));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void newThreadRun(int start, int end) {
        new Thread() {
            @Override
            public void run() {
                getAllContentFromUrl(start, end);
            }
        }.start();
    }

    /**
     * 获取完整的网页源码
     */
    private static void getAllContentFromUrl(int start, int end) {
        //设置启动google浏览器的驱动和位置,此处需要提前做好配置
        //chromedriver.exe文件与chrome.exe文件放置到同一个文件夹下
        //注意chromedriver.exe与chrome.exe的版本对应关系


        //=========================1111========================================================================
//        System.setProperty("webdriver.chrome.driver",
//                "C:" + File.separator +
//                        "Program Files (x86)" + File.separator +
//                        "Google" + File.separator +
//                        "Chrome" + File.separator +
//                        "Application" + File.separator +
//                        "chromedriver.exe");
//        System.out.println("启动 ...");
        //=========================22222=======================================================================
        System.setProperty("webdriver.chrome.driver",
                "C:" + File.separator +
                        "Users" + File.separator +
                        "20313" + File.separator +
                        "AppData" + File.separator +
                        "Local" + File.separator +
                        "Google" + File.separator +
                        "Chrome" + File.separator +
                        "Application" + File.separator +
                        "chromedriver.exe");
        System.out.println("启动 ...");
        //=============33333===================================================================================
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
        ChromeOptions options = new ChromeOptions();
        disableChromeImages(options);
        WebDriver driver = new ChromeDriver(options);

        //http://audio.xmcdn.com/group11/M06/90/B4/wKgDbVYeVuzRAN0CAJhSM6pJ8hs524.m4a
//{"resourceUrl":"http://audio.xmcdn.com/group11/M06/90/B4/wKgDbVYeVuzRAN0CAJhSM6pJ8hs524.m4a","resourcePosition":40}

        for (int i = start; i < end; i++) {
            mCurrentNum = i;
            WebDriver.Options manage = driver.manage();
            Set<Cookie> cookies = manage.getCookies();
            for (Cookie c : cookies) {
                manage.deleteCookie(new Cookie(c.getName(), c.getValue()));
//                System.out.println(c.getName()+ " = " + c.getValue());
            }

            driver.get("https://ting55.com/book/11250-" + mCurrentNum);
            try {
                //不休眠可能获取不到完整的html文件,此处应该视网速情况进行设置
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取网页源码,此时可以获取到js解析的内容https://m.ting55.com/book/11250-24


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
        driver.close();
    }


    public static void disableChromeImages(ChromeOptions options) {
        HashMap<String, Object> images = new HashMap<String, Object>();
        images.put("images", 2);
//        images.put("deviceName", "Apple iPhone 6");

        HashMap<String, Object> prefs = new HashMap<String, Object>();
        //设置不加载图片
        prefs.put("profile.default_content_setting_values", images);
        //设置访问手机网页
//        prefs.put("deviceName", "Nexus 5");
        prefs.put("deviceName", "Apple iPhone 6");
        options.setExperimentalOption("prefs", prefs);

    }


    /**
     * 解析获取资源url
     */
    private static void parseSource(String html, int num) {
//        System.out.println(html);
        count++;
        Document document = Jsoup.parse(html);
//
//        Elements elements = document.getElementsByTag("meta");
//        for (int i = 0; i < elements.size(); i++) {
//            try {
//                Element element = elements.get(i).getElementsByAttributeValue("name", "_c").get(0);
//                ;
////            element.getElementsByAttributeValue("name","_c");
//                System.out.println(element.attr("content"));
//                UrlRequestUtil.sendMyPost(num, element.attr("content"));
//            } catch (Exception e) {
//
//            }
//
//        }

//
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
                try {
                    LUtils.i("DZX 开始获取失败,休眠一小时 ",
                            new SimpleDateFormat("HH:mm:ss").format(new Date()),
                            " mCurrentNum = ", mCurrentNum);
                    count = 1;
                    Thread.sleep(3600 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                ResourceEntity resourceEntity = new ResourceEntity();
                resourceEntity.resourcePosition = num;
                resourceEntity.resourceUrl = url;
                resultList.add(resourceEntity);
                saveContentToFile(resourceEntity, num);
            }
            System.out.println(url + "--" + num);
        }

        if (count % 21 == 0) {
            System.out.println("开始休眠.........." + (new SimpleDateFormat("yyyyMMdd  HH:mm:ss").format(new Date())));
            try {
                Thread.sleep(20 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("休眠结束.........." + (new SimpleDateFormat("yyyyMMdd  HH:mm:ss").format(new Date())));
        }
    }

    private static void saveContentToFile(ResourceEntity resourceEntity, int num) {
        String content = new Gson().toJson(resourceEntity) + "\n";
//        try {
//            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\下载资源.txt"), content, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //==============================111111==========================================
//        String content=new Gson().toJson(resourceEntity)+"\n";
        try {
            FileUtils.write(new File("C:\\Users\\20313\\Desktop\\唐砖", "唐砖连接.txt"), content, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void copyUrlSource() {
        String json = "[{\"resourceUrl\":\"http://audio.xmcdn.com/group7/M07/6B/77/wKgDWlXqvOew3XN7AJ5AXiR-vdU292.m4a\",\"resourcePosition\":1},{\"resourceUrl\":\"http://audio.xmcdn.com/group14/M04/6C/20/wKgDZFXtBkXS6hriAJb1UvJOFP4079.m4a\",\"resourcePosition\":2},{\"resourceUrl\":\"http://audio.xmcdn.com/group8/M0A/6D/19/wKgDYVXtlXygAtttAJeGZoeWHvA422.m4a\",\"resourcePosition\":3},{\"resourceUrl\":\"http://audio.xmcdn.com/group9/M05/6D/41/wKgDZlXu5bPzbxfAAJrsQQCq7o0016.m4a\",\"resourcePosition\":4},{\"resourceUrl\":\"http://audio.xmcdn.com/group11/M0B/7A/7F/wKgDbVXwRXfiWopyAJsofVNGd6o576.m4a\",\"resourcePosition\":5},{\"resourceUrl\":\"http://audio.xmcdn.com/group11/M02/7B/13/wKgDbVXxjEvC2rK6AJriCJu1MdQ596.m4a\",\"resourcePosition\":6},{\"resourceUrl\":\"http://audio.xmcdn.com/group7/M08/6F/5A/wKgDWlXy4p6jH8LxAJoZHikHa50274.m4a\",\"resourcePosition\":7},{\"resourceUrl\":\"http://audio.xmcdn.com/group13/M00/6F/F9/wKgDXlX0L5GCuoqiAJMPzbV6C68148.m4a\",\"resourcePosition\":8},{\"resourceUrl\":\"http://audio.xmcdn.com/group12/M04/6F/D3/wKgDXFX1hbvAWdPBAJTd0R_DlL8067.m4a\",\"resourcePosition\":9},{\"resourceUrl\":\"http://audio.xmcdn.com/group11/M0B/7D/6B/wKgDbVX21YKyIf81AJVL4WVId5M580.m4a\",\"resourcePosition\":10},{\"resourceUrl\":\"http://audio.xmcdn.com/group8/M07/71/F3/wKgDYVX4IC-xyANhAJL1Wa9oucA135.m4a\",\"resourcePosition\":11},{\"resourceUrl\":\"http://audio.xmcdn.com/group13/M09/77/1D/wKgDXlYCuF7i4utHAJh4exfiP-g737.m4a\",\"resourcePosition\":12},{\"resourceUrl\":\"http://audio.xmcdn.com/group12/M00/76/24/wKgDW1YCuN_T7tGtAJ2TR2-fD28691.m4a\",\"resourcePosition\":13},{\"resourceUrl\":\"http://audio.xmcdn.com/group9/M06/73/52/wKgDYlX8IgeAT2cWAJx1eHzR3zE027.m4a\",\"resourcePosition\":14},{\"resourceUrl\":\"http://audio.xmcdn.com/group9/M0B/76/BB/wKgDZlYCuibR8DmBAJZyuUqFZGI513.m4a\",\"resourcePosition\":15},{\"resourceUrl\":\"http://audio.xmcdn.com/group16/M00/75/95/wKgDalYAGb6zqA3sAJL2xcVrqak860.m4a\",\"resourcePosition\":16},{\"resourceUrl\":\"http://audio.xmcdn.com/group12/M0B/76/28/wKgDW1YCusWypI8hAJuYBdf-hXw154.m4a\",\"resourcePosition\":17},{\"resourceUrl\":\"http://audio.xmcdn.com/group15/M05/75/F0/wKgDZVYBZZDhTyhiAJSb2zeGPMI302.m4a\",\"resourcePosition\":18},{\"resourceUrl\":\"http://audio.xmcdn.com/group11/M03/83/30/wKgDbVYCt-bzsLIHAJgRwFNQQsI674.m4a\",\"resourcePosition\":19}]\n";
        Type type = new TypeToken<List<ResourceEntity>>() {
        }.getType();
        List<ResourceEntity> list = new Gson().fromJson(json, type);
        for (ResourceEntity resourceEntity : list) {
            try {
                FileUtils.copyURLToFile(new URL(resourceEntity.resourceUrl), new File("C:\\Users\\dingzhixin.ex\\Desktop\\下载资源", resourceEntity.resourcePosition + ".m4a"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 设置打开手机网页,未验证
     */
    private static void phoneWeb() {

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
