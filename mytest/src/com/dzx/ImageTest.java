package com.dzx;

import com.dzx.bean.PositionBean;
import com.dzx.util.BinaryOperationUtil;
import com.dzx.util.LUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;
import org.apache.poi.hslf.blip.Bitmap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;

public class ImageTest {
    private static float[] redHsbMin1 = new float[]{0 / 180.0f, 43 / 255.0f, 46 / 255.0f};
    private static float[] redHsbMax1 = new float[]{11 / 180.0f, 255 / 255.0f, 255 / 255.0f};

    private static float[] orangeHsbMin = new float[]{11 / 180.0f, 43 / 255.0f, 46 / 255.0f};
    private static float[] orangeHsbMax = new float[]{26 / 180.0f, 255 / 255.0f, 255 / 255.0f};

    private static float[] yellowHsbMin = new float[]{26 / 180.0f, 43 / 255.0f, 46 / 255.0f};
    private static float[] yellowHsbMax = new float[]{35 / 180.0f, 255 / 255.0f, 255 / 255.0f};


    private static float[] greenHsbMin = new float[]{35 / 180.0f, 43 / 255.0f, 46 / 255.0f};
    private static float[] greenHsbMax = new float[]{78 / 180.0f, 255 / 255.0f, 255 / 255.0f};

    private static float[] cyanHsbMin = new float[]{78 / 180.0f, 43 / 255.0f, 46 / 255.0f};
    private static float[] cyanHsbMax = new float[]{100 / 180.0f, 255 / 255.0f, 255 / 255.0f};

    private static float[] blueHsbMin = new float[]{100 / 180.0f, 43 / 255.0f, 46 / 255.0f};
    private static float[] blueHsbMax = new float[]{125 / 180.0f, 255 / 255.0f, 255 / 255.0f};

    private static float[] purpleHsbMin = new float[]{125 / 180.0f, 43 / 255.0f, 46 / 255.0f};
    private static float[] purpleHsbMax = new float[]{156 / 180.0f, 255 / 255.0f, 255 / 255.0f};

    private static float[] redHsbMin2 = new float[]{156 / 180.0f, 43 / 255.0f, 46 / 255.0f};
    private static float[] redHsbMax2 = new float[]{180 / 180.0f, 255 / 255.0f, 255 / 255.0f};

    private static float[] whiteHsbMin = new float[]{0 / 180.0f, 0 / 255.0f, 221 / 255.0f};
    private static float[] whiteHsbMax = new float[]{180 / 180.0f, 31 / 255.0f, 255 / 255.0f};

    private static float[] otherHsbMin = new float[]{0 / 180.0f, 31 / 255.0f, 221 / 255.0f};
    private static float[] otherHsbMax = new float[]{180 / 180.0f, 43 / 255.0f, 255 / 255.0f};

    private static float[] grayHsbMin = new float[]{0 / 180.0f, 0 / 255.0f, 46 / 255.0f};
    private static float[] grayHsbMax = new float[]{180 / 180.0f, 43 / 255.0f, 221 / 255.0f};

    private static float[] blackHsbMin = new float[]{0 / 180.0f, 0 / 255.0f, 0 / 255.0f};
    private static float[] blackHsbMax = new float[]{180 / 180.0f, 255 / 255.0f, 46 / 255.0f};


    public static void main(String[] args) throws IOException {
//        logRange();
//        taoYi();
        //{(.|\r\n)+}

        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\参数处理.txt");
        List<String> list = FileUtils.readLines(file);
        int count = 0;
        List<String> textResult = new ArrayList<>();
        for (String s : list) {
            if (!TextUtils.isEmpty(s)) {
                textResult.add(s.replaceAll("\\{[\\S\\s]+}|;", ""));
            }
        }
        LUtils.i(textResult.size());

        List<float[]> paramsList = new ArrayList<>();
        paramsList.add(redHsbMin1);
        paramsList.add(redHsbMax1);
        paramsList.add(orangeHsbMin);
        paramsList.add(orangeHsbMax);
        paramsList.add(yellowHsbMin);
        paramsList.add(yellowHsbMax);
        paramsList.add(greenHsbMin);
        paramsList.add(greenHsbMax);
        paramsList.add(cyanHsbMin);
        paramsList.add(cyanHsbMax);
        paramsList.add(blueHsbMin);
        paramsList.add(blueHsbMax);
        paramsList.add(purpleHsbMin);
        paramsList.add(purpleHsbMax);
        paramsList.add(redHsbMin2);
        paramsList.add(redHsbMax2);
        paramsList.add(whiteHsbMin);
        paramsList.add(whiteHsbMax);
        paramsList.add(otherHsbMin);
        paramsList.add(otherHsbMax);
        paramsList.add(grayHsbMin);
        paramsList.add(grayHsbMax);
        paramsList.add(blackHsbMin);
        paramsList.add(blackHsbMax);

        LUtils.i(paramsList.size());
        for (int i = 0; i < paramsList.size(); i++) {
            count++;
            LUtils.i(textResult.get(i), "{", outParam(paramsList.get(i)), "};");
        }
        LUtils.i(count);
//        for (String s : textResult) {
//            LUtils.i("paramsList.add("+s.split("\\s+")[4]+");");
//        }
//        String s = "private static float[] blackHsbMax = new float[]{180 / 180.0f, 255 / 255.0f, 46 / 255.0f};";
//        LUtils.i(s.replaceAll("\\{(.|\\r\\n)+}", ""));
//        LUtils.i(s.replaceAll("\\{[\\S\\s]+}", ""));
    }

