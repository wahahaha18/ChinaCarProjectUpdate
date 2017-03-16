package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.MaintainPlan;
import com.ycl.car.model.PM;

import java.util.List;

/**
 * PM 可行性维修计划
 * Created by y11621546 on 2017/2/15.
 */

public interface MaintainPlanContract {
    interface Presenter extends BasePresenter {
        void initData(String uid, String eqno);

        //初始化首页可行性PM
        void initToDoList(String uid);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<MaintainPlan> maintainPlanList);

        void onToDoSuccess(List<MaintainPlan> pmList);

        void onError();
    }
}
