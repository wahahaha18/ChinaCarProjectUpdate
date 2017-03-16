package com.ycl.car.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.ContantValue;
import com.ycl.car.MyApp;
import com.ycl.car.R;
import com.ycl.car.contract.CheckUrlContract;
import com.ycl.car.presenter.CheckUrlPresenter;
import com.ycl.car.utils.HttpManager;
import com.ycl.car.utils.RegUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by y11621546 on 2017/3/9.
 */

public class IPDialogFragment extends DialogFragment implements CheckUrlContract.View {
    @BindView(R.id.btn_cancel)
    TextView btnCancel;
    @BindView(R.id.btn_sure)
    TextView btnSure;
    private SharedPreferences sharedPreferences;
    private KProgressHUD kProgressHUD;
    private CheckUrlPresenter presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }

    public static IPDialogFragment newInstance() {

        Bundle args = new Bundle();

        IPDialogFragment fragment = new IPDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.et_ip)
    EditText etIp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_ip, container, false);
        ButterKnife.bind(this, view);
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        presenter = new CheckUrlPresenter(this);
        return view;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_sure:

                final String ip = etIp.getText().toString();
                presenter.checkUrl(ip);

                break;
        }
    }

    @Override
    public void setPresenter(CheckUrlContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(getContext())
                    .setLabel("正在校验...")
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
    public void onSuccess(String url) {
        sharedPreferences.edit().putString("url", url).apply();
        if (sharedPreferences.contains("url")) {
            if (sharedPreferences.getString("url", "").equals(url)) {
                Toast.makeText(getActivity(), "http地址设置成功", Toast.LENGTH_SHORT).show();
                HttpManager.getInstance().setBASE_URL();
                dismiss();
            } else {
                Toast.makeText(getActivity(), "url地址修改失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
