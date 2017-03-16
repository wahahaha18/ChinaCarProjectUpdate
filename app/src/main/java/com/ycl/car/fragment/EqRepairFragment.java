package com.ycl.car.fragment;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.EqRepairAdapter;
import com.ycl.car.contract.EqRepairContract;
import com.ycl.car.model.EqMainInfo;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.presenter.EqRepairPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 维修信息--设备详情
 * Created by y11621546 on 2017/2/16.
 */

public class EqRepairFragment extends BasePageFragment implements EqRepairContract.View, View.OnClickListener {
    @BindView(R.id.btn_deal_no)
    Button btnDealNo;
    @BindView(R.id.btn_deal)
    Button btnDeal;
    @BindView(R.id.rv_repair)
    RecyclerView rvRepair;
    private Bundle bundle;
    private KProgressHUD kProgressHUD;
    private EqRepairPresenter presenter;
    private LoginResponse.BBean userInfo;
    private BroadcastReceiver receiver;
    private List<EqMainInfo> eqMainInfoList;

    public static EqRepairFragment newInstance(Bundle bundle) {

        EqRepairFragment fragment = new EqRepairFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eq_repair, container, false);
        ButterKnife.bind(this, view);
        rvRepair.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRepair.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        btnDeal.setOnClickListener(this);
        btnDealNo.setOnClickListener(this);
        rvRepair.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("flag", "repair");
                bundle.putString("id", String.valueOf(eqMainInfoList.get(position).getId()));
                ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        presenter = new EqRepairPresenter(this);
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", ""), LoginResponse.BBean.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        setTitleRight("");
        setTitle(bundle.getString("title"));
        if (TextUtils.isEmpty(MyApp.getInstance().getState())) {
            selectView(0);
        } else {
            MyApp.getInstance().setState(null);
            selectView(1);
        }


    }


    @Override
    public void fetchData() {

    }

    @Override
    public void showLoading() {
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            kProgressHUD = KProgressHUD.create(getActivity())
                    .setLabel("正在加载...").show();
        }

    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onSuccess(List<EqMainInfo> eqMainInfoList) {
        this.eqMainInfoList = eqMainInfoList;
        EqRepairAdapter adapter = new EqRepairAdapter(eqMainInfoList);
        rvRepair.setAdapter(adapter);
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(EqRepairContract.Presenter presenter) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_deal:
                btnDeal.setSelected(true);
                btnDealNo.setSelected(false);
                selectView(1);
                break;
            case R.id.btn_deal_no:
                btnDeal.setSelected(false);
                btnDealNo.setSelected(true);
                selectView(0);
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    private void selectView(int index) {
        switch (index) {
            case 0:
                btnDeal.setSelected(false);
                btnDealNo.setSelected(true);
                presenter.initData(String.valueOf(0), String.valueOf(userInfo.getId()), bundle.getString("eqno"));
                break;
            case 1:
                btnDeal.setSelected(true);
                btnDealNo.setSelected(false);
                presenter.initData(String.valueOf(1), String.valueOf(userInfo.getId()), bundle.getString("eqno"));
                break;
        }
    }


}
