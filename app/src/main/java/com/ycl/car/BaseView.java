package com.ycl.car;

/**
 * Created by y11621546 on 2017/1/18.
 */

public interface BaseView<T> {
    void showLoading();

    void dismissLoading();

    void setPresenter(T presenter);
}
