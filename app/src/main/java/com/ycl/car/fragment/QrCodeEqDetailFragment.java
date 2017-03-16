package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.CateAdapter;
import com.ycl.car.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备维修-扫一扫
 * Created by y11621546 on 2017/2/16.
 */

public class QrCodeEqDetailFragment extends BasePageFragment {
    @BindView(R.id.tv_list)
    RecyclerView tvList;
    private String eqno = "";

    public static QrCodeEqDetailFragment newInstance(Bundle bundle) {

        QrCodeEqDetailFragment fragment = new QrCodeEqDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private String[] strings = new String[]{"设备详情", "设备资料", "智能备件", "维修信息", "PM预防性维修计划", "TPM点检"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qrcode_eq_detail, container, false);
        ButterKnife.bind(this, view);
        tvList.setLayoutManager(new LinearLayoutManager(getContext()));
        tvList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        List<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            LoginResponse.CBean.ChildsBeanXX.ChildsBeanX childsBeanX = new LoginResponse.CBean.ChildsBeanXX.ChildsBeanX();
            childsBeanX.setName_(strings[i]);
            list.add(childsBeanX);
        }
//        CateAdapter adapter = new CateAdapter(list);
        CateAdapter adapter = new CateAdapter(list);
        tvList.setAdapter(adapter);
        tvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        bundle.putString("title", "设备详情");
                        bundle.putString("eqno", eqno);
                        ((Next1Activity) getActivity()).addFragment(EqInfoFragment.newInstance(bundle), true);
                        break;
                    case 1:
                        bundle.putString("title", "设备资料");
                        bundle.putString("eqno", eqno);
                        ((Next1Activity) getActivity()).addFragment(EquipmentInfoFragment.newInstance(bundle), true);
                        break;
                    case 2:
                        bundle.putString("title", "智能备件");
                        bundle.putString("eqno", eqno);
                        ((Next1Activity) getActivity()).addFragment(SparePartEqFragment.newInstance(bundle), true);
                        break;
                    case 3:
                        bundle.putString("title", "维修信息");
                        bundle.putString("eqno", eqno);

                        ((Next1Activity) getActivity()).addFragment(EqRepairFragment.newInstance(bundle), true);

                        break;
                    case 4:
                        bundle.putString("title", "PM预防性维修计划");
                        bundle.putString("eqno", eqno);
                        ((Next1Activity) getActivity()).addFragment(PMFragment.newInstance(), true);
                        break;
                    case 5:
                        bundle.putString("title", "TPM点检");
                        bundle.putString("eqno", eqno);
                        ((Next1Activity) getActivity()).addFragment(TPMFragment.newInstance(), true);
                        break;
                }


            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        eqno = getArguments().getString("code", "");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        setTitleRight("");
        setTitle("设备信息");
    }
}
