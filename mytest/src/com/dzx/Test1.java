package com.dzx;

import com.dzx.bean.EnumType;
import com.dzx.util.LUtils;
import luyao.parser.xml.XmlParser;
import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Test1 {


    public static void main(String[] args) throws Exception {

//        FinalTest1 finalTest1=new FinalTest1();
//        FinalTest1 finalTest2=new FinalTest1();
//        FinalTest1 finalTest3=new FinalTest1();
//
//       System.out.println(finalTest1.getMyGson()==finalTest2.getMyGson());
//       System.out.println(finalTest1.getMyGson()==finalTest3.getMyGson());
//       System.out.println(finalTest2.getMyGson()==finalTest3.getMyGson());


//       String  target="";
//       System.out.println(target.substring(3));

//        String s="abcde";
//        System.out.println(s.substring(0,5));
//        byte[] bytes = {0x36,0x68,0x0b};
//
//        byte b=0x36;
//        System.out.println(Integer.toBinaryString(0xff));
//
//
//        System.out.println(byte2int(bytes));
//        byte[] b={-91,-67};
//        byte[] b = {-70, -61};
//        try {
//            System.out.println(new String(b, "gbk"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }  //开始网络请求
// 47         URL url = new URL(keyUrl);
// 48         HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
// 49         InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(),"utf-8");
// 50         BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
//        try {
////           System.out.println(Jsoup.connect("https://ting55.com/book/11250").get().body());
////            System.out.println(Jsoup.connect("https://ting55.com/book/11250-924").get().body());
//            Document document = Jsoup.connect("https://ting55.com/book/11250-924").get();
//            Elements element=document.body().getElementsByTag("audio");
//            for (int i = 0; i < element.size(); i++) {
//                System.out.println(element.get(i).toString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        URL url = null;
//        try {
////            url = new URL("https://zhidao.baidu.com/question/544743183.html");
//            url = new URL("https://ting55.com/book/11250");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
////            url.openConnection().connect();
////            url = new URL("http://audio.xmcdn.com/group8/M0A/6D/19/wKgDYVXtlXygAtttAJeGZoeWHvA422.m4a");
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
//            String s;
//            while ((s = bufferedReader.readLine()) != null) {
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("29741\tpool-1-thread-13\t0\tjava.lang.Character.isHighSurrogate(C)Z".length());
//        downloadFromUrl();
//        testEnum();
        try {
//            List<ApkInfo> list = GetApkInfo.listApkInfoByDir("C:\\Users\\dingzhixin.ex\\Desktop\\百科日志\\");
//            LUtils.i(list);


//            ApkInfo apkInfo = GetApkInfo.getApkInfoByFilePath("C:\\Users\\dingzhixin.ex\\Desktop\\百科日志\\tusou_032.apk");
//            LUtils.i(apkInfo);

            //java.lang.ArrayIndexOutOfBoundsException: 1707
            //	at org.apkinfo.api.util.StringBlock.getShort(StringBlock.java:231)
            //	at org.apkinfo.api.util.StringBlock.getString(StringBlock.java:91)
            //	at org.apkinfo.api.util.AXmlResourceParser.getName(AXmlResourceParser.java:138)
            //	at org.apkinfo.api.util.AXMLPrinter2.parse(AXMLPrinter2.java:64)
            //	at com.dzx.Test1.main(Test1.java:91)
//            LUtils.i(1835009);
//
//            InputStream stream = getApkInfo("C:\\Users\\dingzhixin.ex\\Desktop\\百科日志\\tusou_032.apk");
//            String strContent = AXMLPrinter2.parse(stream);
////            LUtils.i(strContent);
//
//
//            AXmlResourceParser parser = new AXmlResourceParser();
//            parser.open(stream);
//            LUtils.i(parser.getAttributeCount());

//            InputStream inputStream = new FileInputStream("C:\\Users\\dingzhixin.ex\\Desktop\\百科日志\\tusou_032\\AndroidManifest.xml");
//            InputStream inputStream = new FileInputStream("C:\\Users\\dingzhixin.ex\\Desktop\\micmanager_12100_1.1.7_test1\\AndroidManifest.xml");
            InputStream inputStream = new FileInputStream("C:\\Users\\dingzhixin.ex\\Desktop\\com.ygkj.chelaile.standard_4.22.0_266\\AndroidManifest.xml");

            XmlParser parser = new XmlParser(inputStream);
            parser.parse();

            //            IntReader intReader=new IntReader(inputStream,false);
//            LUtils.i(Integer.toHexString(intReader.readInt()));
//            LUtils.i(intReader.readInt());
//
//            LUtils.i(intReader.readInt());
//            LUtils.i(intReader.readInt());
//            LUtils.i(intReader.readInt());
//            LUtils.i(intReader.readInt());


        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private static InputStream getApkInfo(String apkpath) {
        try {
            ZipFile zFile = new ZipFile(apkpath);
            ZipEntry entry = zFile.getEntry("AndroidManifest.xml");
            entry.getComment();
            entry.getCompressedSize();
            entry.getCrc();
            entry.isDirectory();
            entry.getSize();
            entry.getMethod();
            InputStream stream = zFile.getInputStream(entry);
            return stream;
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    private static void testEnum() {
        List<EnumType> list = new ArrayList<>();
        list.add(EnumType.TYPE_FIVE);
        list.add(EnumType.TYPE_SIX);
        list.add(EnumType.TYPE_FOUR);
        list.add(EnumType.TYPE_SEVEN);
        list.add(EnumType.TYPE_SEVEN);
        list.add(EnumType.TYPE_THREE);
        list.add(EnumType.TYPE_THREE);
        list.add(EnumType.TYPE_EIGHT);
        list.add(EnumType.TYPE_EIGHT);
        list.add(EnumType.TYPE_TWO);
        list.add(EnumType.TYPE_TWO);
        list.add(EnumType.TYPE_NINE);
        list.add(EnumType.TYPE_ONE);
        list.add(EnumType.TYPE_TEN);

        List<EnumType> list1 = new ArrayList<>(list);
        list.clear();

        LUtils.i(list.size());
        LUtils.i(list1.size());
        LUtils.i(list1.get(0));
//
//
//
//        LUtils.i(list);
//        LinkedHashSet<EnumType> linkedHashSet = new LinkedHashSet<>(list);
//        list.clear();
//        list.addAll(linkedHashSet);
//        LUtils.i(list);
//
//
//        Collections.sort(list, new Comparator<EnumType>() {
//            @Override
//            public int compare(EnumType o1, EnumType o2) {
//                return o2.ordinal() - o1.ordinal();
//            }
//        });
//        LUtils.i(list);


    }

    public static void getContent() throws Exception {
        //获取url
        URL url = new URL("https://ting55.com/book/11250-924");
        //下载资源
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String msg = null;
        while (null != (msg = br.readLine())) {
            System.out.println(msg);
        }
        br.close();
    }

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


    public static String getFileNameFromUrl(String url) {
        String name = new Long(System.currentTimeMillis()).toString() + ".X";
        int index = url.lastIndexOf("/");
        if (index > 0) {
            name = url.substring(index + 1);
            if (name.trim().length() > 0) {
                return name;
            }
        }
        return name;
    }

    /**
     * 36   0011 0110
     * 68   0110 1000
     * 0b   0000 1011
     * ff   1111 1111
     * <p>
     * 0000 0000 0000 0000  0000 0000 0000 0000
     * <p>
     * 0011 0110
     * <p>
     * <p>
     * 小端方式转换
     * <p>
     * 00000000 00000000 00000000 11111111
     * 00110110
     * <p>
     * 00000000 00000000 00000000 00110110
     * 00000000 00000000 01101000 00000000
     * 00000000 00001011 00000000 00000000
     */

    public static int byte2int(byte[] b) {
        int res = 0;
        int bLen = b.length;

        if (bLen < 5) {// int 最大到4个字节
            for (int i = 0; i < bLen; i++) {
                res += (b[i] & 0xFF) << (8 * i);
            }
        }


        return res;
    }
}
