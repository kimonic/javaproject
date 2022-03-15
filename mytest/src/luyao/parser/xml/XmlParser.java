package luyao.parser.xml;

import android.util.TypedValue;
import com.dzx.util.LUtils;
import luyao.parser.utils.BytesReader;
import luyao.parser.utils.Utils;
import luyao.parser.xml.bean.Attribute;
import luyao.parser.xml.bean.Xml;
import luyao.parser.xml.bean.chunk.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static luyao.parser.utils.Reader.log;

/**
 * Created by luyao
 * on 2018/12/14 10:00
 */
public class XmlParser {

    private BytesReader reader;

    private List<String> stringChunkList = new ArrayList<>();
    private List<Chunk> chunkList = new ArrayList<>();

    public XmlParser(InputStream in) {
        this.reader = new BytesReader(Utils.readAll(in), true);
    }

    public void parse() {
        //累计读取8字节
        parseHeader();
        parseStringChunk();
//        stringChunkList.clear();
//        int reult = BinaryOperationUtil.parseHeaderAndStringChunk(reader.data, stringChunkList);
//        LUtils.i("reult = ",reult);
//        reader.moveTo(reult); // ResourceIdChunk 之前可能存在 0000,应该是为了对齐
        parseResourceIdChunk();
        parseXmlContentChunk();
        generateXml();
    }

    private void parseHeader() {
        //读取两个int,即8个字节
        try {
            Xml.nameSpaceMap.clear();
            String magicNumber = reader.readHexString(4);
            log("magic number: %s", magicNumber);

            int fileSize = reader.readInt();
            log("file size: %d", fileSize);
        } catch (IOException e) {
            e.printStackTrace();
            log("parse header error!");
        }
    }

    private void parseStringChunk() {
        try {

            String chunkType = reader.readHexString(4);
            log("chunk type: %s", chunkType);

            int chunkSize = reader.readInt();
            log("chunk size: %d", chunkSize);

            int stringCount = reader.readInt();
            log("string count: %d", stringCount);

            int styleCount = reader.readInt();
            log("style count: %d", styleCount);

            reader.skip(4);  // unknown

            int stringPoolOffset = reader.readInt();
            log("string pool offset: %d", stringPoolOffset);

            int stylePoolOffset = reader.readInt();
            log("style pool offset: %d", stylePoolOffset);

            //至此累计跳过36字节

            // 每个 string 的偏移量
            List<Integer> stringPoolOffsets = new ArrayList<>(stringCount);
            for (int i = 0; i < stringCount; i++) {
                stringPoolOffsets.add(reader.readInt());
            }
            int sum = 0;
            int tempCount = 0;
            for (Integer integer : stringPoolOffsets) {
                sum += integer;
//                LUtils.i(tempCount++, "   ", integer);
            }

            LUtils.i("字符串累计偏移量1   ", stringPoolOffsets.size());
            LUtils.i("字符串累计偏移量2  ", sum);

            // 每个 style 的偏移量
            List<Integer> stylePoolOffsets = new ArrayList<>(styleCount);
            for (int i = 0; i < styleCount; i++) {
                stylePoolOffsets.add(reader.readInt());
            }

//            byte[] resultN = reader.readOrigin(stringPoolOffset + stylePoolOffset);
//            int ccount1=0;
//            for (byte b : resultN) {
//                log((ccount1++) + "  =========  " + Integer.toBinaryString(Byte.toUnsignedInt(b)));
//            }

            log("string pool:");
            for (int i = 1; i <= stringCount; i++) { // 没有读最后一个字符串
                String string;
                if (i == stringCount) {
                    int lastStringLength = reader.readShort() * 50;
                    log("lastStringLength 1  " + lastStringLength);
                    log("lastStringLength 1  " + reader.avaliable());
                    if (lastStringLength > reader.avaliable()) {
                        lastStringLength = reader.avaliable() - 10;
                    }

//                    byte[] bytes = intToByte(lastStringLength);
//                    lastStringLength = bytes[3] * 2;

//                    log("lastStringLength 2  " + lastStringLength);
                    byte[] result = reader.readOrigin(lastStringLength);

                    int ccount = 0;
                    int zeroCount = 0;
                    for (byte b : result) {
                        log(ccount + "  =========  " + Integer.toBinaryString(Byte.toUnsignedInt(b)));
                        if (zeroCount == 2) {
                            break;
                        }
                        if (b == 0) {
                            zeroCount++;
                        } else {
                            zeroCount = 0;
                        }
                        ccount++;
                    }

                    byte[] target = new byte[ccount];
                    for (int j = 0; j < ccount - 2; j++) {
                        target[j] = result[j];
                    }

                    string = new String(moveBlank(target));//- 6
                    reader.skip(2);
                } else {
                    reader.skip(2);

//                    reader.positionReduce();
//                    reader.positionReduce();
//                    reader.positionReduce();
//                    reader.positionReduce();
//                    reader.positionReduce();

                    byte[] content = reader.readOrigin(stringPoolOffsets.get(i) - stringPoolOffsets.get(i - 1) - 3);
                    reader.positionReduce();
//                    reader.positionReduce();

//                    reader.positionIncrease();
//                    reader.positionIncrease();
//                    reader.positionIncrease();

                    reader.skip(2);
                    string = new String(moveBlank(content), StandardCharsets.UTF_8);

                }
//                if (i < 5) {
//                }
                log("   %s", string);
                stringChunkList.add(string);
            }


            log("style pool:");
            for (int i = 1; i < styleCount; i++) {
                reader.skip(2);
                byte[] content = reader.readOrigin(stylePoolOffsets.get(i) - stylePoolOffsets.get(i - 1) - 4);
                reader.skip(2);
                String string = new String(content);
                log("   %s", string);
            }

            LUtils.i("chunkSize   = ",chunkSize);
            reader.moveTo(chunkSize + 8); // ResourceIdChunk 之前可能存在 0000,应该是为了对齐

        } catch (IOException e) {
            e.printStackTrace();
            log("parse StringChunk error!");
        }
    }

    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (i > 3) {
                break;
            }
            int shift = (3 - i) * 8;
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

