package com.zhishen.soft.happy.util;

import com.zhishen.soft.happy.main.R;

/**
 * **********************************************************
 * <p/>
 * 说明：
 * <p/>
 * 作者：cailin
 * <p/>
 * 创建日期：
 * <p/>
 * 描述：
 * **********************************************************
 */
public class ButtonUtil {
    private static long mLastClickTime = 0;
    private static int mLastButtonId = -1;
    private static long DIFF = 1500;//默认间隔时间
    private static long mLastToastTime = 0;

    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long clickTime = System.currentTimeMillis();
        long timeSpace = Math.abs(clickTime - mLastClickTime);

        if (mLastButtonId != buttonId) {
            if (mLastClickTime > 0 && timeSpace < diff / 3) {
                if (clickTime - mLastToastTime > diff) {
                    ToastHelper.toast(R.string.click_fast_tip);
                    mLastToastTime = clickTime;
                }
                mLastClickTime = clickTime;
                return true;
            }
        } else {
            if (mLastClickTime > 0 && timeSpace < diff / 3) {
                if (clickTime - mLastToastTime > diff) {
                    ToastHelper.toast(R.string.click_fast_tip);
                    mLastToastTime = clickTime;
                }
                mLastClickTime = clickTime;
                return true;
            }
        }
        mLastClickTime = clickTime;
        mLastButtonId = buttonId;
        return false;
    }
}
