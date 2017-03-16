package com.ycl.car.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.EqRepairContract;
import com.ycl.car.model.EqMainInfo;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 维修信息---设备详情
 * Created by y11621546 on 2017/2/16.
 */

public class EqRepairPresenter implements EqRepairContract.Presenter {
    private EqRepairContract.View view;

    public EqRepairPresenter(EqRepairContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void initData(String status, String uid, String eqno) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getZqEquipRepair(eqno, status, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), EqMainInfo.class));
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
}
