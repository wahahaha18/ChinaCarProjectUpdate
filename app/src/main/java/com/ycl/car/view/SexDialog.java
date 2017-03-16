package com.ycl.car.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.car.MyApp;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/2/13.
 */

public class SexDialog extends DialogFragment {
    private LoginResponse.BBean userInfo;
    public static ResultCallback callback1;
    private int checkItem;


    public static SexDialog newInstance(LoginResponse.BBean userInfo, ResultCallback callback) {
        callback1 = callback;
        Bundle args = new Bundle();
        args.putParcelable("user", userInfo);
        SexDialog fragment = new SexDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = getArguments().getParcelable("user");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("修改性别")
                .setSingleChoiceItems(new String[]{"男", "女"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("SexDialog", "i:" + i);
                        checkItem = i;

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HttpManager.getInstance().getHttpService().updateUserInfo(String.valueOf(userInfo.getId()), userInfo.getCompany_(), userInfo.getAlias_(), checkItem == 0 ? "男" : "女")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<ResponseBody>() {
                                    @Override
                                    public void accept(ResponseBody responseBody) throws Exception {
                                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                                        Log.d("SexDialog", jsonObject.toJSONString());
                                        if (jsonObject.getString("a").equals("0")) {
                                            userInfo.setGender_(checkItem == 0 ? "男" : "女");
                                            MyApp.getInstance().getSharedPreferences().edit().putString("user", JSON.toJSONString(userInfo)).apply();
                                            callback1.onSuccess();
                                        } else {
                                            callback1.onError();
                                        }
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        callback1.onError();
                                    }
                                }, new Action() {
                                    @Override
                                    public void run() throws Exception {

                                    }
                                });
                    }
                });
        return builder.create();
    }

    public interface ResultCallback {
        void onSuccess();

        void onError();
    }
}