    private static String outParam(float[] floats) {
        String[] result = Arrays.toString(floats).replaceAll("\\[", "").replaceAll("]", "").split(",");
        String[] list = new String[result.length];
        for (int i = 0; i < result.length; i++) {
            list[i] = result[i] + "f";
        }
        return Arrays.toString(list).replaceAll("\\[", "").replaceAll("]", "");
    }

    private static void isContains(float[] floats) {
        if (containsColor(floats, orangeHsbMin, orangeHsbMax)
                || containsColor(floats, whiteHsbMin, whiteHsbMax)
                || containsColor(floats, purpleHsbMin, purpleHsbMax)
                || containsColor(floats, blueHsbMin, blueHsbMax)
                || containsColor(floats, greenHsbMin, greenHsbMax)
                || containsColor(floats, cyanHsbMin, cyanHsbMax)
                || containsColor(floats, yellowHsbMin, yellowHsbMax)
                || containsColor(floats, redHsbMin1, redHsbMax1)
                || containsColor(floats, redHsbMin2, redHsbMax2)
                || containsColor(floats, blackHsbMin, blackHsbMax)
                || containsColor(floats, grayHsbMin, grayHsbMax)
                || containsColor(floats, otherHsbMin, otherHsbMax)
        ) {
            LUtils.i("包含");
        } else {
            LUtils.i("不包含");
        }
    }

    private static void test(List<String> list) {
        LUtils.i(list.hashCode());
        list.add("123456");
        list = null;
        LUtils.i(list);
    }


