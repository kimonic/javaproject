package com.kimonik;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by kimonik on 2017/5/12 0012.
 * <p>
 * 打印自定义信息
 */


public class LUtils {


//    private static final boolean  DEBUG=false;

    private static final boolean DEBUG = true;

    private static final int showCount = 3 * 1024;


    public static String buildMessage(Object... str) {
        StringBuilder builder = new StringBuilder();

        if (str != null && str.length > 0) {
            for (Object s : str) {
                builder.append(s);
            }
        }

        return builder.toString();

    }

    public static void e(Class clz, Object... objects) {

        if (DEBUG) {
            String str = buildMessage(objects);
            System.out.println(clz.getSimpleName() + "==  " + Thread.currentThread().getStackTrace()[3].getMethodName() + "  =DZX=  " + str + "  =");
        }

    }

    public static void i(Class clz, Object... objects) {

        if (DEBUG) {
            String str = buildMessage(objects);
            System.out.println(clz.getSimpleName() + "==  " + Thread.currentThread().getStackTrace()[3].getMethodName() + "  =DZX=  " + str + "  =");
        }

    }


    public static void i(Object... objects) {
        if (DEBUG) {
            String str = buildMessage(objects);
            System.out.println(str);
        }
    }

    public static String getErrorMessage(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    public static String getCurrentDateString(Date date) {
        String result;
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.getDefault());
        LUtils.e(LUtils.class, "date = null  ", (date == null));
        if (date == null) {

            result = simpleDateFormat.format(new Date());
        } else {
            result = simpleDateFormat.format(date);
        }
        return result;
    }

    public static void outputBeanContent(Object bean) {
        if (bean == null) {
            return;
        }
        LUtils.e(LUtils.class, "实体数据======      " + new Gson().toJson(bean));
    }


}
