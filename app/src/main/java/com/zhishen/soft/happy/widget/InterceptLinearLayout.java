package com.zhishen.soft.happy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * **********************************************************
 * <p/>
 * 说明：
 * <p/>
 * 作者：cailin
 * <p/>
 * 创建日期：2015/3/17
 * <p/>
 * 描述：
 * **********************************************************
 */
public class InterceptLinearLayout extends LinearLayout {
    public InterceptLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
    }
}
