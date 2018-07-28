package com.zhishen.soft.happy.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabMessageFragment extends BaseTabFragment implements View.OnClickListener {

    private TextView mTvGoutong;
    private TextView mTvHudong;
    private View mGoutongDivider;
    private View mHudongDivider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_tab_message, container, false);

        view.findViewById(R.id.btn_tab_message_title_goutong).setOnClickListener(this);
        view.findViewById(R.id.btn_tab_message_title_hudong).setOnClickListener(this);
        mTvGoutong =view.findViewById(R.id.tv_tab_message_title_goutong);
        mTvHudong =view.findViewById(R.id.tv_tab_message_title_hudong);
        mGoutongDivider =view.findViewById(R.id.tv_tab_message_title_goutong_divider);
        mHudongDivider =view.findViewById(R.id.tv_tab_message_title_hudong_divider);

        showSelectFirst();

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tab_message_title_goutong: {
                showSelectFirst();
            }
            break;
            case R.id.btn_tab_message_title_hudong:{
                setStatusSelect(false, mTvGoutong, mGoutongDivider);
                setStatusSelect(true, mTvHudong, mHudongDivider);
                FragmentTransaction mTransaction = getChildFragmentManager().beginTransaction();
                mTransaction.replace(R.id.area_tab_message_show, new TabMessageHudongFragment());
                mTransaction.commit();
            }
            break;
        }

    }

    private void showSelectFirst() {
        setStatusSelect(true, mTvGoutong, mGoutongDivider);
        setStatusSelect(false, mTvHudong, mHudongDivider);
        FragmentTransaction mTransaction = getChildFragmentManager().beginTransaction();
        mTransaction.replace(R.id.area_tab_message_show, new TabMessageGoutongFragment());
        mTransaction.commit();
    }

}