    private static void taoYi() {
        int count = 0;
        int hMin = 255;
        int hMax = 0;
        int sMin = 255;
        int sMax = 0;
        int bMin = 255;
        int bMax = 0;

        for (int i = 0; i < 181; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    float[] floats = new float[]{i / 180.0f, j / 255.0f, k / 255.0f};
                    if (containsColor(floats, orangeHsbMin, orangeHsbMax)
                            || containsColor(floats, whiteHsbMin, whiteHsbMax)
                            || containsColor(floats, purpleHsbMin, purpleHsbMax)
                            || containsColor(floats, blueHsbMin, blueHsbMax)
                            || containsColor(floats, greenHsbMin, greenHsbMax)
                            || containsColor(floats, cyanHsbMin, cyanHsbMax)
                            || containsColor(floats, yellowHsbMin, yellowHsbMax)
                            || containsColor(floats, redHsbMin1, redHsbMax1)
                            || containsColor(floats, redHsbMin2, redHsbMax2)
                            || containsColor(floats, blackHsbMin, blackHsbMax)
                            || containsColor(floats, grayHsbMin, grayHsbMax)
                            || containsColor(floats, otherHsbMin, otherHsbMax)
                    ) {

                    } else {
                        count++;
                        if (hMax < i) {
                            hMax = i;
                        }
                        if (hMin > i) {
                            hMin = i;
                        }
                        if (sMax < j) {
                            sMax = j;
                        }
                        if (sMin > j) {
                            sMin = j;
                        }
                        if (bMax < k) {
                            bMax = k;
                        }
                        if (bMin > k) {
                            bMin = k;
                        }
                        LUtils.i("逃逸 = ", i, " , ", j, " ,  ", k);
                    }
                }
            }
        }
        LUtils.i("逃逸次数 = ", count, " , 总数 = ", 180 * 255 * 255, " , 占比 = ", 1.0f * count / (180 * 255 * 255));
        LUtils.i(hMin, " , ", hMax, "\n", sMin, " , ", sMax, "\n", bMin, " , ", bMax);
    }


    private static void logRange() {


//        filterImage();

        logRange("黑色", blackHsbMin, blackHsbMax);
        logRange("橙色", orangeHsbMin, orangeHsbMax);
        logRange("白色", whiteHsbMin, whiteHsbMax);

        logRange("紫色", purpleHsbMin, purpleHsbMax);
        logRange("蓝色", blueHsbMin, blueHsbMax);
        logRange("绿色", greenHsbMin, greenHsbMax);

        logRange("青色", cyanHsbMin, cyanHsbMax);
        logRange("红色1", redHsbMin1, redHsbMax1);
        logRange("黄色", yellowHsbMin, yellowHsbMax);

        logRange("红色2", redHsbMin2, redHsbMax2);
        logRange("灰色", grayHsbMin, grayHsbMax);
        logRange("其他", otherHsbMin, otherHsbMax);
    }

    private static void logRange(String colorName, float[] hsbMin, float[] hsbMax) {
        LUtils.i(colorName, " \nH分量范围: ", hsbMin[0], " ~ ", hsbMax[0],
                " ,\nS分量范围: ", hsbMin[1], " ~ ", hsbMax[1],
                " ,\nB分量范围: ", hsbMin[2], " ~ ", hsbMax[2], "\n\n"
        );
    }

    private static void filterImage() {
        //橙色 11,43,46  25 255 255
        //
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\ColorBlend-master\\pic-008.png");
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\0001.png");
        try {
            float[] orangeHsbMin = new float[]{11 / 180.0f, 43 / 255.0f, 46 / 255.0f};
            float[] orangeHsbMax = new float[]{25 / 180.0f, 255 / 255.0f, 255 / 255.0f};

            float[] whiteHsbMin = new float[]{0 / 180.0f, 0 / 255.0f, 221 / 255.0f};
            float[] whiteHsbMax = new float[]{180 / 180.0f, 30 / 255.0f, 255 / 255.0f};

            float[] purpleHsbMin = new float[]{125 / 180.0f, 43 / 255.0f, 46 / 255.0f};
            float[] purpleHsbMax = new float[]{155 / 180.0f, 255 / 255.0f, 255 / 255.0f};

            float[] blueHsbMin = new float[]{100 / 180.0f, 43 / 255.0f, 46 / 255.0f};
            float[] blueHsbMax = new float[]{124 / 180.0f, 255 / 255.0f, 255 / 255.0f};

            float[] greenHsbMin = new float[]{35 / 180.0f, 43 / 255.0f, 46 / 255.0f};
            float[] greenHsbMax = new float[]{77 / 180.0f, 255 / 255.0f, 255 / 255.0f};

            float[] cyanHsbMin = new float[]{78 / 180.0f, 43 / 255.0f, 46 / 255.0f};
            float[] cyanHsbMax = new float[]{99 / 180.0f, 255 / 255.0f, 255 / 255.0f};

            float[] yellowHsbMin = new float[]{26 / 180.0f, 43 / 255.0f, 46 / 255.0f};
            float[] yellowHsbMax = new float[]{34 / 180.0f, 255 / 255.0f, 255 / 255.0f};

            float[] redHsbMin1 = new float[]{0 / 180.0f, 43 / 255.0f, 46 / 255.0f};
            float[] redHsbMax1 = new float[]{10 / 180.0f, 255 / 255.0f, 255 / 255.0f};

            float[] redHsbMin2 = new float[]{156 / 180.0f, 43 / 255.0f, 46 / 255.0f};
            float[] redHsbMax2 = new float[]{180 / 180.0f, 255 / 255.0f, 255 / 255.0f};

            float[] grayHsbMin = new float[]{0 / 180.0f, 0 / 255.0f, 46 / 255.0f};
            float[] grayHsbMax = new float[]{180 / 180.0f, 43 / 255.0f, 220 / 255.0f};

            float[] blackHsbMin = new float[]{0 / 180.0f, 0 / 255.0f, 0 / 255.0f};
            float[] blackHsbMax = new float[]{180 / 180.0f, 255 / 255.0f, 46 / 255.0f};

            BufferedImage bufferedImage = ImageIO.read(file);
            LUtils.i(bufferedImage);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            LUtils.i(width, ", ", height);
            BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Set<Float> set = new HashSet<>();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int color = bufferedImage.getRGB(i, j);
                    float[] floats = Color.RGBtoHSB(red(color), green(color), blue(color), null);
                    if (
                            containsColor(floats, orangeHsbMin, orangeHsbMax)
                                    || containsColor(floats, whiteHsbMin, whiteHsbMax)
                                    || containsColor(floats, purpleHsbMin, purpleHsbMax)
                                    || containsColor(floats, blueHsbMin, blueHsbMax)
                                    || containsColor(floats, greenHsbMin, greenHsbMax)
                                    || containsColor(floats, cyanHsbMin, cyanHsbMax)
                                    || containsColor(floats, yellowHsbMin, yellowHsbMax)
                                    || containsColor(floats, redHsbMin1, redHsbMax1)
                                    || containsColor(floats, redHsbMin2, redHsbMax2)
//                            || containsColor(floats, blackHsbMin, blackHsbMax)
                                    || containsColor(floats, grayHsbMin, grayHsbMax)
                    ) {
                        result.setRGB(i, j, color);
                    } else {
                        if (!containsColor(floats, blackHsbMin, blackHsbMax)) {
                            set.add(floats[0]);
                            LUtils.i(Arrays.toString(floats));
                        } else {
                            result.setRGB(i, j, 0xff000000);
                        }
                        result.setRGB(i, j, 0xff000000);
                    }
                }
            }

            int color = bufferedImage.getRGB(170, 50);
            LUtils.i(Integer.toHexString(color));
            LUtils.i(Arrays.toString(Color.RGBtoHSB(red(color), green(color), blue(color), null)));
            List<Float> list = new ArrayList<>(set);
            list.sort(new Comparator<Float>() {
                @Override
                public int compare(Float o1, Float o2) {
                    if (o1 > o2) {
                        return 1;
                    } else if (o1 < o2) {
                        return -1;
                    }
                    return 0;
                }
            });
            LUtils.i(list);

            ImageIO.write(result, "PNG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\f1.png"));//保存图片 JPEG表示保存格式
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //[0.13953488, 0.2559524, 0.65882355]
    //[0.5520833, 0.24365482, 0.77254903]
    //[0.13953488, 0.2559524, 0.65882355]
    //[0.13953488, 0.2559524, 0.65882355]
    //[0.5520833, 0.24365482, 0.77254903]
    //[0.13953488, 0.2559524, 0.65882355]
    //[0.5520833, 0.24365482, 0.77254903]

    //    private static boolean containsColor(float[] floats, float[] hsbMin, float[] hsbMax) {
