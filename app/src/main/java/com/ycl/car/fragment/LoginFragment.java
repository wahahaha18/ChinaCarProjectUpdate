package com.ycl.car.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.contract.LoginContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.presenter.LoginPresenter;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 登录
 * Created by y11621546 on 2017/1/18.
 */

public class LoginFragment extends BasePageFragment implements LoginContract.View {
    //    FragmentLoginBinding binding;
    LoginPresenter presenter;
    ProgressDialog progressDialog;
    @BindView(R.id.tv_pass_forget)
    TextView tvPassForget;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_pwd)
    EditText userPwd;
    private KProgressHUD kProgressHUD;
    private final String TAG = LoginFragment.class.getName();

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }

        }
    };

    private void setAlias(String alias) {
        if (TextUtils.isEmpty(alias)) {
            Toast.makeText(getActivity(), "别名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        presenter = new LoginPresenter(this);
        tvPassForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NextActivity) getActivity()).addFragment(FindPassFragment.newInstance(), true);
            }
        });
        getToolbarRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                String pwd = userPwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(getActivity(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.login(name, pwd);
                }

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("管理登录");
        setTitleRight("登录");
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getContext())
                .setLabel("登录中...")
                .setAnimationSpeed(2)
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
    public void onSuccess(LoginResponse.BBean userInfo) {
        setAlias(userInfo.getU_name());
        getActivity().finish();
    }

    @Override
    public void onError(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showWarnMsg(String msg) {

        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

}
