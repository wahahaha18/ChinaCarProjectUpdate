package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取设备管理下的类别
 * Created by y11621546 on 2017/2/23.
 */

public interface EqControlListContract {

    interface Presenter extends BasePresenter {
        void getData(String sen, String uid);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(ArrayList<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX> childsBeanXList);

        void onError();
    }


}
