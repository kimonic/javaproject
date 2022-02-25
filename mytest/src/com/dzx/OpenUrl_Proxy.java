package com.dzx;


import com.dzx.util.LUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OpenUrl_Proxy
 * @Description:通过代理实现Java代码访问指定URL
 * @date: 2017年8月24日
 * @修改备注:
 */
public class OpenUrl_Proxy {


    /**
     * @Description:HttpComponents--HttpClient方式访问指定URL 通常是因为服务器的安全设置不接受Java程序作为客户端访问，解决方案是设置客户端的User Agent
     * @date: 2017年8月24日 下午7:45:14
     * @修改备注:
     */
    public static void openUrl_httpComponents(String url) {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = client.execute(get);
            int httpStatusCode = response.getStatusLine().getStatusCode();
            //由于一些设置问题，访问百度首页可能返回的httpStatusCode是403，不是200
            //其原因就是可能百度服务器不支持通过代码来调用url
            if (HttpStatus.SC_OK == httpStatusCode) {
                System.out.println("打印服务器返回的状态: " + response.getStatusLine());
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("打印返回信息:" + EntityUtils.toString(entity));//打印返回信息
                    //entity.consumeContent();//释放资源
                    EntityUtils.consume(entity);//释放资源 this is the new consume method
                }
            } else {
                System.out.println("not ok");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.getConnectionManager().shutdown();
    }


    /**
     * @Description:通过添加代理方式，实现访问指定URL
     * @date: 2017年8月24日 下午8:21:26
     * @修改备注:
     */
    public static void openUrl_setProxy_1(String ip, int port) {
        InetSocketAddress inetAddress = null;
        URL url = null;
        try {
            url = new URL("https://github.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            inetAddress = new InetSocketAddress(InetAddress.getByName(ip), port);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        LUtils.i(inetAddress);
        URLConnection con = null;
        try {
            con = url.openConnection(new Proxy(Type.HTTP, inetAddress));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (con == null) {
            LUtils.i("con is null");
            return;
        }
        BufferedReader br = null;
        try {
            InputStream inputStream = con.getInputStream();
            if (inputStream == null) {
                return;
            }
            br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = null;
        StringBuffer sb = new StringBuffer();
        if (br == null) {
            LUtils.i("br is null");
            return;
        }
        try {
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }


    /**
     * @Description:通过添加代理方式，实现访问指定URL 通常是因为服务器的安全设置不接受Java程序作为客户端访问，解决方案是设置客户端的User Agent
     * @date: 2017年8月24日 下午8:22:19
     * @修改备注:
     */
    public static void openUrl_setProxy_2() {
        String host = "47.92.234.75";
        String port = "80";
        setProxy(host, port);
        URL url = null;
        try {
            url = new URL("http://www.github.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println(url.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void setProxy(String host, String port) {
        System.setProperty("proxySet", "true");
        System.setProperty("proxyHost", host);
        System.setProperty("proxyPort", port);
    }


    public static void main(String[] args) {
//        openUrl_setProxy_1("121.232.148.55",9000);
//        openUrl_setProxy_1("106.15.197.250", 8001);
//        openUrl_setProxy_1("",);
//        openUrl_setProxy_1("",);
//        openUrl_setProxy_1("",);
//        openUrl_setProxy_1("",);
//        openUrl_setProxy_1("",);

        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\ip.log");

        try {
            List<Entity> entityList = new ArrayList<>();
            List<String> list = FileUtils.readLines(file);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String s = list.get(i);
                LUtils.i(s.replaceAll(" ", ""));
                if (s.contains("<tr>")) {
                    Entity entity = new Entity();
                    entity.ip = list.get(i + 1).replaceAll("<td data-title=\"IP\">","").replaceAll("</td>","").replaceAll(" ", "");
                    entity.port = list.get(i + 2).replaceAll("<td data-title=\"PORT\">","").replaceAll("</td>","").replaceAll(" ", "");
                    entityList.add(entity);
                }
            }

            LUtils.i(entityList);
            for (Entity entity : entityList) {
                openUrl_setProxy_1(entity.ip,Integer.parseInt(entity.port));
                LUtils.i("\n\n\n====================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class Entity {
        String ip;
        String port;

        @Override
        public String toString() {
            return ip+"    "+port+"\n\n";
        }
    }


}
