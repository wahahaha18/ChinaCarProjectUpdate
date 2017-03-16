package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;
import com.ycl.car.model.Post;

import java.util.List;

/**
 * 帖子契约类
 * Created by y11621546 on 2017/1/21.
 */

public interface PostContract {
    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void onSuccess(List<Post.BBean> list);

        void onError();
    }

    interface Presenter extends BasePresenter {
        void getPostInfo(int position);
    }
}
