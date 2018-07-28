package com.zhishen.soft.happy.jni;

import android.support.v4.app.Fragment;

/**
 * **********************************************************
 * <p/>
 * 说明：fragment的基类，需要转圈圈的要继承这个类
 * <p/>
 * 作者：cailin
 * <p/>
 * 创建日期：2014/11/23
 * <p/>
 * 描述：当多个线程同时要转圈圈时，会实现最后一个才消失的效果
 * **********************************************************
 */
public class BaseFragment extends Fragment {

    public final void showProgressInfo(final String s) {
        if (getActivity()!=null && getActivity() instanceof BaseActivity){
            ((BaseActivity)getActivity()).showProgressInfo(s);
        }
    }

    public final void dismissProgress() {
        if (getActivity()!=null && getActivity() instanceof BaseActivity){
            ((BaseActivity)getActivity()).dismissProgress();
        }
    }

}
