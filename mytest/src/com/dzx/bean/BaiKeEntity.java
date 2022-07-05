package com.dzx.bean;

import com.dzx.util.LUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingzhixin
 * create  2022/4/21  15:07
 */
public class BaiKeEntity {

    public BaikeDefaultLemmaBean baikeDefaultLemma;
    /**
     * 人物百科标签
     */
    public BaikeTagsBean baikeTags;

    public static class CatalogContentStructuredBean {

        public String tag;
        public String title;
        public String type;

        public List<ContentBean> content;

        public JsonElement data;

        public ModuleDataBean getModuleDataBean() {
            ModuleDataBean moduleDataBean = null;
            if (data != null
                    && data.isJsonObject()) {
                moduleDataBean = new Gson().fromJson(data, ModuleDataBean.class);
            }
            return moduleDataBean;
        }

        public List<List<TableBean>> getTableBean() {
            List<List<TableBean>> lists = new ArrayList<>();
            if (data != null
                    && data.isJsonArray()) {
                lists = new Gson().fromJson(data, new TypeToken<List<List<TableBean>>>() {
                }.getType());
            }
            return lists;
        }

        public ModuleMusicBean getModuleMusicBean() {
            ModuleMusicBean moduleMusicBean = null;
            if (data != null
                    && data.isJsonObject()) {
                moduleMusicBean = new Gson().fromJson(data, ModuleMusicBean.class);
            }

            return moduleMusicBean;
        }

        public VarietyShowDataBean getVarietyShowDataBean() {
            VarietyShowDataBean varietyShowDataBean = null;
            if (data != null
                    && data.isJsonObject()) {
                varietyShowDataBean = new Gson().fromJson(data, VarietyShowDataBean.class);
            }

            return varietyShowDataBean;
        }

        public ConcertRecordDataBean getConcertRecordDataBean() {
            ConcertRecordDataBean concertRecordDataBean = null;
            if (data != null
                    && data.isJsonObject()) {
                concertRecordDataBean = new Gson().fromJson(data, ConcertRecordDataBean.class);
            }
            return concertRecordDataBean;
        }

        public BaiKeEntityJson.MusicAlbumDataBean getMusicAlbumDataBean() {
            BaiKeEntityJson.MusicAlbumDataBean musicAlbumDataBean = null;
            musicAlbumDataBean = new Gson().fromJson(data, BaiKeEntityJson.MusicAlbumDataBean.class);
            LUtils.i("=================", new Gson().toJson(musicAlbumDataBean));
            return musicAlbumDataBean;
        }

    }

    public static class ContentBean {

        public String tag;
        public String text;


    }


    public static class ModuleDataBean {

        public List<RowListBean> rowList;
    }

    public static class RowListBean {
        public CellListBean cellList;
    }

    public static class CellListBean {
        public List<List<JumingBeanX>> juming;

    }

    public static class JumingBeanX {
        public String lemmaTitle;
    }

    public static class TableBean {

        public String type;
        public JsonElement content;

        public List<TableContentBean> getListContent() {
            List<TableContentBean> lists = new ArrayList<>();
            try {
                if (content != null) {
                    lists = new Gson().fromJson(content, new TypeToken<List<TableContentBean>>() {
                    }.getType());
                }
            } catch (Throwable e) {
//                e.printStackTrace();
            }
            return lists;
        }

        public List<List<TableContentBean>> getListListContent() {
            List<List<TableContentBean>> lists = new ArrayList<>();
            try {
                if (content != null) {
                    lists = new Gson().fromJson(content, new TypeToken<List<List<TableContentBean>>>() {
                    }.getType());
                }
            } catch (Throwable e) {
//                e.printStackTrace();
            }
            return lists;
        }

    }

    public static class TableContentBean {
        /**
         * tag : text
         * text : 时间
         */

        public String tag;
        public String text;
    }

    public static class ModuleMusicBean {

        public ModuleMusicContentBean content;

    }

    public static class ModuleMusicContentBean {

        public ModuleMusicSinglesongsBean singlesongs;
        public ModuleMusicAlbumsBean albums;
    }

    public static class ModuleMusicSinglesongsBean {
        public List<ModuleMusicSongBean> song;

    }

    public static class ModuleMusicSongBean {

        public List<ModuleMusicTitleBean> title;
        public String date;

    }

    public static class ModuleMusicTitleBean {

        public String lemmaTitle;
    }

    /**
     * 综艺节目数据
     */
    public static class VarietyShowDataBean {

        public List<VarietyShowRowListBean> rowList;


    }

    public static class VarietyShowRowListBean {

        public VarietyShowCellListBean cellList;

    }

    public static class VarietyShowCellListBean {
        public List<List<VarietyShowTimeBean>> time;
        public List<List<VarietyShowNameBean>> name;


    }

    public static class VarietyShowTimeBean {
        /**
         * 时间
         */
        public String text;
    }

    public static class VarietyShowNameBean {
        /**
         * 综艺名称
         */
        public String text;
    }

    /**
     * 演唱会记录
     */
    public static class ConcertRecordDataBean {
        public List<ConcertRecordRowListBean> rowList;
    }


    public static class ConcertRecordRowListBean {
        public ConcertRecordCellListBean cellList;
    }

    public static class ConcertRecordCellListBean {
        public List<List<ConcertRecordTimeBean>> jubanshijian;
        public List<List<ConcertRecordNameBean>> yanchanghuimingcheng;
    }

    public static class ConcertRecordTimeBean {
        public String text;
    }

    public static class ConcertRecordNameBean {
        public String text;
    }

    /**
     * 音乐作品专辑
     */
    public static class ModuleMusicAlbumsBean {
        public List<ModuleMusicAlbumBean> album;

    }

    public static class ModuleMusicAlbumBean {

        public String date;
        public List<ModuleMusicAlbumsTitleBean> title;


    }

    public static class ModuleMusicAlbumsTitleBean {
        public String text;
    }


    public static class BaikeDefaultLemmaBean {

        public String lemmaTitle;
        public String lemmaDesc;
        public String url;
        public String summary;
        public String picUrl;

        public List<CardBean> card;
        public List<CatalogContentStructuredBean> catalogContentStructured;


    }

    /**
     * 人物简介
     */
    public static class CardBean {
        public String key;
        public String name;
        public List<String> value;
    }


    public static class BaikeTagsBean {

        public List<String> tags;

    }


}
