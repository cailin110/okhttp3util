package com.zhishen.soft.happy.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.common.http.okhttp.OkHttpUtils;
import com.common.http.okhttp.callback.StringCallback;
import com.google.gson.Gson;
import com.zhishen.soft.happy.jni.BaseActivity;
import com.zhishen.soft.happy.main.R;
import com.zhishen.soft.happy.main.activity.fragment.CommonHeaderFragment;
import com.zhishen.soft.happy.util.AppConfigUse;
import com.zhishen.soft.happy.util.MyLog;
import com.zhishen.soft.happy.util.SPUtil;
import com.zhishen.soft.happy.util.StringUtil;
import com.zhishen.soft.happy.util.ToastHelper;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private CommonHeaderFragment mHeadFrg;
    private EditText mEtUserName;
    private EditText mEtPassWord;

    @Override
    protected void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        mHeadFrg = (CommonHeaderFragment) getSupportFragmentManager().findFragmentById(R.id.frg_common_header);
        mHeadFrg.getView().post(new Runnable() {
            @Override
            public void run() {
                mHeadFrg.setTitleInfo("登录");
                mHeadFrg.setHeadColor(getResources().getColor(R.color.white));
                mHeadFrg.setBackImage(R.drawable.ic_back_yellow);
            }
        });

    }

    @Override
    protected int showHeadBarColor() {
        return R.color.white;
    }

    @Override
    protected void initViews() {
        mEtUserName = (EditText) findViewById(R.id.et_login_username);
        mEtPassWord = (EditText) findViewById(R.id.et_login_password);
        findViewById(R.id.btn_login_login).setOnClickListener(this);
        findViewById(R.id.btn_register_getcode).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register_getcode:
            {
                final String userName=mEtUserName.getText().toString();
                if(StringUtil.isEmptyOrNull(userName)){
                    ToastHelper.toast("请输入手机号");
                    return;
                }
                clickBtnLoginVerify(userName);
            }
                break;
//            case R.id.btn_login_register:
//                startActivity(new Intent(getApplication(), RegisterActivity.class));
//                break;
//            case R.id.btn_login_findpwd:
//                startActivity(new Intent(getApplication(), FindPwdActivity.class));
//                break;
            case R.id.btn_login_login:
                final String userName=mEtUserName.getText().toString();
                final String pwd=mEtPassWord.getText().toString();
                if(StringUtil.isEmptyOrNull(userName)){
                    ToastHelper.toast("请输入手机号");
                    return;
                }
                if(StringUtil.isEmptyOrNull(pwd)){
                    ToastHelper.toast("请输入验证码");
                    return;
                }
                clickBtnLogin(userName, pwd);
                break;
        }
    }

    private void clickBtnLogin(final String userName, final String pwd) {
        showProgressInfo("");
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Login);

        Map<String ,String> param=new HashMap<String, String>();
        param.put("mobile", userName);
        param.put("code", pwd);

        OkHttpUtils.postString().url(url).mediaType(AppConfigUse.sJsonType).content(new Gson().toJson(param)).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i, int errorCode, String msg) {
                dismissProgress();
                ToastHelper.toast(msg);
            }

            @Override
            public void onResponse(String s, int i) {
                dismissProgress();
                MyLog.i("xiaoccai", "response=" + s);
                SPUtil.setString(SPUtil.sLogin_User_Info, s);

//                ToastHelper.toast(s);

//                {
//                    "hr": {
//                              "id": 5,
//                            "created_on": "2018-07-12T13:13:08.715022",
//                            "mobile": "18960870526",
//                            "name": null,
//                            "nick_name": null,
//                            "password": null,
//                            "avatar": null,
//                            "type": "1",
//                            "gender": "0",
//                            "address": null,
//                            "province": null,
//                            "city": null,
//                            "region": null,
//                            "email": null,
//                            "birthday": null,
//                            "rating": null,
//                            "status": "1",
//                            "register_ip": null,
//                            "last_login_ip": null,
//                            "last_login_on": null,
//                            "last_login_device": "1",
//                            "tel": null,
//                            "img1": null,
//                            "img2": null,
//                            "img3": null,
//                            "id_card": null,
//                            "id_verified": "0",
//                            "company": 41
//                },
//                    "company": "N",
//                        "token": "d844c4676538a20b9e30d755cedcb045"
//                }

            }
        });

    }

    private void clickBtnLoginVerify(final String userName ) {
        showProgressInfo("");
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_LoginVerify);

        Map<String ,String> param=new HashMap<String, String>();
        param.put("mobile", userName);

        OkHttpUtils.postString().url(url).mediaType(AppConfigUse.sJsonType).content(new Gson().toJson(param)).build().execute(new StringCallback() {
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

//        OkHttpUtils.post().url(url).params(param).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int i) {
//                dismissProgress();
//                ToastHelper.toast("error");
//
//            }
//
//            @Override
//            public void onResponse(String response, int i) {
//                dismissProgress();
//                ToastHelper.toast("response");
//                MyLog.i("xiaocai", "response=" + response);
//
//            }
//        });


    }

}
