package com.zhishen.soft.happy.jni;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;

public class Loading {
    private ProgressDialog mDialog;
    private Context mContext;

    public Loading(Context context) {
        mContext = context;
    }

    /**
     * 显示等待窗
     */
    public void show() {
        show(null);
    }

    public void show(String title) {
        if (null == mDialog) {
            initDialog();
        }
        title=title==null||"".equals(title)? "正在加载...": title;
        mDialog.setMessage(title);
        mDialog.show();
    }

    /**
     * 取消等待窗
     */
    public void dismiss() {
        if (null != mDialog) {
            mDialog.cancel();
        }
    }

    private void initDialog() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setCancelable(true);
        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
//        mDialog.getWindow().setGravity(Gravity.TOP);
//        params.y = dip2px(mContext, 50);
        mDialog.getWindow().setAttributes(params);
    }


}
