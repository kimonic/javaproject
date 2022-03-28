package com.dzx.util;

import android.util.TypedValue;
import luyao.parser.xml.XmlParser;
import luyao.parser.xml.bean.Xml;
import org.apache.commons.io.FileUtils;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BinaryOperationUtil {

    public static void main(String[] args) {

//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\百科日志\\tusou_032\\AndroidManifest.xml");
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\micmanager_12100_1.1.7_test1\\AndroidManifest.xml");
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\com.ygkj.chelaile.standard_4.22.0_266\\AndroidManifest.xml");

        try {
            byte[] data = FileUtils.readFileToByteArray(file);
            List<String> stringPoolList = new ArrayList<>();
            int step2Start = parseHeaderAndStringChunk(data, stringPoolList);
            int step3Start = parseResourceIds(data, step2Start);
            parseXmlContentChunk(data, step3Start, stringPoolList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final int START_NAMESPACE_CHUNK_TYPE = 0x00100100;
    public static final int END_NAMESPACE_CHUNK_TYPE = 0x00100101;
    public static final int START_TAG_CHUNK_TYPE = 0x00100102;
    public static final int END_TAG_CHUNK_TYPE = 0x00100103;
    public static final int TEXT_CHUNK_TYPE = 0x00100104;
    public static final int UTF8_FLAG = 0x00000100;
    public static String blank = "    ";

    public static void parseXmlContentChunk(byte[] data, int start, List<String> stringPoolList) {
        int length = data.length;
        int flag = start;
        int count = 0;
        Map<String, String> nameSpaceMap = new HashMap<>();
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        while (flag < length) {
            int chunkType = byteArrayToIntLowHigh(new byte[]{data[flag], data[flag + 1], data[flag + 2], data[flag + 3]});
            flag += 4;
            switch (chunkType) {
                case START_NAMESPACE_CHUNK_TYPE:
                    //暂时没有需要填充到生成的xml中的内容
                    parseStartNamespaceChunk(data, flag, count, stringPoolList, nameSpaceMap);
                    flag += 20;
                    break;
                case START_TAG_CHUNK_TYPE:
                    int skipCount2 = parseStartTagChunk(data, flag, count, stringPoolList, xmlBuilder, nameSpaceMap);
                    flag += skipCount2;
                    break;
                case END_TAG_CHUNK_TYPE:
                    parseEndTagChunk(data, flag, count, stringPoolList, xmlBuilder, nameSpaceMap);
                    flag += 20;
                    break;
                case END_NAMESPACE_CHUNK_TYPE:
                    parseEndNamespaceChunk(data, flag, count, stringPoolList, nameSpaceMap);
                    flag += 20;
                    break;
                case TEXT_CHUNK_TYPE:
                    LUtils.i("flag = " + flag);
                    parseTextChunk();
                    break;
                default:
                    break;
            }
            count++;
        }
        LUtils.i("===========================================");
        LUtils.i("===========================================");
        LUtils.i("===========================================");
        LUtils.i(xmlBuilder.toString());
        try {
            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\gradle-3.6.3\\Performii\\自解析.xml"), xmlBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void parseTextChunk() {
        LUtils.i("\nparse Text Chunk");
    }

    private static void parseEndNamespaceChunk(byte[] data, int start, int position, List<String> strings, Map<String, String> nameSpaceMap) {
        LUtils.i("\nparse End NameSpace Chunk, chunk type: 0x", Integer.toHexString(END_NAMESPACE_CHUNK_TYPE));

        int flag = start;

        int chunkSize = readFourByteToLowHighInt(data, flag);
        flag += 4;


        int lineNumber = readFourByteToLowHighInt(data, flag);
        //包含跳过的四字节
        flag += 8;


        int prefix = readFourByteToLowHighInt(data, flag);
        flag += 4;

        int uri = readFourByteToLowHighInt(data, flag);
        flag += 4;
        LUtils.i("End chunk size: ", chunkSize, ",line number: ", lineNumber,
                ",prefix: ", strings.get(prefix), ",uri: ", strings.get(uri));
        nameSpaceMap.put(strings.get(uri), strings.get(prefix));
    }


    private static void parseEndTagChunk(byte[] data, int start, int position, List<String> strings,
                                         StringBuilder xmlBuilder, Map<String, String> nameSpaceMap) {
        LUtils.i("\nparse End Tag Chunk,chunk type: 0x", Integer.toHexString(END_TAG_CHUNK_TYPE));

        int flag = start;

        int chunkSize = readFourByteToLowHighInt(data, flag);
        flag += 4;

        int lineNumber = readFourByteToLowHighInt(data, flag);
        //包含跳过的4字节
        flag += 8;

        int namespaceUri = readFourByteToLowHighInt(data, flag);
        flag += 4;

        int name = readFourByteToLowHighInt(data, flag);
        flag += 4;
        LUtils.i("chunk size: ", chunkSize, ",line number: ", lineNumber, ", namespace uri: ",
                ((namespaceUri == -1) ? "null" : strings.get(namespaceUri)), " , name: ", strings.get(name));

        xmlBuilder.append("\t\t</").append(strings.get(name)).append(">\n\n");
    }


    public static int parseStartTagChunk(byte[] data, int start, int position, List<String> strings,
                                         StringBuilder xmlBuilder, Map<String, String> nameSpaceMap) {
        LUtils.i("\nparse Start Tag Chunk,chunk type: 0x", Integer.toHexString(START_TAG_CHUNK_TYPE));

        int flag = start;

        int chunkSize = readFourByteToLowHighInt(data, flag);
        flag += 4;

        int lineNumber = readFourByteToLowHighInt(data, flag);
        //包含跳过的4字节
        flag += 8;


        int namespaceUri = readFourByteToLowHighInt(data, flag);
        flag += 4;

        int nameIndex = readFourByteToLowHighInt(data, flag);
        //包含跳过的4字节
        flag += 8;

        int attributeCount = readFourByteToLowHighInt(data, flag);
        flag += 4;

        int classAttribute = readFourByteToLowHighInt(data, flag);
        flag += 4;


        LUtils.i("Start Tag chunk size:", chunkSize, ",line number:", lineNumber, ",namespace uri: ",
                ((namespaceUri == -1) ? "null" : strings.get(namespaceUri)), ",name:", strings.get(nameIndex),
                ",attributeCount: ", attributeCount, ",class attribute: ", classAttribute);

        String name = strings.get(nameIndex);
        String nameSpaceUri = ((namespaceUri == -1) ? "null" : strings.get(namespaceUri));
        xmlBuilder.append("\n");
        if (name.replace(" ", "").replaceAll("\\u0000", "").equals("manifest")) {
            xmlBuilder.append("<manifest");
            if (namespaceUri == -1) {
                for (String s : nameSpaceMap.keySet()) {
                    xmlBuilder.append(XmlParser.format(" xmlns:%s=\"%s\"", nameSpaceMap.get(s), s));
                }
            }
        } else {
            if (attributeCount == 0) {
                xmlBuilder.append(XmlParser.format("    %s<%s>", Xml.BLANK, name));
            } else {
                xmlBuilder.append(XmlParser.format("    %s<%s", Xml.BLANK, name));
            }
        }
        xmlBuilder.append(blank);

        StringBuilder builder = new StringBuilder();
        // 每个 attribute 五个属性，每个属性 4 字节
        for (int i = 0; i < attributeCount; i++) {

            int namespaceUriAttr = readFourByteToLowHighInt(data, flag);
            flag += 4;

            int nameAttr = readFourByteToLowHighInt(data, flag);
            flag += 4;

            int valueStr = readFourByteToLowHighInt(data, flag);
            flag += 4;

            int type = readFourByteToLowHighInt(data, flag) >> 24;
            flag += 4;

            int dataIndex = readFourByteToLowHighInt(data, flag);
            flag += 4;
            String dataString = type == TypedValue.TYPE_STRING ? strings.get(dataIndex) : TypedValue.coerceToString(type, dataIndex);

            LUtils.i("Attribute[", i, "]", ", namespace uri: ", ((namespaceUriAttr == -1) ? "null" : strings.get(namespaceUriAttr)),
                    ",name: ", ((nameAttr == -1) ? "null" : strings.get(nameAttr)), ",type: ", type, ",valueStr: ",
                    ((valueStr == -1) ? "null" : strings.get(valueStr)), ",dataString: ", dataString);

            String nameSpaceUriA = ((namespaceUriAttr == -1) ? "null" : strings.get(namespaceUriAttr));
            String prefix = getNamespacePrefix(nameSpaceMap.get(nameSpaceUriA));

            builder.append(format("\n\t\t%s%s%s=\"%s\"", "", prefix, strings.get(nameAttr), dataString));

            if (i == attributeCount - 1) builder.append(">");
        }

        xmlBuilder.append(builder.toString()).append("\n");

        return 32 + attributeCount * 20;
    }

    public static String format(String format, Object... params) {
        return String.format(format, params);
    }

    public static String getNamespacePrefix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return "";
        }
        return prefix + ":";
    }

    public static int readFourByteToLowHighInt(byte[] data, int start) {
        byte[] bytes = new byte[]{data[start], data[start + 1], data[start + 2], data[start + 3]};
        return byteArrayToIntLowHigh(bytes);
    }

    public static void parseStartNamespaceChunk(byte[] data, int start, int position, List<String> strings, Map<String, String> nameSpaceMap) {
        //parse Start NameSpace Chunk
        //chunk type: 0x100100
        //chunk size: 24
        //line number: 2
        //prefix: android
        //uri: http://schemas.android.com/apk/res/android
        LUtils.i("\nparse Start NameSpace Chunk,chunk type: 0x", Integer.toHexString(START_NAMESPACE_CHUNK_TYPE));

        int flag = start;
        int chunkSize = readFourByteToLowHighInt(data, flag);
        flag += 4;

        int lineNumber = readFourByteToLowHighInt(data, flag);
        //包含跳过4字节
        flag += 8;

        int prefix = readFourByteToLowHighInt(data, flag);
        flag += 4;

        int uri = readFourByteToLowHighInt(data, flag);
        flag += 4;
        nameSpaceMap.put(strings.get(uri), strings.get(prefix));
        LUtils.i("prefixString: == ", strings.get(prefix), " ,uriString: == ", strings.get(uri), ",name space chunk size:",
                chunkSize, ",line number:", lineNumber, ",prefixPosition: ", prefix, ",uriPosition: ", uri, ",   position = ", position);

    }


    public static int parseHeaderAndStringChunk(byte[] data, List<String> stringChunkList) {

        int flag = 0;

        //第一个四字节魔数
        int magicNum = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        LUtils.i("魔数 = ", magicNum);

        //读取文件字字节数
        int fileByteLength = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        LUtils.i("文件总字节数  ", fileByteLength);

        //块类型,应该改为固定值
        int chunkType = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        LUtils.i("chunk type 块类型 ", chunkType);

        //块大小
        int chunkSize = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        LUtils.i("chunk size 块大小 ", chunkSize);

        //字符串数量
        int stringCount = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        LUtils.i("string count: ", stringCount);

        //style 数量
        int styleCount = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        LUtils.i("style count:  ", styleCount);

        //字符编码标识,确定是否为utf-8
        int charSetFlag = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        boolean isUtf_8 = (charSetFlag & UTF8_FLAG) != 0;
        LUtils.i("charSetFlag:  ", charSetFlag, ", isUtf_8 = ", isUtf_8);


        //字符串在字符串池中的偏移量
        int stringPoolOffset = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        LUtils.i("string pool offset:  ", stringPoolOffset);

        int stylePoolOffset = byteArrayToIntLowHigh(new byte[]{data[flag++], data[flag++], data[flag++], data[flag++]});
        LUtils.i("style pool offset:  ", stylePoolOffset);
        LUtils.i("准备读取字符串偏移量  至此flag的值应为36  flag = ", flag);


        //读取每个字符串在字符串池中的偏移量
        List<Integer> list = new ArrayList<>();
        int stringPoolOffsetStart = flag;
        int stringPoolOffsetStartEnd = flag + stringCount * 4;
        for (int i = stringPoolOffsetStart; i < stringPoolOffsetStartEnd; i = i + 4) {
            int offset = byteArrayToIntLowHigh(new byte[]{data[i], data[i + 1], data[i + 2], data[i + 3]});
            list.add(offset);
        }


        // 读取每个style在style池中的偏移量
        flag = stringPoolOffsetStartEnd;
        int stylePoolOffsetStart = flag;
        int stylePoolOffsetEnd = flag + 4 * styleCount;
        List<Integer> stylePoolOffsets = new ArrayList<>(styleCount);
        for (int i = stylePoolOffsetStart; i < stylePoolOffsetEnd; i++) {
            int offset = byteArrayToIntLowHigh(new byte[]{data[i], data[i + 1], data[i + 2], data[i + 3]});
            stylePoolOffsets.add(offset);
        }

        //跳过两字节分隔,准备读取字符串池
        flag = stylePoolOffsetEnd + 2;
        int checkNode1 = 36 + stringCount * 4 + styleCount * 4 + 2;
        LUtils.i("校验flag的值是否正确   ", (flag == checkNode1));

        int size;
        int offsetSize = list.size();
        for (int i = 1; i < offsetSize; i++) {
            size = list.get(i) - list.get(i - 1) - 2;
            byte[] bytes = getByteArray(size, flag, data);
            String temp = "";
            if (isUtf_8) {
                temp = new String(bytes);
            } else {
                temp = decodeString(bytes);
            }
            LUtils.i(i, "   ", temp);
            stringChunkList.add(temp.replaceAll("\\u0000", ""));
            flag = flag + size + 2;
        }

        int checkNode2 = checkNode1 + list.get(offsetSize - 1);
        LUtils.i("校验flag的值是否正确   ", (flag == checkNode2), "  ", checkNode2, "   ", flag);
        byte[] lastStringBytes = getByteUntilTwoZero(data, flag);
        String lastString = new String(moveBlank(lastStringBytes));
        stringChunkList.add(lastString);
        LUtils.i(offsetSize, "   ", lastString);
        flag += lastStringBytes.length;

        //继续读取style pool ,目前未发现有该内容存在的androidManifest.xml文件

        return chunkSize + 8;
    }

    /**
     * 以小端方式读取Unicode字符
     * UTF-16LE  小端编码方式
     * UTF-16BE  大端编码方式
     * <p>
     * 从文件中读取的字节顺序   a4 64
     * 小端值     0x64a4
     * 大端值     0xa464
     */
    private static String decodeString(byte[] bytes) {
        //a4  64  de  56  88  63  43  67
        try {
            return StandardCharsets.UTF_16LE.newDecoder().decode(
                    ByteBuffer.wrap(bytes)).toString();
        } catch (CharacterCodingException ex) {
            return null;
        }
    }

    /**
     * 解析resourceId
     */
    public static int parseResourceIds(byte[] data, int start) {
        int flag = start;
        byte[] resourceIdChunkType = getByteArray(4, start, data);
        String type = outByteArrayToHexAloneStringHighLow(resourceIdChunkType);
        LUtils.i("resourceIdChunkType : ", type);
        flag += 4;

        byte[] resourceIdChunkSize = getByteArray(4, flag, data);
        int idSize = byteArrayToIntLowHigh(resourceIdChunkSize);
        LUtils.i("resourceIdChunkSize : ", idSize);
        flag += 4;

        byte[] temp = new byte[4];
        for (int i = flag; i < start + idSize; i = i + 4) {
            temp[0] = data[i];
            temp[1] = data[i + 1];
            temp[2] = data[i + 2];
            temp[3] = data[i + 3];
            String id = outByteArrayToHexAloneStringHighLow(temp);
            LUtils.i("resource id[", (i - flag) / 4, "]: ", id, "\n");
        }
        return start + idSize;
    }

    public static byte[] getByteUntilTwoZero(byte[] array, int start) {
        List<Byte> temp = new ArrayList<>();
        int size = array.length;
        int zeroCount = 0;
        for (int i = start; i < size; i++) {
            if (zeroCount == 2) {
                break;
            }
            if (array[i] == 0) {
                zeroCount++;
            } else {
                zeroCount = 0;
            }
            temp.add(array[i]);
        }
        int tempSize = temp.size();
        LUtils.i(tempSize);
        if (tempSize < 2) {
            return new byte[0];
        }
        int resultSize = tempSize - 2;
        byte[] result = new byte[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = temp.get(i);
        }
        return result;
    }

    public static byte[] getByteArray(int byteSize, int start, byte[] array) {
        byte[] string3 = new byte[byteSize];
        for (int i = start; i < start + byteSize; i++) {
            string3[i - start] = array[i];
        }
        return string3;
    }


    /**
     * 将异常宽字符转化为正常窄字符byte数组
     */
    public static byte[] moveBlank(byte[] data) {
        List<Byte> byteList = new ArrayList<>();
        for (Byte b : data) {
            if (b != 0) byteList.add(b);
        }
        byte[] result = new byte[byteList.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = byteList.get(i);
        return result;
    }

    /**
     * byte数组转化为int,高位在前,低位在后
     */
    public static int byteArrayToIntHighLow(byte[] bytes) {
        if (bytes == null) {
            System.out.println("bytes is null");
            return 0;
        }
        if (bytes.length != 4) {
            System.out.println("bytes length is not int need 4");
            return 0;
        }
        int value = 0;
        for (int i = 0; i < bytes.length; i++) {
            int shift = (3 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    /**
     * byte数组转化为int,低位在前,高位在后
     */
    public static int byteArrayToIntLowHigh(byte[] bytes) {
        if (bytes == null) {
            System.out.println("bytes is null");
            return 0;
        }
        if (bytes.length != 4) {
            System.out.println("bytes length is not int need 4");
            return 0;
        }
        int value = 0;
        for (int i = 0; i < bytes.length; i++) {
            int shift = i * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    /**
     * int 转化为byte数组,高位在前,低位在后
     */
    public static byte[] intToByteHighLow(int in) {
        byte[] b = new byte[4];
        b[3] = (byte) (in & 0xff);
        b[2] = (byte) (in >> 8 & 0xff);
        b[1] = (byte) (in >> 16 & 0xff);
        b[0] = (byte) (in >> 24 & 0xff);
        return b;
    }


    /**
     * int 转化为byte数组,低位在前,高位在后
     */
    public static byte[] intToByteLowHigh(int in) {
        byte[] b = new byte[4];
        b[0] = (byte) (in & 0xff);
        b[1] = (byte) (in >> 8 & 0xff);
        b[2] = (byte) (in >> 16 & 0xff);
        b[3] = (byte) (in >> 24 & 0xff);
        return b;
    }


    /**
     * 将byte输出为二进制字符串
     */
    public static String outBinaryString(byte target) {
        String result = "";
        String temp = Integer.toBinaryString(Byte.toUnsignedInt(target));
        if (temp.length() != 8) {
            result = binaryCompensateZero(temp);
        } else {
            result = temp;
        }
        return result;
    }

    /**
     * 二进制字符串左侧补齐8位0
     */
    public static String binaryCompensateZero(String s) {
        if (s == null || s.length() == 0) {
            return "00000000";
        }
        if (s.length() > 7) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        int length = s.length();
        for (int i = length; i < 8; i++) {
            result.append("0");
        }
        return result.toString() + s;
    }

    /**
     * byte转化为16进制字符串
     */
    public static String byteToHexString(byte target) {
        String result = Integer.toHexString(Byte.toUnsignedInt(target));
        if (result.length() < 2) {
            result = "0" + result;
        }
        return result;
    }

    /**
     * 输出byte数组的16进制字符串
     */
    public static String outByteArrayToHexString(byte[] target) {
        StringBuilder builder = new StringBuilder();
        for (byte b : target) {
            builder.append(byteToHexString(b)).append("  ");
        }
        builder.append("\n");
        return builder.toString();
    }

    public static String outByteArrayToHexStringByBlock(byte[] target, int blockSize) {
        StringBuilder builder = new StringBuilder();
        byte[] bytes = new byte[blockSize];
        Arrays.fill(bytes, (byte) 0);

        for (int i = 0; i < target.length; i++) {
            if (i > 0 && i % blockSize == 0) {
                builder.append(outByteArrayToHexString(bytes)).append("\n");
                Arrays.fill(bytes, (byte) 0);
            }
            bytes[i % blockSize] = target[i];

        }
        builder.append(outByteArrayToHexString(bytes));
        return builder.toString();
    }

    /**
     * 输出byte数组为单独的一个16进制字符串
     */
    public static String outByteArrayToHexAloneStringLowHigh(byte[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append("0x");
        for (byte b : target) {
            builder.append(byteToHexString(b));
        }
        return builder.toString();
    }

    /**
     * 输出byte数组为单独的一个16进制字符串
     */
    public static String outByteArrayToHexAloneStringHighLow(byte[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append("0x");
        int length = target.length;
        for (int i = length - 1; i > -1; i--) {
            builder.append(byteToHexString(target[i]));
        }
        return builder.toString();
    }

    /**
     * 输出byte数组的二进制字符串
     */
    public static String outByteArrayToBinaryString(byte[] target) {
        StringBuilder builder = new StringBuilder();
        for (byte b : target) {
            builder.append(outBinaryString(b)).append("  ");
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * 判断字符串是否包含中文乱码
     * 针对Unicode字符按照utf-8编码方式解码造成的乱码
     * <p>
     * 乱码的一种表现即为Unicode的中文表示,对应编码为utf-16(分Le,be两种)
     *
     * @param strText 需要判断的字符串
     * @return 字符串包含乱码则返回true, 字符串不包含乱码则返回false
     */
    public static boolean isMessyCode(String strText, byte[] bytes) {
        char[] chars = new char[strText.length()];
        strText.getChars(0, strText.length(), chars, 0);
        for (char c : chars) {
            if (!isPrintableChar(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将字节数组转化为汉字,两个字节一个汉字,数组中低位在前,高位在后
     */
    public static String getChineseChar(byte[] bytes) {
        StringBuilder out = new StringBuilder();
        int length = bytes.length;
        for (int i = 0; i < length; i = i + 2) {
            out.append(twoByteConvertString(bytes[i + 1], bytes[i]));
        }
        return out.toString();
    }

    /**
     * 16进制两字节插入字符,16进制高位在前,低位在后
     */
    public static char twoByteConvertString(byte front, byte after) {
        //运算符优先级,注意括号
        int value = ((front & 0xFF) << 8) + (after & 0xff);
        return (char) (value);
    }

    /**
     * 字符串转unicode
     *
     * @param str
     * @return
     */
    public static String stringToUnicode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            String hexString = Integer.toHexString(c[i]);
            String zero = "";
            if (hexString.length() != 4) {
                zero = supplementZero(4 - hexString.length());
            }
            sb.append("\\u").append(zero).append(hexString);
        }
        return sb.toString();
    }

    public static String supplementZero(int type) {
        switch (type) {
            case 0:
                return "";
            case 1:
                return "0";
            case 2:
                return "00";
            case 3:
                return "000";
            case 4:
                return "0000";
            case 5:
                return "00000";
            case 6:
                return "000000";
            case 7:
                return "0000000";
            case 8:
                return "00000000";
            default:
                break;
        }
        return "";
    }

    /**
     * unicode转字符串
     *
     * @param unicode
     * @return
     */
    public static String unicodeToString(String unicode) {
        StringBuffer sb = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int index = Integer.parseInt(hex[i], 16);
            sb.append((char) index);
        }
        return sb.toString();
    }

    private static boolean isPrintableChar(char c) {
        //char(32) 是空格,所以下边界为32
        if ((int) (c) > 126 || (int) (c) < 32) {
            LUtils.i("不符合条件的字符    ", (int) (c));
            return false;
        }
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return !Character.isISOControl(c) && c != KeyEvent.CHAR_UNDEFINED
                && block != null && block != Character.UnicodeBlock.SPECIALS;
    }


}
