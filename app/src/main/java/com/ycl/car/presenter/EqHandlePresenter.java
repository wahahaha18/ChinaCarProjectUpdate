package com.ycl.car.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.EqHandleContract;
import com.ycl.car.model.Repair;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 设备维修
 * Created by y11621546 on 2017/3/4.
 */

public class EqHandlePresenter implements EqHandleContract.Presenter {
    private EqHandleContract.View view;

    public EqHandlePresenter(EqHandleContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getHandleList(String uid, String eqno, String status) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getRepairList(uid, eqno, status, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Repair.class));
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
    public void queryHandleList(String uid, String eqno, String status, String startm, String endm) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getRepairList(uid, eqno, status, startm, endm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onQuerySuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Repair.class));
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
