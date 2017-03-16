package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.MaintainPlanAdapter;
import com.ycl.car.adapter.PMAdapter;
import com.ycl.car.contract.MaintainPlanContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.MaintainPlan;
import com.ycl.car.presenter.MaintainPlanPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PM 可行性维修计划
 * Created by y11621546 on 2017/2/15.
 */

public class MaintainPlanFragment extends BasePageFragment implements MaintainPlanContract.View {
    @BindView(R.id.rv_alarm_real)
    RecyclerView rvAlarmReal;
    private KProgressHUD kProgressHUD;
    private MaintainPlanPresenter planPresenter;
    private LoginResponse.BBean userInfo;
    private List<MaintainPlan> maintainPlanList;
    private String uid, eqno;
    private int type;

    public static MaintainPlanFragment newInstance(String uid, String eqno, int type) {
        MaintainPlanFragment fragment = new MaintainPlanFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("eqno", eqno);
        bundle.putString("uid", uid);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void fetchData() {
        if (type == 1) {
            planPresenter.initToDoList(String.valueOf(userInfo.getId()));
        } else {
            planPresenter.initData(String.valueOf(userInfo.getId()), eqno);
        }
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type", 0);
        eqno = getArguments().getString("eqno", null);
        uid = getArguments().getString("uid", null);
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", ""), LoginResponse.BBean.class);
        planPresenter = new MaintainPlanPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maintain_plan, container, false);
        ButterKnife.bind(this, view);
        rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvAlarmReal.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if (type == 1) {
                Bundle bundle = new Bundle();
                switch (view.getId()) {
                    case R.id.tv_note:
                        bundle.putString("pmid", String.valueOf(maintainPlanList.get(position).getPmid()));
                        ((Next1Activity) getActivity()).addFragment(PMRecordFragment.newInstance(bundle), true);
                        break;
                    case R.id.tv_deal:
                        bundle.putString("date", maintainPlanList.get(position).getPlandate());
                        bundle.putString("name", maintainPlanList.get(position).getEqname());
                        bundle.putString("content", maintainPlanList.get(position).getPmcontent());
                        bundle.putString("flag", "pm");
                        bundle.putString("id", String.valueOf(maintainPlanList.get(position).getPmid()));
                        ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
                        break;
                }


            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("PM预防性维修计划");
        setTitleRight("");


    }

    @Override
    public void showLoading() {
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(getContext())
                    .setLabel("正在加载...")
                    .show();
        }

    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onSuccess(List<MaintainPlan> maintainPlanList) {
        this.maintainPlanList = maintainPlanList;
        MaintainPlanAdapter adapter = new MaintainPlanAdapter(maintainPlanList);
        rvAlarmReal.setAdapter(adapter);
    }

    @Override
    public void onToDoSuccess(List<MaintainPlan> pmList) {
        this.maintainPlanList = pmList;
        PMAdapter adapter = new PMAdapter(pmList);
        rvAlarmReal.setAdapter(adapter);
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MaintainPlanContract.Presenter presenter) {

    }
}
