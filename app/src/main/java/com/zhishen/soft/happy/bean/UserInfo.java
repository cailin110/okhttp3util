package com.zhishen.soft.happy.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cailin on 2018/7/12.
 */
public class UserInfo implements Serializable {
    public String company;
    public String token;
    @SerializedName("hr")
    public UserInfoBean mUserInfoBean;
}
