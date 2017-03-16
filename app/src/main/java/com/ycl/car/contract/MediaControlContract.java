package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.fragment.BasePageFragment;
import com.ycl.car.model.Media;

import java.util.List;

/**
 * Created by y11621546 on 2017/3/13.
 */

public interface MediaControlContract {

    interface Presenter extends BasePresenter {
        void getMediaList(String uid, String type);
    }

    interface View extends BaseView<Presenter> {
        void onSuccess(List<Media> mediaList);

        void onError();

        void showLoading();

        void dismissLoading();
    }
}
