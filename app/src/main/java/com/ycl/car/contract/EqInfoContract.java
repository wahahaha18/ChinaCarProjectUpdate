package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.EqInfo;

import java.util.List;

/**
 * 设备信息
 * Created by y11621546 on 2017/2/16.
 */

public interface EqInfoContract {
    interface Presenter extends BasePresenter {
        void init(String eqno);

    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(EqInfo eqInfo);

        void onError();
    }
}
