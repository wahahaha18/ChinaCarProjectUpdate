package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.AlarmRealTime;
import com.ycl.car.model.EqMainInfo;

import java.util.List;

/**
 * 实时报警
 * Created by y11621546 on 2017/2/13.
 */

public interface EqRepairContract {
    interface Presenter extends BasePresenter {
        void initData(String status, String uid, String eqno);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<EqMainInfo> eqMainInfoList);

        void onError();
    }
}
