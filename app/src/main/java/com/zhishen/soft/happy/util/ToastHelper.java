package com.zhishen.soft.happy.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.zhishen.soft.happy.jni.BaseApp;

public class ToastHelper {

    private static Toast toast;

    private static void prepare(Context context) {
        if (null == toast) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
    }

    public static void clear() {
        if (null != toast) {
            toast.cancel();
        }
    }

    public static void toast(Context context, String str) {
        prepare(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(str);
        toast.show();
    }

    public static void toast(String str) {
        toast(BaseApp.getApplication(), str);
    }

    public static void toast(Context context, int resourceId) {
        String str = context.getResources().getString(resourceId);
        toast(context, str);
    }

    public static void toast(int resourceId) {
        toast(BaseApp.getApplication(), resourceId);
    }

    public static void toastLong(Context context, String str) {
        prepare(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setText(str);
        toast.show();
    }

    public static void toastLong(Context context, int resourceId) {
        String str = context.getResources().getString(resourceId);
        toastLong(context, str);
    }

    public static void toastLong(Context context, String str, int width, int height) {
        prepare(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, width, height);
        toast.setText(str);
        toast.show();
    }

}
