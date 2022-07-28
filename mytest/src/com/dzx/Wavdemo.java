package com.dzx;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.apache.commons.io.FileUtils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 绘制波形图
 * <p>
 * FileName: Wavdemo
 * Author:   xuan zongjun
 * Date:     2018/10/18 17:16
 * Description:https://www.dandelioncloud.cn/article/details/1524091403294773249
 * <p>
 * https://mvnrepository.com/artifact/com.googlecode.soundlibs/mp3spi
 * https://mvnrepository.com/artifact/org.jflac/jflac-codec
 * https://baijiahao.baidu.com/s?id=1692396053352296939&wfr=spider&for=pc
 */
public class Wavdemo {
    public static class WaveformGraph extends JFrame {
        private LinkedList<Short> deque = new LinkedList<Short>();
        private Timer timer;
        private Image buffered;
        private Image showing;

        public WaveformGraph(int width, int height) {
            setSize(width, height);
            timer = new Timer();
            buffered = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Graphics g = buffered.getGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.BLACK);
                    g.translate(10, getHeight() / 2);
                    synchronized (deque) {
                        float heightRate = 1;
                        if (deque.size() > 1) {
                            Iterator<Short> iter = deque.iterator();
                            Short p1 = iter.next();
                            Short p2 = iter.next();
                            int x1 = 0, x2 = 0;
                            while (iter.hasNext()) {
                                g.drawLine(x1, (int) (p1 * heightRate), x2, (int) (p2 * heightRate));
                                p1 = p2;
                                p2 = iter.next();
                                x1 = x2;
                                x2 += 1;
                            }
                        }
                    }
                    g.dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            showing = buffered;
                            repaint();
                            showing = null;
                        }
                    });
                }
            }, 100, 100);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (buffered != null) {
                g.drawImage(buffered, 0, 0, null);
            }
        }

        public void put(short v) {
            synchronized (deque) {
                deque.add(v);
                if (deque.size() > 500) {
                    deque.removeFirst();
                }
            }
        }

        public void clear() {
            deque.clear();
        }
    }

    public static void main(String[] args) throws Exception {
//  record();
//        WaveformGraph waveformGraph = new WaveformGraph(500, 300);
//        waveformGraph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        waveformGraph.setVisible(true);
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\SoundTouchDemo-master\\bjbj.mp3");
//        MpegAudioFileReader reader = new MpegAudioFileReader();
        AudioInputStream ais = getPcmAudioInputStream(file.getAbsolutePath());

        File file1 = new File("C:\\Users\\dingzhixin.ex\\Desktop\\858.pcm");
        byte[] bytes = new byte[1024];
        while (ais.read(bytes) != -1) {
            FileUtils.writeByteArrayToFile(file1, bytes, true);
        }


//        //AudioInputStream ais = reader.getAudioInputStream(file);
//
//        //AudioInputStream ais = AudioSystem.getAudioInputStream(file);
//        printFormat(ais.getFormat());
//        SourceDataLine player = AudioSystem.getSourceDataLine(ais.getFormat());
//        player.open();
//        player.start();
//        byte[] buf = new byte[4];
//        int len;
//        while ((len = ais.read(buf)) != -1) {
//            if (ais.getFormat().getChannels() == 2) {
//                if (ais.getFormat().getSampleRate() == 16) {
//                    waveformGraph.put((short) ((buf[1] << 8) | buf[0]));//左声道
////     waveformGraph.put((short) ((buf[3] << 8) | buf[2]));//右声道
//                } else {
//                    waveformGraph.put(buf[1]);//左声道
//                    waveformGraph.put(buf[3]);//左声道
////     waveformGraph.put(buf[2]);//右声道
////     waveformGraph.put(buf[4]);//右声道
//                }
//            } else {
//                if (ais.getFormat().getSampleRate() == 16) {
//                    waveformGraph.put((short) ((buf[1] << 8) | buf[0]));
//                    waveformGraph.put((short) ((buf[3] << 8) | buf[2]));
//                } else {
//                    waveformGraph.put(buf[1]);
//                    waveformGraph.put(buf[2]);
//                    waveformGraph.put(buf[3]);
//                    waveformGraph.put(buf[4]);
//                }
//            }
//            player.write(buf, 0, len);
//        }
//        player.close();
//        ais.close();
    }

    public static void printFormat(AudioFormat format) {
        System.out.println(format.getEncoding() + " => "
                + format.getSampleRate() + " hz, "
                + format.getSampleSizeInBits() + " bit, "
                + format.getChannels() + " channel, "
                + format.getFrameRate() + " frames/second, "
                + format.getFrameSize() + " bytes/frame");
    }


    /**
     * http://t.zoukankan.com/attilax-p-15197260.html
     * 获取pcm流
     */
    private static AudioInputStream getPcmAudioInputStream(String mp3filepath) throws Exception, IOException {
        AudioInputStream AudioInputStream1 = new MpegAudioFileReader().getAudioInputStream(new File(mp3filepath));
        AudioFormat sourceFormat = AudioInputStream1.getFormat();
        AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), 16,
                sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(), false);
        AudioInputStream audioInputStream_Pcm = AudioSystem.getAudioInputStream(targetFormat, AudioInputStream1);
        return audioInputStream_Pcm;
    }
}
