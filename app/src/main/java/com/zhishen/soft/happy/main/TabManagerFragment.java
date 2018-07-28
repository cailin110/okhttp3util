package com.zhishen.soft.happy.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabManagerFragment extends BaseTabFragment implements View.OnClickListener {

    private TextView mTvQuanzhi;
    private TextView mTvJianzhi;
    private View mQuanzhiDivider;
    private View mJianzhiDivider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_tab_manager, container, false);

        view.findViewById(R.id.btn_tab_manager_title_quanzhi).setOnClickListener(this);
        view.findViewById(R.id.btn_tab_manager_title_jianzhi).setOnClickListener(this);
        mTvQuanzhi=view.findViewById(R.id.tv_tab_manager_title_quanzhi);
        mTvJianzhi=view.findViewById(R.id.tv_tab_manager_title_jianzhi);
        mQuanzhiDivider=view.findViewById(R.id.tv_tab_manager_title_quanzhi_divider);
        mJianzhiDivider=view.findViewById(R.id.tv_tab_manager_title_jianzhi_divider);

        showFirstTab();

        return view;
    }

    private void setStatusSelect(boolean b, TextView tv, View divider){
        if (b){
            tv.setTextColor(getResources().getColor(R.color.bg_common_green_color));
            divider.setVisibility(View.VISIBLE);
        }else{
            tv.setTextColor(getResources().getColor(R.color.bg_common_black));
            divider.setVisibility(View.INVISIBLE);
        }
    }

    private void showFirstTab(){
        setStatusSelect(true, mTvQuanzhi, mQuanzhiDivider);
        setStatusSelect(false, mTvJianzhi, mJianzhiDivider);
        FragmentTransaction mTransaction = getChildFragmentManager().beginTransaction();
        mTransaction.replace(R.id.area_tab_manager_show, new TabManagerQuanzhiFragment());
        mTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tab_manager_title_quanzhi: {
                showFirstTab();
            }
                break;
            case R.id.btn_tab_manager_title_jianzhi:{
                setStatusSelect(false, mTvQuanzhi, mQuanzhiDivider);
                setStatusSelect(true, mTvJianzhi, mJianzhiDivider);
                FragmentTransaction mTransaction = getChildFragmentManager().beginTransaction();
                mTransaction.replace(R.id.area_tab_manager_show, new TabManagerJianzhiFragment());
                mTransaction.commit();
            }
                break;
        }
    }



}
