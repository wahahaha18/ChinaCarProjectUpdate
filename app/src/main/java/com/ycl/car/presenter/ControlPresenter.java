package com.ycl.car.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ycl.car.MyApp;
import com.ycl.car.contract.ControlContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.utils.HttpManager;

/**
 * Created by y11621546 on 2017/2/13.
 */

public class ControlPresenter implements ControlContract.Presenter {
    private ControlContract.View view;
    private SharedPreferences sharedPreferences;
    private LoginResponse.BBean userInfo;
    public ControlPresenter(ControlContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData() {
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        if (!sharedPreferences.contains("user")) return;
        if (sharedPreferences.contains("user")) {
            userInfo = JSON.parseObject(sharedPreferences.getString("user", ""), LoginResponse.BBean.class);
            Log.d("UserInfoFragment", userInfo.toString());

        }

    }

    @Override
    public void start() {

    }
}
