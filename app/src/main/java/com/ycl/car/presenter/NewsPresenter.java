package com.ycl.car.presenter;

import com.ycl.car.contract.NewsContract;
import com.ycl.car.model.MainNews;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by y11621546 on 2017/1/21.
 */

public class NewsPresenter implements NewsContract.Presenter {
    private NewsContract.View view;

    public NewsPresenter(NewsContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getMainNews(String menu_id) {
        view.showLoading();
        HttpManager.getInstance().getHttpService().showMainNews(menu_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MainNews>() {
                    @Override
                    public void accept(MainNews mainNews) throws Exception {
                        if ("0".equals(mainNews.getA())) {
                            view.onSuccess(mainNews.getB());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dismissLoading();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dismissLoading();
                    }
                });
    }

    @Override
    public void start() {

    }
}
