package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.PartLibraryAdapter;
import com.ycl.car.contract.PartLibraryContract;
import com.ycl.car.model.PartLibrary;
import com.ycl.car.presenter.PartLibraryPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 实时报警
 * Created by y11621546 on 2017/2/13.
 */

public class SparePartEqFragment extends BasePageFragment implements PartLibraryContract.View {
    private static final String TAG = "SparePartLibraryFragment";
    @BindView(R.id.et_no)
    EditText etNo;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.rv_alarm_real)
    RecyclerView rvAlarmReal;
    private KProgressHUD kProgressHUD;

    private Bundle bundle;
    private PartLibraryPresenter presenter;

    public static SparePartEqFragment newInstance(Bundle bundle) {
        SparePartEqFragment fragment = new SparePartEqFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PartLibraryPresenter(this);

        bundle = getArguments();
    }

    @Override
    public void fetchData() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spare_library, container, false);
        ButterKnife.bind(this, view);
        rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hint1 = etNo.getText().toString();
                String hint2 = etName.getText().toString();
                presenter.queryData(hint1, hint2);
            }
        });
        llTop.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        setTitleRight("");
        setTitle(bundle.getString("title"));
        presenter.initEqInfo(bundle.getString("eqno"));
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
    public void onSuccess(List<PartLibrary> partLibraryList) {
        PartLibraryAdapter adapter = new PartLibraryAdapter(partLibraryList);
        rvAlarmReal.setAdapter(adapter);
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PartLibraryContract.Presenter presenter) {

    }
}
