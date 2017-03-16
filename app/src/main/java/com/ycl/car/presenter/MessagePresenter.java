package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.AboutContract;
import com.ycl.car.contract.MessageContract;
import com.ycl.car.model.Msg;
import com.ycl.car.model.Msg1;
import com.ycl.car.utils.HttpManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/1/22.
 */

public class MessagePresenter implements MessageContract.Presenter {
    private MessageContract.View view;

    public MessagePresenter(MessageContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    //我的页面通知
    @Override
    public void initMessage_Mine(String uid) {

    }

    //首页消息
    @Override
    public void initMessage_notification(String uid) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getPushList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onPushSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Msg.class));
                        } else {

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

    //首页通知
    @Override
    public void initMessage_Message(String uid) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getMessageList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onMsgSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), Msg1.class));
                        } else {

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
    public void getPushDetailInfo(String uid, String id) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getPushDetailInfo(uid, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {

                        } else {

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
    public void getMessageDetailInfo(String uid, String id) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getMessageDetailInfo(uid, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                        } else {

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
