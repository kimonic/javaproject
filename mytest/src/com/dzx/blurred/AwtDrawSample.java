package com.dzx.blurred;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AwtDrawSample {

    public static void main(String[] args) {
        File file = new File("E:\\调试颜色.png");
        try {
            BufferedImage image = ImageIO.read(file);
            ColorFilterUtil.getMultiplyColor(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gradient() {
        try {
            String pathForWang = "C:\\Users\\dingzhixin.ex\\Desktop\\sss.png";
            BufferedImage background = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
            //*******
            Graphics2D g = background.createGraphics();
            //设置笔画宽度
            BasicStroke stroke = new BasicStroke(2);
            g.setStroke(stroke);
            //设置渐变填充
            GradientPaint gt = new GradientPaint(0, 0, new Color(210, 247, 16), 50, 30, new
                    Color(247, 22, 3), true);
            g.setPaint(gt);
            Line2D line = new Line2D.Float(0, 100, 200, 100);
            g.draw(line);
            //结束
            ImageIO.write(background, "png", new File(pathForWang));

        } catch (Exception e) {
        }
    }
}
