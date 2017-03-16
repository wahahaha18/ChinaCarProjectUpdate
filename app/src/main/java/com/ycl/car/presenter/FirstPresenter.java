package com.ycl.car.presenter;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.R;
import com.ycl.car.contract.FirstContract;
import com.ycl.car.model.Weather;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class FirstPresenter implements FirstContract.Presenter {
    private FirstContract.View view;

    public FirstPresenter(FirstContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getWeatherInfo() {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            Weather weather = JSON.parseObject(jsonObject.getJSONObject("b").toJSONString(), Weather.class);
                            view.onWeatherSucceess(weather);
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
    public void getNotificationInfo(String uid) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getNotificationNum(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            JSONObject object = jsonObject.getJSONObject("b");
                            Weather.WeatherBean weatherBean = new Weather.WeatherBean();
                            weatherBean.setPushNum("(" + object.getString("push_c") + "条)");
                            weatherBean.setMaintainNum("(" + object.getString("repair_c") + "条)");
                            weatherBean.setMsgNum("(" + object.getString("message_c") + "条)");
                            weatherBean.setPMNum("(" + object.getString("pm_c") + "条)");
                            weatherBean.setTPMNum("(" + object.getString("tpm_c") + "条)");
                            weatherBean.setWarnNum("(" + object.getString("alrm_c") + "条)");
                            view.onNotifiCationSucceess(weatherBean);
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
