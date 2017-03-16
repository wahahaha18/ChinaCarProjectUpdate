package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.MaintainContract;
import com.ycl.car.model.Maintain;
import com.ycl.car.utils.HttpManager;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/2/14.
 */

public class MaintainPresenter implements MaintainContract.Presenter {
    private MaintainContract.View view;

    public MaintainPresenter(MaintainContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void initData(int type) {
        view.showLoading();
        switch (type) {
            case 0://设备资料
                HttpManager.getInstance().getHttpService().getMaintainEquimentInfo(String.valueOf(type), null, null)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                JSONObject jsonObject = JSON.parseObject(responseBody.string());
                                Log.d("MaintainPresenter1", jsonObject.toJSONString());
                                if (jsonObject.getString("a").equals("0")) {
                                    view.dismissLoading();
                                    view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Maintain.class));
                                } else {
                                    view.dismissLoading();
                                    view.onSuccess(new ArrayList<Maintain>());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.dismissLoading();
                                view.onError();
                            }
                        });
                break;
            case 1://备件资料
                HttpManager.getInstance().getHttpService().getMaintainSpareInfo(String.valueOf(type), null, null)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                JSONObject jsonObject = JSON.parseObject(responseBody.string());
                                if (jsonObject.getString("a").equals("0")) {
                                    view.dismissLoading();
                                    view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Maintain.class));
                                } else {
                                    view.dismissLoading();
                                    view.onSuccess(new ArrayList<Maintain>());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.dismissLoading();
                                view.onError();
                            }
                        });
                break;
        }


    }

    @Override
    public void queryData(int type, String hint1, String hint2) {
        view.showLoading();
        switch (type) {
            case 0://设备资料
                HttpManager.getInstance().getHttpService().getMaintainEquimentInfo(String.valueOf(type), hint1, hint2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                JSONObject jsonObject = JSON.parseObject(responseBody.string());
                                Log.d("MaintainPresenter", jsonObject.toJSONString());
                                if (jsonObject.getString("a").equals("0")) {
                                    view.dismissLoading();
                                    view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Maintain.class));
                                } else {
                                    view.dismissLoading();
                                    view.onSuccess(new ArrayList<Maintain>());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.dismissLoading();
                                view.onError();
                            }
                        });
                break;
            case 1://备件资料
                HttpManager.getInstance().getHttpService().getMaintainSpareInfo(String.valueOf(type), hint1, hint2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                JSONObject jsonObject = JSON.parseObject(responseBody.string());
                                if (jsonObject.getString("a").equals("0")) {
                                    view.dismissLoading();
                                    view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Maintain.class));
                                } else {
                                    view.dismissLoading();
                                    view.onSuccess(new ArrayList<Maintain>());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.dismissLoading();
                                view.onError();
                            }
                        });
                break;
        }
    }
}
