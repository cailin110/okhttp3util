package com.zhishen.soft.happy.jni;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.zhishen.soft.happy.util.ToastHelper;

/**
 * **********************************************************
 * <p/>
 * 说明：开启线程的基类
 * <p/>
 * 作者：
 * <p/>
 * 创建日期：2015/7/5
 * <p/>
 * 描述：
 * **********************************************************
 */
public abstract class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    private Context mContext;
    private String mErrorMsg;
    private Exception mException;
    private Loading mLoading;
    private String mTitle;

    public BaseAsyncTask(ILoading loading) {
        this(loading, false);
    }

    public BaseAsyncTask(ILoading loading, boolean showLoading) {
        this(loading, showLoading, null);
    }

    public BaseAsyncTask(ILoading loading, boolean showLoading, String title) {
        super();
        if (loading != null) {
            mContext = loading.getLoadingContext();
        }
        if (showLoading) {
            mLoading = loading.getLoading();
            mTitle=title;
        }
    }

    public Context getContext() {
        return mContext;
    }

    protected Exception getException() {
        return mException;
    }

    @Override
    protected final void onPreExecute() {
        if (null != mLoading) {
            try {
                if (!(mTitle == null || mTitle.trim().length() <= 0)){
                    mLoading.show(mTitle);
                }else{
                    mLoading.show();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected final Result doInBackground(Params... params) {
        Result result = null;
        try {
            result = onLoad(params);
        } catch (Exception e) {
            e.printStackTrace();
            mException = e;
            mErrorMsg = e.getMessage();
        }
        return result;
    }

    @Override
    protected final void onPostExecute(Result result) {
        if (!isCancelled()) {
            if (null != mErrorMsg) {
                onHandleError(mErrorMsg);
            } else {
                onUI(result);
            }
        }
        if (null != mLoading) {
            try {
                mLoading.dismiss();
            } catch (Exception e) {
            }
        }
    }

    protected abstract Result onLoad(Params... params)
            throws Exception;

    protected abstract void onUI(Result result);

    protected void onHandleError(String errorMsg) {
        ToastHelper.toast(errorMsg);
    }

    public final void executeParallelly(Params... params) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            execute(params);
        } else {
            executeOnExecutor(THREAD_POOL_EXECUTOR, params);
        }
    }
}
