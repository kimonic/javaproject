package com.dzx;

import com.alibaba.fastjson.JSON;
import com.dzx.bean.BaiKeEntity;
import com.dzx.bean.BaiKeEntityJson;
import com.dzx.bean.BaiKeMapEntity;
import com.dzx.bean.VcaDataBean;
import com.dzx.util.LUtils;
import com.dzx.util.UZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.TextUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test9 {


    private static Thread mThread;
    public static void main(String[] args) {
        mThread = new Thread() {
            @Override
            public void run() {
                LUtils.i(getName());
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            LUtils.i("子线程执行");
                            Thread.sleep(5000);
                            LUtils.i("子线程执行结束");
                            String s = null;
                            s.toCharArray();
                        } catch (InterruptedException e) {

                        }
                    }
                };
                thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        LUtils.i("接收到了抛出异常  ", Thread.currentThread().getName());
                        mThread.stop();
                        String s = null;
                        s.toCharArray();
                        LUtils.i("异常收集器抛出异常结束 ");
                    }
                });
                LUtils.i("子线程名称  ", thread.getName());
                thread.start();
                try {
                    LUtils.i("主线程执行");
                    //Thread.sleep(20000);
                    while (true) {

                    }
                    //LUtils.i("主线程执行结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mThread.start();

//        LUtils.i(URLDecoder.decode("%E5%92%95%E5%8A%9B%E5%92%95%E5%8A%9B%E5%AE%89%E5%85%A8%E6%95%99%E8%82%B2"));

    }

    private synchronized static void test1() {
        LUtils.i("开始执行  1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LUtils.i("结束执行  1 ");
    }

    private synchronized static void test2() {
        LUtils.i("开始执行  2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LUtils.i("结束执行   2");
    }

    private synchronized static void test3() {
        LUtils.i("开始执行 3 ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LUtils.i("结束执行   3");
    }

    private static void test4() {
        LUtils.i("未加锁");
    }


    private static void renameFile() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\截图");
        File[] files = file.listFiles();
        int count = 1;
        for (File file1 : files) {
            count++;
            String temp;
            if (count > 9) {
                temp = "" + count;
            } else {
                temp = "0" + count;

            }
            file1.renameTo(new File("C:\\Users\\dingzhixin.ex\\Desktop\\截图", "test_0" + temp + ".png"));
        }
    }

    private static void filterLog() {
        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\异常结果 - 副本.txt");
        try {
            List<String> list = FileUtils.readLines(file1);
            for (String s : list) {
                if (s.contains("21083")) {
                    LUtils.i(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void random() {
        StringBuilder builder = new StringBuilder();
        int total = 0;
        for (int i = 0; i < 10; i++) {
            int ss = (int) (1000 + Math.random() * 1000);
            total += ss;
            builder.append(ss).append(",");

        }

        LUtils.i(builder.toString(), total);
//        String temp = "1.95,1.87,1.89,1.89,2.1,2.1,2,1.99,2.1,1.99,2.59,2.19,1.37,2,2,1.93";
//        String[] result = temp.split(",");
//        float total = 0;
//        StringBuilder builder=new StringBuilder();
//        for (String s : result) {
//            total += Float.parseFloat(s);
//            builder.append(s).append("s").append("\n");
//        }
//        LUtils.i(builder.toString());
//        LUtils.i(total/16.0f);
    }

    //014567
    private static void testListInsert() {
        List<String> list = new ArrayList<>();
        list.add("第一个");
        list.add("第二个");
        list.add("第三个");
        list.add("第四个");
        list.add("第五个");
        list.add("第六个");

        int size = list.size();
        List<String> tempList = new ArrayList<>();
        tempList.add("0");
        tempList.add("1");
        tempList.add("4");
        tempList.add("5");
        tempList.add("6");
        tempList.add("7");
        for (String s : tempList) {
            if (Integer.parseInt(s) < size) {
                list.add(Integer.parseInt(s), s);
                size = size + 1;
            } else {
                list.add(s);
            }
        }

        LUtils.i(list);

    }

    private static void temp(String anYe1) {

        StringBuilder builder = new StringBuilder();
        for (String s : anYe1.split(",")) {
            builder.append("\"").append(s).append("\"").append(",");
        }
        LUtils.i(builder.toString().replaceAll(" ", ""));

    }

    private static void testStringCompare() {
        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\test\\true.txt");
//        File file2 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\test\\true1.txt");
        File file2 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\test\\false.txt");
//        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\test\\false1.txt");


        try {
            List<String> list1 = FileUtils.readLines(file1);
            List<String> list2 = FileUtils.readLines(file2);

            int size = list1.size();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                if (!list1.get(i).equals(list2.get(i))) {
                    LUtils.i(list1.get(i));
                    LUtils.i(list2.get(i));
                    LUtils.i("\n");
                    builder.append(list1.get(i)).append("\n").append(list2.get(i)).append("\n\n");
                }
            }
            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\test\\比较结果.txt"), builder.toString());


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static boolean checkIsEmpty(String target) {
        if (!TextUtils.isEmpty(target)) {
            char[] chars = target.toCharArray();
            for (char c : chars) {
                if (c != ' ' && c != '\u3000') {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private static void testScheduledThreadPool() {
        long start = System.currentTimeMillis();
        LUtils.i("开始");
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture scheduledFuture = executor.schedule(new Runnable() {
            @Override
            public void run() {
                LUtils.i(System.currentTimeMillis() - start);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    LUtils.i("异常退出");
                    e.printStackTrace();
                    return;
                }
                LUtils.i("第二次打印  ", System.currentTimeMillis() - start);
            }
        }, 5, TimeUnit.SECONDS);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LUtils.i("取消定时任务");
//        executor.shutdownNow();
        scheduledFuture.cancel(false);
    }

    public static String getNowTime(Long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    private static void reverseFile() {
        try {
            String result = FileUtils.readFileToString(new File("E:\\work\\app\\MyApplication\\drawui\\src\\main\\java\\com\\kimonik\\drawui\\ui\\PixelOperationView.java"));

            int length = result.length();
            List<String> list = new ArrayList<>();
            for (int i = length - 1; i > -1; i--) {
                list.add("" + (int) result.charAt(i));
            }
            StringBuilder builder1 = new StringBuilder();
            StringBuilder builder2 = new StringBuilder();
            StringBuilder builder3 = new StringBuilder();

            int size = list.size();
            int offset = 3;
            for (int i = 0; i < size; i++) {
                if (i % offset == 0) {
                    builder1.append(list.get(i)).append(",");
                } else if (i % offset == 1) {
                    builder2.append(list.get(i)).append(",");
                } else if (i % offset == 2) {
                    builder3.append(list.get(i)).append(",");
                }
            }

            builder1.append((int) '鑫').append(",");
            builder2.append((int) '鑫').append(",");

            LUtils.i((int) '鑫');
            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\ss.log"),
                    builder1.toString() + builder2.toString() + builder3.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void restoreFile() {
        try {
            String result = FileUtils.readFileToString(new File("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\ss.log"));
            String[] temp = result.split(",");
            int length = temp.length;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append((char) Integer.parseInt(temp[i]));
            }

            String[] target = builder.toString().split("鑫");
            LUtils.i(target.length);
            int lengthTarget = target.length;
            StringBuilder resultBuilder = new StringBuilder();
            int count = 0;
            while (true) {
                boolean needBreak = false;
                for (int i = 0; i < lengthTarget; i++) {
                    int length1 = target[i].length();
                    if (count < length1) {
                        resultBuilder.append(target[i].charAt(count));
                        needBreak = false;
                    } else {
                        needBreak = true;
                    }
                }
                count++;
                if (needBreak) {
                    break;
                }
            }

            String last = resultBuilder.toString();
            StringBuilder lastBuilder = new StringBuilder();
            int lastLength = last.length();
            for (int i = lastLength - 1; i > -1; i--) {
                lastBuilder.append(last.charAt(i));
            }


            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\bug\\result.log"), lastBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testBaiKeData() {
        String starName = "夏仪秋";
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\哈士奇vca数据.txt");
        try {
            String content = FileUtils.readFileToString(file);

            long start = System.currentTimeMillis();
            String result = content.replaceAll("\\\\{2}", "\\\\")
                    .replaceAll("\\\\n", "")
                    .replaceAll("\"\\{", "{")
                    .replaceAll("\\\\\"", "\"")
                    .replaceAll("}\"", "}");
            LUtils.i("处理字符串耗时  ", (System.currentTimeMillis() - start));

            FileUtils.write(new File("C:\\Users\\dingzhixin.ex\\Desktop\\" + starName), result);
            VcaDataBean baiKeEntity = JSON.parseObject(result, VcaDataBean.class);
//            LUtils.i("\n\n\n", result, "\n\n\n");
//            BaiKeEntity baiKeEntity = new Gson().fromJson(result, BaiKeEntity.class);
//            handleBaiKeMapData(baiKeEntity);
            handleBaiKeMapDataJson(baiKeEntity);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleBaiKeMapDataJson(VcaDataBean vcaBaiKeBean1) {
        if (vcaBaiKeBean1 != null
                && !checkListEmpty(vcaBaiKeBean1.vca_tag)) {
            for (VcaDataBean.VcaBaiKeBean vcaBaiKeBean : vcaBaiKeBean1.vca_tag) {
                if (vcaBaiKeBean != null) {
                    BaiKeMapEntity data = new BaiKeMapEntity();


                    if (vcaBaiKeBean.baikeDefaultLemma != null) {

                        data.name = vcaBaiKeBean.baikeDefaultLemma.lemmaTitle;
                        data.description = vcaBaiKeBean.baikeDefaultLemma.summary;
                        data.type = vcaBaiKeBean.baikeDefaultLemma.lemmaDesc;

                        data.baiKeUrl = vcaBaiKeBean.baikeDefaultLemma.url;
                        data.picUrl = vcaBaiKeBean.baikeDefaultLemma.picUrl;

                        if (!checkListEmpty(vcaBaiKeBean.baikeDefaultLemma.card)) {
                            data.profile = new ArrayList<>();
                            for (BaiKeEntityJson.CardBean cardBean : vcaBaiKeBean.baikeDefaultLemma.card) {
                                if (cardBean != null
                                        && !TextUtils.isEmpty(cardBean.name)
                                        && !checkListEmpty(cardBean.value)) {
                                    data.profile.add(cardBean.name + ":" + listToString(cardBean.value));
                                }
                            }
                        }


                        if (!checkListEmpty(vcaBaiKeBean.baikeDefaultLemma.catalogContentStructured)) {
                            data.contentBeans = new ArrayList<>();
                            for (BaiKeEntityJson.CatalogContentStructuredBean bean : vcaBaiKeBean.baikeDefaultLemma.catalogContentStructured) {
                                if (bean != null) {
                                    BaiKeMapEntity.ContentBean contentBean = new BaiKeMapEntity.ContentBean();
                                    contentBean.tag = bean.tag;
                                    contentBean.tempType = bean.type;
                                    LUtils.i(bean.tag);
                                    if (BaiKeMapEntity.HEADER.equals(bean.tag)) {
                                        //标题
                                        contentBean.content = bean.title;
                                    } else if (BaiKeMapEntity.PARAGRAPH.equals(bean.tag)) {
                                        //段落
                                        if (bean.content != null
                                                && !checkListEmpty(bean.content)) {
                                            StringBuilder builder = new StringBuilder();
                                            for (BaiKeEntityJson.ContentBean contentBean1 : bean.content) {
                                                if (contentBean1 != null) {
                                                    if (BaiKeMapEntity.TEXT.equals(contentBean1.tag)
                                                            || BaiKeMapEntity.INNERLINK.equals(contentBean1.tag)) {
                                                        builder.append(contentBean1.text);
                                                    }
                                                }
                                            }
                                            contentBean.content = builder.toString();
                                        }
                                    } else if (BaiKeMapEntity.MODULE.equals(bean.tag)) {
                                        if (BaiKeMapEntity.ALBUM.equals(bean.type)
                                                || BaiKeMapEntity.CANYANDIANSHIJU2.equals(bean.type)
                                                || BaiKeMapEntity.CANYANDIANYING2.equals(bean.type)) {
                                            //参演电视剧
                                            BaiKeEntityJson.ModuleDataBean moduleDataBean = bean.getModuleDataBean();
                                            if (moduleDataBean != null
                                                    && !checkListEmpty(moduleDataBean.rowList)) {
                                                StringBuilder builder = new StringBuilder();
                                                for (BaiKeEntityJson.RowListBean rowListBean : moduleDataBean.rowList) {
                                                    if (rowListBean != null
                                                            && rowListBean.cellList != null
                                                            && !checkListEmpty(rowListBean.cellList.juming)) {
                                                        for (List<BaiKeEntityJson.JumingBeanX> jumingBeanXES : rowListBean.cellList.juming) {
                                                            if (!checkListEmpty(jumingBeanXES)) {
                                                                for (BaiKeEntityJson.JumingBeanX jumingBeanX : jumingBeanXES) {
                                                                    if (jumingBeanX != null
                                                                            && !TextUtils.isEmpty(jumingBeanX.lemmaTitle)) {
                                                                        builder.append("《").append(jumingBeanX.lemmaTitle).append("》").append("\n");
                                                                    }
                                                                }
                                                            }
                                                        }

                                                    }
                                                }
                                                contentBean.content = builder.toString();
                                            }
                                        } else if (BaiKeMapEntity.MUSIC_ALBUM.equals(bean.type)) {
                                            BaiKeEntityJson.MusicAlbumDataBean musicAlbumDataBean = bean.getMusicAlbumDataBean();
                                            if (musicAlbumDataBean != null && checkListEmpty(musicAlbumDataBean.rowList)) {
                                                StringBuilder builder = new StringBuilder();
                                                for (BaiKeEntityJson.MusicAlbumRowListBean musicAlbumRowListBean : musicAlbumDataBean.rowList) {
                                                    if (musicAlbumRowListBean != null
                                                            && musicAlbumRowListBean.cellList != null
                                                            && checkListEmpty(musicAlbumRowListBean.cellList.name)
                                                            && checkListEmpty(musicAlbumRowListBean.cellList.dateOfPublication)
                                                            && musicAlbumRowListBean.cellList.name.size() == musicAlbumRowListBean.cellList.dateOfPublication.size()) {
                                                        if (checkListEmpty(musicAlbumRowListBean.cellList.name.get(0))) {
                                                            BaiKeEntityJson.MusicAlbumNameBean musicAlbumNameBean = musicAlbumRowListBean.cellList.name.get(0).get(0);
                                                            if (musicAlbumNameBean != null
                                                                    && !TextUtils.isEmpty(musicAlbumNameBean.text)) {
                                                                builder.append(musicAlbumNameBean.text).append("  ");
                                                            } else {
                                                                continue;
                                                            }
                                                        }
                                                        if (checkListEmpty(musicAlbumRowListBean.cellList.dateOfPublication.get(0))) {
                                                            BaiKeEntityJson.MusicAlbumDateOfPublicationBean musicAlbumDateOfPublicationBean =
                                                                    musicAlbumRowListBean.cellList.dateOfPublication.get(0).get(0);
                                                            if (musicAlbumDateOfPublicationBean != null
                                                                    && !TextUtils.isEmpty(musicAlbumDateOfPublicationBean.text)) {
                                                                builder.append(musicAlbumDateOfPublicationBean.text).append("\n");
                                                            }
                                                        }


                                                    }
                                                }
                                                contentBean.content = builder.toString();
                                                LUtils.i(contentBean.content);
                                            }
                                        } else if (BaiKeMapEntity.MUSIC.equals(bean.type)
                                                || BaiKeMapEntity.MUSIC_SINGLE.equals(bean.type)
                                        ) {
                                            //音乐作品
                                            BaiKeEntityJson.ModuleMusicBean moduleMusicBean = bean.getModuleMusicBean();
                                            if (moduleMusicBean != null
                                                    && moduleMusicBean.content != null) {
                                                StringBuilder builder = new StringBuilder();
                                                if (moduleMusicBean.content.albums != null
                                                        && !checkListEmpty(moduleMusicBean.content.albums.album)) {
                                                    for (BaiKeEntityJson.ModuleMusicAlbumBean moduleMusicAlbumBean : moduleMusicBean.content.albums.album) {
                                                        if (moduleMusicAlbumBean != null
                                                                && !checkListEmpty(moduleMusicAlbumBean.title)) {
                                                            for (BaiKeEntityJson.ModuleMusicAlbumsTitleBean moduleMusicAlbumsTitleBean : moduleMusicAlbumBean.title) {
                                                                if (moduleMusicAlbumsTitleBean != null) {
                                                                    builder.append(moduleMusicAlbumsTitleBean.text).append(" ").append(moduleMusicAlbumBean.date);
                                                                }
                                                            }
                                                        }
                                                        builder.append("\n");
                                                    }
                                                }

                                                if (moduleMusicBean.content.singlesongs != null
                                                        && !checkListEmpty(moduleMusicBean.content.singlesongs.song)) {
                                                    for (BaiKeEntityJson.ModuleMusicSongBean moduleMusicSongBean : moduleMusicBean.content.singlesongs.song) {
                                                        if (moduleMusicSongBean != null
                                                                && !checkListEmpty(moduleMusicSongBean.title)) {
                                                            for (BaiKeEntityJson.ModuleMusicTitleBean moduleMusicTitleBean : moduleMusicSongBean.title) {
                                                                builder.append(moduleMusicTitleBean.lemmaTitle).append(" ").append(moduleMusicSongBean.date);
                                                            }
                                                        }
                                                        builder.append("\n");
                                                    }
                                                }


                                                contentBean.content = builder.toString();
                                                LUtils.i(contentBean.content);
                                            }

                                        } else if (BaiKeMapEntity.TV.equals(bean.type)
                                                || BaiKeMapEntity.MAGAZINE1.equals(bean.type)) {
                                            //综艺节目,杂志写真
                                            BaiKeEntityJson.VarietyShowDataBean varietyShowDataBean = bean.getVarietyShowDataBean();
                                            if (varietyShowDataBean != null
                                                    && !checkListEmpty(varietyShowDataBean.rowList)) {
                                                StringBuilder builder = new StringBuilder();
                                                for (BaiKeEntityJson.VarietyShowRowListBean rowListBean : varietyShowDataBean.rowList) {
                                                    if (rowListBean != null
                                                            && rowListBean.cellList != null
                                                            && !checkListEmpty(rowListBean.cellList.name)
                                                            && !checkListEmpty(rowListBean.cellList.time)) {

                                                        for (List<BaiKeEntityJson.VarietyShowNameBean> list : rowListBean.cellList.name) {
                                                            if (!checkListEmpty(list)) {
                                                                for (BaiKeEntityJson.VarietyShowNameBean varietyShowNameBean : list) {
                                                                    if (varietyShowNameBean != null
                                                                            && !TextUtils.isEmpty(varietyShowNameBean.text)) {
                                                                        builder.append(varietyShowNameBean.text).append(" ");
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        for (List<BaiKeEntityJson.VarietyShowTimeBean> list : rowListBean.cellList.time) {
                                                            if (!checkListEmpty(list)) {
                                                                for (BaiKeEntityJson.VarietyShowTimeBean varietyShowTimeBean : list) {
                                                                    if (varietyShowTimeBean != null) {
                                                                        builder.append(varietyShowTimeBean.text);

                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    builder.append("\n");
                                                }
                                                contentBean.content = builder.toString();
                                            }
                                        } else if (BaiKeMapEntity.YANCHANGHUIJILU.equals(bean.type)) {
                                            //演唱会记录
                                            BaiKeEntityJson.ConcertRecordDataBean concertRecordDataBean = bean.getConcertRecordDataBean();
                                            if (concertRecordDataBean != null
                                                    && !checkListEmpty(concertRecordDataBean.rowList)) {
                                                StringBuilder builder = new StringBuilder();
                                                for (BaiKeEntityJson.ConcertRecordRowListBean concertRecordRowListBean : concertRecordDataBean.rowList) {
                                                    if (concertRecordRowListBean != null
                                                            && concertRecordRowListBean.cellList != null
                                                            && !checkListEmpty(concertRecordRowListBean.cellList.jubanshijian)
                                                            && !checkListEmpty(concertRecordRowListBean.cellList.yanchanghuimingcheng)) {

                                                        for (List<BaiKeEntityJson.ConcertRecordNameBean> list :
                                                                concertRecordRowListBean.cellList.yanchanghuimingcheng) {
                                                            if (!checkListEmpty(list)) {
                                                                for (BaiKeEntityJson.ConcertRecordNameBean concertRecordNameBean : list) {
                                                                    if (concertRecordNameBean != null) {
                                                                        builder.append(concertRecordNameBean.text).append(" ");
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        for (List<BaiKeEntityJson.ConcertRecordTimeBean> list : concertRecordRowListBean.cellList.jubanshijian) {
                                                            if (!checkListEmpty(list)) {
                                                                for (BaiKeEntityJson.ConcertRecordTimeBean concertRecordTimeBean : list) {
                                                                    if (concertRecordTimeBean != null) {
                                                                        builder.append(concertRecordTimeBean.text);
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        builder.append("\n");
                                                    }
                                                }
                                                contentBean.content = builder.toString();
                                            }

                                        }
//                                else if (BaiKeMapEntity.MAGAZINE1.equals(bean.type)) {
//                                    杂志写真
//
//                                }

                                    } else if (BaiKeMapEntity.TABLE.equals(bean.tag)) {
                                        List<List<BaiKeEntityJson.TableBean>> tableBean = bean.getTableBean();
                                        if (tableBean != null
                                                && !checkListEmpty(tableBean)) {
                                            StringBuilder builder = new StringBuilder();
                                            for (List<BaiKeEntityJson.TableBean> list : tableBean) {
                                                if (!checkListEmpty(list)) {
                                                    for (BaiKeEntityJson.TableBean tableBean1 : list) {
                                                        if (!checkListEmpty(tableBean1.content)) {
                                                            for (BaiKeEntityJson.TableContentBean tableContentBean : tableBean1.content) {
                                                                if (tableContentBean != null && !TextUtils.isEmpty(tableContentBean.text)) {
                                                                    builder.append(tableContentBean.text);
                                                                }
                                                            }
                                                        }
                                                        builder.append("  ");
                                                    }
                                                    builder.append("\n");
                                                }
                                            }
                                            String[] result = builder.toString().split("\n");
                                            builder = new StringBuilder();
                                            for (String s : result) {
                                                builder.append(s.trim()).append("\n");
                                            }
                                            contentBean.content = builder.toString();
                                        }
                                    }


                                    data.contentBeans.add(contentBean);
                                }
                            }
                        }

                    }

                    if (vcaBaiKeBean.baikeTags != null) {
                        data.tags = vcaBaiKeBean.baikeTags.tags;
                    }


                    //=============输出内容===================================
                    LUtils.i(data.name);
                    LUtils.i(data.description);
                    LUtils.i(data.type);
                    LUtils.i(data.baiKeUrl);
                    LUtils.i(data.picUrl);


                    LUtils.i(data.tags);
                    if (data.profile != null) {
                        for (String s : data.profile) {
                            LUtils.i(s);
                        }
                    }

                    if (!checkListEmpty(data.contentBeans)) {
                        for (BaiKeMapEntity.ContentBean contentBean : data.contentBeans) {
                            LUtils.i(contentBean.tag, "  ", contentBean.tempType, "\n", contentBean.content, "\n\n");
                        }
                    }
                }
            }
        }

    }

    private static void handleBaiKeMapData(BaiKeEntity baiKeEntity) {
        BaiKeMapEntity data = new BaiKeMapEntity();
        if (baiKeEntity != null) {


            if (baiKeEntity.baikeDefaultLemma != null) {

                data.name = baiKeEntity.baikeDefaultLemma.lemmaTitle;
                data.description = baiKeEntity.baikeDefaultLemma.summary;
                data.type = baiKeEntity.baikeDefaultLemma.lemmaDesc;

                data.baiKeUrl = baiKeEntity.baikeDefaultLemma.url;
                data.picUrl = baiKeEntity.baikeDefaultLemma.picUrl;

                if (!checkListEmpty(baiKeEntity.baikeDefaultLemma.card)) {
                    data.profile = new ArrayList<>();
                    for (BaiKeEntity.CardBean cardBean : baiKeEntity.baikeDefaultLemma.card) {
                        if (cardBean != null
                                && !TextUtils.isEmpty(cardBean.name)
                                && !checkListEmpty(cardBean.value)) {
                            data.profile.add(cardBean.name + ":" + listToString(cardBean.value));
                        }
                    }
                }


                if (!checkListEmpty(baiKeEntity.baikeDefaultLemma.catalogContentStructured)) {
                    data.contentBeans = new ArrayList<>();
                    for (BaiKeEntity.CatalogContentStructuredBean bean : baiKeEntity.baikeDefaultLemma.catalogContentStructured) {
                        if (bean != null) {
                            BaiKeMapEntity.ContentBean contentBean = new BaiKeMapEntity.ContentBean();
                            contentBean.tag = bean.tag;
                            contentBean.tempType = bean.type;
                            if (BaiKeMapEntity.HEADER.equals(bean.tag)) {
                                //标题
                                contentBean.content = bean.title;
                            } else if (BaiKeMapEntity.PARAGRAPH.equals(bean.tag)) {
                                //段落
                                if (bean.content != null
                                        && !checkListEmpty(bean.content)) {
                                    StringBuilder builder = new StringBuilder();
                                    for (BaiKeEntity.ContentBean contentBean1 : bean.content) {
                                        if (contentBean1 != null) {
                                            if (BaiKeMapEntity.TEXT.equals(contentBean1.tag)
                                                    || BaiKeMapEntity.INNERLINK.equals(contentBean1.tag)) {
                                                builder.append(contentBean1.text);
                                            }
                                        }
                                    }
                                    contentBean.content = builder.toString();
                                }
                            } else if (BaiKeMapEntity.MODULE.equals(bean.tag)) {
                                if (BaiKeMapEntity.ALBUM.equals(bean.type)
                                        || BaiKeMapEntity.CANYANDIANSHIJU2.equals(bean.type)
                                        || BaiKeMapEntity.CANYANDIANYING2.equals(bean.type)) {
                                    //参演电视剧
                                    BaiKeEntity.ModuleDataBean moduleDataBean = bean.getModuleDataBean();
                                    if (moduleDataBean != null
                                            && !checkListEmpty(moduleDataBean.rowList)) {
                                        StringBuilder builder = new StringBuilder();
                                        for (BaiKeEntity.RowListBean rowListBean : moduleDataBean.rowList) {
                                            if (rowListBean != null
                                                    && rowListBean.cellList != null
                                                    && !checkListEmpty(rowListBean.cellList.juming)) {
                                                for (List<BaiKeEntity.JumingBeanX> jumingBeanXES : rowListBean.cellList.juming) {
                                                    if (!checkListEmpty(jumingBeanXES)) {
                                                        for (BaiKeEntity.JumingBeanX jumingBeanX : jumingBeanXES) {
                                                            if (jumingBeanX != null
                                                                    && !TextUtils.isEmpty(jumingBeanX.lemmaTitle)) {
                                                                builder.append("《").append(jumingBeanX.lemmaTitle).append("》").append("\n");
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                        contentBean.content = builder.toString();
                                    }
                                } else if (BaiKeMapEntity.MUSIC_ALBUM.equals(bean.type)
                                        || BaiKeMapEntity.MUSIC_SINGLE.equals(bean.type)) {
                                    BaiKeEntityJson.MusicAlbumDataBean musicAlbumDataBean = bean.getMusicAlbumDataBean();
                                    LUtils.i("11111111111  ", musicAlbumDataBean);
                                    if (musicAlbumDataBean != null && !checkListEmpty(musicAlbumDataBean.rowList)) {
                                        LUtils.i("11111111111  22222222222");
                                        StringBuilder builder = new StringBuilder();
                                        for (BaiKeEntityJson.MusicAlbumRowListBean musicAlbumRowListBean : musicAlbumDataBean.rowList) {
                                            if (musicAlbumRowListBean != null
                                                    && musicAlbumRowListBean.cellList != null
                                                    && !checkListEmpty(musicAlbumRowListBean.cellList.name)
                                                    && !checkListEmpty(musicAlbumRowListBean.cellList.dateOfPublication)
                                                    && musicAlbumRowListBean.cellList.name.size() == musicAlbumRowListBean.cellList.dateOfPublication.size()) {
                                                if (!checkListEmpty(musicAlbumRowListBean.cellList.name.get(0))) {
                                                    BaiKeEntityJson.MusicAlbumNameBean musicAlbumNameBean = musicAlbumRowListBean.cellList.name.get(0).get(0);
                                                    if (musicAlbumNameBean != null
                                                            && !TextUtils.isEmpty(musicAlbumNameBean.text)) {
                                                        builder.append(musicAlbumNameBean.text).append("  ");
                                                    } else {
                                                        continue;
                                                    }
                                                }
                                                if (!checkListEmpty(musicAlbumRowListBean.cellList.dateOfPublication.get(0))) {
                                                    BaiKeEntityJson.MusicAlbumDateOfPublicationBean musicAlbumDateOfPublicationBean =
                                                            musicAlbumRowListBean.cellList.dateOfPublication.get(0).get(0);
                                                    if (musicAlbumDateOfPublicationBean != null
                                                            && !TextUtils.isEmpty(musicAlbumDateOfPublicationBean.text)) {
                                                        builder.append(musicAlbumDateOfPublicationBean.text).append("\n");
                                                    }
                                                }


                                            }
                                        }
                                        contentBean.content = builder.toString();
                                        LUtils.i("特殊??  ", contentBean.content);
                                    }
                                } else if (BaiKeMapEntity.MUSIC.equals(bean.type)) {
                                    //音乐作品
                                    BaiKeEntity.ModuleMusicBean moduleMusicBean = bean.getModuleMusicBean();
                                    if (moduleMusicBean != null
                                            && moduleMusicBean.content != null) {
                                        StringBuilder builder = new StringBuilder();
                                        if (moduleMusicBean.content.albums != null
                                                && !checkListEmpty(moduleMusicBean.content.albums.album)) {
                                            for (BaiKeEntity.ModuleMusicAlbumBean moduleMusicAlbumBean : moduleMusicBean.content.albums.album) {
                                                if (moduleMusicAlbumBean != null
                                                        && !checkListEmpty(moduleMusicAlbumBean.title)) {
                                                    for (BaiKeEntity.ModuleMusicAlbumsTitleBean moduleMusicAlbumsTitleBean : moduleMusicAlbumBean.title) {
                                                        if (moduleMusicAlbumsTitleBean != null) {
                                                            builder.append(moduleMusicAlbumsTitleBean.text).append(" ").append(moduleMusicAlbumBean.date);
                                                        }
                                                    }
                                                }
                                                builder.append("\n");
                                            }
                                        }

                                        if (moduleMusicBean.content.singlesongs != null
                                                && !checkListEmpty(moduleMusicBean.content.singlesongs.song)) {
                                            for (BaiKeEntity.ModuleMusicSongBean moduleMusicSongBean : moduleMusicBean.content.singlesongs.song) {
                                                if (moduleMusicSongBean != null
                                                        && !checkListEmpty(moduleMusicSongBean.title)) {
                                                    for (BaiKeEntity.ModuleMusicTitleBean moduleMusicTitleBean : moduleMusicSongBean.title) {
                                                        builder.append(moduleMusicTitleBean.lemmaTitle).append(" ").append(moduleMusicSongBean.date);
                                                    }
                                                }
                                                builder.append("\n");
                                            }
                                        }


                                        contentBean.content = builder.toString();
                                    }

                                } else if (BaiKeMapEntity.TV.equals(bean.type)
                                        || BaiKeMapEntity.MAGAZINE1.equals(bean.type)) {
                                    //综艺节目,杂志写真
                                    BaiKeEntity.VarietyShowDataBean varietyShowDataBean = bean.getVarietyShowDataBean();
                                    if (varietyShowDataBean != null
                                            && !checkListEmpty(varietyShowDataBean.rowList)) {
                                        StringBuilder builder = new StringBuilder();
                                        for (BaiKeEntity.VarietyShowRowListBean rowListBean : varietyShowDataBean.rowList) {
                                            if (rowListBean != null
                                                    && rowListBean.cellList != null
                                                    && !checkListEmpty(rowListBean.cellList.name)
                                                    && !checkListEmpty(rowListBean.cellList.time)) {

                                                for (List<BaiKeEntity.VarietyShowNameBean> list : rowListBean.cellList.name) {
                                                    if (!checkListEmpty(list)) {
                                                        for (BaiKeEntity.VarietyShowNameBean varietyShowNameBean : list) {
                                                            if (varietyShowNameBean != null
                                                                    && !TextUtils.isEmpty(varietyShowNameBean.text)) {
                                                                builder.append(varietyShowNameBean.text).append(" ");
                                                            }
                                                        }
                                                    }
                                                }

                                                for (List<BaiKeEntity.VarietyShowTimeBean> list : rowListBean.cellList.time) {
                                                    if (!checkListEmpty(list)) {
                                                        for (BaiKeEntity.VarietyShowTimeBean varietyShowTimeBean : list) {
                                                            if (varietyShowTimeBean != null) {
                                                                builder.append(varietyShowTimeBean.text);

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            builder.append("\n");
                                        }
                                        contentBean.content = builder.toString();
                                    }
                                } else if (BaiKeMapEntity.YANCHANGHUIJILU.equals(bean.type)) {
                                    //演唱会记录
                                    BaiKeEntity.ConcertRecordDataBean concertRecordDataBean = bean.getConcertRecordDataBean();
                                    if (concertRecordDataBean != null
                                            && !checkListEmpty(concertRecordDataBean.rowList)) {
                                        StringBuilder builder = new StringBuilder();
                                        for (BaiKeEntity.ConcertRecordRowListBean concertRecordRowListBean : concertRecordDataBean.rowList) {
                                            if (concertRecordRowListBean != null
                                                    && concertRecordRowListBean.cellList != null
                                                    && !checkListEmpty(concertRecordRowListBean.cellList.jubanshijian)
                                                    && !checkListEmpty(concertRecordRowListBean.cellList.yanchanghuimingcheng)) {

                                                for (List<BaiKeEntity.ConcertRecordNameBean> list :
                                                        concertRecordRowListBean.cellList.yanchanghuimingcheng) {
                                                    if (!checkListEmpty(list)) {
                                                        for (BaiKeEntity.ConcertRecordNameBean concertRecordNameBean : list) {
                                                            if (concertRecordNameBean != null) {
                                                                builder.append(concertRecordNameBean.text).append(" ");
                                                            }
                                                        }
                                                    }
                                                }

                                                for (List<BaiKeEntity.ConcertRecordTimeBean> list : concertRecordRowListBean.cellList.jubanshijian) {
                                                    if (!checkListEmpty(list)) {
                                                        for (BaiKeEntity.ConcertRecordTimeBean concertRecordTimeBean : list) {
                                                            if (concertRecordTimeBean != null) {
                                                                builder.append(concertRecordTimeBean.text);
                                                            }
                                                        }
                                                    }
                                                }

                                                builder.append("\n");
                                            }
                                        }
                                        contentBean.content = builder.toString();
                                    }

                                }
//                                else if (BaiKeMapEntity.MAGAZINE1.equals(bean.type)) {
//                                    杂志写真
//
//                                }

                            } else if (BaiKeMapEntity.TABLE.equals(bean.tag)) {
                                List<List<BaiKeEntity.TableBean>> tableBean = bean.getTableBean();
                                if (tableBean != null
                                        && !checkListEmpty(tableBean)) {
                                    StringBuilder builder = new StringBuilder();
                                    for (List<BaiKeEntity.TableBean> list : tableBean) {
                                        if (!checkListEmpty(list)) {
                                            for (BaiKeEntity.TableBean tableBean1 : list) {
                                                if (!checkListEmpty(tableBean1.getListContent())) {
                                                    for (BaiKeEntity.TableContentBean tableContentBean : tableBean1.getListContent()) {
                                                        if (tableContentBean != null && !TextUtils.isEmpty(tableContentBean.text)) {
                                                            builder.append(tableContentBean.text);
                                                        }
                                                    }
                                                } else {
                                                    if (!checkListEmpty(tableBean1.getListListContent())) {
                                                        if (!checkListEmpty(tableBean1.getListListContent().get(0))) {
                                                            for (BaiKeEntity.TableContentBean tableContentBean : tableBean1.getListListContent().get(0)) {
                                                                if (tableContentBean != null && !TextUtils.isEmpty(tableContentBean.text)) {
                                                                    builder.append(tableContentBean.text);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                builder.append("  ");
                                            }
                                            builder.append("\n");
                                        }
                                    }
                                    String[] result = builder.toString().split("\n");
                                    builder = new StringBuilder();
                                    for (String s : result) {
                                        builder.append(s.trim()).append("\n");
                                    }
                                    contentBean.content = builder.toString();
                                }
                            }


                            data.contentBeans.add(contentBean);
                        }
                    }
                }

            }

            if (baiKeEntity.baikeTags != null) {
                data.tags = baiKeEntity.baikeTags.tags;
            }

        }


        //=============输出内容===================================
        LUtils.i(data.name);
        LUtils.i(data.description);
        LUtils.i(data.type);
        LUtils.i(data.baiKeUrl);
        LUtils.i(data.picUrl);


        LUtils.i(data.tags);
        if (data.profile != null) {
            for (String s : data.profile) {
                LUtils.i(s);
            }
        }

        if (!checkListEmpty(data.contentBeans)) {
            for (BaiKeMapEntity.ContentBean contentBean : data.contentBeans) {
                LUtils.i(contentBean.tag, "  ", contentBean.tempType, "\n", contentBean.content, "\n\n");
            }
        }
    }

    private static String listToString(List<?> list) {
        if (checkListEmpty(list)) {
            return "";
        }
        return list.toString().replaceAll("\\[|]", "");
    }

    private static boolean checkListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 正则表达式过滤日志开头
     */
    private static void filterLogStart() {
        String str = "04-21 15:32:24.007  38888578  3579998 I VideoEduCenterCardView: |updateCornerMark() view : 186506086";
//        String pattern = "<.+?>";
//        String pattern = "^(?i)mn";
        String pattern = "^[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3} {2}[0-9]+ {2}[0-9]+ ";

        //匹配04-21
        String dataPattern = "^[0-9]{2}-[0-9]{2}";
        //匹配15:32:24.007
        String timePattern = "[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}";

        Pattern r = Pattern.compile(timePattern);
        LUtils.i(r.flags());
        Matcher m = r.matcher(str);
        while (m.find()) {
            LUtils.i("匹配结果   ", m.group(), "==");
        }
        System.out.println(m.replaceAll(""));
    }


    private static void handleBaiKeResponseData() {
        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\虞书欣");
        try {
            List<String> list = FileUtils.readLines(file1);
            List<String> result = new ArrayList<>();
            list.forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    LUtils.i(s.replaceAll("\\\\{2}", "\\\\").replaceAll("\\\\n", ""));
//                    result.add(s.replaceAll("\\\\n","").replaceAll("\"\\{","{")
//                            .replaceAll("}\"","}")
//                            .replaceAll("\\\\\\\\","|").replaceAll("\\\\","").replaceAll("\\|","\\\\"));
                }
            });

//            FileUtils.writeLines(new File("C:\\Users\\dingzhixin.ex\\Desktop\\虞书欣百科.txt"),result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String asciiToNative(String asciicode) {
        String[] asciis = asciicode.split("\\\\u");
        String nativeValue = asciis[0];
        try {
            for (int i = 1; i < asciis.length; i++) {
                String code = asciis[i];
                nativeValue += (char) Integer.parseInt(code.substring(0, 4), 16);
                if (code.length() > 4) {
                    nativeValue += code.substring(4, code.length());
                }
            }
        } catch (NumberFormatException e) {
            return asciicode;
        }
        return nativeValue;
    }
//04-21 07:56:39.604  5256  5366 I SmartImageApplication XJST| : XJST|shouldBlock(),
// isProvisioned=[true], isTvInFactoryOKMode=[false], isTvInFactoryMode=[false],
// isInChildLockMode=[false], isInVideoChatMode=[false], facMode=[U], debugable=[false].

    private static AtomicInteger mAtomicInteger = new AtomicInteger(0);
    private static Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LUtils.i(mAtomicInteger.getAndIncrement(), "   ", Thread.currentThread());
        }
    };

    private static void testThread() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(mRunnable);
        }
    }


    public static String randomString(int i) {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int k = ran.nextInt(27);
            if (k == 0)
                break;

            sb.append((char) ('`' + k));
        }
        return sb.toString();
    }

    private static class TaoBaoBean {
        public String uri = "http";
        public String type = "{\"uri\":\"aaaaaaa\",\"type\":\"1\",\"bannerInfo\":\"\",\"tvtaoAnalytics\":{\"type\":\"Expose\",\"pageName\":\"Page_HisenseScreen\",\"eventName\":\"Expose_BannerPit\",\"args\":{\"shop_id\":\"185269192\",\"seller_id\":\"3486856666\",\"position_id\":\"166\",\"banner_name\":\"honlife\"}}}";
        public String tbAnalytics = "aaa";
    }


    private static void testVar() {
        byte[] bytes = new byte[300000000];
        Arrays.fill(bytes, (byte) 0);
        byte[] bytes1 = new byte[300000000];
        Arrays.fill(bytes1, (byte) 0);
        long start = System.currentTimeMillis();

        boolean result = Arrays.equals(bytes, bytes1);
        LUtils.i(result, " 耗时11 ", (System.currentTimeMillis() - start));


        long start1 = System.currentTimeMillis();
        boolean result1 = false;
        for (byte aByte : bytes) {
            if (aByte != 0) {
                result1 = true;
                break;
            }
        }
        LUtils.i(result1, " 耗时22 ", (System.currentTimeMillis() - start1));


//        for (int i = 0; i < bytes.length; i++) {
//            LUtils.i(bytes[i]);
//        }

    }

    private static void out() {
        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\001.pcm");

        try {
            byte[] bytes = FileUtils.readFileToByteArray(file1);
            StringBuilder builder = new StringBuilder();
            LUtils.i("开始=============================");
            for (int i = 0; i < bytes.length; i++) {
                if (i % 100 == 0) {
                    builder.append("\n");
                }
                builder.append(bytes[i]).append("  ");
            }
            LUtils.i(builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void splitStereoToMonoBitOne() {
        try {
            File dir = new File("C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件\\新建文件夹");
            File file1 = new File(dir, "重采样-8k-双声道-位深8-手机可识别.pcm");
            byte[] data = FileUtils.readFileToByteArray(file1);


            List<Byte> leftList = new ArrayList<>();
            List<Byte> rightList = new ArrayList<>();
            for (int i = 0; i < data.length; i = i + 2) {
                leftList.add(data[i]);
                rightList.add(data[i + 1]);
            }


            int leftSize = leftList.size();
            byte[] leftBytes = new byte[leftSize];
            for (int i = 0; i < leftSize; i++) {
                leftBytes[i] = leftList.get(i);
            }

            int rightSize = rightList.size();
            byte[] rightBytes = new byte[rightSize];
            for (int i = 0; i < rightSize; i++) {
                rightBytes[i] = rightList.get(i);
            }

            FileUtils.writeByteArrayToFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件\\重采样-8k-左声道-位深8-手机可识别.pcm"),
                    leftBytes);
            FileUtils.writeByteArrayToFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件\\重采样-8k-右声道-位深8-手机可识别.pcm"),
                    rightBytes);

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public static void splitStereoToMonoBitTwo() {
        try {
            File dir = new File("C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件\\新建文件夹");
            File file1 = new File(dir, "重采样-8k-双声道-位深16-手机可识别.pcm");
            byte[] data = FileUtils.readFileToByteArray(file1);


            List<Byte> leftList = new ArrayList<>();
            List<Byte> rightList = new ArrayList<>();
            for (int i = 0; i < data.length; i = i + 4) {
                leftList.add(data[i]);
                leftList.add(data[i + 1]);
                rightList.add(data[i + 2]);
                rightList.add(data[i + 3]);
            }


            int leftSize = leftList.size();
            byte[] leftBytes = new byte[leftSize];
            for (int i = 0; i < leftSize; i++) {
                leftBytes[i] = leftList.get(i);
            }

            int rightSize = rightList.size();
            byte[] rightBytes = new byte[rightSize];
            for (int i = 0; i < rightSize; i++) {
                rightBytes[i] = rightList.get(i);
            }

            FileUtils.writeByteArrayToFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件\\重采样-8k-左声道-位深16-手机可识别.pcm"),
                    leftBytes);
            FileUtils.writeByteArrayToFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件\\重采样-8k-右声道-位深16-手机可识别.pcm"),
                    rightBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void filterView() {
        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\8958.txt");
        try {
            List<String> list = FileUtils.readLines(file1);
            for (String s : list) {
                if (!s.contains("0,0-0,0")) {
                    LUtils.i(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static void unzipGradleLibJarFile() {
        File file = new File("C:\\Users\\dingzhixin.ex\\.gradle\\wrapper\\dists\\gradle-5.6.4-all\\ankdp27end7byghfw1q2sw75f\\gradle-5.6.4\\lib");
        List<File> list = getAllFile(file);

        for (File file1 : list) {
            try {
                UZipFile.unZipFiles(file1, "C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件\\libs\\");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            LUtils.i(file1.getAbsolutePath());
        }


    }

    private static void getGradleJavaFile() {
        File file = new File("E:\\work\\app\\gradlesrcc\\src");
        File[] files = file.listFiles();
        List<File> list = new ArrayList<>();

        if (files != null) {
            for (File file1 : files) {
                list.addAll(getFiles(file1));
            }
        }

        List<File> result = new ArrayList<>();
        for (File file1 : list) {
            String path = file1.getAbsolutePath();
            if (path.contains("org")) {
                result.addAll(getAllFile(file1));
            }
        }

        for (File s : result) {
            String path = s.getAbsolutePath().substring(s.getAbsolutePath().indexOf("org"));
            LUtils.i(path);
            File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\抽取java文件", path);
            LUtils.i(file1.getAbsolutePath());
            try {
                FileUtils.copyFile(s, file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void getHierarchyFiles(File file, int deep) {
        List<File> list = new ArrayList<>();
        if (file.isDirectory()) {
            for (int i = 0; i < deep; i++) {

            }
        }

    }

    public static void getFiles(List<File> list) {
        List<File> result = new ArrayList<>();
        for (File file : list) {
            result.addAll(getFiles(file));
        }
    }

    /**
     * 获取文件夹的下一级的所有文件
     */
    public static List<File> getFiles(File file) {
        List<File> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                list.addAll(Arrays.asList(files));
            }
        }
        return list;
    }

    public static List<String> getFilePath(File file) {
        List<String> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    list.addAll(getFilePath(file1));
                }
            }
        } else {
            String path = file.getAbsolutePath();
//            LUtils.i(path);
            list.add(path);
        }
        return list;
    }

    public static List<File> getAllFile(File file) {
        List<File> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    list.addAll(getAllFile(file1));
                }
            }
        } else {
            String path = file.getAbsolutePath();
//            LUtils.i(path);
            list.add(file);
        }
        return list;
    }

    private static void parseUrlEncodeString(String s) {
        //"%E9%AB%98%E5%85%B4%E7%9A%84%E9%85%B8%E7%94%9C%E8%8B%A6%E8%BE%A3"
        LUtils.i(URLDecoder.decode(s));
    }

}
