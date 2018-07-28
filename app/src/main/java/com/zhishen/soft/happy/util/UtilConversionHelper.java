package com.zhishen.soft.happy.util;

import android.content.Context;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class UtilConversionHelper {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static String formatShowPoints(double value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
		/*
		 * setMinimumFractionDigits设置成2
		 *
		 * 如果不这么做，那么当value的值是100.00的时候返回100
		 *
		 * 而不是100.00
		 */
//        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
		/*
		 * 如果想输出的格式用逗号隔开，可以设置成true
		 */
        nf.setGroupingUsed(true);
        return nf.format(value);
    }

}
