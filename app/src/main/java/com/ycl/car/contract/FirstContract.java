package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.Weather;

/**
 * Created by y11621546 on 2017/3/4.
 */

public interface FirstContract {
    interface Presenter extends BasePresenter {
        void getWeatherInfo();

        void getNotificationInfo(String uid);
    }

    interface View extends BaseView<Presenter> {
        @Override
        void showLoading();

        @Override
        void dismissLoading();

        void onWeatherSucceess(Weather weather);

        void onNotifiCationSucceess(Weather.WeatherBean weatherBean);

        void onError();
    }

}
