package com.zhishen.soft.happy.util;

import android.os.Environment;

import okhttp3.MediaType;

public class AppConfigUse {

    // 本地基础路径
    public static final String PATH_BASE = Environment.getExternalStorageDirectory() + "/avene";
    // 下载更新apk存储路径
    public static final String PATH_APK = PATH_BASE + "/Apk/";
    // 下载图片存储路径
    public static final String PATH_PIC_CACHE = PATH_BASE + "/PicCache";
    // 数据库存储路径
    public static final String PATH_DB_SVAE = PATH_BASE + "/database/";
    // 录音存储路径
    public static final String PATH_AUDIO_SVAE = PATH_BASE + "/audio";

    public static final String TYPE_LANGUAGE_SHOW_DEFAULT = "sans";
//    public static final String TYPE_LANGUAGE_SHOW_DEFAULT = "sans-serif";


    //    public static final String sCommonUrl = "http://192.168.1.10:8080/EatWhatWeb/";
//    public static final String sCommonUrl = "http://192.168.31.212:8080/EatWhatWeb/";
//    public static final String sCommonUrl = "http://115.29.44.219:8080/EatWhatWeb/";

    //请求超时时间
    public static final int TIME=30000;

    public static final String sCommonUrl = "https://api4b-dev.mowei.net/api/v1";

    //json请求
    public static final MediaType sJsonType = MediaType.parse("application/json; charset=utf-8");

    //登录
    public static final String Api_Login = "/login/";

    //发送验证码
    public static final String Api_LoginVerify = "/login/verify/";

    //获取公司信息
    public static final String Api_Company = "/company/";

    //已申请职位列表
    public static final String Api_Apply = "/apply/";
    //已申请职位列表
    public static final String Api_Apply_Id = "/apply/%s/";

    //已发布职位列表
    public static final String Api_Position = "/position/";
    //已发布职位列表
    public static final String Api_Position_Id = "/position/%s/";

    //配置信息列表
    public static final String Api_Config = "/config/";
    //公司交易流水
    public static final String Api_Transaction = "/transaction/";
    //公司钱包接口
    public static final String Api_Wallet = "/wallet/";
    //提现申请
    public static final String Api_Withdraw = "/withdraw/";


}
