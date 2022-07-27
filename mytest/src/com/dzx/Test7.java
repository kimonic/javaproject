package com.dzx;

import com.dzx.util.LUtils;
import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1TagField;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test7 {
    private static boolean mLoop = true;
    private static int mCount = 100;

    public static void main(String[] args) throws FileNotFoundException {

//        try {
//            copyFile(new File("C:\\Users\\dingzhixin.ex\\Desktop\\channel复制文件测试.bat"), new File("C:\\Users\\dingzhixin.ex\\Desktop\\启动调试界面.bat"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        renameImageFile();
        try {
            MP3File mp3File = new MP3File("C:\\Users\\dingzhixin.ex\\Desktop\\1.mp3");

            AbstractID3v2Tag tag = mp3File.getID3v2Tag();
            List<TagField> frame = tag.getFrame("APIC");
            LUtils.i(frame.get(0).getClass().getName());

            byte[] imageData =((FrameBodyAPIC) ((ID3v23Frame) (frame.get(0))).getBody()).getImageData();
            Image image = Toolkit.getDefaultToolkit().createImage(imageData);
            ImageIcon icon = new ImageIcon(image);
            FileOutputStream fos = new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\test1.jpg");
            fos.write(imageData);
            fos.close();

            LUtils.i(tag instanceof ID3v23Tag);

            LUtils.i(tag.getClass().getName());
            System.out.println(tag.getClass());

//            ID3v23Frame tagField = new ID3v23Frame("COMM");
//            FrameBodyCOMM bodyLINK = new FrameBodyCOMM((byte) 0,"ENG", "", "wewewe");
//            tagField.setBody(bodyLINK);
//            ((ID3v23Tag)tag).setField(tagField);
////            LUtils.i(mp3File);
////            LUtils.i(tag);
//            mp3File.setTag(tag);
//            mp3File.commit();

//            mp3File.getTag().deleteField("COMM");
            Iterator<TagField> iterator = mp3File.getTag().getFields();
            LUtils.i("==========");
            while (iterator.hasNext()) {

                TagField tagField = iterator.next();
                if (tagField instanceof ID3v1TagField) {
                    LUtils.i(tagField.getId(), " 1 ", ((ID3v1TagField) tagField).getContent());
                } else if (tagField instanceof ID3v23Frame) {
                    LUtils.i(((ID3v23Frame) tagField).getIdentifier(), " 2 ", ((ID3v23Frame) tagField).getContent());
                } else {
                    LUtils.i(tagField);
                }

            }

            mp3File.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        String url = "D:\\Paparazzi.mp3";
        //        File sourceFile = new File(url);
        //        MP3File mp3file = new MP3File(sourceFile);
        //
        //        AbstractID3v2Tag tag = mp3file.getID3v2Tag();
        //        AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");
        //        FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
        //        byte[] imageData = body.getImageData();
//                Image img= Toolkit.getDefaultToolkit().createImage(imageData, 0,imageData.length);
        //        System.out.println("img----" + imageData);
        //        ImageIcon icon = new ImageIcon(img);
        //        FileOutputStream fos = new FileOutputStream("D://test1.jpg");
        //        fos.write(imageData);
        //        fos.close();
        //
        //        System.out.println("width:"+icon.getIconWidth());
        //        System.out.println("height:"+icon.getIconHeight());
        //        getImg(icon);

    }


    private static synchronized void testSync(String tag) {
        LUtils.i("开始===============   ", tag);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LUtils.i("结束===============   ", tag);
    }

    public static void copyFile(File dest, File src) throws IOException {
        if (!dest.exists()) {
            dest.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = (new FileInputStream(src)).getChannel();
            destination = (new FileOutputStream(dest)).getChannel();
            long bytesToCopy = source.size();

            for (long bytesCopied = 0L; bytesCopied < bytesToCopy; bytesCopied += destination.transferFrom(source, 0L, source.size())) {
            }
        } finally {
            if (source != null) {
                source.close();
            }

            if (destination != null) {
                destination.close();
            }

        }

    }

    private static void renameImageFile() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\少数民族测试语料\\转换维语");

        File[] list = file.listFiles();
        int size = list.length;
        for (int i = 0; i < size; i++) {
            File origin = list[i];
            origin.renameTo(new File(origin.getParent(), "维语" + i + ".wav"));
        }

    }

    /**
     * 复制文件到U盘
     */
    private static void copyFileToUsb() {
        try {
            FileUtils.copyFile(new File("E:\\语音问答app相关资料\\aiCore-demo-sendrecfile-0721-01.apk"), new File("F:\\Android\\speechanswer\\aiCore-demo-sendrecfile-0721-01.apk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testTsvWrite() throws FileNotFoundException {

        //如果仅仅是需要以Excel方式打开tsv文件以表格形式展示,则可以简单的将每一行的值使用
        //,进行分隔即可

        //输出tsv相关设置
        TsvWriterSettings settings = new TsvWriterSettings();
        //行分隔符
        settings.getFormat().setLineSeparator("\n");
        //字段含义栏,顶部标题栏
        String[] headers = new String[]{"deviceId", "version", "name", "age"};
        //设置顶部字段含义
        settings.setHeaders(headers);
        //tsv文件要输出到的文件
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\测试写入.tsv");
        //输出流
        FileOutputStream outputStream = new FileOutputStream(file);
        //tsv输出writer
        TsvWriter writer = new TsvWriter(outputStream, settings);
        //使用map key-value的形式添加一行tsv,一个map为1行,无值字段可以不输入
        Map<String, String> map = new HashMap<>();
        //map.put("deviceId","设备id");
        map.put("age", "100");
        map.put("version", "your version");
        //map.put("name","your name");
        //如果需要写入标题栏,在settings设置后,需要调用该语句
        writer.writeHeaders();
        //以map形式写入一行tsv数据
        writer.writeRow(map);
        //关闭流
        writer.close();
    }

}
