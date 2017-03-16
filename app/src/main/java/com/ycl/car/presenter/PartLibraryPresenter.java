package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ycl.car.contract.PartLibraryContract;
import com.ycl.car.model.PartLibrary;
import com.ycl.car.utils.HttpManager;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 智能备件
 * Created by y11621546 on 2017/2/14.
 */

public class PartLibraryPresenter implements PartLibraryContract.Presenter {
    private PartLibraryContract.View view;

    public PartLibraryPresenter(PartLibraryContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override

    public void initData() {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getSparePartLibraryInfo(null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("PartLibraryPresenter1", JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                        if (jsonObject.getString("a").equals("0")) {
                            view.dismissLoading();
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), PartLibrary.class));
                        } else {
                            view.dismissLoading();
                            view.onError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dismissLoading();
                        view.onError();
                    }
                });
    }

    @Override
    public void queryData(String hint1, String hint2) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getSparePartLibraryInfo(hint1, hint2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("PartLibraryPresenter", responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.dismissLoading();
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), PartLibrary.class));
                        } else {
                            view.dismissLoading();
                            view.onSuccess(new ArrayList<PartLibrary>());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dismissLoading();
                        view.onError();
                    }
                });
    }

    @Override
    public void initEqInfo(String eqno) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getEqPart(eqno)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), PartLibrary.class));
                        } else {
                            view.onError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onError();
                        view.dismissLoading();
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
