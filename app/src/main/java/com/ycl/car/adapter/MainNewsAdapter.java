package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.MainNews;

import java.util.List;

/**
 * Created by y11621546 on 2017/1/18.
 */

public class MainNewsAdapter extends BaseQuickAdapter<MainNews.BBean, BaseViewHolder> {
    public MainNewsAdapter(List<MainNews.BBean> data) {
        super(R.layout.layout_item_news_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainNews.BBean item) {
        helper.setText(R.id.tv_news_main_title, item.getTitle_())
                .setText(R.id.tv_news_main_description, item.getSummary())
                .setText(R.id.tv_news_main_time, item.getStrAddTime());

    }
}
