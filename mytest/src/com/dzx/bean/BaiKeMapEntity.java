package com.dzx.bean;

import java.util.List;

public class BaiKeMapEntity {

    public static final String HEADER = "header";
    public static final String PARAGRAPH = "paragraph";
    public static final String REF = "ref";
    public static final String TEXT = "text";
    public static final String INNERLINK = "innerlink";
    public static final String MODULE = "module";
    public static final String TABLE = "table";
    public static final String MUSIC = "music";
    public static final String ALBUM = "album";
    public static final String CANYANDIANSHIJU2 = "canyandianshiju2";
    public static final String TV = "tv";
    public static final String YANCHANGHUIJILU = "yanchanghuijilu";
    public static final String MAGAZINE1 = "magazine1";
    public static final String CANYANDIANYING2 = "canyandianying2";
    public static final String MUSIC_ALBUM = "musicAlbum";
    public static final String MUSIC_SINGLE = "musicSingle";


    /**
     * 人物百科标签
     */
    public List<String> tags;
    /**
     * 百科简介
     */
    public String description;
    /**
     * 百科画像
     */
    public List<String> profile;
    /**
     * 百科类别简介
     */
    public String type;
    /**
     * 百科名称
     */
    public String name;
    /**
     * 百科网址
     */
    public String baiKeUrl;
    /**
     * 图片网址
     */
    public String picUrl;

    public List<ContentBean> contentBeans;


    public static class ContentBean {
        /**
         * 标签,段落还是标题
         */
        public String tag;
        /**
         * 详细内容
         */
        public String content;
        public String tempType;
    }

}
