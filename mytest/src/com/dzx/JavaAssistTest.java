package com.dzx;

import com.dzx.util.LUtils;
import javassist.*;
import javassist.bytecode.AccessFlag;

import java.io.File;
import java.lang.reflect.Method;

public class JavaAssistTest {


    public static void main(String[] args) throws Exception {
//        testJavaAssistAddMethod();
        try {
            LUtils.i(JavaAssistTest.class.getResource(""));

        } catch (Exception e) {
            LUtils.i("=================");
        }
        testJavaAssistAddMethod();
    }


    private static void testJavaAssistAddMethod() throws Exception {
        //Manifest-Version: 1.0
        //Ant-Version: Apache Ant 1.10.7
        //Created-By: 12.0.2+10 (Oracle Corporation)
        //Specification-Title: Javassist
        //Specification-Vendor: Shigeru Chiba, www.javassist.org
        //Specification-Version: 3.29.0-SNAPSHOT 使用版本
        //Main-Class: javassist.CtClass
        //Automatic-Module-Name: org.javassist

        ClassPool pool = ClassPool.getDefault();
        //设置目标类的路径(即目标类的class路径,不包含包名)
        pool.insertClassPath("C:\\Users\\dingzhixin.ex\\Desktop\\jartest");
        //以android.view.View为例,如果报Exception in thread "main" javassist.NotFoundException: android.view.View异常
        //则将android.jar以绝对路径的方式通过appendClassPath的方式添加到pool中,其他jar包仿照此例,如下
        pool.appendClassPath("D:\\Android\\Sdk\\platforms\\android-29\\android.jar");
        //手动导入类,母亲详细用法不明
        //pool.importPackage("android.os.Bundle");
        //获得要修改的类（注意，一定要类的全称,含包名）
        CtClass cc = pool.get("org.apache.commons.io.FileUtils");
//        CtClass cc1 = pool.get("android.view.View");
//        CtClass cc2 = pool.get("android.content.Context");
        CtClass cc3 = pool.get("android.os.Bundle");
        //获取勒种所有的声明方法
        CtMethod[] ctMethods = cc.getDeclaredMethods();
        //直接使用方法名获取方法,如果有重载方法则会获取第一个,有重载方法要使用带参数的方法获取
        //CtMethod test=cc.getDeclaredMethod("copyFileToDirectory");
        //带参数类型的获取方法
        //CtMethod test1=cc.getDeclaredMethod("copyFileToDirectory",new CtClass[]{pool.get("java.io.File"),pool.get("java.io.File"),
        //        CtClass.booleanType});

        CtMethod method = null;
        CtMethod method1 = null;
        for (int i = 0; i < ctMethods.length; i++) {
            if ("copyFileToDirectory".equals(ctMethods[i].getName())) {
                if (ctMethods[i].getParameterTypes() != null) {
                    //可根据参数个数判断不同的同名方法
                    int paramCount = ctMethods[i].getParameterTypes().length;
                    if (paramCount == 2) {
                        method1 = ctMethods[i];
                    } else if (paramCount == 3) {
                        method = ctMethods[i];
                    }
                }
            }
        }
        if (method != null) {
            //如果class本次运行中被修改过,则会被冻结,尝试修改冻结的类会报异常,需要先进行解冻处理
            if (cc.isFrozen()) {
                //解冻当前被冻结的类
                cc.defrost();
            }

            try {
                CtField ctField = cc.getDeclaredField("MY_ADD_PARAM");
                LUtils.i(ctField);
            } catch (Exception e) {
                // 添加私有成员name及其getter、setter方法,如果要在后续添加的方法中使用添加的字段
                //则添加的字段必须提前添加,常量字段会直接将值写入到使用的地方,非常量字段则会添加变量名
                CtField param = new CtField(pool.get("java.lang.String"), "MY_ADD_PARAM", cc);
                //字段的属性定义,如下为 private static final
                param.setModifiers(Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL);
                //添加字段的set方法
                //cc.addMethod(CtNewMethod.setter("setName", param));
                //添加字段的get方法
                //cc.addMethod(CtNewMethod.getter("getName", param));
                //添加字段到类,第二个参数为初始值
                cc.addField(param, CtField.Initializer.constant("后来添加的常量"));

                //创建新方法,第一个参数,新添加方法体内容,第二个参数,新建方法要添加到的类,注意,第二个参数是A类,将该方法add
                //到B类,则会报异常,即第二个参数为A类则该方法只能添加到A类中,int 类型可以直接使用,String对象类型需要全路径名
                CtMethod ctMethod = CtMethod.make("public java.lang.String myAddNewMethod(){return this.MY_ADD_PARAM;}", cc);
                //将方法添加到类中
                cc.addMethod(ctMethod);
            }


//            //在方法体最前面插入代码
            method.insertBefore("System.out.println(\"这是插入在前面的代码\");");
//            //在方法体最后面插入代码
//            method.insertAfter("System.out.println(\"这是插入在后面的代码\");");
//            //获取异常类型
//            CtClass etype = ClassPool.getDefault().get("java.lang.Exception");
//            //添加catch会捕获整个方法的代码,第一个参数为 catch (Exception e)方法体内的代码,不能为空
//            //catch方法体至少要包含一个 return 0;语句,否则会异常
//            //其中参数Exception 要与第三个参数的名称保持一致,第二个参数为要捕获的异常类型
//            //addCatch方法与insertBefore,insertAfter可任意调整顺序,但是addCatch中的捕获异常会包含
//            //该语句前的方法内的所有代码
//            method.addCatch("{ System.out.println(a); return 0; }", etype, "a");

            //该方法会完全将原方法中的代码替换为body体代码,多行代码必须用{}包裹,调用同一个类的方法可直接使用方法名加参数
            //$1,$2,$n分别标识第一个第二个参数第n个参数
            //method.setBody("{copyFileToDirectory(new java.io.File(MY_ADD_PARAM), $2, true); System.out.println(\"完全替换方法代码\");}");

            //使用该方法获取方法的首行号,获取到首行号后再根据方法代码行数加要插入的行数后作为insertAr方法的lineNum参数
            //实际测试,getLineNumber方法中的参数返回的值可能是一致的,所以通过传入0后获取开始行号后再添加对应要插入代码的行数
            //是比较可行的一种方案,该方法与setBody不能同时使用,
            int lineNum = method.getMethodInfo().getLineNumber(20) + 1;
            //通过以上方法获取起始行号,然后加上要插入代码在方法体中要跨越的行数
            method.insertAt(lineNum, "System.out.println(\"在方法的指定行插入代码" + lineNum + "\");");

            LUtils.i("修改类执行完毕");
            //将class文件写入指定目录,包名目录会自动创建
            cc.writeFile("C:\\Users\\dingzhixin.ex\\Desktop\\jartest\\test001");
        }


    }

