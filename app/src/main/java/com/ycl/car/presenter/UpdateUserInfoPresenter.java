package com.ycl.car.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.MyApp;
import com.ycl.car.contract.UpdateUserInfoContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/2/13.
 */

public class UpdateUserInfoPresenter implements UpdateUserInfoContract.Presenter {
    private UpdateUserInfoContract.View view;
    private SharedPreferences sharedPreferences;

    public UpdateUserInfoPresenter(UpdateUserInfoContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void updateUserInfo(final LoginResponse.BBean userInfo) {
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        view.showLoading();
        HttpManager.getInstance().getHttpService().updateUserInfo(String.valueOf(userInfo.getId()), userInfo.getCompany_(), userInfo.getAlias_(), userInfo.getGender_())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("UpdateUserInfoPresenter", jsonObject.toJSONString());
                        if (jsonObject.getString("a").equals("0")) {
                            sharedPreferences.edit().putString("user", JSON.toJSONString(userInfo)).apply();
                            view.onSuccess();
                        } else {
                            view.onError();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dismissLoading();
                        view.onError();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dismissLoading();
                    }
                });
    }
}
