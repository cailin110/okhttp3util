package com.zhishen.soft.happy.jni;

import android.app.Application;

public abstract class BaseApp extends Application {

	private static BaseApp sInstance;

	public static BaseApp getApplication() {
		return sInstance;
	}

	protected BaseApp getApp() {
		return sInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		afterCreate();
	}

	protected abstract void afterCreate();

	public void exit() {
		beforeExit();
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	protected abstract void beforeExit();

}
