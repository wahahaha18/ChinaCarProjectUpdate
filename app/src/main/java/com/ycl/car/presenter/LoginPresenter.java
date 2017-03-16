package com.ycl.car.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.MyApp;
import com.ycl.car.contract.LoginContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.utils.HttpManager;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/1/21.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void login(String userName, String userPwd) {
        Log.d("LoginPresenter", "name:" + userName + "\npwd:" + userPwd);
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPwd)) {
            view.showLoading();
            HttpManager.getInstance().getHttpService().login(userName, userPwd)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<LoginResponse>() {
                        @Override
                        public void accept(LoginResponse loginResponse) throws Exception {

                            Log.d("LoginPresenter", loginResponse.toString());
                            for (LoginResponse.CBean cBean : loginResponse.getC()) {
                                Log.d("LoginPresenter", cBean.toString());
                            }
                            if ("0".equals(loginResponse.getA())) {
                                MyApp.getInstance().getSharedPreferences().edit().putString("user", JSON.toJSONString(loginResponse.getB())).apply();
                                MyApp.getInstance().getSharedPreferences().edit().putString("control", JSON.toJSONString(loginResponse.getC())).apply();
                                MyApp.getInstance().getSharedPreferences().edit().putString("notification", JSON.toJSONString(loginResponse.getD())).apply();
                            }
                        }
                    })


                    .subscribe(new Consumer<LoginResponse>() {
                        @Override
                        public void accept(LoginResponse loginResponse) throws Exception {
                            if ("0".equals(loginResponse.getA())) {
                                view.showWarnMsg("登录成功");
                                view.onSuccess(loginResponse.getB());
//                                MyApp.getInstance().getSharedPreferences().edit().putString("user", JSON.toJSONString(loginResponse.getB())).apply();
                            } else {
                                view.onError("登录失败");
                            }
                            for (LoginResponse.CBean c : loginResponse.getC()) {
                                Log.d("LoginPresenter", c.getName_());
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            view.dismissLoading();
                            view.onError("网络有误");
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                            view.dismissLoading();
                        }
                    });
        } else {
            view.onError("用户名或密码不能为空");
        }
    }


    @Override
    public void start() {

    }


}
