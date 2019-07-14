package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.aip.nlp.AipNlp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class shao {


	public static void main(String[] args) throws IOException, FileNotFoundException {
		int count=0;
		List<String> list = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\20313\\Desktop\\wangtong\\yingping.txt")),"UTF-8"));
		String lineTxt1 = null;
	   while ((lineTxt1 = br.readLine()) != null) {
		   
		   list.add(lineTxt1);
	   
	   }br.close();
	   System.out.println(list.size());
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:\\Users\\20313\\Desktop\\wangtong\\yingping3.txt")),"UTF-8"));
		for (String string : list) {
			Map<String, JSONArray> mapbig = new HashMap<>();
			JSONObject fromObject = JSONObject.fromObject(string);
			Map<String,JSONArray> map =(Map)fromObject;
			for(Map.Entry<String, JSONArray> entry : map.entrySet()){

				List<String> ls = new ArrayList<>();
				String key = entry.getKey();
				System.out.println(key);
				JSONArray value = entry.getValue();
				for (int i = 0; i < value.size(); i++) {
					JSONObject jsonObject = value.getJSONObject(i);
					Map<String,String> map1 =(Map)jsonObject;
					for(Map.Entry<String,String> entry1 : map1.entrySet()){
						String key2 = entry1.getKey();
						String  aipNlo =AipNlo(key2);
						ls.add(aipNlo);
						count++;
						System.out.println("------------------------"+count);
					}
					
				}
			JSONArray fromObject2 = JSONArray.fromObject(ls);
			mapbig.put(key, fromObject2);
			JSONObject fromObject3 = JSONObject.fromObject(mapbig);
			System.out.println(fromObject3);
			bw.write(fromObject3.toString());
			bw.newLine();
			bw.flush();
			}
		}
		bw.close();
	}
	public static final String APP_ID = "15456997";
    public static final String API_KEY = "mSHOgb6RDK88Hxq0G4OopYAw";
    public static final String SECRET_KEY = "o8SNfZUktGdMVbVPKLfck5EUBoBWRhca";

    public static  String  AipNlo(String test){
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
        //System.setProperty("aip.log4j.conf", "log4j.properties");

        // 调用接口
        
        String sample = sample(client, test);
		return sample;
    }
    public static String sample(AipNlp client, String text) {

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        
        // 情感倾向分析
        org.json.JSONObject res = client.sentimentClassify(text, options);
        return res.toString();

    }
}
