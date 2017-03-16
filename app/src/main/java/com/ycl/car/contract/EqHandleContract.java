package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.Repair;

import java.util.List;

/**
 * 设备维修
 * Created by y11621546 on 2017/3/4.
 */

public interface EqHandleContract {

    interface Presenter extends BasePresenter {
        void getHandleList(String uid, String eqno, String status);

        void queryHandleList(String uid, String eqno, String status, String startm, String endm);


    }

    interface View extends BaseView<Presenter> {
        @Override
        void showLoading();

        @Override
        void dismissLoading();

        void onSuccess(List<Repair> repairList);


        void onQuerySuccess(List<Repair> repairList);


        void onError();
    }

}
