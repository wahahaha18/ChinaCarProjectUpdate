package com.ycl.car.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.AboutContract;
import com.ycl.car.contract.EqInfoContract;
import com.ycl.car.model.EqInfo;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/2/16.
 */

public class EqInfoPresenter implements EqInfoContract.Presenter {
    private EqInfoContract.View view;

    public EqInfoPresenter(EqInfoContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void init(String eqno) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getEqInfo(eqno)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseObject(jsonObject.getJSONObject("b").toJSONString(), EqInfo.class));
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
