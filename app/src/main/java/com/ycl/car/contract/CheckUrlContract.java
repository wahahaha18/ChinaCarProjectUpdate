package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;

/**
 * Created by y11621546 on 2017/3/9.
 */

public interface CheckUrlContract {
    interface Presenter extends BasePresenter {
        void checkUrl(String url);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(String url);

        void onError(String msg);
    }

}