//        return (floats[0] > hsbMin[0] && floats[0] < hsbMax[0])
//                && (floats[1] > hsbMin[1] && floats[1] < hsbMax[1])
//                && (floats[2] > hsbMin[2] && floats[2] < hsbMax[2]);
//    }
    private static boolean containsColor(float[] floats, float[] hsbMin, float[] hsbMax) {
        return (floats[0] >= hsbMin[0] && floats[0] <= hsbMax[0])
                && (floats[1] >= hsbMin[1] && floats[1] <= hsbMax[1])
                && (floats[2] >= hsbMin[2] && floats[2] <= hsbMax[2]);
    }

    private static float alphaResult(int top, int bottom) {
        return (top / 255.0f + bottom / 255.0f * (1 - top / 255.0f)) * 255;
    }

    // Cr = Cs * ( 1 - Ad ) + Cd  ;            Ar = As  * ( 1 -  Ad ) + Ad
    private static int DstOver(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = Da + Sa * (1 - Da);


        float Rr = (Dr + Sr * (1 - Da)) / Ra;
        float Rg = (Dg + Sg * (1 - Da)) / Ra;
        float Rb = (Db + Sb * (1 - Da)) / Ra;

        LUtils.i(Integer.toHexString(argb(Ra, Rr, Rg, Rb)));
        return argb(Ra, Rr, Rg, Rb);
    }

    //Cr = Cs + Cd * ( 1 - As ) ;            Ar = As + Ad * ( 1 -  As )
    private static int SrcOver(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;


        float Ra = Sa + Da * (1 - Sa);

        float Rr = (Sr + Dr * (1 - Sa)) / Ra;
        float Rg = (Sg + Dg * (1 - Sa)) / Ra;
        float Rb = (Sb + Db * (1 - Sa)) / Ra;


        LUtils.i(Integer.toHexString(argb(Ra, Rr, Rg, Rb)));
        return argb(Ra, Rr, Rg, Rb);
    }

    public static int argb(float alpha, float red, float green, float blue) {
        return ((int) (alpha * 255.0f + 0.5f) << 24) |
                ((int) (red * 255.0f + 0.5f) << 16) |
                ((int) (green * 255.0f + 0.5f) << 8) |
                (int) (blue * 255.0f + 0.5f);
    }

    public static void overlapImage() {
        try {
            // 设置图片大小
            //设置图片大小
            File file = new File("E:\\图搜相关资料\\图搜3.5开发跟踪记录\\5月5日第一版UI\\小聚识图 v3.5 标注切图\\小聚识图 v3.5 标注切图\\小聚识图标注\\assets\\未匹配.png");
            File file1 = new File("E:\\图搜相关资料\\图搜3.5开发跟踪记录\\5月5日第一版UI\\小聚识图 v3.5 标注切图\\小聚识图 v3.5 标注切图\\小聚识图标注\\assets\\形状结合.png");
            BufferedImage background = resizeImage(124, 124, ImageIO.read(file));
            BufferedImage qrCode = resizeImage(58, 65, ImageIO.read(file1));
            BufferedImage bufferedImage = new BufferedImage(136, 133, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = bufferedImage.createGraphics();
            LUtils.i(g);
            //在背景图片上添加二维码图片
            g.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
            g.drawImage(qrCode, 78, 68, qrCode.getWidth(), qrCode.getHeight(), null);
            g.dispose();

            for (int i = 0; i < 136; i++) {
                for (int j = 0; j < 133; j++) {
                    if (i < 124 && j < 124 && i > 78 && j > 68) {
                        if (background.getRGB(i, j) == 0 || qrCode.getRGB(i - 78, j - 68) == 0) {
                            continue;
                        }
                        LUtils.i("结果 = 0x", Integer.toHexString(bufferedImage.getRGB(i, j)),
                                " , 底色 = 0x", Integer.toHexString(background.getRGB(i, j)),
                                " , 顶色 = 0x", Integer.toHexString(qrCode.getRGB(i - 78, j - 68))
                        );
                    }
                }
            }


            ImageIO.write(bufferedImage, "PNG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\f1.png"));//保存图片 JPEG表示保存格式
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().drawImage(bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }


    /**
     * 层叠图片
     */
    private static void mergePic() {
        File file = new File("E:\\图搜相关资料\\图搜3.5开发跟踪记录\\5月5日第一版UI\\小聚识图 v3.5 标注切图\\小聚识图 v3.5 标注切图\\小聚识图标注\\assets\\未匹配.png");
        File file1 = new File("E:\\图搜相关资料\\图搜3.5开发跟踪记录\\5月5日第一版UI\\小聚识图 v3.5 标注切图\\小聚识图 v3.5 标注切图\\小聚识图标注\\assets\\形状结合.png");
        File end = new File("C:\\Users\\dingzhixin.ex\\Desktop\\f1.png");


        BufferedImage bi = null;//124*124
        BufferedImage bi1 = null;//58*65
        BufferedImage bEnd = null;//58*65
        int realWidth = 136;
        int realHeight = 133;
        int minWidth = 58;
        int minHeight = 65;
        BufferedImage bufferedImage = new BufferedImage(136, 133, BufferedImage.TYPE_4BYTE_ABGR);
        try {
            bi = ImageIO.read(file);
            bi1 = ImageIO.read(file1);
            bEnd = ImageIO.read(end);
            int width = bi.getWidth();
            int height = bi.getHeight();
            int offsetWidth = realWidth - minWidth;
            int offsetHeight = realHeight - minHeight;
            LUtils.i(width, "  ,  ", height);
            for (int i = 0; i < realWidth; i++) {
                for (int j = 0; j < realHeight; j++) {
                    if (i > offsetWidth && j > offsetHeight) {
                        int topColor = bi1.getRGB(i - offsetWidth, j - offsetHeight);

                        if (topColor != 0) {
                            String temp = Integer.toHexString(topColor);
                            if ("ff".equals(temp.substring(0, 2).toLowerCase())) {
                                bufferedImage.setRGB(i, j, topColor);
                            } else {

                                if (i < width && j < height) {
                                    int bottomColor = bi.getRGB(i, j);
//                                    LUtils.i("结果色值 ", Integer.toHexString(bEnd.getRGB(i, j)),
//                                            " , 顶层色值 = ", Integer.toHexString(topColor),
//                                            " , 底层色值 = ", Integer.toHexString(bottomColor));
//                                    float alpha1 = alpha(topColor) / 255.0f;
//                                    float alpha2 = alpha(bottomColor) / 255.0f;
//                                    float alpha = 1 - (1 - alpha1) * (1 - alpha2);

//                                    float r = (red(topColor) * alpha1 + (1 - alpha1) * alpha2 * red(bottomColor));
//                                    float g = (green(topColor) * alpha1 + (1 - alpha1) * alpha2 * green(bottomColor));
//                                    float b = (blue(topColor) * alpha1 + (1 - alpha1) * alpha2 * blue(bottomColor));
//(b[i] < 128) ? (2 * a[i] * b[i] / 255) : (255 - 2 * (255 - a[i]) * (255 - b[i]) / 255)
//                                    int r = hunHe(red(topColor), red(bottomColor));
//                                    int g = hunHe(green(topColor), green(bottomColor));
//                                    int b = hunHe(blue(topColor), blue(bottomColor));

                                    bufferedImage.setRGB(i, j, DstOver(topColor, bottomColor));
//
//                                    if (alpha1 != 0) {
////                                        bufferedImage.setRGB(i, j, bottomColor);
//                                    } else {
//                                        bufferedImage.setRGB(i, j, topColor);
//                                    }
                                } else {
                                    bufferedImage.setRGB(i, j, topColor);
                                }


                            }
                        } else {
                            if (i < width && j < height) {
                                bufferedImage.setRGB(i, j, bi.getRGB(i, j));
                            }
                        }
                    } else {
                        if (i < width && j < height) {
                            bufferedImage.setRGB(i, j, bi.getRGB(i, j));
                        }
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            ImageIO.write(bufferedImage, "PNG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\f.png"));//保存图片 JPEG表示保存格式
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static int hunHe(int a, int b) {
//        return (a < 128) ? (2 * a * b / 255) : (255 - 2 * (255 - a) * (255 - b) / 255);
        return (b < 128 ?
                (b == 0 ? 2 * b : Math.max(0, (255 - ((255 - a) << 8) / (2 * b))))
                : ((2 * (b - 128)) == 255 ? (2 * (b - 128)) : Math.min(255, ((a << 8) / (255 - (2 * (b - 128)))))))
                < 128 ? 0 : 255;
    }

    public static void combineBitmap(Bitmap bottomLayer, Bitmap topLayer) {
//        if(bottomLayer.getHeight() != topLayer.getHeight() || bottomLayer.getWidth() != topLayer.getWidth()){
//            return null;
//        }
//        int canvasDataBottom[] = new int[bottomLayer.getWidth() * bottomLayer.getHeight()];
//        int canvasDataTop[] = new int[topLayer.getWidth() * topLayer.getHeight()];
//        bottomLayer.copyPixelsToBuffer(IntBuffer.wrap(canvasDataBottom));
//        topLayer.copyPixelsToBuffer(IntBuffer.wrap(canvasDataTop));
//        //canvasData2和canvasData依据两者透明度混合
//        int canvasDataCombine[] = new int[canvasDataBottom.length];
//        for(int i = 0; i < canvasDataBottom.length; i++){
//            float k = (float) (canvasDataTop[i] >> 24 & 0xFF) / 255f; //取顶部像素第一字节作为透明度，除以0xFF求该顶部像素有多不透明
//            float reverseK = 1f - k;  //底部像素有多不透明（底部不透明度+顶部不透明度为1，刚好为完全不透明）
//            //红绿蓝元素分别做同一操作：顶部像素淡化为只有（k * 100）%的程度，加上淡化为((1-k）* 100)%程度的底部像素形成混合像素。当顶部k为1时，顶部完全不透明；（1-k）为0，底部完全透明，反之亦然。
//            int r = (int)((canvasDataTop[i] >> 16 & 0xFF) * k + (canvasDataBottom[i] >> 16 & 0xFF) * reverseK);
//            int g = (int)((canvasDataTop[i] >> 8 & 0xFF) * k + (canvasDataBottom[i] >> 8 & 0xFF) * reverseK);
//            int b = (int)((canvasDataTop[i] & 0xFF) * k + (canvasDataBottom[i] & 0xFF) * reverseK);
//            canvasDataCombine[i] |= 0xFF000000;
//            canvasDataCombine[i] |= (r << 16);
//            canvasDataCombine[i] |= (g << 8);
//            canvasDataCombine[i] |= b;
//        }
//        return  Bitmap.createBitmap(canvasDataCombine, bottomLayer.getWidth(), bottomLayer.getHeight(), Bitmap.Config.ARGB_8888);
    }

    public static int alpha(int color) {
        return color >>> 24;
    }

    public static int red(int color) {
        return (color >> 16) & 0xFF;
    }

    public static int green(int color) {
        return (color >> 8) & 0xFF;
    }

    public static int blue(int color) {
        return color & 0xFF;
    }

    public static int rgb(float red, float green, float blue) {
        return 0xff000000 |
                ((int) (red * 255.0f + 0.5f) << 16) |
                ((int) (green * 255.0f + 0.5f) << 8) |
                (int) (blue * 255.0f + 0.5f);
    }

    /**
     * 裁剪图片
     */
    private static void cropPicture() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\验证温度\\3.5\\遮罩\\secondary_page_bak.png");
        BufferedImage bi = null;
        BufferedImage bufferedImage = new BufferedImage(642, 84, BufferedImage.TYPE_4BYTE_ABGR);
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            LUtils.i(width, "  ,  ", height);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (j < 84) {
                        LUtils.i(i, " , ", j, "  ===  ", i, " , ", j);
                        bufferedImage.setRGB(i, j, bi.getRGB(i, j));
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            ImageIO.write(bufferedImage, "PNG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\f.png"));//保存图片 JPEG表示保存格式
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getRectColor(int startX, int endX, int startY, int endY, File file) {
        Set<Integer> result = new HashSet<>();
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            if (startX < 0) {
                startX = 0;
            }
            if (endX > width) {
                endX = width;
            }
            if (startY < 0) {
                startY = 0;
            }
            if (endY > height) {
                endY = height;
            }

            for (int i = startX; i < endX; i++) {
                for (int j = startY; j < endY; j++) {
                    int color = bi.getRGB(i, j);
                    if (!result.contains(color)) {
                        result.add(color);
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        LUtils.i("累计颜色种数 =  ", result.size());
        for (Integer color : result) {
            LUtils.i(color, "  =  ", BinaryOperationUtil.outByteArrayToHexString(BinaryOperationUtil.intToByteHighLow(color)).replaceAll(" "
                    , "").replaceAll("\n", ""), "     ", color);
        }
    }

    private static int resetColor(int color) {
        byte[] bytes = BinaryOperationUtil.intToByteHighLow(color);
        LUtils.i((Byte.toUnsignedInt(bytes[1]) > 0xc7));
        if (Byte.toUnsignedInt(bytes[1]) < 0xa0) {
            LUtils.i("0   ", Integer.toHexString(color));
//            bytes[1] = (byte) 0xc7;
//            bytes[2] = (byte) (bytes[2] -20);
//            bytes[3] = (byte) (bytes[3] -20);
//            bytes[2] = 0;
//            bytes[3] = 90;
//            LUtils.i("1  ",Integer.toHexString(BinaryOperationUtil.byteArrayToIntHighLow(bytes)));
            return 0;
        }
        return BinaryOperationUtil.byteArrayToIntHighLow(bytes);
    }


    private static void changeImage() {
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\baidubaike.png");
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\d.png");
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            LUtils.i(bi.getWidth());
            LUtils.i(bi.getHeight());
            LUtils.i(bi.getType());
            //807f84
            //dd6c5a
            Set<Integer> result = new HashSet<>();
//            for (int i = 77; i < 191; i++) {
//                for (int j = 0; j < height; j++) {
////                for (int j = 40; j < 75; j++) {
//                    int color = bi.getRGB(i, j);
//                    if (!result.contains(color)) {
//                        result.add(color);
//                    }
//                }
//            }

//
//            for (int i = 0; i < 360; i++) {
//                for (int j = 0; j < height; j++) {
//                    int color = bi.getRGB(i, j);
//                    if (!result.contains(color)) {
//                        result.add(color);
//                    }
//                }
//            }
//
//            for (int i = 0; i < width; i++) {
//                for (int j = 0; j < height; j++) {
//                    int color = bi.getRGB(i, j);
//                    if (result.contains(color)) {
//                        bi.setRGB(i, j, 0);
//                    }
//                }
//            }
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            int taget = 28;
            int result1 = 29;
////            result.clear();c87271
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int color = bi.getRGB(i, j);
                    if (i > 39 && i < 80) {
//                        if (j < 12) {
//                            bufferedImage.setRGB(i, j, 0);
//                        } else if (j < 17) {
//                            LUtils.i(j, "  ", bi.getRGB(width - 28, j));
//                            bufferedImage.setRGB(i, j, bi.getRGB(width - 28, j));
//                        } else if (j < 25) {
//                            LUtils.i(j, "  ", bi.getRGB(width - 26, j));
//                            bufferedImage.setRGB(i, j, bi.getRGB(width - 26, j));
//                        } else {
//                            bufferedImage.setRGB(i, j, 0);
//                        }
                        bufferedImage.setRGB(i, j, resetColor(color));

                    } else {
                        bufferedImage.setRGB(i, j, color);
                    }

                }
            }
//            for (int i = 0; i < width; i++) {
//                for (int j = 0; j < height; j++) {
//                    int color=bi.getRGB(i,j);
//                    if (!result.contains(color)){
//                        result.add(color);
//                    }
//                }
//            }
            LUtils.i("累计颜色种数 =  ", result.size());
            for (Integer color : result) {
                LUtils.i(color, "  =  ", BinaryOperationUtil.outByteArrayToHexString(BinaryOperationUtil.intToByteHighLow(color)).replaceAll(" "
                        , "").replaceAll("\n", ""), "     ", color);
            }

//            LUtils.i(bi.getColorModel().hasAlpha());
//            bufferedImage.setRGB(0, 0, 0xff000000);
//            LUtils.i(bufferedImage.getRGB(0,0));
            try {
                ImageIO.write(bufferedImage, "PNG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\f.png"));//保存图片 JPEG表示保存格式
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void byteReadFile() {
        //输入读取文件路径
        FileInputStream fis = null;
        BufferedImage bi = new BufferedImage(1080, 1920, BufferedImage.TYPE_INT_ARGB);

        try {
            fis = new FileInputStream(new File("C:\\Users\\dingzhixin.ex\\Desktop\\badimage.png"));
            //        定义变量存储读取文件的有效字节数
            int len = 0;
// 定义字节数组，一般长度为1024的倍数（定义其他数据，易产生乱码）
            byte[] bytes = new byte[1920 * 3];
//根据read返回值判断是否到达文件末尾
            int count = 0;
            StringBuilder builder = new StringBuilder();
            while ((len = fis.read(bytes)) != -1) {

                for (int i = 0; i < bytes.length; i = i + 4) {
                    try {
                        bi.setRGB(i, count, new Color(bytes[i], bytes[i + 1], bytes[i + 2]).getRGB());
                    } catch (Exception e) {

                    }
                }

//使用String构造方法，输出数组
                count++;
//                builder.append((char) bytes[0]);
//                System.out.println((Integer.toHexString((bytes[0] & 0x000000ff) | 0xffffff00)).substring(6));
//                System.out.println(bytes[0]);
//                String tString = Integer.toBinaryString((bytes[0] & 0xFF) + 0x100).substring(1);
//                System.out.println("tString:" + tString);
            }
            try {
                ImageIO.write(bi, "PNG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\a99.png"));//保存图片 JPEG表示保存格式
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("大小 =  " + count + "  有效 = " + fis.available() + "  文字  =  " + builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void editImage(String image) {
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //长宽
        int width = bi.getWidth();
        int height = bi.getHeight();
//        bi.setRGB(j, i, Color.RED.getRGB());

        Set<Integer> colorSet = new HashSet<>();
        Map<Integer, Integer> colorMap = new HashMap<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = bi.getRGB(j, i);
                if (colorSet.contains(pixel)) {
                    colorMap.put(pixel, colorMap.get(pixel) + 1);
                } else {
                    colorSet.add(pixel);
                    colorMap.put(pixel, 1);
                }
//                if (pixel != -13948117) {
//                    bi.setRGB(j, i, Color.RED.getRGB());
//                }

            }
        }

        Set<Integer> set = colorMap.keySet();
        int key = 0;
        int value = 0;
        boolean isFirst = true;
        for (Integer integer : set) {
            System.out.println("key = " + integer + "  , value = " + colorMap.get(integer));
            if (colorMap.get(integer) == null) {
                continue;
            }
            if (isFirst) {
                isFirst = false;
                key = integer;
                value = colorMap.get(integer);
                continue;
            }
            int result = colorMap.get(integer);
            if (result < value) {
                key = integer;
                value = result;
            }

        }

        System.out.println("result key = " + key + "  , result value = " + value);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = bi.getRGB(j, i);
                if (pixel == key) {
                    bi.setRGB(j, i, Color.RED.getRGB());
                }

            }
        }


        try {
            ImageIO.write(bi, "PNG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\a.png"));//保存图片 JPEG表示保存格式
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 计算绿色像素所占比例，传入参数为图片的文件路径
    public static String calculateGreen(String image) throws Exception {

        List<PositionBean> positionBeans = new ArrayList<>();

        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //长宽
        int width = bi.getWidth();
        int height = bi.getHeight();

        System.out.println("图片宽度 = " + width);
        System.out.println("图片高度 = " + height);

        //横纵坐标起始点
        int minx = bi.getMinX();
        int miny = bi.getMinY();

        //绿色像素点个数
        long greenNumber = 0;


        int[] rgb = new int[3];// 定义RGB空间
        float[] hsv = new float[3];// 定义HSV空间

        Set<Integer> colorSet = new HashSet<>();
        Set<Integer> lineColorSet = new HashSet<>();

        // 开始遍历所有像素点
        for (int i = minx; i < height; i++) {
            System.out.println("单行颜色值 " + (i - 1) + " = " + lineColorSet.toString());
            lineColorSet.clear();
            PositionBean positionBean = null;
            for (int j = miny; j < width; j++) {

                // 当前像素点
                int pixel = bi.getRGB(j, i);
                colorSet.add(pixel);
                lineColorSet.add(pixel);

                // 获取RGB各值
                rgb[0] = (pixel & 0xff0000) >> 16;//R
                rgb[1] = (pixel & 0xff00) >> 8;//G
                rgb[2] = (pixel & 0xff);//B

                // rgb转hsv
                Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsv);

                //使用hsv判断该像素点是否可以判定为绿色像素点
                if (hsv[2] >= 0.075 && hsv[1] >= 0.15 && hsv[0] > 0.1389 &&
                        hsv[0] <= 0.4444) {
                    greenNumber++;
                }
            }
            if (lineColorSet.size() > 2) {
                List<Integer> list = new ArrayList<>();
                List<Integer> positionList = new ArrayList<>();
                for (int j = miny; j < width; j++) {

                    // 当前像素点
                    int pixel = bi.getRGB(j, i);
                    list.add(pixel);
                }
                int size = list.size();
                for (int j = size - 1; j > -1; j--) {
                    if (list.get(j) == -13948117) {
                        list.remove(j);
                    } else {
                        positionList.add(j);
                        positionBean = new PositionBean(i, list.get(j), j);
                    }

                }
//                positionBeans.add(new PositionBean(i, positionList));
                if (positionBean != null) {
                    positionBeans.add(positionBean);
                }
//                System.out.println("第 " + i + " 行的像素分布为 = " + positionList.toString() + "  总个数 = " + positionList.size());
            }

        }
        System.out.println("总颜色种类 = " + colorSet.size());

        System.out.println(positionBeans.toString());


//        positionBeans.sort(new Comparator<PositionBean>() {
//            @Override
//            public int compare(PositionBean o1, PositionBean o2) {
//                if (o1.)
//                return 0;
//            }
//        });
        int positionBeansSize = positionBeans.size();


//        for (int i = 0; i < positionBeansSize; i++) {
//            positionBeans.get(i).colorPosition.sort(new Comparator<Integer>() {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    if (o1 > o2) {
//                        return 1;
//                    } else if (o1 < o2) {
//                        return -1;
//                    }
//                    return 0;
//                }
//            });
//        }
//
//        for (int i = minx; i < height; i++) {
//            StringBuilder builder = new StringBuilder();
//            for (int k = 0; k < positionBeansSize; k++) {
//                if (positionBeans.get(k).lineNumber == i) {
//                    for (int j = 0; j < width; j++) {
//                        boolean result = false;
//                        for (int l = 0; l < positionBeans.get(k).colorPosition.size(); l++) {
//                            if (positionBeans.get(k).colorPosition.get(l) == j) {
//                                result = true;
//                                break;
//                            }
//                        }
//                        if (result) {
//                            builder.append("0");
//                        } else {
//                            builder.append(" ");
//                        }
//                    }
//                    break;
//                }
//
//            }
//            System.out.println(builder.toString().replaceAll(" {2,}", "                       "));
//        }


        // 总像素点个数
        long totalPixelNumber = width * height;

        // 获取浮点数表示的占比
        double greenPixelProportion = (double) greenNumber / totalPixelNumber;

        // 返回百分制字符串
        return

                translateDoubleIntoPercent(greenPixelProportion);

    }

    /**
     * 将浮点数转换为百分制
     *
     * @param d
     * @return
     */
    public static String translateDoubleIntoPercent(double d) {
        BigDecimal bDecimal = new BigDecimal(d);
        bDecimal = bDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);
        DecimalFormat dFormat = new DecimalFormat("0.00%");
        String result = dFormat.format(bDecimal.doubleValue());
        return result;
    }

    public static void createImage() {
        //得到图片缓冲区
        BufferedImage bi = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);//INT精确度达到一定,RGB三原色，高度70,宽度150

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if (j > 20 && j < 30 && i > 20 && i < 130) {
                    bi.setRGB(i, j, Color.CYAN.getRGB());
                } else if (j > 30 && j < 130 && i > 70 && i < 80) {
                    bi.setRGB(i, j, Color.CYAN.getRGB());
                } else {
                    bi.setRGB(i, j, Color.BLACK.getRGB());
                }

                if (j > 115 && j < 130 && i > 60 && i < 75 && j - i == 55) {
                    int m = i - 10;
                    for (int k = m; k < i; k++) {
                        System.out.println(k + " == " + j);
                        bi.setRGB(k, j, Color.CYAN.getRGB());
                    }
                }
            }
        }

//得到它的绘制环境(这张图片的笔)
//        Graphics2D g2 = (Graphics2D) bi.getGraphics();

//        g2.fillRect(0, 0, 150, 150);//填充一个矩形 左上角坐标(0,0),宽70,高150;填充整张图片
//设置颜色
//        g2.setColor(Color.WHITE);
//        g2.fillRect(0, 0, 150, 150);//填充整张图片(其实就是设置背景颜色)

//        g2.setColor(Color.RED);
//        g2.fillRect(0, 0, 150, 150); //画边框

//        g2.setFont(new Font("宋体", Font.BOLD, 18)); //设置字体:字体、字号、大小
//        g2.setColor(Color.BLACK);//设置背景颜色
//
//        g2.drawString("HelloWorld", 3, 50); //向图片上写字符串
        try {
            ImageIO.write(bi, "JPEG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\a.jpg"));//保存图片 JPEG表示保存格式
            ImageIO.write(bi, "PNG", new FileOutputStream("C:\\Users\\dingzhixin.ex\\Desktop\\a.png"));//保存图片 JPEG表示保存格式
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
