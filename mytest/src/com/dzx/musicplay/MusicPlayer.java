package com.dzx.musicplay;


import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Java 播放音频
 *
 * @ClassName: MusicPlayer
 * @Description: TODO
 * @author: hyacinth
 * @date: 2019年10月25日 下午12:28:41
 * @Copyright: hyacinth
 */
public class MusicPlayer {

    private static DataLine.Info info = null;
    private static AudioFormat format = null;
    private static SourceDataLine line = null;
    private static AudioInputStream audio = null;

    public static void main(String[] args) throws Exception {
        MusicPlayer player = new MusicPlayer();
        //player.audio_to_pcm("E:\\KuGou\\左宏元 - 功德圆满 (情与法笛子版).mp3", "E:\\KuGou\\左宏元 - 功德圆满 (情与法笛子版).pcm");

        //player.get_info("C:\\Users\\Administrator\\Desktop\\梦涵 - 加减乘除.wav");

        //player.play("C:\\Users\\Administrator\\Desktop\\梦涵 - 加减乘除.pcm");
        //player.play("C:\\Users\\Administrator\\Desktop\\梦涵 - 加减乘除.wav");
        player.play("C:\\Users\\dingzhixin.ex\\Desktop\\SoundTouchDemo-master\\bjbj.mp3");
        //player.play("C:\\Users\\Administrator\\Desktop\\梦涵 - 加减乘除.flac");
    }

    /**
     * Java Music 播放
     *
     * @param path 文件路径
     * @throws Exception
     * @Title: play_pcm
     * @Description: 播放 pcm
     * @date 2019年10月25日 下午12:28:41
     */
    public void play(String path) throws Exception {
        load(path);
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = audio.read(buffer)) > 0) {
                line.write(buffer, 0, len);
            }
            line.drain();
            line.stop();
            line.close();
            audio.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 加载音频
     *
     * @param url
     * @throws Exception
     * @date 2022年1月7日14点15分
     */
    public void load(URL url) throws Exception {
        load(AudioSystem.getAudioInputStream(url));
    }

    /**
     * 加载音频
     *
     * @param file
     * @throws Exception
     * @date 2022年1月7日14点15分
     */
    public void load(File file) throws Exception {
        String name = file.getName();
        if (Audio.isSupport(name)) {
            if (Audio.getIndex(name) == Audio.MP3.index()) {
                MpegAudioFileReader reader = new MpegAudioFileReader();
                load(reader.getAudioInputStream(file));
            } else {
                load(AudioSystem.getAudioInputStream(file));
            }
        }
    }

    /**
     * 加载音频
     *
     * @param path
     * @throws Exception
     * @date 2022年1月7日14点15分
     */
    public void load(String path) throws Exception {
        load(new File(path));
    }

    /**
     * 加载音频
     *
     * @param stream
     * @throws Exception
     * @date 2022年1月7日14点15分
     */
    public void load(InputStream stream) throws Exception {
        load_audio(AudioSystem.getAudioInputStream(stream));
    }

    /**
     * 加载音频
     *
     * @param encoding
     * @param stream
     * @date 2022年1月7日14点15分
     */
    public void load(AudioFormat.Encoding encoding, AudioInputStream stream) {
        load_audio(AudioSystem.getAudioInputStream(encoding, stream));
    }

    /**
     * 加载音频
     *
     * @param format
     * @param stream
     * @date 2022年1月7日14点15分
     */
    public void load(AudioFormat format, AudioInputStream stream) {
        load_audio(AudioSystem.getAudioInputStream(format, stream));
    }

    /**
     * 加载音频
     *
     * @param stream 音频流
     * @date 2022年1月7日14点15分
     */
    public void load_audio(AudioInputStream stream) {
        try {
            format = stream.getFormat();
            format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(),
                    format.getChannels() * 2, format.getSampleRate(), false);
            audio = AudioSystem.getAudioInputStream(format, stream);
            info = new DataLine.Info(SourceDataLine.class, format, AudioSystem.NOT_SPECIFIED);
            line = (SourceDataLine) AudioSystem.getLine(info);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 导出文件为PCM
     *
     * @param src 音频文件路径
     * @param dst PCM文件导出路径
     * @throws Exception
     * @date 2022年1月7日14点15分
     */
    private void audio_to_pcm(String src, String dst) throws Exception {
        load(src);
        AudioSystem.write(audio, AudioFileFormat.Type.WAVE, new File(dst));
        line.close();
        audio.close();
    }

    /**
     * Java Music 获取wav或者pcm文件的编码信息
     *
     * @param path wav或者pcm文件路径
     * @return wav或者pcm文件的编码信息
     * @Title: get_info
     * @Description: 获取wav或者pcm文件的编码信息
     * @date 2019年10月25日 下午12:28:41
     */
    public String get_info(String path) {
        File file = new File(path);
        AudioInputStream ais;
        String result = "";
        try {
            ais = AudioSystem.getAudioInputStream(file);
            AudioFormat af = ais.getFormat();
            result = af.toString();
            System.out.println(result);
            System.out.println("音频总帧数：" + ais.getFrameLength());
            System.out.println("每秒播放帧数：" + af.getSampleRate());
            float len = ais.getFrameLength() / af.getSampleRate();
            System.out.println("音频时长（秒）：" + len);
            System.out.println("音频时长：" + (int) len / 60 + "分" + len % 60 + "秒");
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    /**
     * Java Music 获取mp3文件的图片
     *
     * @param mpath mp3flac文件路径
     * @Title: get_image_from_mp3
     * @Description: 获取mp3文件的图片
     * @date 2019年10月25日 下午12:28:41
     */
    public void get_image_from_mp3(String mpath) throws Exception {
        File sourceFile = new File(mpath);
        MP3File mp3file = new MP3File(sourceFile);
        AbstractID3v2Tag tag = mp3file.getID3v2Tag();
        AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");
        FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
        byte[] image = body.getImageData();
        Image img = Toolkit.getDefaultToolkit().createImage(image, 0, image.length);
        ImageIcon icon = new ImageIcon(img);
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\梦涵 - 加减乘除.jpg");
        fos.write(image);
        fos.close();
        System.out.println("width:" + icon.getIconWidth());
        System.out.println("height:" + icon.getIconHeight());
    }

}
