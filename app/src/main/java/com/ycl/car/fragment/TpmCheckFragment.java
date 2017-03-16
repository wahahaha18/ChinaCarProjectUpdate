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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.TpmAdapter;
import com.ycl.car.adapter.TpmCheckAdapter;
import com.ycl.car.contract.TpmCheckContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.TpmCheck;
import com.ycl.car.presenter.TpmCheckPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by y11621546 on 2017/2/15.
 */

public class TpmCheckFragment extends BasePageFragment implements TpmCheckContract.View {
    @BindView(R.id.rv_alarm_real)
    RecyclerView rvAlarmReal;
    private String uid, eqno;
    private int type;

    public static TpmCheckFragment newInstance(String uid, String eqno, int type) {

        TpmCheckFragment fragment = new TpmCheckFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("uid", uid);
        bundle.putString("eqno", eqno);
        fragment.setArguments(bundle);
        return fragment;
    }
    private KProgressHUD kProgressHUD;
    private TpmCheckPresenter presenter;
    private LoginResponse.BBean userInfo;
    private List<TpmCheck> tpmCheckList;


    @Override
    public void fetchData() {
        if (type == 1) {
            presenter.initTPMAToDoList(String.valueOf(userInfo.getId()));
        } else {
            presenter.initData(String.valueOf(userInfo.getId()), eqno);
        }
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = getArguments().getString("uid", null);
        eqno = getArguments().getString("eqno", null);
        type = getArguments().getInt("type", 0);

        presenter = new TpmCheckPresenter(this);
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", ""), LoginResponse.BBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitleRight("");
        setTitle("TPM点检");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tpm_check, container, false);
        ButterKnife.bind(this, view);
        rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvAlarmReal.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("id", String.valueOf(tpmCheckList.get(position).getTpmid()));
//                ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
            }
        });
        rvAlarmReal.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                Bundle bundle = new Bundle();
                switch (view.getId()) {
                    case R.id.tv_note:
                        bundle.putString("pmid", String.valueOf(tpmCheckList.get(position).getTpmid()));
                        ((Next1Activity) getActivity()).addFragment(TPMRecordFragment.newInstance(bundle), true);
                        break;
                    case R.id.tv_deal:
                        bundle.putString("date", tpmCheckList.get(position).getPlandate());
                        bundle.putString("content", tpmCheckList.get(position).getWorkinfo());
                        bundle.putString("name", tpmCheckList.get(position).getSystemname());
                        bundle.putString("flag", "tpm");
                        bundle.putString("id", String.valueOf(tpmCheckList.get(position).getTpmid()));
                        ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
                        break;
                }
            }
        });

        return view;
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
    public void onSuccess(List<TpmCheck> tpmCheckList) {
        this.tpmCheckList = tpmCheckList;
        TpmCheckAdapter adapter = new TpmCheckAdapter(tpmCheckList);
        rvAlarmReal.setAdapter(adapter);
    }

    @Override
    public void onToDoSuccess(List<TpmCheck> tpmList) {
        this.tpmCheckList = tpmList;
        TpmAdapter adapter = new TpmAdapter(tpmList);
        rvAlarmReal.setAdapter(adapter);
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(TpmCheckContract.Presenter presenter) {

    }
}
