package com.dzx.bean;

import com.google.gson.Gson;

import java.util.List;

public class PositionBean {
    public PositionBean(int lineNumber, List<Integer> colorPosition) {
        this.lineNumber = lineNumber;
        this.colorPosition = colorPosition;
    }

    public PositionBean(int lineNumber, int color, int position) {
        this.lineNumber = lineNumber;
        this.color = color;
        this.position = position;
    }

    public int lineNumber;
    public int color;
    public int position;
    public List<Integer> colorPosition;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
