package com.zhishen.soft.happy.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.common.http.okhttp.OkHttpUtils;
import com.common.http.okhttp.callback.StringCallback;
import com.zhishen.soft.happy.bean.SystemMsgBean;
import com.zhishen.soft.happy.bean.UserInfo;
import com.zhishen.soft.happy.jni.BaseFragment;
import com.zhishen.soft.happy.util.AppConfigUse;
import com.zhishen.soft.happy.util.LoadDataType;
import com.zhishen.soft.happy.util.MyLog;
import com.zhishen.soft.happy.util.SPUtil;
import com.zhishen.soft.happy.util.ToastHelper;
import com.zhishen.soft.happy.widget.pulltorefresh.PullToRefreshBase;
import com.zhishen.soft.happy.widget.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class TabManagerQuanzhiFragment extends BaseFragment implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2 {

    private PullToRefreshListView mPRLVshow;
    private ListView mLVShow;
    private LoadDataType mLoadDataType = LoadDataType.FirstLoad;

    private MessageListAdapter mListAdapter;
    private final List<SystemMsgBean> mListData=new ArrayList<SystemMsgBean>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_tab_manager_quanzhi, container, false);

        mPRLVshow = (PullToRefreshListView) view.findViewById(R.id.plv_common_pulltofresh);
        mPRLVshow.setOnRefreshListener(this);
//        mPRLVshow.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.common_empty_show, null));
        mLVShow = mPRLVshow.getRefreshableView();
        mLoadDataType = LoadDataType.FirstLoad;

        mLVShow.setAdapter(new MessageListAdapter());

        getApply();

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

    public class MessageListAdapter extends BaseAdapter implements View.OnClickListener {

        public MessageListAdapter() {
        }

        @Override
        public int getCount() {
            return mListData!=null? mListData.size(): 0;
        }

        @Override
        public SystemMsgBean getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            final ViewHolderHair holder;
            if (view == null) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main_tab_msg_list_item, parent, false);
                holder = new ViewHolderHair();
                holder.mTvTitle = (TextView) view.findViewById(R.id.tv_my_message_title);
                holder.mTvTime = (TextView) view.findViewById(R.id.tv_my_message_time);
                holder.mTvInfo = (TextView) view.findViewById(R.id.tv_my_message_info);
                holder.mAreaItem = view.findViewById(R.id.area_my_message_item);

                view.setTag(holder);
            } else {
                holder = (ViewHolderHair) view.getTag();
            }

            SystemMsgBean bean=getItem(position);
            holder.mTvTime.setText(bean.time);
            holder.mTvTitle.setText(bean.title);
            holder.mTvInfo.setText(bean.content);
            holder.mAreaItem.setTag(bean);
            holder.mAreaItem.setOnClickListener(this);

            return view;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.area_my_message_item:
//                    SystemMsgBean bean= (SystemMsgBean) view.getTag();
//                    Intent intent=new Intent(getActivity(), ShowSystemMessageBeanActivity.class);
//                    Bundle args=new Bundle();
//                    args.putSerializable(BundleKey.BUNDLE_KEY_SERIALIZABLE_DATA, bean);
//                    intent.putExtras(args);
//                    startActivity(intent);
                    break;
            }
        }

        class ViewHolderHair {
            View mAreaItem;
            TextView mTvTitle;
            TextView mTvTime;
            TextView mTvInfo;
        }

    }

    private void getApply() {
        String url=String.format("%s%s", AppConfigUse.sCommonUrl, AppConfigUse.Api_Apply);

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


}
