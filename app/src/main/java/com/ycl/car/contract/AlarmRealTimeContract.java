package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.AlarmRealTime;
import com.ycl.car.model.C;
import com.ycl.car.model.D;

import java.util.List;

/**
 * 实时报警
 * Created by y11621546 on 2017/2/13.
 */

public interface AlarmRealTimeContract {
    interface Presenter extends BasePresenter {
        void getInfo(String uid);

        void query(String uid, String level_id, String zone_id);

        void getHistory(String uid);

        void queryHistory(String uid, String level, String zone, String startTime, String endTime);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<AlarmRealTime> alarmRealTimeList);

        void initSpinnerList(List<C> cList, List<D> dList);

        void onError();
    }
}
