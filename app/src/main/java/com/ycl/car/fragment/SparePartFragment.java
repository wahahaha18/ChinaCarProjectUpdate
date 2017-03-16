package com.ycl.car.fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.ContantValue;
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

/**
 * 备件
 * Created by y11621546 on 2017/2/14.
 */

public class SparePartFragment extends BasePageFragment implements MaintainContract.View {
    private static final String TAG = "SparePartFragment";
    @BindView(R.id.spinner_area)
    EditText spinnerArea;
    @BindView(R.id.spinner_level)
    EditText spinnerLevel;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.rv_equiment)
    RecyclerView rvEquiment;
    private String hint1, hint2;
    private List<Maintain> maintainList;
    private MaintainPresenter presenter;
    private KProgressHUD kProgressHUD;

    public static SparePartFragment newInstance() {

        Bundle args = new Bundle();
        SparePartFragment fragment = new SparePartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MaintainPresenter(this);
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void fetchData() {
        Log.d(TAG, "fetchData() called");
        presenter.initData(1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spare_part, container, false);
        ButterKnife.bind(this, view);
        rvEquiment.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEquiment.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hint1 = spinnerArea.getText().toString().trim();
                hint2 = spinnerLevel.getText().toString().trim();
                presenter.queryData(1, hint1, hint2);
            }
        });
        rvEquiment.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String fileName = maintainList.get(position).getDname() + "." + maintainList.get(position).getDext();
                String fileType = maintainList.get(position).getDext();
                String fileUrl = ContantValue.BASE_URL + maintainList.get(position).getDfileurl();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + fileName);
                Log.d(TAG, Environment.getExternalStorageDirectory() + "/" + fileName);
                if (file.exists()) {
                    Log.d(TAG, "文件存在");
                    try {
                        startActivity(FileUtil.getFileIntent(file, fileType));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.d(TAG, "不存在");
                    FileUtil.downloadFile(getContext(), fileUrl, fileName, fileType);
                }


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

    }
}
