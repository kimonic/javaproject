package com.dzx.blurred;


import com.dzx.util.LUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.*;

/**
 * @author dingzhixin
 * create  2022/7/21  16:49
 */
public class ColorFilterUtil {
    public static final String TAG = "ColorFilterUtil";


    public static final int MAX_WIDTH_HEIGHT = 38;


    public static float[] redHsbMin1 = new float[]{0.0f, 0.16862746f, 0.18039216f};
    public static float[] redHsbMax1 = new float[]{0.06111111f, 1.0f, 1.0f};

    public static float[] orangeHsbMin = new float[]{0.06111111f, 0.16862746f, 0.18039216f};
    public static float[] orangeHsbMax = new float[]{0.14444445f, 1.0f, 1.0f};

    public static float[] yellowHsbMin = new float[]{0.14444445f, 0.16862746f, 0.18039216f};
    public static float[] yellowHsbMax = new float[]{0.19444445f, 1.0f, 1.0f};

    public static float[] greenHsbMin = new float[]{0.19444445f, 0.16862746f, 0.18039216f};
    public static float[] greenHsbMax = new float[]{0.43333334f, 1.0f, 1.0f};

    public static float[] cyanHsbMin = new float[]{0.43333334f, 0.16862746f, 0.18039216f};
    public static float[] cyanHsbMax = new float[]{0.5555556f, 1.0f, 1.0f};

    public static float[] blueHsbMin = new float[]{0.5555556f, 0.16862746f, 0.18039216f};
    public static float[] blueHsbMax = new float[]{0.6944444f, 1.0f, 1.0f};

    public static float[] purpleHsbMin = new float[]{0.6944444f, 0.16862746f, 0.18039216f};
    public static float[] purpleHsbMax = new float[]{0.8666667f, 1.0f, 1.0f};

    public static float[] redHsbMin2 = new float[]{0.8666667f, 0.16862746f, 0.18039216f};
    public static float[] redHsbMax2 = new float[]{1.0f, 1.0f, 1.0f};

    public static float[] whiteHsbMin = new float[]{0.0f, 0.0f, 0.8666667f};
    public static float[] whiteHsbMax = new float[]{1.0f, 0.12156863f, 1.0f};

    public static float[] otherHsbMin = new float[]{0.0f, 0.12156863f, 0.8666667f};
    public static float[] otherHsbMax = new float[]{1.0f, 0.16862746f, 1.0f};

    public static float[] grayHsbMin = new float[]{0.0f, 0.0f, 0.18039216f};
    public static float[] grayHsbMax = new float[]{1.0f, 0.16862746f, 0.8666667f};

    public static float[] blackHsbMin = new float[]{0.0f, 0.0f, 0.0f};
    public static float[] blackHsbMax = new float[]{1.0f, 1.0f, 0.18039216f};


