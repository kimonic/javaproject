package com.kimonik;

import javax.swing.*;
import java.awt.*;

/**
 * 带点击id的button
 */
public class MyButton extends JButton {
    /**
     * 用于判断是哪个按钮点击的id
     */
    private int mClickId;

    public MyButton() {
    }

    public MyButton(int mClickId, String buttonName) {
        super(buttonName);
        this.mClickId = mClickId;
    }

    public MyButton(Icon icon) {
        super(icon);
    }

    public MyButton(String text) {
        super(text);
    }

    public MyButton(Action a) {
        super(a);
    }

    public MyButton(String text, Icon icon) {
        super(text, icon);
    }

    public MyButton(String text, int mClickId) {
        super(text);
        this.mClickId = mClickId;
    }


    public int getClickId() {
        return mClickId;
    }

    public void setClickId(int clickId) {
        this.mClickId = clickId;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.green);
//        g.drawString("你好", 60, 20);
//        g.fillRect(0, 0, 130, 40); //实际颜色是green + yellow的混合色=灰色
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.green);

//        getComponentGraphics()
//        g.drawString("你好", 60, 20);
    }

    @Override
    public void updateUI() {
        super.updateUI();
    }

    @Override
    public String toString() {
        return getText() + "  " + mClickId;
    }
}
