package com.ycl.car.presenter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.ContantValue;
import com.ycl.car.contract.CheckUrlContract;
import com.ycl.car.utils.HttpManager;
import com.ycl.car.utils.RegUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by y11621546 on 2017/3/9.
 */

public class CheckUrlPresenter implements CheckUrlContract.Presenter {
    private CheckUrlContract.View view;

    public CheckUrlPresenter(CheckUrlContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void checkUrl(final String url) {

        if (TextUtils.isEmpty(url) || !RegUtil.isHttp(url)) {
            view.onError("请输入正确的http地址");

        } else {
            view.showLoading();
            try {
                OkHttpUtils.post().url(url + ContantValue.checkUrl).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        view.onError("http地址格式不正确");
                        view.dismissLoading();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        if (jsonObject.getString("a").equals("0")) {
                            view.onSuccess(url);
                        } else {
                            view.onError(jsonObject.getString("b"));
                        }
                        view.dismissLoading();

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                view.dismissLoading();
                view.onError("http地址格式不正确");
            }

        }
    }
}
