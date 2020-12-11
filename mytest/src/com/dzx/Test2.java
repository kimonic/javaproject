package com.dzx;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Test2 {


    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(getRandomWord());
        }

        System.out.println(builder.toString());
        char[] chars = builder.toString().toCharArray();
        int length = chars.length;
        for (int i = 0; i <length; i++) {
            System.out.println(chars[i]);
        }
    }

    private static void testRandomStr() {

    }


    /**
     * 获取随机汉字
     *
     * @return
     */
    private static String getRandomWord() {
        String str = "";
        int heightPos;
        int lowPos;
        Random rd = new Random();
        heightPos = 176 + Math.abs(rd.nextInt(39));
        lowPos = 161 + Math.abs(rd.nextInt(93));
        byte[] bt = new byte[2];
        bt[0] = Integer.valueOf(heightPos).byteValue();
        bt[1] = Integer.valueOf(lowPos).byteValue();
        try {
            str = new String(bt, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
