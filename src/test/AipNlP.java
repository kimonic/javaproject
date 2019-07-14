package test;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class AipNlP {
	public static final String APP_ID = "15456997";
    public static final String API_KEY = "mSHOgb6RDK88Hxq0G4OopYAw";
    public static final String SECRET_KEY = "o8SNfZUktGdMVbVPKLfck5EUBoBWRhca";

    public static void main(String[] args) {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "log4j.properties");

        // 调用接口
        
        sample(client);
    }
    public static  void sample(AipNlp client) {
        String text = "苹果是一家伟大的公司";

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        
        // 情感倾向分析
        JSONObject res = client.sentimentClassify(text, options);
        System.out.println(res.toString(2));

    }
}
