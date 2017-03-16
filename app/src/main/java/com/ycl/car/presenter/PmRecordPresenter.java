package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.ycl.car.contract.PMRecordContract;
import com.ycl.car.model.Record;
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

public class PmRecordPresenter implements PMRecordContract.Presenter {
    private static final String TAG = "PmRecordPresenter";
    private PMRecordContract.View view;

    public PmRecordPresenter(PMRecordContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void initData(String uid, String eqno) {
        Log.d(TAG, "initData() called with: uid = [" + uid + "], eqno = [" + eqno + "]");
        view.showLoading();
        HttpManager.getInstance().getHttpService().getPmListPlan(uid, eqno)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Record.class));
                        } else {
                            view.onSuccess(new ArrayList<Record>());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
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
