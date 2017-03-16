package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.LoginResponse;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/13.
 */

public interface ControlContract {
    interface Presenter extends BasePresenter {
        void getData();
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<LoginResponse.CBean> list);

        void onError();
    }

}
