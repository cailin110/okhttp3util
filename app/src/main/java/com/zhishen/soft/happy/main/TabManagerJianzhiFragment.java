package com.zhishen.soft.happy.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.common.http.okhttp.OkHttpUtils;
import com.common.http.okhttp.callback.StringCallback;
import com.google.gson.Gson;
import com.zhishen.soft.happy.bean.UserInfo;
import com.zhishen.soft.happy.jni.BaseFragment;
import com.zhishen.soft.happy.util.AppConfigUse;
import com.zhishen.soft.happy.util.LoadDataType;
import com.zhishen.soft.happy.util.MyLog;
import com.zhishen.soft.happy.util.SPUtil;
import com.zhishen.soft.happy.util.ToastHelper;
import com.zhishen.soft.happy.widget.pulltorefresh.PullToRefreshBase;
import com.zhishen.soft.happy.widget.pulltorefresh.PullToRefreshListView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class TabManagerJianzhiFragment extends BaseFragment implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2 {


    private PullToRefreshListView mPRLVshow;
    private ListView mLVShow;
    private LoadDataType mLoadDataType = LoadDataType.FirstLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_tab_manager_quanzhi, container, false);

        mPRLVshow = (PullToRefreshListView) view.findViewById(R.id.plv_common_pulltofresh);
        mPRLVshow.setOnRefreshListener(this);
//        mPRLVshow.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.common_empty_show, null));
        mLVShow = mPRLVshow.getRefreshableView();
        mLoadDataType = LoadDataType.FirstLoad;

        putApply();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tab_manager_title_quanzhi:
                break;
        }

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        stopLoadingRefreshState();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        stopLoadingRefreshState();
    }

    private void stopLoadingRefreshState() {
        if (mPRLVshow != null) {
            mPRLVshow.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPRLVshow.onRefreshComplete();
                }
            }, 100);
        }
    }


    private void putApply() {
//        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Apply_Id);
        String url=AppConfigUse.sCommonUrl+String.format(AppConfigUse.Api_Apply_Id, "1");

        Map<String ,String> param=new HashMap<String, String>();
        UserInfo userInfo= SPUtil.getLoginInfo();
        if (userInfo!=null&&userInfo.token!=null){
            MyLog.i("xiaocai", "request url=" + url);
            MyLog.i("xiaocai", "request token=" + userInfo.token);
            param.put("Authorization", "Bearer " + userInfo.token);
            showProgressInfo("");
            Map<String ,String> param2=new HashMap<String, String>();
            param2.put("status", "00");
            param2.put("interview_time", "string");
            param2.put("remark", "string");
            OkHttpUtils.putJson().url(url).content(new Gson().toJson(param2)).headers(param).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int i, int errorCode, String msg) {
                    dismissProgress();
                    MyLog.e("xiaocai", e.getMessage());
                    ToastHelper.toast(msg);
                }

                @Override
                public void onResponse(String response, int i) {
                    dismissProgress();
                    ToastHelper.toast(response);
                    MyLog.i("xiaocai", "response=" + response);

                }
            });
        }

    }



}
