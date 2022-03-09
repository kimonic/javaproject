package luyao.parser.dex.bean;

/**
 * Created by luyao
 * on 2018/12/19 10:01
 */
public class DexStringId {

    /*
    struct DexStringId {
        u4 stringDataOff;
    };
    */

    public int string_data_off; // 字符串的偏移量
    public String string_data; // 字符串的内容

    public DexStringId(int string_data_off, String string_data) {
        this.string_data_off = string_data_off;
        this.string_data = string_data;
    }

    @Override
    public String toString() {
        return "DexStringId{" +
                "string_data_off=" + string_data_off +
                ", string_data=" + string_data +
                '}';
    }
}
