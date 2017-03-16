package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/10.
 */

public class PostItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PostItemAdapter(List<String> data) {
        super(R.layout.layout_item_post_1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
