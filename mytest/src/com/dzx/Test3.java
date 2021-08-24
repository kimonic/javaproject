package com.dzx;

import com.dzx.util.LUtils;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Test3 {
    public static void main(String[] args) {
//        TestMethod testMethod=new TestMethod();
//        System.out.println(testMethod.id);
//        resetStr(testMethod);
//        System.out.println(testMethod.id);
//        getXmlString();
//        getFindString();
        testQueue();
    }

    private static void testQueue() {
        ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 20; i++) {
            concurrentLinkedQueue.offer(i);
        }
        LUtils.i("当前大小 = ", concurrentLinkedQueue.size());
        LUtils.i("pool 一次", concurrentLinkedQueue.poll());
        LUtils.i("当前大小 = ", concurrentLinkedQueue.size());
        LUtils.i("peek 一次", concurrentLinkedQueue.peek());
        LUtils.i("当前大小 = ", concurrentLinkedQueue.size());

    }

    private static void resetStr(TestMethod s) {
        s.resetId();
        s = null;
    }

    public static class TestMethod {
        public String id = "99999";

        public void resetId() {
            id = "78987777";
        }
    }

    private static void getXmlString() {
        String s = "<View\n" +
                "        android:id=\"@+id/view_color";
        String s1 = "\"\n" +
                "        android:layout_width=\"@dimen/dimen_200\"\n" +
                "        android:layout_height=\"@dimen/dimen_200\"\n" +
                "        app:layout_constraintLeft_toRightOf=\"@+id/view_color";
        String s2 = "\"\n" +
                "        app:layout_constraintTop_toTopOf=\"@+id/view_color10\" />";


        for (int i = 11; i < 19; i++) {
            System.out.print(s + i + s1 + (i - 1) + s2);
            System.out.println("\n\n\n");
        }
    }

    private static void getFindString() {
        String s = "        mViewColor";
        String s1 = " = findViewById(R.id.view_color";
        String s2 = ");\n";
        for (int i = 10; i < 19; i++) {
            System.out.println(s + i + s1 + i + s2);
        }

    }

}

