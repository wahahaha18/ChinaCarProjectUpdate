package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.LoginResponse;

/**
 * 更新用户信息契约类
 * Created by y11621546 on 2017/2/13.
 */

public interface UpdateUserInfoContract {
    interface Presenter extends BasePresenter {
        void updateUserInfo(LoginResponse.BBean userInfo);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess();

        void onError();
    }

}
