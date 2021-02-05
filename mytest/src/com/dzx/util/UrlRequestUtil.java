package com.dzx.util;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UrlRequestUtil {


    private static final int connectTimeout = 1000 * 120;    // 连接超时时间
    private static final int socketTimeout = 1000 * 180;    // 读取数据超时时间

    /**
     * 向指定 URL 发送 POST请求
     *
     * @param strUrl        发送请求的 URL
     * @param requestParams 请求参数，格式 name1=value1&name2=value2
     * @return 远程资源的响应结果
     */
    public static String sendPost(String strUrl, String requestParams) {
        System.out.println("sendPost strUrl:" + strUrl);
        System.out.println("sendPost requestParams:" + requestParams);

        URL url = null;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(strUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Accept", "application/json");    // 设置接收数据的格式
            httpURLConnection.setRequestProperty("Content-Type", "application/json");  // 设置发送数据的格式
            httpURLConnection.connect();    // 建立连接
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    httpURLConnection.getOutputStream(), "UTF-8");
            outputStreamWriter.append(requestParams);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            // 使用BufferedReader输入流来读取URL的响应
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream(), "utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String strLine = "";
            while ((strLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(strLine);
            }
            bufferedReader.close();
            String responseParams = stringBuffer.toString();

            System.out.println("sendPost responseParams:" + responseParams);

            return responseParams;
        } catch (IOException e) {
            System.out.println("sendPost IOException:" + e.getMessage());
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return null;
    }

    /**
     * HttpClientPost 方式，向指定 URL 发送 POST请求
     *
     * @param strUrl        发送请求的 URL
     * @param requestParams 请求参数
     * @return 远程资源的响应结果
     */
    public static String doPost(String strUrl, List<BasicNameValuePair> requestParams) {
        System.out.println("doPost strUrl:" + strUrl);
        System.out.println("doPost requestParams:" + requestParams);

        String responseParams = "";
        StringBuffer stringBuffer = new StringBuffer();
        long startTime = 0, endTime = 0;

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .build();    // 设置请求和传输超时时间

        HttpPost httpPost = new HttpPost(strUrl);
        httpPost.setConfig(requestConfig);
        HttpEntity httpEntity;

        try {
            if (requestParams != null) {
                // 设置相关参数
                httpEntity = new UrlEncodedFormEntity(requestParams, "UTF-8");
                httpPost.setEntity(httpEntity);

                System.out.println("doPost requestParams:" + EntityUtils.toString(httpEntity));
            }
            startTime = System.nanoTime();
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
            int code = closeableHttpResponse.getStatusLine().getStatusCode();

            System.out.println("doPost 状态码:" + code);

            if (code == 200 || code == 500) {
                try {
                    httpEntity = closeableHttpResponse.getEntity();
                    if (httpEntity != null) {
                        long length = httpEntity.getContentLength();
                        // 当返回值长度较小的时候，使用工具类读取
                        if (length != -1 && length < 2048) {
                            stringBuffer.append(EntityUtils.toString(httpEntity));
                        } else {    // 否则使用IO流来读取
                            BufferedReader bufferedReader = new BufferedReader(
                                    new InputStreamReader(httpEntity.getContent(), "UTF-8"));
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                stringBuffer.append(line);
                            }
                            bufferedReader.close();
                            responseParams = stringBuffer.toString();
                        }
                        endTime = System.nanoTime();
                    }
                } catch (Exception e) {
                    endTime = System.nanoTime();

                    System.out.println("doPost Exception（通讯错误）:" + e.getMessage());
                } finally {
                    closeableHttpResponse.close();
                }
            } else {
                endTime = System.nanoTime();
                httpPost.abort();

                System.out.println("doPost 错误请求，状态码:" + code);
            }
        } catch (IOException e) {
            endTime = System.nanoTime();

            System.out.println("doPost IOException:" + e.getMessage());
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
            }
        }

        System.out.println("doPost 用时（毫秒）:" + (endTime - startTime) / 1000000L);
        System.out.println("doPost responseParams:" + responseParams);

        return responseParams;
    }

    /**
     * 向指定 URL 发送 GET请求
     *
     * @param strUrl        发送请求的 URL
     * @param requestParams 请求参数
     * @return 远程资源的响应结果
     */
    public static String sendGet(String strUrl, String requestParams) {
        System.out.println("sendGet strUrl:" + strUrl);
        System.out.println("sendGet requestParams:" + requestParams);

        String responseParams = "";
        BufferedReader bufferedReader = null;
        try {
            String strRequestUrl = strUrl + "?" + requestParams;
            URL url = new URL(strRequestUrl);
            URLConnection urlConnection = url.openConnection();    // 打开与 URL 之间的连接

            // 设置通用的请求属性
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            urlConnection.connect();    // 建立连接

            Map<String, List<String>> map = urlConnection.getHeaderFields();    // 获取所有响应头字段

            // 使用BufferedReader输入流来读取URL的响应
            bufferedReader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                responseParams += strLine;
            }
        } catch (Exception e) {
            System.out.println("sendGet Exception:" + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        System.out.println("sendPost responseParams:" + responseParams);

        return responseParams;
    }


    public static String sendMyPost(int page, String xt) {
        String strUrl = "https://m.ting55.com/glink";

        URL url = null;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(strUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(true);

            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");    // 设置接收数据的格式
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");


            httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            httpURLConnection.setRequestProperty("Host", "m.ting55.com");
            httpURLConnection.setRequestProperty("Origin", "https://m.ting55.com");


            httpURLConnection.setRequestProperty("Referer", "https://m.ting55.com/book/11250-59");
            httpURLConnection.setRequestProperty("sec-ch-ua", "&#34;Chromium&#34;;v=&#34;88&#34;, &#34;Google Chrome&#34;;v=&#34;88&#34;, &#34;;Not\\\\A\\&#34;Brand&#34;;v=&#34;99&#34;");

            httpURLConnection.setRequestProperty("sec-ch-ua-mobile", "?1");
            httpURLConnection.setRequestProperty("Sec-Fetch-Dest", "empty");
            httpURLConnection.setRequestProperty("Sec-Fetch-Mode", "cors");
            httpURLConnection.setRequestProperty("Sec-Fetch-Site", "same-origin");

            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.146 Mobile Safari/537.36");
            httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");


            httpURLConnection.setRequestProperty("xt", "259e842674e3614895cf7b70aaf399c0");
            httpURLConnection.setRequestProperty("Connection", "close");
            httpURLConnection.setRequestProperty("Content-Length", "28");
//            httpURLConnection.setRequestProperty("Cookie", "Hm_lvt_ac3da4632dc24e9d361235e3b2d3a131=1611812691,1611814682,1612503927; mhting55=a5b8a455c3da0f87; JSESSIONID=6B5AFB28E1E32ADF0AC5684D741A8EFE; Hm_lpvt_ac3da4632dc24e9d361235e3b2d3a131=1612520635; t55hm=1");



            //:
            httpURLConnection.connect();    // 建立连接
            System.out.println("==============================");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    httpURLConnection.getOutputStream(), "UTF-8");
            outputStreamWriter.append("bookId=11250&isPay=0&page=" + page);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            // 使用BufferedReader输入流来读取URL的响应
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream(), "UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String strLine = "";
            while ((strLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(strLine);
            }
            bufferedReader.close();
            String responseParams = stringBuffer.toString();
            if (responseParams.contains("m4a")) {
                try {
                    FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\直接获取.txt"), responseParams + "-------" + page + "\n", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("sendPost responseParams:" + responseParams + "    " + page);

            return responseParams;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("sendPost IOException:" + e.getMessage());
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return null;
    }

    private static List<String> randomList = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "a", "b", "c", "d", "e", "f"));

    public static String getRandom() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            builder.append(randomList.get((int) (Math.random() * 15 + 1)));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        for (int i = 59; i < 930; i++) {
            sendMyPost(i, "");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

