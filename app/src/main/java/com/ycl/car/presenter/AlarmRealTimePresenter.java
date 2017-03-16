package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ycl.car.contract.AlarmRealTimeContract;
import com.ycl.car.model.AlarmRealTime;
import com.ycl.car.model.C;
import com.ycl.car.model.D;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 实时报警
 * Created by y11621546 on 2017/2/13.
 */

public class AlarmRealTimePresenter  implements AlarmRealTimeContract.Presenter {
    private final String TAG = AlarmRealTimePresenter.class.getName();
    private AlarmRealTimeContract.View view;

    public AlarmRealTimePresenter(AlarmRealTimeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void getInfo(String uid) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getAlarmRealTimeInfo(uid, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("AlarmRealTimePresenter", JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), AlarmRealTime.class)
                            );
                            view.initSpinnerList(JSON.parseArray(jsonObject.getJSONArray("c").toJSONString(), C.class)
                                    , JSON.parseArray(jsonObject.getJSONArray("d").toJSONString(), D.class));
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

    @Override
    public void query(String uid, String level_id, String zone_id) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getAlarmRealTimeInfo(uid, level_id, zone_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("AlarmRealTimePresenter", JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), AlarmRealTime.class)
                            );
//                            view.initSpinnerList(JSON.parseArray(jsonObject.getJSONArray("c").toJSONString(), C.class)
//                                    , JSON.parseArray(jsonObject.getJSONArray("d").toJSONString(), D.class));
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

    @Override
    public void getHistory(String uid) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getAlrmHistory(uid, null, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("AlarmRealTimePresenterH", JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), AlarmRealTime.class)
                            );
                            view.initSpinnerList(JSON.parseArray(jsonObject.getJSONArray("c").toJSONString(), C.class)
                                    , JSON.parseArray(jsonObject.getJSONArray("d").toJSONString(), D.class));
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

    @Override
    public void queryHistory(String uid, String level, String zone, String startTime, String endTime) {
        Log.d(TAG, "queryHistory() called with: uid = [" + uid + "], level = [" + level + "], zone = [" + zone + "], startTime = [" + startTime + "], endTime = [" + endTime + "]");
        HttpManager.getInstance().getHttpService().getAlrmHistory(uid, level, zone, startTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.d("AlarmRealTimePresenterH", JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), AlarmRealTime.class)
                            );
                            view.initSpinnerList(JSON.parseArray(jsonObject.getJSONArray("c").toJSONString(), C.class)
                                    , JSON.parseArray(jsonObject.getJSONArray("d").toJSONString(), D.class));
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

    @Override
    public void start() {

    }
}
