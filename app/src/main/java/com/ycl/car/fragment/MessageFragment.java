package com.ycl.car.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.MyApp;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.MessageAdapter;
import com.ycl.car.adapter.MessageAdapter1;
import com.ycl.car.contract.MessageContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.Msg;
import com.ycl.car.model.Msg1;
import com.ycl.car.presenter.MessagePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息通知
 * Created by y11621546 on 2017/1/18.
 */

public class MessageFragment extends BasePageFragment implements MessageContract.View {
    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    private MessagePresenter presenter;
    private LoginResponse.BBean userInfo;
    private SharedPreferences sharedPreferences;
    private Bundle bundle;

    public static MessageFragment newInstance(Bundle bundle) {

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        bundle = getArguments();
        userInfo = JSON.parseObject(sharedPreferences.getString("user", "{}"), LoginResponse.BBean.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mssage, container, false);
        ButterKnife.bind(this, view);
        rvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMessage.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        presenter = new MessagePresenter(this);
        if (bundle.getInt("type", 0) == 1) {
            presenter.initMessage_Message(String.valueOf(userInfo.getId()));
        } else if (bundle.getInt("type") == 2) {
            presenter.initMessage_notification(String.valueOf(userInfo.getId()));
        } else {
            presenter.start();
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("消息通知");
        setTitleRight("全部已读");
    }

    @Override
    public void fetchData() {
        System.out.println("MessageFragment.fetchData");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onPushSuccess(final List<Msg> msgList) {
        MessageAdapter adapter = new MessageAdapter(msgList);
        rvMessage.setAdapter(adapter);
        rvMessage.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.getPushDetailInfo(String.valueOf(userInfo.getId()), String.valueOf(msgList.get(position).getId()));
            }
        });
    }

    @Override
    public void onMsgSuccess(List<Msg1> msgList) {
        MessageAdapter1 adapter = new MessageAdapter1(msgList);
        rvMessage.setAdapter(adapter);
        rvMessage.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


    @Override
    public void onError() {

    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {

    }
}
