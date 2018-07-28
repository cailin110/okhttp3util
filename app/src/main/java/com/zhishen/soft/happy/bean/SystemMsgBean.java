package com.zhishen.soft.happy.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SystemMsgBean implements Serializable{

    public int id;
    public int type;
    public String title;
    @SerializedName("createTime")
    public String time;
    public String content;
    public String order;


}
