package com.dzx;

import com.dzx.util.LUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AccessFlag;

import java.io.File;

public class JavaAssistTest {


    public static class Test2 {

        static {
            LUtils.i("未使用也初始化了");
        }
        public int f(int i) {
            i++;
            return i;
        }
    }

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        //设置目标类的路径(即目标类的class路径，我这里的test2.class是在工程下WebRoot/WEB-INF/classes/com/hcj/javaassist)
//            pool.insertClassPath("E:\\work\\app\\MethodLogMaster-master\\MethodLogMaster-master\\app\\build\\intermediates\\javac\\debug\\classes");
        //获得要修改的类（注意，一定要类的全称）
//            CtClass cc = pool.get("com.tim.fly.MainActivity");
        CtClass cc = pool.get("com.dzx.JavaAssistTest");
        Test2 test2=new Test2();
        CtMethod[] ctMethods = cc.getDeclaredMethods();
        if (cc.isFrozen()) {
            cc.defrost();
        }

        CtClass etype = ClassPool.getDefault().get("java.lang.Exception");
        for (CtMethod ctMethod : ctMethods) {
            if ("ff".equals(ctMethod.getName())) {
                ctMethod.setModifiers(AccessFlag.setPublic(ctMethod.getModifiers()) | AccessFlag.STATIC);
//                ctMethod.insertAt(68, "  try {\n" +
//                        "                    System.out.println(\"==\");\n" );
//                ctMethod.insertAt(69,
//                        "                } catch (Exception e) {\n" +
//                        "                    e.printStackTrace();\n" +
//                        "                }");
                ctMethod.setBody("{ try {\n" +
                        "            java.io.File file = new java.io.File(\"\");\nfile.exists();" +
                        "        } catch (Exception e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }\n" +
                        "        java.io.File file1 = new java.io.File(\"\");\n" +
                        "        java.io.File file2 = new java.io.File(\"\");\n" +
                        "        return 0;}");
//                ctMethod.addCatch("{ System.out.println(a); return 0; }", etype,"a");
//            ctMethod.setModifiers(AccessFlag.PUBLIC|AccessFlag.STATIC);



                LUtils.i(ctMethod.getName());
            }

        }


//        Test2 test = new Test2();
//        Class c = test.getClass();
////		Method[] method = c.getDeclaredMethods();
//        try {
//            cc.getDeclaredMethod("g");
//            System.out.println("g() is already defined in sample.Test.");
//        } catch (Exception e) {
//            CtMethod fMethod = cc.getDeclaredMethod("f");
//            CtMethod gMethod = CtNewMethod.copy(fMethod, "g", cc, null);
//            cc.addMethod(gMethod);
//        cc.writeFile("E:\\work\\app\\mytoolsjavaproject\\javaproject\\out\\production\\mytest\\com\\dzx\\util"); // 更新class文件
//            System.out.println("g() was added.");
//        }
        cc.writeFile();
    }

    private int ff() {
        File file = new File("");

        File file1 = new File("");
        File file2 = new File("");
        return 0;
    }

}
