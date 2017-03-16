package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.R;
import com.ycl.car.contract.EqInfoContract;
import com.ycl.car.model.EqInfo;
import com.ycl.car.presenter.EqInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备详情
 * Created by y11621546 on 2017/2/16.
 */

public class EqInfoFragment extends BasePageFragment implements EqInfoContract.View {
    @BindView(R.id.eq_no)
    TextView eqNo;
    @BindView(R.id.eq_name)
    TextView eqName;
    @BindView(R.id.eq_model)
    TextView eqModel;
    @BindView(R.id.eq_factory)
    TextView eqFactory;
    @BindView(R.id.eq_location)
    TextView eqLocation;
    @BindView(R.id.eq_info)
    TextView eqInfo;
    private EqInfoPresenter presenter;
    private Bundle bundle;
    private KProgressHUD kProgressHUD;

    public static EqInfoFragment newInstance(Bundle bundle) {

        EqInfoFragment fragment = new EqInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EqInfoPresenter(this);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eq_info, container, false);
        presenter.init(bundle.getString("eqno"));
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(bundle.getString("title"));
        setTitleRight("");
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getContext())
                .setLabel("正在加载...").show();
    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onSuccess(EqInfo eqInfo) {
        if (eqInfo == null) return;
        eqNo.setText(eqInfo.getEqno());
        eqName.setText(eqInfo.getEqname());
        eqFactory.setText(eqInfo.getEqfactory());
        eqModel.setText(eqInfo.getEqspeci());
        eqLocation.setText(eqInfo.getInstallocation());
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(EqInfoContract.Presenter presenter) {

    }
}
