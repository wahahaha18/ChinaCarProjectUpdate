package com.ycl.car.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.LedgerContract;
import com.ycl.car.model.Ledger;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class LedgerPresenter implements LedgerContract.Presenter {
    private LedgerContract.View view;

    public LedgerPresenter(LedgerContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getLedgerList() {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getLedgerInfo(null, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(JSON.parseObject(jsonObject.toJSONString(), Ledger.class));
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
    public void queryLedgerList(String eqno, String eqtype_id, String eqwksp_id, String eqsystem_id) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getLedgerInfo(eqno, eqtype_id, eqwksp_id, eqsystem_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onQuerySuccess(JSON.parseObject(jsonObject.toJSONString(), Ledger.class));
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
