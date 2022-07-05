package com.dzx.data;

import java.util.List;

public class RelativeVideoEntity {
    public int time;
    public String signatureServer;
    public List<PersonVideo> personVideos;

    public static class PersonVideo {
        public String personName;
        public List<Video> videos;
    }

    public static class Video {
        public String verticalposter;
        public String categoryName;
        public String jumpurl;
        public String rate;
        public String title;
        public String vendorName;
        public String poster;

    }
}
