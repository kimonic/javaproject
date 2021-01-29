package com.dzx;

public class Test3 {
    public static void main(String[] args) {
//        TestMethod testMethod=new TestMethod();
//        System.out.println(testMethod.id);
//        resetStr(testMethod);
//        System.out.println(testMethod.id);
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

}

