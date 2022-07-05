package com.dzx.bean;

import java.util.List;

/**
 * @author dingzhixin
 * create  2022/4/24  17:01
 */
public class VcaDataBean {
    public List<VcaBaiKeBean> vca_tag;

    public static class VcaBaiKeBean{
        public BaiKeEntityJson.BaikeDefaultLemmaBean baikeDefaultLemma;
        public BaiKeEntityJson.BaikeTagsBean baikeTags;
    }
}
