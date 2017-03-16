package com.ycl.car.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.ycl.car.Next1Activity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by y11621546 on 2017/3/7.
 */

public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
//        Logger.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + AndroidUtil.printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Logger.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Logger.d("接受到推送下来的自定义消息" + bundle);
//            initNotification(context);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Logger.d(TAG, "接受到推送下来的通知");
            receivingNotification(context, bundle);


        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Logger.d(TAG, "用户点击打开了通知");

            openNotification(context, bundle);

        } else {
            Logger.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.d(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.d(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.d(TAG, "extras : " + extras);
    }


    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        JSONObject jsonObject = JSON.parseObject(extras);
        String a = jsonObject.getString("a");
        if (TextUtils.isEmpty(a)) return;
        Bundle bundle1 = new Bundle();
        switch (a) {
            case "1"://维修
                bundle1.putString("title", "设备维修");
                bundle1.putString("sen", "wx_wx");//60
                bundle1.putBundle("extras", bundle);
                Next1Activity.start(context, bundle1);
                break;
            case "2"://PM
                bundle1.putString("title", "PM可行性预防计划");
                bundle1.putString("sen", "wx_pm");//38
                bundle1.putBundle("extras", bundle);
                Next1Activity.start(context, bundle1);
                break;
            case "3"://TPM

                bundle1.putString("title", "TPM点检");
                bundle1.putString("sen", "wx_tpm");//39
                bundle1.putBundle("extras", bundle);
                Next1Activity.start(context, bundle1);
                break;
            case "4"://预警
                bundle1.putString("title", "实时预警");
                bundle1.putString("sen", "wx_ssbj");//34
                bundle1.putBundle("extras", bundle);
                Next1Activity.start(context, bundle1);
                break;
        }


    }

}
