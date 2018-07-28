package com.zhishen.soft.happy.jni;

import android.content.Context;
import android.graphics.Bitmap;

import com.common.http.okhttp.OkHttpUtils;
import com.common.http.okhttp.log.LoggerInterceptor;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.L;
import com.zhishen.soft.happy.main.R;
import com.zhishen.soft.happy.util.NetStateManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class ZhiShenApp extends BaseApp {

    private DisplayImageOptions mDefaultoptions;
    private DisplayImageOptions mUserPicoptions;
    private static ZhiShenApp sUpApp;

    public static ZhiShenApp getInstance() {
        return sUpApp;
    }


    @Override
    protected void afterCreate() {
        sUpApp = (ZhiShenApp) getApp();
        NetStateManager.init(getBaseContext());

        boolean isNotDebug=false;
        L.writeDebugLogs(isNotDebug);
        L.writeLogs(isNotDebug);
        initImageLoader(getApplicationContext());

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new LoggerInterceptor("zhishenlog", true)).build();
        OkHttpUtils.initClient(okHttpClient);


    }


//    public OkHttpClient getClientInfo(){
//        if (mOkHttpClient==null){
////            CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
//            ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
//
//            mOkHttpClient = new OkHttpClient.Builder()
//                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                    .cookieJar(cookieJar)
//                    .build();
//        }
//        return mOkHttpClient;
//    }


    @Override
    protected void beforeExit() {
    }


    private void initImageLoader(Context context) {
//        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), AppConfigUse.PATH_PIC_CACHE);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
//                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    public DisplayImageOptions getDefaultImgeOptions(){
        if (mDefaultoptions ==null){
            mDefaultoptions = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_begin_loading)
                    .showImageForEmptyUri(R.drawable.ic_no_loading_default)
                    .showImageOnFail(R.drawable.ic_no_loading_default)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }
        return mDefaultoptions;
    }

    public DisplayImageOptions getUserPicOptions(){
        if (mUserPicoptions ==null){
            mUserPicoptions = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageOnLoading(R.drawable.ic_default_head_old)
                    .showImageForEmptyUri(R.drawable.ic_default_head_old)
                    .showImageOnFail(R.drawable.ic_default_head_old)
                    .cacheInMemory(true)
                    .cacheOnDisc(true)
                    .considerExifParams(true)
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                    .displayer(new RoundedBitmapDisplayer(360))
                    .build();
        }
        return mUserPicoptions;
    }

}
