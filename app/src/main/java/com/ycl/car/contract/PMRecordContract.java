package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.Record;
import com.ycl.car.model.TpmCheck;

import java.util.List;

/**
 * TPM点检
 * Created by y11621546 on 2017/2/15.
 */

public interface PMRecordContract {
    interface Presenter extends BasePresenter {
        void initData(String uid, String eqno);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<Record> tpmCheckList);

        void onError();
    }
}
