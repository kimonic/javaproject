package com.dzx.blurred;

import java.util.List;

/**
 * @author dingzhixin
 * create  2022/7/21  9:26
 */
public class ColorTestEntity {
    /**
     * 主导色
     */
    public int dominantColor = 0;
    /**
     * 柔和色
     */
    public int mutedColor = 0;
    /**
     * 柔和 暗色
     */
    public int mutedDarkColor = 0;
    /**
     * 柔和 亮色
     */
    public int mutedLightColor = 0;

    /**
     * 醒目色
     */
    public int vibrantColor = 0;
    /**
     * 醒目 暗色
     */
    public int vibrantDarkColor = 0;
    /**
     * 醒目 亮色
     */
    public int vibrantLightColor = 0;

    /**
     * 生成的混合色
     */
    public int blendColor = 0;

    public List<ColorAssistEntity> colors;
//    /**
//     * 主导色
//     */
//    public int dominantColor = Color.RED;
//    /**
//     * 柔和色
//     */
//    public int mutedColor = Color.YELLOW;
//    /**
//     * 柔和 暗色
//     */
//    public int mutedDarkColor = Color.GREEN;
//    /**
//     * 柔和 亮色
//     */
//    public int mutedLightColor = Color.CYAN;
//
//    /**
//     * 醒目色
//     */
//    public int vibrantColor = Color.BLUE;
//    /**
//     * 醒目 暗色
//     */
//    public int vibrantDarkColor = Color.rgb(0x93, 0x68, 0xf6);
//    /**
//     * 醒目 亮色
//     */
//    public int vibrantLightColor = Color.rgb(0xff, 0x83, 0);
//
//    /**
//     * 生成的混合色
//     */
//    public int blendColor = Color.rgb(0x40, 0x95, 0xd0);
}
