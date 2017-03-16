package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.MainNews;

import java.util.List;

/**
 * 新闻契约类
 * Created by y11621546 on 2017/1/21.
 */

public interface NewsContract {

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<MainNews.BBean> list);

        void onError();

    }

    interface Presenter extends BasePresenter {
        void getMainNews(String menu_id);
    }
}