    public static ColorTestEntity getMultiplyColor(BufferedImage targetBitmap) {
        long start = System.currentTimeMillis();
        BufferedImage bitmap = null;
        int targetWidth = targetBitmap.getWidth();
        int targetHeight = targetBitmap.getHeight();
        if (targetWidth <= MAX_WIDTH_HEIGHT && targetHeight <= MAX_WIDTH_HEIGHT) {
            bitmap = targetBitmap;
        } else {
            float ratio = 1;
            if (targetWidth > targetHeight) {
                ratio = 1.0f * targetWidth / MAX_WIDTH_HEIGHT;
            } else {
                ratio = 1.0f * targetHeight / MAX_WIDTH_HEIGHT;
            }
            bitmap = resizeImage((int) (targetWidth / ratio), (int) (targetHeight / ratio), targetBitmap);
        }


        Raster raster = bitmap.getData();
        int realWidth = raster.getWidth();
        int realHeight = raster.getHeight();
        int colorTotal = realWidth * realHeight;


        try {
            BufferedImage temp = new BufferedImage(realWidth, realHeight, BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < realWidth; i++) {
                for (int j = 0; j < realHeight; j++) {
                    int resultColor = complementaryColor(bitmap.getRGB(i, j));
                    float[] mix = colorRgbToHsb(resultColor);
                    resultColor = hsbToRgb(mix[0], mix[1], mix[2] * 0.3f);
                    temp.setRGB(i, j, resultColor);
                }
            }
            BufferedImage bufferedImage = resizeImage(1920, 342, temp);
            BufferedImage temp1 = GaussianBlur.gaussianBlur(bufferedImage);
            ImageIO.write(temp1, "png", new File("C:\\Users\\dingzhixin.ex\\Desktop\\8523.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LUtils.i(TAG, "getThemeColor 实际宽高 = ", realWidth, ", ", realHeight, " , raster.getNumBands() = ", raster.getNumBands());
        int[] temp = null;
        temp = raster.getPixels(0, 0, realWidth, realHeight, temp);

        LUtils.i();

        List<ArgbColorEntity> colors = new ArrayList<>();
        for (int i = 1; i < temp.length; i++) {
            if (i % 4 == 0) {
                colors.add(new ArgbColorEntity(temp[i - 1], temp[i - 2], temp[i - 3], temp[i - 4]));
            }
        }

        LUtils.i(TAG, "getThemeColor 第一阶段耗时 ", (System.currentTimeMillis() - start));
        int length = colors.size();
        Set<Integer> colorTypes = new HashSet<>();
        List<ColorAssistEntity> resultList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            resultList.add(new ColorAssistEntity());
        }
        resultList.get(0).colorName = "橙色";
        resultList.get(1).colorName = "白色";
        resultList.get(2).colorName = "紫色";
        resultList.get(3).colorName = "蓝色";
        resultList.get(4).colorName = "绿色";
        resultList.get(5).colorName = "青色";
        resultList.get(6).colorName = "黄色";
        resultList.get(7).colorName = "红色";
        resultList.get(8).colorName = "黑色";
        resultList.get(9).colorName = "灰色";
        resultList.get(10).colorName = "其他";
        resultList.get(11).colorName = "异常";

        for (int i = 0; i < length; i++) {
            int colorArgb = colors.get(i).getColor();
            colorTypes.add(colorArgb);
            int colorR = colors.get(i).colorR;
            int colorG = colors.get(i).colorG;
            int colorB = colors.get(i).colorB;
            float[] hsbResult = rgbToHsb(colorR, colorG, colorB, null);
            if (containsColor(hsbResult, orangeHsbMin, orangeHsbMax)) {
                recordColor(resultList, 0, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, whiteHsbMin, whiteHsbMax)) {
                recordColor(resultList, 1, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, purpleHsbMin, purpleHsbMax)) {
                recordColor(resultList, 2, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, blueHsbMin, blueHsbMax)) {
                recordColor(resultList, 3, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, greenHsbMin, greenHsbMax)) {
                recordColor(resultList, 4, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, cyanHsbMin, cyanHsbMax)) {
                recordColor(resultList, 5, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, yellowHsbMin, yellowHsbMax)) {
                recordColor(resultList, 6, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, redHsbMin1, redHsbMax1)
                    || containsColor(hsbResult, redHsbMin2, redHsbMax2)) {
                recordColor(resultList, 7, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, blackHsbMin, blackHsbMax)) {
                recordColor(resultList, 8, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, grayHsbMin, grayHsbMax)) {
                recordColor(resultList, 9, colorR, colorG, colorB);
            } else if (containsColor(hsbResult, otherHsbMin, otherHsbMax)) {
                recordColor(resultList, 10, colorR, colorG, colorB);
            } else {
                LUtils.i(TAG, "getThemeColor other color ", Integer.toHexString(colorArgb),
                        " ", Arrays.toString(hsbResult));
                recordColor(resultList, 11, colorR, colorG, colorB);
            }
        }
        int count = 0;
        List<ColorAssistEntity> resultColors = new ArrayList<>();
        Collections.sort(resultList, new Comparator<ColorAssistEntity>() {
            @Override
            public int compare(ColorAssistEntity o1, ColorAssistEntity o2) {
                return o2.rangeCount - o1.rangeCount;
            }
        });
        for (ColorAssistEntity entity : resultList) {
            if (entity.rangeCount > 0) {
                count++;
                entity.resultColor = rgb(1.0f * entity.colorR / entity.rangeCount,
                        1.0f * entity.colorG / entity.rangeCount, 1.0f * entity.colorB / entity.rangeCount);
                entity.colorPercent = 100.f * entity.rangeCount / colorTotal;
                BigDecimal bigDecimal = new BigDecimal(entity.colorPercent);
                bigDecimal = bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);
                entity.colorPercent = bigDecimal.floatValue();
                resultColors.add(entity);
                LUtils.i(TAG, "getThemeColor ", Integer.toHexString(entity.resultColor),
                        " 出现次数 = ", entity.rangeCount, " , ", entity.colorName, " , 占比 = ", entity.colorPercent, "%");
            }
        }
        if (resultColors.size() > 0) {
            ColorAssistEntity entity = new ColorAssistEntity();
            //entity.colorName = "混合色";
            //entity.resultColor = multiply(resultColors.get(1).resultColor, resultColors.get(2).resultColor);

            entity.colorName = "补色";
            entity.resultColor = complementaryColor(resultColors.get(0).resultColor);
            float[] mix = colorRgbToHsb(entity.resultColor);
            entity.resultColor = hsbToRgb(mix[0], mix[1], mix[2] * 0.3f);
            LUtils.i("补色 = ", Integer.toHexString(entity.resultColor));
            resultColors.add(0, entity);
        }
        ColorTestEntity colorTestEntity = new ColorTestEntity();
        colorTestEntity.colors = resultColors;
        LUtils.i(TAG, "getThemeColor  累计颜色数 = ", colorTypes.size(),
                " , 累计色彩数 = ", count);
        LUtils.i(TAG, "getThemeColor  取色耗时 ", (System.currentTimeMillis() - start));
        return colorTestEntity;

    }

    public static int complementaryColor(int color) {
        int colorR = 255 - red(color);
        int colorG = 255 - green(color);
        int colorB = 255 - blue(color);
        return argb(0xff, colorR, colorG, colorB);
    }

    public static void recordColor(List<ColorAssistEntity> resultList, int index, int colorR, int colorG, int colorB) {
        resultList.get(index).colorR += colorR;
        resultList.get(index).colorG += colorG;
        resultList.get(index).colorB += colorB;
        resultList.get(index).rangeCount += 1;
    }

    public static int multiply(int src, int dst) {
        float sr = red(src) / 255.0f;
        float sg = green(src) / 255.0f;
        float sb = blue(src) / 255.0f;

        float dr = red(dst) / 255.0f;
        float dg = green(dst) / 255.0f;
        float db = blue(dst) / 255.0f;

        float rr = sr * dr;
        float rg = sg * dg;
        float rb = sb * db;

        return rgb(rr, rg, rb);
    }

    public static float[] colorRgbToHsb(int color) {
        return rgbToHsb(red(color), green(color), blue(color), null);
    }

    public static float[] rgbToHsb(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = Math.max(r, g);
        if (b > cmax) {
            cmax = b;
        }
        int cmin = Math.min(r, g);
        if (b < cmin) {
            cmin = b;
        }

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0) {
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        } else {
            saturation = 0;
        }
        if (saturation == 0) {
            hue = 0;
        } else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax) {
                hue = bluec - greenc;
            } else if (g == cmax) {
                hue = 2.0f + redc - bluec;
            } else {
                hue = 4.0f + greenc - redc;
            }
            hue = hue / 6.0f;
            if (hue < 0) {
                hue = hue + 1.0f;
            }
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    public static int rgb(float red, float green, float blue) {
        return 0xff000000 |
                ((int) (red * 255.0f + 0.5f) << 16) |
                ((int) (green * 255.0f + 0.5f) << 8) |
                (int) (blue * 255.0f + 0.5f);
    }

    public static boolean containsColor(float[] floats, float[] hsbMin, float[] hsbMax) {
        return (floats[0] >= hsbMin[0]) && floats[1] >= hsbMin[1] && floats[2] >= hsbMin[2]
                && floats[0] <= hsbMax[0] && floats[1] <= hsbMax[1] && floats[2] <= hsbMax[2];
    }


    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().drawImage(bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
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

    public static int argb(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public static int hsbToRgb(float hue, float saturation, float brightness) {
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            float h = (hue - (float) Math.floor(hue)) * 6.0f;
            float f = h - (float) java.lang.Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
            }
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
    }

}
