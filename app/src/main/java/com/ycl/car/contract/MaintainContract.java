package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.Maintain;

import java.util.List;

/**
 * 维修资料
 * Created by y11621546 on 2017/2/14.
 */

public interface MaintainContract {
    interface Presenter extends BasePresenter {
        void initData(int type);

        void queryData(int type, String hint1, String hint2);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<Maintain> maintainList);

        void onError();
    }


}
