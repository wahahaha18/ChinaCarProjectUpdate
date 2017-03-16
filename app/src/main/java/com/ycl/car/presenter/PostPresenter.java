package com.ycl.car.presenter;

import android.util.Log;

import com.ycl.car.contract.PostContract;
import com.ycl.car.model.Post;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 帖子
 * Created by y11621546 on 2017/1/21.
 */

public class PostPresenter implements PostContract.Presenter {
    private PostContract.View view;

    public PostPresenter(PostContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }


    @Override
    public void getPostInfo(final int position) {
        view.showLoading();
        switch (position) {
            case 0://最新帖
                HttpManager.getInstance().getHttpService().showFreshBulletin("1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Post>() {
                            @Override
                            public void accept(Post post) throws Exception {
                                Log.d("PostPresenter", "最新帖" + post.toString());
                                view.onSuccess(post.getB());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.dismissLoading();
                                view.onError();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                view.dismissLoading();
                            }
                        });
                break;
            case 1://热门帖
                HttpManager.getInstance().getHttpService().showHotBulletin("1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Post>() {
                            @Override
                            public void accept(Post post) throws Exception {
                                Log.d("PostPresenter", "热门帖" + post.toString());
                                view.onSuccess(post.getB());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.dismissLoading();
                                view.onError();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                view.dismissLoading();
                            }
                        });
                break;
            case 2://精华帖
                HttpManager.getInstance().getHttpService().showTypicalBulletin("1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Post>() {
                            @Override
                            public void accept(Post post) throws Exception {
                                Log.d("PostPresenter", "精华帖" + post.toString());
                                view.onSuccess(post.getB());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.dismissLoading();
                                view.onError();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                view.dismissLoading();
                            }
                        });
                break;
        }
    }
}