    private static void consult3() throws Exception {
        System.out.println("这是插入在前面的代码");
        System.out.println("这是插入在后面的代码");
        ClassPool pool = ClassPool.getDefault();
        //设置目标类的路径(即目标类的class路径，我这里的test2.class是在工程下WebRoot/WEB-INF/classes/com/hcj/javaassist)
//            pool.insertClassPath("E:\\work\\app\\MethodLogMaster-master\\MethodLogMaster-master\\app\\build\\intermediates\\javac\\debug\\classes");
        //获得要修改的类（注意，一定要类的全称）
//            CtClass cc = pool.get("com.tim.fly.MainActivity");
        CtClass cc = pool.get("com.dzx.JavaAssistTest");
        Test2 test2 = new Test2();
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

    private static void consult2() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
        CtClass cl = null;
        try {
            // 从classpath中查询该类
            cl = pool.get("com.zero.test.TestMainInJar");
            // ...
            // 获取二进制格式
            byte[] byteArr = cl.toBytecode();
            //输出.class文件到该目录中
            cl.writeFile("/Users/zero/git/xxx/src/main/java/");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cl != null) {
                //把CtClass object 从ClassPool中移除
                cl.detach();
            }
        }
    }

    private static void consult() throws Exception {
        // 创建类
        ClassPool pool = ClassPool.getDefault();
        CtClass cls = pool.makeClass("cn.ibm.com.TestClass");

        // 添加私有成员name及其getter、setter方法
        CtField param = new CtField(pool.get("java.lang.String"), "name", cls);
        param.setModifiers(Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL);
        cls.addMethod(CtNewMethod.setter("setName", param));
        cls.addMethod(CtNewMethod.getter("getName", param));
        cls.addField(param, CtField.Initializer.constant(""));

        // 添加无参的构造体
        CtConstructor cons = new CtConstructor(new CtClass[]{}, cls);
        cons.setBody("{name = \"Brant\";}");
        cls.addConstructor(cons);

        // 添加有参的构造体
        //  $0代码的是this，$1代表方法参数的第一个参数、$2代表方法参数的第二个参数,以此类推，$N代表是方法参数的第N个
        cons = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, cls);
        cons.setBody("{$0.name = $1;}");
        cls.addConstructor(cons);

        // 打印创建类的类名
        System.out.println(cls.toClass());

        // 通过反射创建无参的实例，并调用getName方法
        Object o = Class.forName("cn.ibm.com.TestClass").newInstance();
        Method getter = o.getClass().getMethod("getName");
        System.out.println(getter.invoke(o));

        // 调用其setName方法
        Method setter = o.getClass().getMethod("setName", new Class[]{String.class});
        setter.invoke(o, "Adam");
        System.out.println(getter.invoke(o));

        // 通过反射创建有参的实例，并调用getName方法
        o = Class.forName("cn.ibm.com.TestClass").getConstructor(String.class).newInstance("Liu Jian");
        getter = o.getClass().getMethod("getName");
        System.out.println(getter.invoke(o));
    }

    private int ff() {
        Method method;
        File file = new File("");

        File file1 = new File("");
        File file2 = new File("");
        return 0;
    }

}
