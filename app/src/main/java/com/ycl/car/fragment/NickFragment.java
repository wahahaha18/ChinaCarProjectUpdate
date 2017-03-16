package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.contract.UpdateUserInfoContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.presenter.UpdateUserInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 昵称
 * Created by y11621546 on 2017/1/18.
 */

public class NickFragment extends BasePageFragment implements UpdateUserInfoContract.View {
    private static final String BUNDLE = "bundle";
    @BindView(R.id.et_hint)
    EditText etHint;
    private Bundle bundle;
    LoginResponse.BBean user;
    UpdateUserInfoPresenter presenter;
    KProgressHUD kProgressHUD;

    public static NickFragment newInstance(Bundle bundle) {

        Bundle args = new Bundle();
        args.putBundle(BUNDLE, bundle);
        NickFragment fragment = new NickFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments().getBundle(BUNDLE);
        user = bundle.getParcelable("user");
        if (user != null) {
            Log.d("NickFragment", user.getU_name());
        }
        presenter = new UpdateUserInfoPresenter(this);
    }

    @Override
    public void fetchData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nick, container, false);
        ButterKnife.bind(this, view);
        getToolbarRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (bundle.getString("title", "title")) {
                    case "性别":

                        break;
                    case "公司名称":
                        user.setCompany_(etHint.getText().toString().trim());
                        presenter.updateUserInfo(user);
                        break;
                    case "昵称":
                        user.setAlias_(etHint.getText().toString().trim());
                        presenter.updateUserInfo(user);
                        break;
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        setTitle(bundle.getString("title", ""));
        setTitleRight("确认");
        etHint.setHint("请输入" + bundle.getString("title", "内容"));
    }


    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getActivity())
                .setLabel("请稍候...")
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
    public void onSuccess() {
        getActivity().onBackPressed();
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "修改失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(UpdateUserInfoContract.Presenter presenter) {

    }
}
