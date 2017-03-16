package com.ycl.car.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ycl.car.MyApp;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.view.SexDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人资料
 * Created by y11621546 on 2017/1/18.
 */

public class UserInfoFragment extends BasePageFragment implements View.OnClickListener, SexDialog.ResultCallback {
    LoginResponse.BBean user;
    SharedPreferences sharedPreferences;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.ll_nick)
    LinearLayout llNick;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.ll_company_name)
    LinearLayout llCompanyName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;

    public static UserInfoFragment newInstance() {

        Bundle args = new Bundle();

        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = MyApp.getInstance().getSharedPreferences();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        llNick.setOnClickListener(this);
        llCompanyName.setOnClickListener(this);
        llSex.setOnClickListener(this);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("个人资料");
        setTitleRight("");
        if (sharedPreferences.contains("user")) {
            user = JSON.parseObject(sharedPreferences.getString("user", ""), LoginResponse.BBean.class);
            tvAccount.setText(user.getU_name());
            tvCompanyName.setText(user.getCompany_());
            tvGender.setText(user.getGender_());
            tvNick.setText(user.getAlias_());
        }
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        if (user == null) return;
        bundle.putParcelable("user", user);
        switch (v.getId()) {
            case R.id.ll_nick:
                bundle.putString("title", "昵称");
                ((NextActivity) getActivity()).addFragment(NickFragment.newInstance(bundle), true);
                break;
            case R.id.ll_company_name:
                bundle.putString("title", "公司名称");
                ((NextActivity) getActivity()).addFragment(NickFragment.newInstance(bundle), true);
                break;
            case R.id.ll_sex:
                SexDialog.newInstance(user, this).show(getFragmentManager(), "");

                break;
        }
    }

    @Override
    public void fetchData() {


    }

    @Override
    public void onSuccess() {
        if (sharedPreferences.contains("user")) {
            user = JSON.parseObject(sharedPreferences.getString("user", ""), LoginResponse.BBean.class);
        }
        tvAccount.setText(user.getU_name());
        tvCompanyName.setText(user.getCompany_());
        tvGender.setText(user.getGender_());
        tvNick.setText(user.getAlias_());
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "性别修改失败", Toast.LENGTH_SHORT).show();
    }
}
