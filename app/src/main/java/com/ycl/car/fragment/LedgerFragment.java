package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.R;
import com.ycl.car.adapter.LedgerAdapter;
import com.ycl.car.contract.LedgerContract;
import com.ycl.car.model.Ledger;
import com.ycl.car.presenter.LedgerPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class LedgerFragment extends BasePageFragment implements LedgerContract.View {
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.spinner3)
    Spinner spinner3;
    @BindView(R.id.tv_query)
    TextView tvQuery;
    @BindView(R.id.rv_alarm_real)
    RecyclerView rvAlarmReal;
    private Bundle bundle;
    private String[][] dNameItems;
    private int[] dIds;
    private int[] cIds;
    private int[][] dItemIds;
    private String[] dNames;
    private String[] cNames;
    private LedgerPresenter presenter;
    private String eqno, eqtype_id, eqwksp_id, eqsystem_id;
    private KProgressHUD kProgressHUD;


    public static LedgerFragment newInstance(Bundle bundle) {


        LedgerFragment fragment = new LedgerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        presenter = new LedgerPresenter(this);
        presenter.getLedgerList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ledger, container, false);
        ButterKnife.bind(this, view);
        rvAlarmReal.setHasFixedSize(true);
        rvAlarmReal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.queryLedgerList(null, eqtype_id, eqwksp_id, eqsystem_id);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        setTitle(bundle.getString("title"));
        setTitleRight("");
    }

    @Override
    public void setPresenter(LedgerContract.Presenter presenter) {

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
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败,请稍候重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Ledger ledger) {
        dNames = new String[ledger.getD().size()];
        cNames = new String[ledger.getC().size()];
        dIds = new int[ledger.getD().size()];
        cIds = new int[ledger.getC().size()];
        dItemIds = new int[ledger.getD().size()][];
        dNameItems = new String[ledger.getD().size()][];
        for (int i = 0; i < ledger.getC().size(); i++) {
            cNames[i] = ledger.getC().get(i).getTpname();
            cIds[i] = ledger.getC().get(i).getId();
        }

        for (int i = 0; i < ledger.getD().size(); i++) {
            dNames[i] = ledger.getD().get(i).getWname();
            dIds[i] = ledger.getD().get(i).getId();
            dNameItems[i] = new String[ledger.getD().get(i).getSys().size()];
            dItemIds[i] = new int[ledger.getD().get(i).getSys().size()];
            for (int i1 = 0; i1 < ledger.getD().get(i).getSys().size(); i1++) {
                dNameItems[i][i1] = ledger.getD().get(i).getSys().get(i1).getSname();
                dItemIds[i][i1] = ledger.getD().get(i).getSys().get(i1).getId();
            }
        }

        initSpinner1(cNames);
        initSpinner2(dNames);
        initSpinner3(dNameItems[spinner2.getSelectedItemPosition()]);
        LedgerAdapter adapter = new LedgerAdapter(ledger.getB());
        rvAlarmReal.setAdapter(adapter);
        eqtype_id = String.valueOf(cIds[spinner1.getSelectedItemPosition()]);
        eqwksp_id = String.valueOf(dIds[spinner2.getSelectedItemPosition()]);
        eqsystem_id = String.valueOf(dItemIds[spinner3.getSelectedItemPosition()]);
    }

    @Override
    public void onQuerySuccess(Ledger ledger) {
        LedgerAdapter adapter = new LedgerAdapter(ledger.getB());
        rvAlarmReal.setAdapter(adapter);
    }

    private void initSpinner2(String[] strings) {

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, strings);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initSpinner3(dNameItems[i]);
                eqwksp_id = String.valueOf(dIds[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initSpinner1(String[] strings) {
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, strings);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                eqtype_id = String.valueOf(cIds[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initSpinner3(String[] strings) {

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, strings);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(arrayAdapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                eqsystem_id = String.valueOf(dItemIds[spinner2.getSelectedItemPosition()][i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
