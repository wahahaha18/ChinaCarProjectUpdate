package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.CateAdapter;
import com.ycl.car.contract.EqControlListContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.presenter.EqControlListPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 管理一级内页
 * Created by y11621546 on 2017/2/9.
 */

public class CategoryFragment extends BasePageFragment implements EqControlListContract.View {
    @BindView(R.id.rv_cate)
    RecyclerView rvCate;
    private EqControlListPresenter presenter;
    private static final String TITLE = "title";
    private CateAdapter adapter;
    private KProgressHUD kProgressHUD;
    ArrayList<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX> list;
    private Bundle bundle;
    private LoginResponse.BBean userInfo;

    public static CategoryFragment newInstance(Bundle bundle) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        presenter = new EqControlListPresenter(this);
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", "{}"), LoginResponse.BBean.class);
    }

    @Override
    public void fetchData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cate, container, false);
        ButterKnife.bind(this, view);
        rvCate.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCate.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        getToolbarRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                new IntentIntegrator(getActivity()).initiateScan();
////                Bundle bundle = new Bundle();
////                bundle.putInt("position", -1);
////                bundle.putString("code", "eq_01");
////                Next1Activity.start(getActivity(), bundle);
            }
        });
        rvCate.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("sen", list.get(position).getSen());
                bundle.putString("params", list.get(position).getParam());
//                Toast.makeText(getActivity(), list.get(position).getSen(), Toast.LENGTH_SHORT).show();
                bundle.putString("title", list.get(position).getName_());
                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.e("CategoryFragment", "进入了CategoryFragment");
        setTitle(bundle.getString("title"));
        if ("维修管理".equals(bundle.getString("title"))) {
            setTitleRight("扫一扫");
            list = bundle.getParcelableArrayList("child");
            adapter = new CateAdapter(list);
            rvCate.setAdapter(adapter);
        } else if ("设备管理".equals(bundle.getString("title"))) {
            presenter.getData("sb", String.valueOf(userInfo.getId()));
            setTitleRight("");
        } else {
            setTitleRight("");
            list = bundle.getParcelableArrayList("child");
            adapter = new CateAdapter(list);
            rvCate.setAdapter(adapter);
        }

    }


    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getActivity())
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
    public void onSuccess(ArrayList<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX> childsBeanXList) {
        this.list = childsBeanXList;
        adapter = new CateAdapter(childsBeanXList);
        rvCate.setAdapter(adapter);
    }


    public void onError() {

    }

    @Override
    public void setPresenter(EqControlListContract.Presenter presenter) {

    }
}
