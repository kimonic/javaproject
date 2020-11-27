package com.dzx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class Test1 {
    public static void main(String[] args) {

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
        byte[] b = {-70, -61};
        try {
            System.out.println(new String(b, "gbk"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        URL url = null;
        try {
            url = new URL("https://zhidao.baidu.com/question/544743183.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(),"gbk"));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("29741\tpool-1-thread-13\t0\tjava.lang.Character.isHighSurrogate(C)Z".length());
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
