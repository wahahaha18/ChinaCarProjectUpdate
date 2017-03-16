package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.EqControlListContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.utils.HttpManager;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/2/23.
 */

public class EqControlListPresenter implements EqControlListContract.Presenter {
    private EqControlListContract.View view;

    public EqControlListPresenter(EqControlListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(String sen, String uid) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getMenuByParent(sen, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess((ArrayList<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX>) JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), LoginResponse.CBean.ChildsBeanXX.ChildsBeanX.class));
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
