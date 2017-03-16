package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.Collection;

import java.util.List;

/**
 * 收藏Adapter
 * Created by y11621546 on 2017/2/9.
 */

public class CollectionAdapter extends BaseQuickAdapter<Collection, BaseViewHolder> {
    public CollectionAdapter(List<Collection> data) {
        super(R.layout.layout_item_collection, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Collection item) {
        helper.setText(R.id.tv_content_title, item.getTitle())
                .setText(R.id.tv_content_content, item.getContent())
                .addOnClickListener(R.id.btn_collect_cancel);

    }
}
