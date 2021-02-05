package com.dzx;

import javafx.scene.paint.Color;

public class Test6 {
    public static void main(String[] args) {
        // 相似度从0到1
        System.out.println(getColorSemblance(Color.rgb(0, 0, 0), Color.rgb(255, 255, 255)));
        System.out.println(getColorSemblance(Color.rgb(255, 255, 255), Color.rgb(200, 200, 200)));
        System.out.println(getColorSemblance(Color.rgb(255, 255, 255), Color.rgb(255, 255, 255)));
    }

    public static double getColorSemblance(Color color1, Color color2) {
        // 此处Color为javafx.scene.paint.Color，getRed()为红色通道的程度，getRed() * 255为红色通道的值
        double semblance = (255 - (
                Math.abs(color1.getRed() - color2.getRed()) * 255 * 0.297
                        + Math.abs(color1.getGreen() - color2.getGreen()) * 255 * 0.593
                        + Math.abs(color1.getBlue() - color2.getBlue()) * 255 * 11.0 / 100)
        ) / 255;
        return semblance;
    }


}
