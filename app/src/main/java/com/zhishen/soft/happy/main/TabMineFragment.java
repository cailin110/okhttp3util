package com.zhishen.soft.happy.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.common.http.okhttp.OkHttpUtils;
import com.common.http.okhttp.callback.StringCallback;
import com.google.gson.Gson;
import com.zhishen.soft.happy.bean.UserInfo;
import com.zhishen.soft.happy.main.activity.LoginActivity;
import com.zhishen.soft.happy.util.AppConfigUse;
import com.zhishen.soft.happy.util.MyLog;
import com.zhishen.soft.happy.util.SPUtil;
import com.zhishen.soft.happy.util.ToastHelper;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class TabMineFragment extends BaseTabFragment implements View.OnClickListener {

    private Button mBtnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_tab_mine, container, false);
        view.findViewById(R.id.tv_tab_my_item1).setOnClickListener(this);
        view.findViewById(R.id.tv_tab_my_item2).setOnClickListener(this);
        view.findViewById(R.id.tv_tab_my_item3).setOnClickListener(this);
        view.findViewById(R.id.tv_tab_my_item4).setOnClickListener(this);
        view.findViewById(R.id.tv_tab_my_item5).setOnClickListener(this);

        mBtnLogin= (Button) view.findViewById(R.id.btn_login_login);
        mBtnLogin.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_tab_my_item1:
                getTest1();
                break;
            case R.id.tv_tab_my_item2:
                getTest2();
                break;
            case R.id.tv_tab_my_item3:
                getTest3();
                break;
            case R.id.tv_tab_my_item4:
                getTest4();
                break;
        }

    }

    private void getTest1() {
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Config);
        Map<String ,String> param=new HashMap<String, String>();
        UserInfo userInfo= SPUtil.getLoginInfo();
        if (userInfo!=null&&userInfo.token!=null){
            MyLog.i("xiaocai", "request token=" + userInfo.token);
            param.put("Authorization", "Bearer " + userInfo.token);
            showProgressInfo("");
            Map<String ,String> param2=new HashMap<String, String>();
            param2.put("page", "1");
            OkHttpUtils.getJson().url(url).params(param2).headers(param).build().execute(new StringCallback() {
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

    private void getTest2() {
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Transaction);
        Map<String ,String> param=new HashMap<String, String>();
        UserInfo userInfo= SPUtil.getLoginInfo();
        if (userInfo!=null&&userInfo.token!=null){
            MyLog.i("xiaocai", "request token=" + userInfo.token);
            param.put("Authorization", "Bearer " + userInfo.token);
            showProgressInfo("");
            Map<String ,String> param2=new HashMap<String, String>();
            param2.put("page", "1");
            OkHttpUtils.getJson().url(url).params(param2).headers(param).build().execute(new StringCallback() {
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

    private void getTest3() {
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Wallet);
        Map<String ,String> param=new HashMap<String, String>();
        UserInfo userInfo= SPUtil.getLoginInfo();
        if (userInfo!=null&&userInfo.token!=null){
            MyLog.i("xiaocai", "request token=" + userInfo.token);
            param.put("Authorization", "Bearer " + userInfo.token);
            showProgressInfo("");
            Map<String ,String> param2=new HashMap<String, String>();
            OkHttpUtils.getJson().url(url).params(param2).headers(param).build().execute(new StringCallback() {
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


    private void getTest4() {
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Withdraw);
        Map<String ,String> param=new HashMap<String, String>();
        UserInfo userInfo= SPUtil.getLoginInfo();
        if (userInfo!=null&&userInfo.token!=null){
            MyLog.i("xiaocai", "request token=" + userInfo.token);
            param.put("Authorization", "Bearer " + userInfo.token);
            showProgressInfo("");
            Map<String ,String> param2=new HashMap<String, String>();
            param2.put("amount", "100");
            OkHttpUtils.postString().url(url).mediaType(AppConfigUse.sJsonType).content(new Gson().toJson(param2)).headers(param).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int i, int errorCode, String msg) {
                    dismissProgress();
                    MyLog.e("xiaocai", e.getMessage());
                    ToastHelper.toast(msg);
                }


                @Override
                public void onResponse(String s, int i) {
                    dismissProgress();
                    MyLog.i("xiaoccai", "response=" + s);
                    ToastHelper.toast(s);
                }
            });

        }

    }



}
