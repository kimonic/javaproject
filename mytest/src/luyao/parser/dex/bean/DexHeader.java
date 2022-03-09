package luyao.parser.dex.bean;

import luyao.parser.utils.Reader;
import luyao.parser.utils.TransformUtils;

import java.io.IOException;

import static luyao.parser.utils.Reader.log;

public class DexHeader {

    /*
    struct DexHeader {
    u1  magic[8];
    u4  checksum;
    u1  signature[kSHA1DigestLen];
    u4  fileSize;
    u4  headerSize;
    u4  endianTag;
    u4  linkSize;
    u4  linkOff;
    u4  mapOff;
    u4  stringIdsSize;
    u4  stringIdsOff;
    u4  typeIdsSize;
    u4  typeIdsOff;
    u4  protoIdsSize;
    u4  protoIdsOff;
    u4  fieldIdsSize;
    u4  fieldIdsOff;
    u4  methodIdsSize;
    u4  methodIdsOff;
    u4  classDefsSize;
    u4  classDefsOff;
    u4  dataSize;
    u4  dataOff;
    }
    */

    private Reader reader;
    public String magic;
    public long checkSum;
    public String signature;
    public int file_size;
    public int header_size;
    public int endian_tag;
    public int link_size;
    public int link_off;
    public int map_off;
    public int string_ids__size;
    public int string_ids_off;
    public int type_ids__size;
    public int type_ids_off;
    public int proto_ids__size;
    public int proto_ids_off;
    public int field_ids__size;
    public int field_ids_off;
    public int method_ids_size;
    public int method_ids_off;
    public int class_defs_size;
    public int class_defs_off;
    public int data_size;
    public int data_off;

    public DexHeader(Reader reader) {
        this.reader = reader;
    }

    public void parse() {
        try {
            this.magic = TransformUtils.bytes2String(reader.readOrigin(8));
            log("magic: %s", magic);

            this.checkSum = reader.readUnsignedInt();
            log("checkSum: %d", checkSum);

            this.signature = TransformUtils.byte2HexStr(reader.readOrigin(20));
            log("signature: %s", signature);

            this.file_size = reader.readInt();
            log("file_size: %d", file_size);

            this.header_size = reader.readInt();
            log("header_size: %d", header_size);

            this.endian_tag = reader.readInt();
            log("endian_tag: 0x%x", endian_tag);

            this.link_size = reader.readInt();
            log("link_size: %d", link_size);

            this.link_off = reader.readInt();
            log("link_off: %d", link_off);

            this.map_off = reader.readInt();
            log("map_off: %d", map_off);

            this.string_ids__size = reader.readInt();
            log("string_ids__size: %d", string_ids__size);

            this.string_ids_off = reader.readInt();
            log("string_ids_off: %d", string_ids_off);

            this.type_ids__size = reader.readInt();
            log("type_ids__size: %d", type_ids__size);

            this.type_ids_off = reader.readInt();
            log("type_ids_off: %d", type_ids_off);

            this.proto_ids__size = reader.readInt();
            log("proto_ids__size: %d", proto_ids__size);

            this.proto_ids_off = reader.readInt();
            log("proto_ids_off: %d", proto_ids_off);

            this.field_ids__size = reader.readInt();
            log("field_ids__size: %d", field_ids__size);

            this.field_ids_off = reader.readInt();
            log("field_ids_off: %d", field_ids_off);

            this.method_ids_size = reader.readInt();
            log("method_ids_size: %d", method_ids_size);

            this.method_ids_off = reader.readInt();
            log("method_ids_off: %d", method_ids_off);

            this.class_defs_size = reader.readInt();
            log("class_defs_size: %d", class_defs_size);

            this.class_defs_off = reader.readInt();
            log("class_defs_off: %d", class_defs_off);

            this.data_size = reader.readInt();
            log("data_size: %d", data_size);

            this.data_off = reader.readInt();
            log("data_off: %d", data_off);

        } catch (IOException e) {
            e.printStackTrace();
            log("parse dex header error!");
        }
    }
}
