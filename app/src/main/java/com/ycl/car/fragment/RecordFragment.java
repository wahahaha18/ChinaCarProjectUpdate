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
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.R;
import com.ycl.car.adapter.RecordAdapter;
import com.ycl.car.contract.PMRecordContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.Record;
import com.ycl.car.presenter.PmRecordPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 记录
 * Created by y11621546 on 2017/2/15.
 */

public class RecordFragment extends BasePageFragment implements PMRecordContract.View {
    @BindView(R.id.rv_alarm_real)
    RecyclerView rvAlarmReal;

    public static RecordFragment newInstance(Bundle bundle) {

        RecordFragment fragment = new RecordFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private PmRecordPresenter presenter;
    private KProgressHUD kProgressHUD;
    private Bundle bundle;
    private LoginResponse.BBean userInfo;

    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        presenter = new PmRecordPresenter(this);
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", ""), LoginResponse.BBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        setTitle("记录");
        setTitleRight("");
        presenter.initData(String.valueOf(userInfo.getId()), bundle.getString("pmid"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        ButterKnife.bind(this, view);
        rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        return view;
    }


    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getContext())
                .setLabel("正在加载...")
                .show();
    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onSuccess(List<Record> tpmCheckList) {
        RecordAdapter adapter = new RecordAdapter(tpmCheckList);
        rvAlarmReal.setAdapter(adapter);
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PMRecordContract.Presenter presenter) {

    }
}
