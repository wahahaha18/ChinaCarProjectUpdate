package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.TpmCheckContract;
import com.ycl.car.contract.TpmCheckContract.Presenter;
import com.ycl.car.model.TPM;
import com.ycl.car.model.TpmCheck;
import com.ycl.car.utils.HttpManager;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/2/15.
 */

public class TpmCheckPresenter implements Presenter {
    private TpmCheckContract.View view;

    public TpmCheckPresenter(TpmCheckContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void initData(String uid, String eqno) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getTPM(uid, eqno)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), TpmCheck.class));
                        } else {
                            view.onSuccess(new ArrayList<TpmCheck>());
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

    @Override
    public void initTPMAToDoList(String uid) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getTPMListTodoList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onToDoSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), TpmCheck.class));
                        } else {
                            view.onToDoSuccess(new ArrayList<TpmCheck>());
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

    @Override
    public void start() {

    }
}
