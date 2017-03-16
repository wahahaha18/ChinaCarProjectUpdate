package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.Ledger;

/**
 * 台账
 * Created by y11621546 on 2017/3/4.
 */

public interface LedgerContract {
    interface Presenter extends BasePresenter {
        void getLedgerList();

        void queryLedgerList(String eqno, String eqtype_id, String eqwksp_id, String eqsystem_id);
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onError();

        void onSuccess(Ledger ledger);

        void onQuerySuccess(Ledger ledger);
    }
}
