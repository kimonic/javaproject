package com.dzx;

import com.google.gson.Gson;

public class FinalTest1 {
    /**
     * final 与无修饰,多个对象时为不同对象
     * 有static修饰,则为同一个对象
     */
    private final static Gson myGson = new Gson();


    public Gson getMyGson() {
        return myGson;
    }

    public void testResult() {
        System.out.println("结果  = " + myGson.hashCode());
    }
}
