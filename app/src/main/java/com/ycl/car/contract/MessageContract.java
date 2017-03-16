package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.Msg;
import com.ycl.car.model.Msg1;

import java.util.List;

/**
 * 消息通知
 * Created by y11621546 on 2017/1/22.
 */

public interface MessageContract {

    interface Presenter extends BasePresenter {
        void initMessage_Mine(String uid);

        void initMessage_notification(String uid);

        void initMessage_Message(String uid);

        void getPushDetailInfo(String uid, String id);

        void getMessageDetailInfo(String uid, String id);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onPushSuccess(List<Msg> msgList);

        void onMsgSuccess(List<Msg1> msgList);

        void onError();

    }
}
