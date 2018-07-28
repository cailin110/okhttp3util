package com.zhishen.soft.happy.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.zhishen.soft.happy.bean.UserInfo;
import com.zhishen.soft.happy.jni.BaseApp;

/**
 * Created by cailin on 2018/7/12.
 */
public class SPUtil {

    public static SharedPreferences mPreference;
    private final static String WEIBO_PREFER = "zhisheng";

    public static final String sLogin_User_Info="sLogin_User_Info";

    public static UserInfo getLoginInfo(){
        String str= SPUtil.getString(SPUtil.sLogin_User_Info);
        if (StringUtil.isNotEmpty(str)){
            UserInfo u=new Gson().fromJson(str, UserInfo.class);
            return u;
        }
        return null;
    }

    public static SharedPreferences getPreference() {
        if (mPreference == null)
            mPreference = BaseApp.getApplication().getSharedPreferences(WEIBO_PREFER, 0);
        return mPreference;
    }

    public static void setInteger(String name, int value) {
        getPreference().edit().putInt(name, value).commit();
    }

    public static int getInteger(String name, int default_i) {
        return getPreference().getInt(name, default_i);
    }

    public static void setString(String name, String value) {
        getPreference().edit().putString(name, value).commit();
    }

    public static String getString(String name) {
        return getPreference().getString(name, null);
    }

    public static String getString(String name, String defalt) {
        return getPreference().getString(name, defalt);
    }

    public static boolean getBoolean(String name, boolean defaultValue) {
        return getPreference().getBoolean(name, defaultValue);
    }

    public static void setBoolean(String name, boolean value) {
        getPreference().edit().putBoolean(name, value).commit();
    }

    public static Long getLong(String name, long defaultValue) {
        return getPreference().getLong(name, defaultValue);
    }

    public static void setLong(String name, long value) {
        getPreference().edit().putLong(name, value).commit();
    }

}
