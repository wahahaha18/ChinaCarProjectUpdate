package com.ycl.car.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.R;
import com.ycl.car.adapter.EqHandleAdapter;
import com.ycl.car.contract.EqHandleContract;
import com.ycl.car.model.Repair;
import com.ycl.car.presenter.EqHandlePresenter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备维修--已处理
 * Created by y11621546 on 2017/3/4.
 */

public class EqHandleFragment extends BasePageFragment implements EqHandleContract.View {
    @BindView(R.id.spinner_start_time)
    TextView spinnerStartTime;
    @BindView(R.id.spinner_end_time)
    TextView spinnerEndTime;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.rv_equiment)
    RecyclerView rvEquiment;

    private EqHandlePresenter presenter;
    private String uid, eqno, status;
    private KProgressHUD kProgressHUD;
    private String startTime, endTime;
    private List<Repair> repairList;
    private EqHandleAdapter adapter;

    public static EqHandleFragment newInstance(String uid, String eqno, String status) {

        Bundle args = new Bundle();
        args.putString("uid", uid);
        args.putString("eqno", eqno);
        args.putString("status", status);
        EqHandleFragment fragment = new EqHandleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void fetchData() {
        presenter.getHandleList(uid, eqno, status);
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EqHandlePresenter(this);
        uid = getArguments().getString("uid");
        eqno = getArguments().getString("eqno", null);
        status = getArguments().getString("status");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eq_handle, container, false);
        ButterKnife.bind(this, view);
        rvEquiment.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEquiment.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvEquiment.setHasFixedSize(true);
        if ("0".equals(status)) {
            llTop.setVisibility(View.GONE);
        } else {
            llTop.setVisibility(View.VISIBLE);
        }
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
                    if (startTime.compareTo(endTime) < 0) {
                        presenter.queryHandleList(uid, null, status, startTime, endTime);
                    } else {
                        Toast.makeText(getActivity(), "开始时间应小于结束时间", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "请选择时间", Toast.LENGTH_SHORT).show();
                }

            }
        });
        spinnerStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                startTime = year + "-" + monthOfYear + "-" + dayOfMonth;
                                spinnerStartTime.setText(startTime);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();//显示日期设置对话框
            }
        });
        spinnerEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                endTime = year + "-" + monthOfYear + "-" + dayOfMonth;
                                spinnerEndTime.setText(endTime);

                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();//显示日期设置对话框
            }

        });

        return view;
    }

    @Override
    public void setPresenter(EqHandleContract.Presenter presenter) {

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
        if (kProgressHUD != null && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }

    }

    @Override
    public void onSuccess(List<Repair> repairList) {
        adapter = new EqHandleAdapter(repairList);
        rvEquiment.setAdapter(adapter);


    }


    @Override
    public void onQuerySuccess(List<Repair> repairList) {
        adapter = new EqHandleAdapter(repairList);
        rvEquiment.setAdapter(adapter);
        adapter.setEmptyView(R.layout.layout_item_empty, (ViewGroup) rvEquiment.getParent());
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败，请稍候重试", Toast.LENGTH_SHORT).show();
    }
}
