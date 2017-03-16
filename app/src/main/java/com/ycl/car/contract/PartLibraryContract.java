package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.PartLibrary;

import java.util.List;

/**
 * 智能备件库
 * Created by y11621546 on 2017/2/14.
 */

public interface PartLibraryContract {
    interface Presenter extends BasePresenter {
        void initData();

        void queryData(String hint1, String hint2);

        void initEqInfo(String eqno);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<PartLibrary> partLibraryList);

        void onError();

    }
}
