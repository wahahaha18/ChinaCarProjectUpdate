package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.model.C;

import java.util.List;

/**
 * Created by y11621546 on 2017/3/3.
 */

public class CAdapter extends BaseQuickAdapter<C, BaseViewHolder> {
    public CAdapter(int layoutResId, List<C> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, C item) {


    }
}
