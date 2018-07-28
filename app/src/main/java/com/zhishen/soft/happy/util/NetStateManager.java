package com.zhishen.soft.happy.util;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zhishen.soft.happy.jni.BaseApp;


public class NetStateManager {
    public enum NetState {
        MOBILE, WIFY, NOWAY
    }

    public static NetState CUR_NETSTATE = NetState.NOWAY;

    public static String MOBILE_PROXY = null;

    private static NetStateReceiver sReceiver;

    public static void init(Context context) {
        CUR_NETSTATE = NetState.NOWAY;
        getCurNetState(context);
        sReceiver = null;
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        sReceiver = new NetStateReceiver();
        context.registerReceiver(sReceiver, filter);
    }

    public static void onExit(Context context) {
        if (sReceiver != null) {
            context.unregisterReceiver(sReceiver);
            CUR_NETSTATE = NetState.NOWAY;
            sReceiver = null;
        }
    }

    public static boolean OnNet() {
        getCurNetState(BaseApp.getApplication());
        return CUR_NETSTATE != NetState.NOWAY;
    }

    protected static NetState getCurNetState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            int netType = networkInfo.getType();
            int subType = networkInfo.getSubtype();
            if (isConnectionFast(netType, subType)) {
                NetStateManager.CUR_NETSTATE = NetState.WIFY;
            } else {
                NetStateManager.CUR_NETSTATE = NetState.MOBILE;
            }
        } else {
            NetStateManager.CUR_NETSTATE = NetState.NOWAY;
        }
        return NetStateManager.CUR_NETSTATE;
    }

    protected static boolean isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            return false;
        }
    }

}
