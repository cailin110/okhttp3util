package com.zhishen.soft.happy.main.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhishen.soft.happy.main.R;

public class CommonHeaderFragment extends Fragment implements View.OnClickListener {
    private TextView mTVTitle;
    private LinearLayout mAreaContent;
    private ImageView mAreaBack;
    private View.OnClickListener mBackListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.common_header_frg, null);
        mTVTitle = (TextView) v.findViewById(R.id.tv_common_header_title);
        mAreaContent = (LinearLayout) v.findViewById(R.id.area_head_content);
        mAreaBack = (ImageView) v.findViewById(R.id.btn_common_back);
        mAreaBack.setOnClickListener(this);

        return v;
    }

    public View getBackBtn() {
        return mAreaBack;
    }

    public void setOnBackListener(View.OnClickListener listener) {
        mBackListener = listener;
    }

    public void setTitleInfo(String info) {
        if (mTVTitle != null) {
            mTVTitle.setText(info);
        }
    }

    public void setHeadColor(int colorId){
        mAreaContent.setBackgroundColor(colorId);
    }
    public void setBackImage(int id){
        mAreaBack.setImageResource(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_common_back:
                if (mBackListener != null) {
                    mBackListener.onClick(v);
                } else {
                    clickBtnBack();
                }
                break;

            default:
                break;
        }
    }

    private void clickBtnBack() {
        if (getActivity() == null) {
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        int number = manager.getBackStackEntryCount();
        if (number > 0) {
            getActivity().onBackPressed();
        } else {
            getActivity().finish();
        }
    }


    public void setHeadContentVisible(boolean show) {
        if (mAreaContent!=null){
            if (show){
                mAreaContent.setVisibility(View.VISIBLE);
            }else{
                mAreaContent.setVisibility(View.GONE);
            }
        }
    }

    public LinearLayout getAreaContent() {
        return mAreaContent;
    }


}
