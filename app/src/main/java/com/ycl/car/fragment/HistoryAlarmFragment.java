package com.ycl.car.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.R;
import com.ycl.car.adapter.AlarmRealTimeAdapter;
import com.ycl.car.contract.AlarmRealTimeContract;
import com.ycl.car.model.AlarmRealTime;
import com.ycl.car.model.C;
import com.ycl.car.model.D;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.presenter.AlarmRealTimePresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 历史报警
 * Created by y11621546 on 2017/2/13.
 */

public class HistoryAlarmFragment extends BasePageFragment implements AlarmRealTimeContract.View {
    @BindView(R.id.spinner_area)
    Spinner spinnerArea;
    @BindView(R.id.spinner_level)
    Spinner spinnerLevel;
    @BindView(R.id.spinner_start_time)
    TextView spinnerStartTime;
    @BindView(R.id.spinner_end_time)
    TextView spinnerEndTime;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.rv_alarm_real)
    RecyclerView rvAlarmReal;
    private AlarmRealTimePresenter presenter;
    private KProgressHUD kProgressHUD;
    private Bundle bundle;
    private LoginResponse.BBean userInfo;
    private int level_id, zone_id;
    private String startTime, endTime;

    public static HistoryAlarmFragment newInstance(Bundle bundle) {
        HistoryAlarmFragment fragment = new HistoryAlarmFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AlarmRealTimePresenter(this);
        bundle = getArguments();
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", "{}"), LoginResponse.BBean.class);
    }

    @Override
    public void fetchData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_history, container, false);
        ButterKnife.bind(this, view);
        rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
                    if (startTime.compareTo(endTime) < 0) {
                        presenter.queryHistory(String.valueOf(userInfo.getId()), String.valueOf(level_id), String.valueOf(zone_id), startTime, endTime);
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
    public void onResume() {
        super.onResume();
        presenter.getHistory(String.valueOf(userInfo.getId()));
        if (bundle == null) return;
        setTitleRight("");
        setTitle(bundle.getString("title"));
    }

    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getContext())
                .setLabel("正在加载...")
                .setCancellable(false)
                .show();
    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onSuccess(List<AlarmRealTime> alarmRealTimeList) {
        final AlarmRealTimeAdapter alarmRealTimeAdapter = new AlarmRealTimeAdapter(alarmRealTimeList);
        rvAlarmReal.setAdapter(alarmRealTimeAdapter);
        rvAlarmReal.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initSpinnerList(List<C> cList, List<D> dList) {
        List<String> cStringList = new ArrayList<>();
        List<String> dStringList = new ArrayList<>();
        final List<Integer> cIntList = new ArrayList<>();
        final List<Integer> dIntList = new ArrayList<>();

        for (C c : cList) {
            cStringList.add(c.getLname());
            cIntList.add(c.getId());
        }
        for (D d : dList) {
            dStringList.add(d.getZname());
            dIntList.add(d.getId());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, cStringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(arrayAdapter);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, dStringList);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(arrayAdapter1);
        if (cIntList.isEmpty()) return;
        if (dIntList.isEmpty()) return;
        level_id = cIntList.get(spinnerArea.getSelectedItemPosition());
        zone_id = dIntList.get(spinnerLevel.getSelectedItemPosition());
        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                zone_id = dIntList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level_id = cIntList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }




    @Override
    public void onError() {

    }

    @Override
    public void setPresenter(AlarmRealTimeContract.Presenter presenter) {

    }
}
