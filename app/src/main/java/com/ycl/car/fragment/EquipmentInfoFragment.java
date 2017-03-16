package com.ycl.car.fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ycl.car.ContantValue;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.MaintainAdapter;
import com.ycl.car.contract.MaintainContract;
import com.ycl.car.model.Maintain;
import com.ycl.car.presenter.MaintainPresenter;
import com.ycl.car.utils.FileUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * 设备资料
 * Created by y11621546 on 2017/2/14.
 */

public class EquipmentInfoFragment extends BasePageFragment implements MaintainContract.View {
    private static final String TAG = "EquipmentFragment";
    @BindView(R.id.spinner_area)
    EditText spinnerArea;
    @BindView(R.id.spinner_level)
    EditText spinnerLevel;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.rv_equiment)
    RecyclerView rvEquiment;
    private String hint1, hint2;
    private List<Maintain> maintainList;
    private MaintainPresenter presenter;
    private KProgressHUD kProgressHUD;
    private RxPermissions rxPermissions;
    private Bundle bundle;

    public static EquipmentInfoFragment newInstance(Bundle bundle) {

        EquipmentInfoFragment fragment = new EquipmentInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MaintainPresenter(this);
        rxPermissions = new RxPermissions(getActivity());
        bundle = getArguments();
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void fetchData() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);
        ButterKnife.bind(this, view);
        rvEquiment.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEquiment.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hint1 = spinnerArea.getText().toString().trim();
                hint2 = spinnerLevel.getText().toString().trim();
                presenter.queryData(0, hint1, hint2);
            }
        });
        presenter.queryData(0, bundle.getString("eqno"), null);
        llTop.setVisibility(View.GONE);
        rvEquiment.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String fileName = maintainList.get(position).getDname() + "." + maintainList.get(position).getDext();
                final String fileType = maintainList.get(position).getDext();
                final String fileUrl = ContantValue.BASE_URL + maintainList.get(position).getDfileurl();
                final File file = new File(Environment.getExternalStorageDirectory() + "/" + fileName);
                Log.d(TAG, Environment.getExternalStorageDirectory() + "/" + fileName);
                rxPermissions
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    if (file.exists()) {
                                        Log.d(TAG, "文件存在");
                                        startActivity(FileUtil.getFileIntent(file, fileType));
                                    } else {
                                        Log.d(TAG, "不存在");
                                        FileUtil.downloadFile(getContext(), fileUrl, fileName, fileType);
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "请开启存储权限", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

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
    public void onSuccess(List<Maintain> maintainList) {
        this.maintainList = maintainList;
        MaintainAdapter adapter = new MaintainAdapter(maintainList);
        rvEquiment.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MaintainContract.Presenter presenter) {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        setTitle(bundle.getString("title"));
        setTitleRight("");
    }
}
