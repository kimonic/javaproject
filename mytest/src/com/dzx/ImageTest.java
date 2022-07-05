package com.dzx;

import com.dzx.bean.PositionBean;
import com.dzx.util.BinaryOperationUtil;
import com.dzx.util.LUtils;

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

    public static void main(String[] args) {
        cropPicture();
        //c47267
//        getRectColor(74, 75, 0, 120, new File("C:\\Users\\dingzhixin.ex\\Desktop\\d.png"));
//        resetColor(0xff112233);
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
