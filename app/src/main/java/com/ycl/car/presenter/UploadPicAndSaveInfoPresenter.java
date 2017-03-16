package com.ycl.car.presenter;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.contract.UploadPicAndSaveInfoContract;
import com.ycl.car.utils.HttpManager;

import java.io.File;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 上传图片或保存信息
 * Created by y11621546 on 2017/2/16.
 */

public class UploadPicAndSaveInfoPresenter implements UploadPicAndSaveInfoContract.Presenter {
    private UploadPicAndSaveInfoContract.View view;

    public UploadPicAndSaveInfoPresenter(UploadPicAndSaveInfoContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private Disposable d;

    @Override
    public void uploadPicAndSavePMInfo(final String uid, final String pmid, final String rcount, File picFile) {
        view.showLoading();
        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), picFile);
        HttpManager.getInstance().getHttpService()
                .getFile("PM", requestBody)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        d = disposable;

                    }
                })
                .flatMap(new Function<ResponseBody, ObservableSource<ResponseBody>>() {
                    @Override
                    public ObservableSource<ResponseBody> apply(ResponseBody responseBody) throws Exception {
                        JSONObject json = JSON.parseObject(responseBody.string());
                        String imgs = null;
                        if (json.getString("a").equals("0")) {
                            imgs = json.getString("b");
                        } else {
                            d.dispose();
                        }
                        Log.d("HandleFragment", json.toJSONString());
                        return HttpManager.getInstance().getHttpService().savePmInfo(uid, pmid, rcount, imgs);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess();
                        } else {
                            view.onError("");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onError("");
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
    public void savePMInfo(String uid, String pmid, String rcount) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().savePmInfo(uid, pmid, rcount, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {

                            view.onSuccess();
                        } else {
                            view.onError("");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dismissLoading();
                        view.onError("");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dismissLoading();
                    }
                });
    }

    @Override
    public void uploadPicAndSaveTPMInfo(final String uid, final String tpmid, final String rcount, File picFile) {
        view.showLoading();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), picFile);
        HttpManager.getInstance().getHttpService()
                .getFile("TPM", requestBody)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        d = disposable;
                    }
                })
                .flatMap(new Function<ResponseBody, ObservableSource<ResponseBody>>() {
                    @Override
                    public ObservableSource<ResponseBody> apply(ResponseBody responseBody) throws Exception {
                        JSONObject json = JSON.parseObject(responseBody.string());
                        Log.d("HandleFragment", json.toJSONString());
                        String imgs = null;
                        if (json.getString("a").equals("0")) {
                            imgs = json.getString("b");
                        } else {
                            d.dispose();
                        }

                        return HttpManager.getInstance().getHttpService().saveTpmInfo(uid, tpmid, rcount, imgs);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess();
                        } else {
                            view.onError("");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dismissLoading();
                        view.onError("");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dismissLoading();
                    }
                });
    }

    @Override
    public void saveTPMInfo(String uid, String tpmid, final String rcount) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().saveTpmInfo(uid, tpmid, rcount, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess();
                        } else {
                            view.onError("");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onError("");
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
    public void uploadPicAndSaveEqInfo(final String uid, final String rcont, final String id, final String rstatus, File picFile) {
        view.showLoading();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), picFile);
        HttpManager.getInstance().getHttpService()
                .getFile("repair", requestBody)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        d = disposable;
                    }
                })
                .flatMap(new Function<ResponseBody, ObservableSource<ResponseBody>>() {
                    @Override
                    public ObservableSource<ResponseBody> apply(ResponseBody responseBody) throws Exception {
                        JSONObject json = JSON.parseObject(responseBody.string());
                        Log.d("HandleFragment", json.toJSONString());
                        String imgs = null;
                        if (json.getString("a").equals("0")) {
                            imgs = json.getString("b");
                        } else {
                            d.dispose();
                        }

                        return HttpManager.getInstance().getHttpService().getZqEquipRepairSave(uid, rcont, id, imgs, rstatus);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess();
                        } else {
                            view.onError("");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dismissLoading();
                        view.onError("");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dismissLoading();
                    }
                });
    }

    @Override
    public void saveEqInfo(String uid, String rcont, String id, String rstatus) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getZqEquipRepairSave(uid, rcont, id, null, rstatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess();
                        } else {
                            view.onError("");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onError("");
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
