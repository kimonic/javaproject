package com.dzx.util;


/**
 * @author tqf
 * @Description
 * @Version 1.0
 * @since 2020-09-23 15:48
 */

import okhttp3.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HttpClientUtil {
    //private static Logger log = Logger.getLogger(HttpClientUtil.class.getClass());

    public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";

    private static final String URL_PARAM_CONNECT_FLAG = "&";

    private static final String EMPTY = "";

    private static MultiThreadedHttpConnectionManager connectionManager = null;

    private static int connectionTimeOut = 25000;

    private static int socketTimeOut = 25000;

    private static int maxConnectionPerHost = 20;

    private static int maxTotalConnections = 20;

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
        connectionManager.getParams().setSoTimeout(socketTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);

        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
    }
//:
//:
//:
//:
//:
//:
//Cookie: JSESSIONID=D45735C34F1955A1FCD0645AABD88E90; Hm_lvt_ac3da4632dc24e9d361235e3b2d3a131=1612603777,1612605309; ting55_history=https%3A%2F%2Fting55.com%2Fbook%2F11250-15%2560%25E5%2594%2590%25E7%25A0%2596%25EF%25BC%2588%25E5%25B0%2596%25E5%2584%25BF%25EF%25BC%2589%25E6%259C%2589%25E5%25A3%25B0%25E5%25B0%258F%25E8%25AF%25B4%25E7%25AC%25AC15%25E7%25AB%25A0; Hm_lpvt_ac3da4632dc24e9d361235e3b2d3a131=1612605416
//:
//:
//:

    public static void main(String[] args) {
        requestLianTingPost("150");
    }

    private static void okHttpTest() {
        System.out.println("===================");
        // 初始化 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        // 初始化请求体
        Request request = new Request.Builder()
                .get()
                .url("http://ting55.com/book/11250-15")
                .build();
        // 得到返回Response
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.isSuccessful());
                System.out.println(response.body().string());
            }
        });

    }


    /**
     * Post提交表单
     */
    public static void postFromParameters(String url, Map<String, String> headParam, Map<String, Object> params) {
        OkHttpClient okHttpClient = new OkHttpClient(); // OkHttpClient对象

        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, (String) params.get(key));
        }
        RequestBody formBody = builder.build(); // 表单键值对

        Request.Builder builder1 = new Request.Builder();
        for (String key : headParam.keySet()) {
            builder1.addHeader(key, headParam.get(key));
        }

        Request request = builder1.url(url).post(formBody).build(); // 请求
        okHttpClient.newCall(request).enqueue(new Callback() {// 回调

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());//成功后的回调
            }

            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());//失败后的回调
            }
        });

    }

    private static void requestLianTingPost(String page) {
        String url = "https://ting55.com/glink";
        Map<String, String> headMap = new HashMap<>();
        headMap.put("Accept", "application/json, text/javascript, */*; q=0.01");
        headMap.put("Accept-Encoding", "gzip, deflate, br");
        headMap.put("Accept-Language", "zh-CN,zh;q=0.9");

        headMap.put("Connection", "close");
        headMap.put("Content-Length", "28");
        headMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");


        headMap.put("Host", "ting55.com");
        headMap.put("Origin", "https://ting55.com");
        headMap.put("Referer", "https://ting55.com/book/11250-15");

        headMap.put("sec-ch-ua", "\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");
        headMap.put("sec-ch-ua-mobile", "?0");
        headMap.put("Sec-Fetch-Dest", "empty");
        headMap.put("Sec-Fetch-Mode", "cors");
        headMap.put("Sec-Fetch-Site", "same-origin");
        headMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36");
        headMap.put("X-Requested-With", "XMLHttpRequest");
        headMap.put("xt", "7ccdf19c1cf74b27b9c21f21e6464d3a");//e4764781305d9b55850698161dc6089a--

        headMap.put("Cookie", "JSESSIONID=D45735C34F1955A1FCD0645AABD88E90; Hm_lvt_ac3da4632dc24e9d361235e3b2d3a131=1612603777,1612605309; ting55_history=https%3A%2F%2Fting55.com%2Fbook%2F11250-15%2560%25E5%2594%2590%25E7%25A0%2596%25EF%25BC%2588%25E5%25B0%2596%25E5%2584%25BF%25EF%25BC%2589%25E6%259C%2589%25E5%25A3%25B0%25E5%25B0%258F%25E8%25AF%25B4%25E7%25AC%25AC15%25E7%25AB%25A0; Hm_lpvt_ac3da4632dc24e9d361235e3b2d3a131=" + System.currentTimeMillis() / 1000);
        OkHttpClient client = new OkHttpClient();

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("bookId", "11250");
        paramsMap.put("isPay", "2");
        paramsMap.put("page", page);
//        URLPost(url, headMap, paramsMap);
        postFromParameters(url, headMap, paramsMap);
    }

    private static HttpClient client = new HttpClient(connectionManager);

    public static String URLPost(String url, Map<String, String> headParam, Map<String, Object> params) {
        String response = "";
        PostMethod method = null;
        try {
            method = new PostMethod(url);
//            String transJson = new Gson().toJson(params);
            Iterator headInfo = headParam.keySet().iterator();
            while (headInfo.hasNext()) {
                String key = (String) headInfo.next();
                String value = (String) headParam.get(key);
                method.setRequestHeader(key, value);
            }

//            RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
//            method.setRequestEntity(se);
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = (String) params.get(key);
                method.addParameter(key, value);
            }

            method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());

            method.getParams().setParameter("http.socket.timeout", Integer.valueOf(10000));

            int statusCode = client.executeMethod(method);
            if (statusCode == 200) {
                response = method.getResponseBodyAsString();
            } else {
                //log.info("响应状态码 = " + method.getStatusCode());
                response = method.getResponseBodyAsString();
            }
            System.out.println(response);
        } catch (Exception e) {
            //log.info("发生异常" + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    public static String URLPostForm(String url, Map<String, String> params, String Authorization) {
        String response = "";
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = (String) params.get(key);
                postMethod.addParameter(key, value);
            }
            int statusCode = new HttpClient().executeMethod(postMethod);
            if (statusCode == 200) {
                response = postMethod.getResponseBodyAsString();
                //log.info(response);
            } else {
                //log.info("响应状态码 = " + postMethod.getStatusCode());
            }
        } catch (HttpException e) {
            //log.info("发生致命的异常，可能是协议不对或者返回的内容有问题" + e);
        } catch (IOException e) {
            //log.info("发生网络异常" + e);
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
                postMethod = null;
            }
        }
        return response;
    }
}
