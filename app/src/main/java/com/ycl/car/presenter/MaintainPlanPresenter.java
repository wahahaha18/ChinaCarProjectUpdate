package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ycl.car.contract.MaintainPlanContract;
import com.ycl.car.model.PM;
import com.ycl.car.model.MaintainPlan;
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

public class MaintainPlanPresenter implements MaintainPlanContract.Presenter {
    private static final String TAG = "MaintainPlanPresenter";
    private MaintainPlanContract.View view;

    public MaintainPlanPresenter(MaintainPlanContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void initData(String uid, String eqno) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getPmMaintainPlan(uid, eqno)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("MaintainPlanPresenter", JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), MaintainPlan.class));
                        } else {
                            view.onSuccess(new ArrayList<MaintainPlan>());
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
    public void initToDoList(String uid) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getPMListTodoList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("MaintainPlanPresenter", JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                        if (jsonObject.getString("a").equals("0")) {
                            view.onToDoSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), MaintainPlan.class));
                        } else {
                            view.onToDoSuccess(new ArrayList<MaintainPlan>());
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
