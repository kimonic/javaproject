package com.dzx;

import brut.androlib.res.util.ExtMXSerializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Random;

public class Test2 {


    public static void main(String[] args) {


    }


    private static String decodeString(byte[] bytes) {
        //a4  64  de  56  88  63  43  67
        try {
            return Charset.forName("UTF-16LE").newDecoder().decode(
                    ByteBuffer.wrap(bytes)).toString();
        } catch (CharacterCodingException ex) {
            return null;
        }
    }

    public static ExtMXSerializer getResXmlSerializer() {
        ExtMXSerializer serial = new ExtMXSerializer();
        serial.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-indentation", "    ");
        serial.setProperty("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator", System.getProperty("line.separator"));
        serial.setProperty("DEFAULT_ENCODING", "utf-8");
        serial.setDisabledAttrEscape(true);
        return serial;
    }

    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            if (i > 3) {
                break;
            }
            int shift = i * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    private static byte[] intToByte(int in) {
        byte[] b = new byte[4];
        b[3] = (byte) (in & 0xff);
        b[2] = (byte) (in >> 8 & 0xff);
        b[1] = (byte) (in >> 16 & 0xff);
        b[0] = (byte) (in >> 24 & 0xff);
        return b;
    }


    /**
     * 随机汉字
     */
    private static void testRandomStr() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(getRandomWord());
        }

        System.out.println(builder.toString());
        char[] chars = builder.toString().toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            System.out.println(chars[i]);
        }
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
