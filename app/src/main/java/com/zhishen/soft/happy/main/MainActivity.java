package com.zhishen.soft.happy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.zhishen.soft.happy.jni.BaseActivity;
import com.zhishen.soft.happy.util.ToastHelper;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int TAG_FRGMENT_SIZE = 4;
    private boolean isShowExitTip = false;

    private TextView mBtnView[];
    private int mSelectPosition = -1;
    private BaseTabFragment mCurrentShowFrg;


    @Override
    protected void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mSelectPosition = -1;
        mBtnView = new TextView[TAG_FRGMENT_SIZE];
        for (int i = 0; i < mBtnView.length; i++) {
            mBtnView[i] = (TextView) findViewById(R.id.tv_btn1 + i);
            mBtnView[i].setOnClickListener(this);
        }
        initDataSelectPosition(0);

    }

    @Override
    protected void initViews() {

    }



    public void initDataSelectPosition(int position) {
        if (mSelectPosition == position) {
            return;
        } else {
            mSelectPosition = position;
        }
        initFrgsAndShow();
        resetSelectBtnBg();
    }

    private void initFrgsAndShow() {
        if (mSelectPosition < 0 || mSelectPosition > TAG_FRGMENT_SIZE) {
            throw new IllegalStateException("position error");
        }
        switch (mSelectPosition) {
            case 0:
                mCurrentShowFrg = new TabFirstFragment();
                break;
            case 1:
                mCurrentShowFrg = new TabManagerFragment();
                break;
            case 2:
                mCurrentShowFrg = new TabMessageFragment();
                break;
            case 3:
                mCurrentShowFrg = new TabMineFragment();
                break;
            default:
                mCurrentShowFrg = new TabFirstFragment();
                break;
        }
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
//        mTransaction.setCustomAnimations(R.anim.slide_in_from_left, 0);
        mTransaction.replace(R.id.area_show_content, mCurrentShowFrg);
        mTransaction.commit();
    }


    private void resetSelectBtnBg() {
        for (int i = 0; i < mBtnView.length; i++) {
            if (mSelectPosition == i) {
                mBtnView[i].setTextColor(getResources().getColor(R.color.tab_color_select));
                switch (i) {
                    case 0:
                        mBtnView[i].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_first_pressed, 0, 0);
                        break;
                    case 1:
                        mBtnView[i].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_manage_pressed, 0, 0);
                        break;
                    case 2:
                        mBtnView[i].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_msg_pressed, 0, 0);
                        break;
                    case 3:
                        mBtnView[i].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_my_pressed, 0, 0);
                        break;
                }
            } else {
                mBtnView[i].setTextColor(getResources().getColor(R.color.tab_color_default));
                switch (i) {
                    case 0:
                        mBtnView[i].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_main_center_first_selector, 0, 0);
                        break;
                    case 1:
                        mBtnView[i].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_main_center_two_selector, 0, 0);
                        break;
                    case 2:
                        mBtnView[i].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_main_center_three_selector, 0, 0);
                        break;
                    case 3:
                        mBtnView[i].setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_main_center_four_selector, 0, 0);
                        break;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_btn1:
                initDataSelectPosition(0);
                break;
            case R.id.tv_btn2:
                initDataSelectPosition(1);
                break;
            case R.id.tv_btn3:
                initDataSelectPosition(2);
                break;
            case R.id.tv_btn4:
                initDataSelectPosition(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (!isShowExitTip) {
            isShowExitTip = true;
            ToastHelper.toast(this, R.string.app_exit_tip);
            mBtnView[0].postDelayed(new Runnable() {
                @Override
                public void run() {
                    isShowExitTip = false;
                }
            }, 2000);
            return;
        }
        super.onBackPressed();
        finish();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


}
