package com.zhishen.soft.happy.util;

import android.util.Log;

public class MyLog {

    private static boolean sDebugMode = true;

    public static void init(boolean flag) {
        sDebugMode = flag;
    }

    public static boolean isDebugMode() {
        return sDebugMode;
    }

    public static void i(String tag, String msg) {
        if (sDebugMode) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sDebugMode) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, Object msg) {
        if (sDebugMode) {
            Log.i(tag, String.valueOf(msg));
        }
    }

    public static void i(Class<?> c, Object msg) {
        i(c.getSimpleName(), msg);
    }

}
