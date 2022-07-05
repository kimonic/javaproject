package com.dzx.data;

import java.util.List;

/**
 * @author dingzhixin
 * create  2022/6/6  9:41
 */
public class ImageRecsysEntity {

    public int resultCode;
    public String error;
    public DataEntity data;

    public static class DataEntity {
        public int type;
        public String title;
        public List<VideosEntity> videos;
        public List<TopsEntity> tops;
    }

    public static class VideosEntity {
        public String poster;
        public String jumpurl;
        public String verticalposter;
        public String title;
        public String rate;
        public String vendorName;
    }

    public static class TopsEntity {
        public String type;
        public List<TopListEntity> toplist;
    }

    public static class TopListEntity {
        public String name;
        public String poster;
        public String times;
    }
}
