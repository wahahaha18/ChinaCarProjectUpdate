package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;

/**
 * toolbar契约类
 * Created by y11621546 on 2017/1/18.
 */

public interface ToolbarContract {
    interface View extends BaseView<Presenter> {
        void setToolbarTitle(String title);

        void setToolbarRight(String content);
    }

    interface Presenter extends BasePresenter {
        void toolbarRightClick();
    }
}
