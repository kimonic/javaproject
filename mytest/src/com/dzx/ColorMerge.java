package com.dzx;

import com.dzx.util.LUtils;
import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.TextUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class ColorMerge {


    public static void main(String[] args) {
        mergePic();
    }

    private static void checkResult() {
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\颜色处理结果.txt");
        try {
            List<String> list = FileUtils.readLines(file);
            for (String s : list) {
                if (!TextUtils.isEmpty(s)) {
                    String[] result = s.split("\\s+");
                    ColorEntity colorEntity = new ColorEntity(new BigInteger(result[2].replaceFirst("0x", ""), 16).intValue(),
                            new BigInteger(result[10].replaceFirst("0x", ""), 16).intValue(),
                            new BigInteger(result[6].replaceFirst("0x", ""), 16).intValue());
                    getResult(colorEntity, 16);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ColorEntity {
        public int result;
        public int top;
        public int bottom;

        public ColorEntity(int result, int top, int bottom) {
            this.result = result;
            this.top = top;
            this.bottom = bottom;
        }
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

    private static String getTemp(ColorEntity colorEntity, int result) {
        int rOffset = red(colorEntity.result) - red(result);
        int gOffset = green(colorEntity.result) - green(result);
        int bOffset = blue(colorEntity.result) - blue(result);
        int aOffset = alpha(colorEntity.result) - alpha(result);
        return "rOffset = " + rOffset + " , gOffset = " + gOffset + " , bOffset = " + bOffset + " , aOffset = " + aOffset;
    }

    /**
     * 一个颜色覆盖到另一个颜色上
     */
    private static int MoNi(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        //1-(1-Sa-Da+Da*Sa)=Sa+Da-Da*Sa
        float Ra = 1 - (1 - Sa) * (1 - Da);
        float Rr = (Sr * Sa + Dr * (1 - Sa) * Da) / Ra;
        float Rg = (Sg * Sa + Dg * (1 - Sa) * Da) / Ra;
        float Rb = (Sb * Sa + Db * (1 - Sa) * Da) / Ra;

        return argb(Ra, Rr, Rg, Rb);
    }


    private static void getResult(ColorEntity colorEntity, int type) {
        int result = 0;
        switch (type % 17) {
            case 0:
                result = Clear(colorEntity.top, colorEntity.bottom);
                LUtils.i("clear type ", getTemp(colorEntity, result));
                break;
            case 1:
                result = Src(colorEntity.top, colorEntity.bottom);
                LUtils.i("src type ", getTemp(colorEntity, result));
                break;
            case 2:
                result = Dst(colorEntity.top, colorEntity.bottom);
                LUtils.i("Dst type ", getTemp(colorEntity, result));
                break;
            case 3:
                result = DstIn(colorEntity.top, colorEntity.bottom);
                LUtils.i("DstIn type ", getTemp(colorEntity, result));

                break;
            case 4:
                result = SrcIn(colorEntity.top, colorEntity.bottom);
                LUtils.i("SrcIn type ", getTemp(colorEntity, result));
                break;
            case 5:
                result = SrcOver(colorEntity.top, colorEntity.bottom);
                LUtils.i("SrcOver type ", getTemp(colorEntity, result));
                break;
            case 6:
                result = DstOver(colorEntity.top, colorEntity.bottom);
                LUtils.i("DstOver type ", getTemp(colorEntity, result));
                break;
            case 7:
                result = SrcOut(colorEntity.top, colorEntity.bottom);
                LUtils.i("SrcOut type ", getTemp(colorEntity, result));
                break;
            case 8:
                result = DstOut(colorEntity.top, colorEntity.bottom);
                LUtils.i("DstOut type ", getTemp(colorEntity, result));
                break;
            case 9:
                result = DstATop(colorEntity.top, colorEntity.bottom);
                LUtils.i("DstATop type ", getTemp(colorEntity, result));

                break;
            case 10:
                result = SrcATop(colorEntity.top, colorEntity.bottom);
                LUtils.i("SrcATop type ", getTemp(colorEntity, result));
                break;
            case 11:
                result = Xor(colorEntity.top, colorEntity.bottom);
                LUtils.i("Xor type ", getTemp(colorEntity, result));

                break;
            case 12:
                result = Multiply(colorEntity.top, colorEntity.bottom);
                LUtils.i("Multiply type ", getTemp(colorEntity, result));

                break;
            case 13:
                result = Darken(colorEntity.top, colorEntity.bottom);
                LUtils.i("Darken type ", getTemp(colorEntity, result));
                break;
            case 14:
                result = Screen(colorEntity.top, colorEntity.bottom);
                LUtils.i("Screen type ", getTemp(colorEntity, result));
                break;
            case 15:
                result = Lighten(colorEntity.top, colorEntity.bottom);
                LUtils.i("Lighten type ", getTemp(colorEntity, result));
                break;
            case 16:
                result = MoNi(colorEntity.top, colorEntity.bottom);
                LUtils.i("MoNi type ", getTemp(colorEntity, result));
                break;
//            case :
//                break;
            default:
                break;
        }
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
                                    if (bottomColor == 0) {
                                        bufferedImage.setRGB(i, j, topColor);
                                    } else {
                                        int result = MoNi(topColor, bottomColor);
                                        LUtils.i("结果色值 = 0x", Integer.toHexString(bEnd.getRGB(i, j)),
                                                " , 顶层色值 = 0x", Integer.toHexString(topColor),
                                                " , 底层色值 = 0x", Integer.toHexString(bottomColor),
                                                " , 计算颜色值 = 0x", Integer.toHexString(result));
                                        bufferedImage.setRGB(i, j, result);
                                    }

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


    // Cr = Cs * 0 + Cd * 0; A = As * 0 + Ad * 0
    public static int Clear(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = Sa * 0 + Da * 0;

        if (Ra == 0) {
            Ra = 1;
        }
        float Rr = (Sr * Sa * 0 + Dr * Da * 0) / Ra;
        float Rg = (Sg * Sa * 0 + Dg * Da * 0) / Ra;
        float Rb = (Sb * Sa * 0 + Db * Da * 0) / Ra;


        return argb(Ra, Rr, Rg, Rb);
    }

    // Cr = Cs * ( 1 - Ad )   ;            Ar = As  * ( 1 -  Ad )
    public static int SrcOut(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;


        float Da = alpha(dst) / 255.0f;

        float Ra = Sa * (1 - Da);


        float Rr = (Sr * (1 - Da)) / Ra;
        float Rg = (Sg * (1 - Da)) / Ra;
        float Rb = (Sb * (1 - Da)) / Ra;

        return argb(Ra, Rr, Rg, Rb);

    }

    // Cr = Cd * ( 1 - As ) ;            Ar = Ad * ( 1 -  As )
    public static int DstOut(int src, int dst) {
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = Da * (1 - Sa);


        float Rr = (Dr * (1 - Sa)) / Ra;
        float Rg = (Dg * (1 - Sa)) / Ra;
        float Rb = (Db * (1 - Sa)) / Ra;

        return argb(Ra, Rr, Rg, Rb);

    }

    //Cs * Ad + Cd * 0 /            Ar = As * Ad
    public static int SrcIn(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Da = alpha(dst) / 255.0f;

        float Ra = Sa * Da;


        float Rr = (Sr * Da) / Ra;
        float Rg = (Sg * Da) / Ra;
        float Rb = (Sb * Da) / Ra;

        return argb(Ra, Rr, Rg, Rb);

    }

    //Cs * 0 + Cd * As /            Ar = Ad * As
    public static int DstIn(int src, int dst) {
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = Da * Sa;

        float Rr = (Dr * Sa) / Ra;
        float Rg = (Dg * Sa) / Ra;
        float Rb = (Db * Sa) / Ra;

        return argb(Ra, Rr, Rg, Rb);

    }

    //Cs*Ad + Cd * (1 - As)    As * Ad + Ad * (1-As)
    public static int SrcATop(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = Sa * Da + Da * (1 - Sa);


        float Rr = (Sr * Da + Dr * (1 - Sa)) / Ra;
        float Rg = (Sg * Da + Dg * (1 - Sa)) / Ra;
        float Rb = (Sb * Da + Db * (1 - Sa)) / Ra;

        return argb(Ra, Rr, Rg, Rb);

    }

    //Cs * ( 1 - Ad ) + Cd * As       As * (1-Ad) + Ad * As
    public static int DstATop(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = Sa * (1 - Da) + Da * Sa;


        float Rr = (Sr * (1 - Da) + Dr * Sa) / Ra;
        float Rg = (Sg * (1 - Da) + Dg * Sa) / Ra;
        float Rb = (Sb * (1 - Da) + Db * Sa) / Ra;

        return argb(Ra, Rr, Rg, Rb);

    }

    //Cs *(1-Ad)+ Cd *(1-As)        As *(1-Ad)+ Ad *(1-As)
    public static int Xor(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = Sa * (1 - Da) + Da * (1 - Sa);

        float Rr = (Sr * (1 - Da) + Dr * (1 - Sa)) / Ra;
        float Rg = (Sg * (1 - Da) + Dg * (1 - Sa)) / Ra;
        float Rb = (Sb * (1 - Da) + Db * (1 - Sa)) / Ra;

        return argb(Ra, Rr, Rg, Rb);

    }


    //Cr = Cs * Cd ;        Ar = Ca * Da;
    public static int Multiply(int src, int dst) {
        float Sa = alpha(src) / 255.0f;
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;

        float Da = alpha(dst) / 255.0f;
        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;


//    float Ra = Sa * Da + Sa * (1-Da) + Da * (1-Sa);
//    float Rr = ((Sr * Dr) + Sr * (1-Da) + Dr * (1-Sa))/(Ra==0?1:Ra);
//    float Rg = ((Sg * Dg) + Sg * (1-Da) + Dg * (1-Sa))/(Ra==0?1:Ra);
//    float Rb = ((Sb * Db) + Sb * (1-Da) + Db * (1-Sa))/(Ra==0?1:Ra);


        float Ra = Sa * Da;
        float Rr = (Sr * Dr) / (Ra == 0 ? 1 : Ra);
        float Rg = (Sg * Dg) / (Ra == 0 ? 1 : Ra);
        float Rb = (Sb * Db) / (Ra == 0 ? 1 : Ra);


        return argb(Ra, Rr, Rg, Rb);

    }

    //Cr = 1 - (1-Cs)(1-Cd)                 Ar =  1 - (1-Ca)(1-Da)
    public static int Screen(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = 1 - (1 - Sa) * (1 - Da);
        float Rr = (1 - (1 - Sr) * (1 - Dr)) / (Ra == 0 ? 1 : Ra);
        float Rg = (1 - (1 - Sg) * (1 - Dg)) / (Ra == 0 ? 1 : Ra);
        float Rb = (1 - (1 - Sb) * (1 - Db)) / (Ra == 0 ? 1 : Ra);


        return argb(Ra, Rr, Rg, Rb);

    }

    public static int Darken(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Sv = Sr + Sg + Sb;
        float Dv = Dr + Dg + Db;


        float Ra = Sv < Dv ? Sa : Da;

        float preA = Ra == 0 ? 1 : Ra;
        float Rr = (Sv < Dv ? Sr : Dr) / preA;
        float Rg = (Sv < Dv ? Sg : Dg) / preA;
        float Rb = (Sv < Dv ? Sb : Db) / preA;


        return argb(Ra, Rr, Rg, Rb);
    }

    public static int Lighten(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;

        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Rr = Math.max(Sr, Dr);
        float Rg = Math.max(Sg, Dg);
        float Rb = Math.max(Sb, Db);
        float Ra = Math.max(Sa, Da);

        return argb(Ra, Rr, Rg, Rb);

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

//        LUtils.i(Integer.toHexString(argb(Ra, Rr, Rg, Rb)));
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


//        LUtils.i(Integer.toHexString(argb(Ra, Rr, Rg, Rb)));
        return argb(Ra, Rr, Rg, Rb);
    }

    // Cr = Cs * As + Cd * 0; A = As * 1 + Ad * 0
    private static int Src(int src, int dst) {
        float Sr = red(src) / 255.0f;
        float Sg = green(src) / 255.0f;
        float Sb = blue(src) / 255.0f;
        float Sa = alpha(src) / 255.0f;


        float Da = alpha(dst) / 255.0f;


        float Ra = Sa * 1 + Da * 0;
        if (Sa == 0) {
            Sa = 1;
        }

        float Rr = Sr / Sa;
        float Rg = Sg / Sa;
        float Rb = Sb / Sa;

        return argb(Ra, Rr, Rg, Rb);

    }

    // Cr = Cs * 0 + Cd * Ad; A = As * 0 + Ad * 1
    private static int Dst(int src, int dst) {
        float Sa = alpha(src) / 255.0f;


        float Dr = red(dst) / 255.0f;
        float Dg = green(dst) / 255.0f;
        float Db = blue(dst) / 255.0f;
        float Da = alpha(dst) / 255.0f;

        float Ra = Sa * 0 + Da * 1;
        if (Da == 0) {
            Da = 1;
        }

        float Rr = Dr / Da;
        float Rg = Dg / Da;
        float Rb = Db / Da;

        return argb(Ra, Rr, Rg, Rb);

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

    public static int argb(float alpha, float red, float green, float blue) {
        return ((int) (alpha * 255.0f + 0.5f) << 24) |
                ((int) (red * 255.0f + 0.5f) << 16) |
                ((int) (green * 255.0f + 0.5f) << 8) |
                (int) (blue * 255.0f + 0.5f);
    }

    public static int argb(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
