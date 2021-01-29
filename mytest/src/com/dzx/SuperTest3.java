package com.dzx;

public class SuperTest3 extends SuperTest2 {

    public static void main(String[] args) {
        SuperTest3 superTest3 = new SuperTest3();
        superTest3.mess();
    }

    public void mess() {
        super.showLog();
    }


    @Override
    public void showLog() {
        System.out.println("这是子类");
    }
}
