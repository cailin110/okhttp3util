package com.zhishen.soft.happy.jni;

import android.content.Context;

public interface ILoading {
    Loading getLoading();

    Loading initLoading();

    Context getLoadingContext();
}