    private void parseResourceIdChunk() {
        try {
            String chunkType = reader.readHexString(4);

            log("chunk type: %s", chunkType);

            int chunkSize = reader.readInt();
            log("chunk size: %d", chunkSize);

            int resourcesIdChunkCount = (chunkSize - 8) / 4;
            for (int i = 0; i < resourcesIdChunkCount; i++) {
                String resourcesId = reader.readHexString(4);
                log("resource id[%d]: %s", i, resourcesId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXmlContentChunk() {
        try {
            while (reader.avaliable() > 0) {
                int chunkType = reader.readInt();
                switch (chunkType) {
                    case Xml.START_NAMESPACE_CHUNK_TYPE:
                        parseStartNamespaceChunk();
                        break;
                    case Xml.START_TAG_CHUNK_TYPE:
                        parseStartTagChunk();
                        break;
                    case Xml.END_TAG_CHUNK_TYPE:
                        parseEndTagChunk();
                        break;
                    case Xml.END_NAMESPACE_CHUNK_TYPE:
                        parseEndNamespaceChunk();
                        break;
                    case Xml.TEXT_CHUNK_TYPE:
                        parseTextChunk();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            log("parse XmlContentChunk error!");
        }
    }

    private void parseStartNamespaceChunk() {
        log("\nparse Start NameSpace Chunk");
        log("chunk type: 0x%x", Xml.START_NAMESPACE_CHUNK_TYPE);

        try {
            int chunkSize = reader.readInt();
            log("chunk size: %d", chunkSize);

            int lineNumber = reader.readInt();
            log("line number: %d", lineNumber);

            reader.skip(4); // 0xffffffff

            int prefix = reader.readInt();
            log("prefix: %s", stringChunkList.get(prefix));

            int uri = reader.readInt();
            log("uri: %s", stringChunkList.get(uri));

            StartNameSpaceChunk startNameSpaceChunk = new StartNameSpaceChunk(chunkSize, lineNumber, prefix, uri);
            chunkList.add(startNameSpaceChunk);

            Xml.nameSpaceMap.put(stringChunkList.get(prefix), stringChunkList.get(uri));
        } catch (IOException e) {
            e.printStackTrace();
            log("parse Start NameSpace Chunk error!");
        }
    }

    private void parseStartTagChunk() {
        log("\nparse Start Tag Chunk");
        log("chunk type: 0x%x", Xml.START_TAG_CHUNK_TYPE);

        try {
            int chunkSize = reader.readInt();
            log("chunk size: %d", chunkSize);

            int lineNumber = reader.readInt();
            log("line number: %d", lineNumber);

            reader.skip(4); // 0xffffffff

            int namespaceUri = reader.readInt();
            if (namespaceUri == -1)
                log("namespace uri: null");
            else
                log("namespace uri: %s", stringChunkList.get(namespaceUri));

            int name = reader.readInt();
            log("name: %s", stringChunkList.get(name));

            reader.skip(4); // flag 0x00140014

            int attributeCount = reader.readInt();
            log("attributeCount: %d", attributeCount);

            int classAttribute = reader.readInt();
            log("class attribute: %s", classAttribute);

            List<Attribute> attributes = new ArrayList<>();
            // 每个 attribute 五个属性，每个属性 4 字节
            for (int i = 0; i < attributeCount; i++) {

                log("Attribute[%d]", i);

                int namespaceUriAttr = reader.readInt();
                if (namespaceUriAttr == -1)
                    log("   namespace uri: null");
                else
                    log("   namespace uri: %s", stringChunkList.get(namespaceUriAttr));

                int nameAttr = reader.readInt();
                if (nameAttr == -1)
                    log("   name: null");
                else
                    log("   name: %s", stringChunkList.get(nameAttr));

                int valueStr = reader.readInt();
                if (valueStr == -1)
                    log("   valueStr: null");
                else
                    log("   valueStr: %s", stringChunkList.get(valueStr));

                int type = reader.readInt() >> 24;
                log("   type: %d", type);

                int data = reader.readInt();
                String dataString = type == TypedValue.TYPE_STRING ? stringChunkList.get(data) : TypedValue.coerceToString(type, data);
                log("   data: %s", dataString);

                Attribute attribute = new Attribute(namespaceUriAttr == -1 ? null : stringChunkList.get(namespaceUriAttr),
                        stringChunkList.get(nameAttr), valueStr, type, dataString);
                attributes.add(attribute);
            }
            StartTagChunk startTagChunk = new StartTagChunk(namespaceUri, stringChunkList.get(name), attributes);
            chunkList.add(startTagChunk);
        } catch (IOException e) {
            e.printStackTrace();
            log("parse Start NameSpace Chunk error!");
        }
    }

    private void parseEndTagChunk() {
        log("\nparse End Tag Chunk");
        log("chunk type: 0x%x", Xml.END_TAG_CHUNK_TYPE);

        try {
            int chunkSize = reader.readInt();
            log("chunk size: %d", chunkSize);

            int lineNumber = reader.readInt();
            log("line number: %d", lineNumber);

            reader.skip(4); // 0xffffffff

            int namespaceUri = reader.readInt();
            if (namespaceUri == -1)
                log("namespace uri: null");
            else
                log("namespace uri: %s", stringChunkList.get(namespaceUri));

            int name = reader.readInt();
            log("name: %s", stringChunkList.get(name));

            EndTagChunk endTagChunk = new EndTagChunk(namespaceUri, stringChunkList.get(name));
            chunkList.add(endTagChunk);
        } catch (IOException e) {
            e.printStackTrace();
            log("parse End Tag Chunk error!");
        }
    }

    private void parseEndNamespaceChunk() {
        log("\nparse End NameSpace Chunk");
        log("chunk type: 0x%x", Xml.END_NAMESPACE_CHUNK_TYPE);

        try {
            int chunkSize = reader.readInt();
            log("chunk size: %d", chunkSize);

            int lineNumber = reader.readInt();
            log("line number: %d", lineNumber);

            reader.skip(4); // 0xffffffff

            int prefix = reader.readInt();
            log("prefix: %s", stringChunkList.get(prefix));

            int uri = reader.readInt();
            log("uri: %s", stringChunkList.get(uri));

            EndNameSpaceChunk endNameSpaceChunk = new EndNameSpaceChunk(chunkSize, lineNumber, prefix, uri);
            chunkList.add(endNameSpaceChunk);

            Xml.nameSpaceMap.put(stringChunkList.get(prefix), stringChunkList.get(uri));
        } catch (IOException e) {
            e.printStackTrace();
            log("parse End NameSpace Chunk error!");
        }
    }

    private void parseTextChunk() {
        log("\nparse Text Chunk");
    }

    private void generateXml() {
        Xml xml = new Xml(stringChunkList, null, chunkList);
        System.out.println(xml.toString());
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

}
