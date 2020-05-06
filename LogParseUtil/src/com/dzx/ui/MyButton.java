package com.dzx.ui;

import javax.swing.*;

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
}
