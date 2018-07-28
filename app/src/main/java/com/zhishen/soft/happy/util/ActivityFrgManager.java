package com.zhishen.soft.happy.util;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * **********************************************************
 * <p/>
 * 说明：
 * <p/>
 * 作者：cailin
 * <p/>
 * 创建日期：2014/12/3
 * <p/>
 * 描述：
 * **********************************************************
 */
public class ActivityFrgManager {

    public static ActivityFrgManager sManager;

    public static ActivityFrgManager getInstance(){
        if (sManager==null){
            sManager=new ActivityFrgManager();
        }
        return sManager;
    }

    private final List<WeakReference<Activity>> mActivities=new LinkedList<WeakReference<Activity>>();

    public void registerActivity(Activity activity) {
        mActivities.add(new WeakReference<Activity>(activity));
    }

    public void clearActivityAdd() {
        mActivities.clear();
    }

    public void finishActivities() {
        for (WeakReference<Activity> activity : mActivities) {
            Activity a=activity.get();
            if (null!=a && !a.isFinishing()) {
                a.finish();
            }
        }
        clearActivityAdd();
    }

    public List<WeakReference<Activity>> getActivities() {
        return mActivities;
    }
}
