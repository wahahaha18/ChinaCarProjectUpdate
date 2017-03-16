package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.DetailActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.MainNewsAdapter;
import com.ycl.car.contract.NewsContract;
import com.ycl.car.model.MainNews;
import com.ycl.car.presenter.NewsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 研发中心要闻
 * Created by y11621546 on 2017/1/18.
 */

public class NewsMainFragment extends BasePageFragment implements NewsContract.View {
    NewsPresenter presenter;
    @BindView(R.id.rv_news_main)
    RecyclerView rvNewsMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public static NewsMainFragment newInstance() {

        Bundle args = new Bundle();

        NewsMainFragment fragment = new NewsMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_main, container, false);

        ButterKnife.bind(this, view);
        rvNewsMain.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvNewsMain.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getMainNews("50");
            }
        });
        rvNewsMain.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "新闻详情");
                DetailActivity.start(getActivity(), bundle);
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void fetchData() {
        presenter.getMainNews("50");
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onSuccess(List<MainNews.BBean> list) {
        MainNewsAdapter adapter = new MainNewsAdapter(list);
        rvNewsMain.setAdapter(adapter);
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }
}
