package com.dzx.util;

import com.sun.istack.Nullable;

public class TextUtils {
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean nonEmpty(@Nullable CharSequence str) {
        return !isEmpty(str);
    }

    public static int getStringInt(String target) {
        int result = 0;
        System.out.println(target);

        if (nonEmpty(target)) {
            try {
                result = Integer.parseInt(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}