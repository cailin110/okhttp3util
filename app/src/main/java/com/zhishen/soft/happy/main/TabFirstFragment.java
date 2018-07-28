package com.zhishen.soft.happy.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.common.http.okhttp.OkHttpUtils;
import com.common.http.okhttp.callback.StringCallback;
import com.google.gson.Gson;
import com.zhishen.soft.happy.bean.UserInfo;
import com.zhishen.soft.happy.util.AppConfigUse;
import com.zhishen.soft.happy.util.MyLog;
import com.zhishen.soft.happy.util.SPUtil;
import com.zhishen.soft.happy.util.ToastHelper;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class TabFirstFragment extends BaseTabFragment implements View.OnClickListener {

    private Button mBtnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_tab_first, container, false);
        view.findViewById(R.id.btn_index_tab_sure).setOnClickListener(this);
        getCompanyInfo();
        return view;
    }

    private void getCompanyInfo() {
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Company);

        Map<String ,String> param=new HashMap<String, String>();
        UserInfo userInfo= SPUtil.getLoginInfo();
        if (userInfo!=null&&userInfo.token!=null){
            MyLog.i("xiaocai", "request token=" + userInfo.token);
            param.put("Authorization", "Bearer "+userInfo.token);
            showProgressInfo("");
            OkHttpUtils.get().url(url).headers(param).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int i, int errorCode, String msg) {
                    dismissProgress();
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_index_tab_sure:
                updateCompanyInfo();
                break;
        }

    }


    private void updateCompanyInfo() {
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Company);

        Map<String ,String> paramHeader=new HashMap<String, String>();
        UserInfo userInfo= SPUtil.getLoginInfo();
        if (userInfo!=null&&userInfo.token!=null){
            MyLog.i("xiaocai", "request token=" + userInfo.token);
            paramHeader.put("Authorization", "Bearer " + userInfo.token);
            Map<String ,String> param2=new HashMap<String, String>();

            param2.put("full_name", "test");
            param2.put("short_name", "string");
            param2.put("logo", "string");
            param2.put("website", "website");
            param2.put("type", "0");
            param2.put("introduction", "introduction");
            param2.put("province", "string");
            param2.put("city", "string");
            param2.put("region", "string");
            param2.put("longitude", "string");
            param2.put("latitude", "string");
            param2.put("img1", "string");
            param2.put("img2", "string");
            param2.put("img3", "string");
            param2.put("address", "string");
            param2.put("verify", "0");
            param2.put("status", "0");
            param2.put("priority", "0");
            param2.put("tags", "string");
            showProgressInfo("");
            OkHttpUtils.putJson().url(url).content(new Gson().toJson(param2)).headers(paramHeader).build().execute(new StringCallback() {
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
