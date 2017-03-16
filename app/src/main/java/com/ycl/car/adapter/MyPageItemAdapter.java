package com.ycl.car.adapter;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.MyItem;

import java.util.List;

/**
 * 我的
 * Created by y11621546 on 2017/1/18.
 */

public class MyPageItemAdapter extends BaseQuickAdapter<MyItem, BaseViewHolder> {
    public MyPageItemAdapter(List<MyItem> data) {
        super(R.layout.layout_item_my, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyItem item) {
        helper.setImageResource(R.id.img_item_my_title, item.getImageUrl())
                .setText(R.id.tv_item_my_title, item.getTitle());
        TextView tvRight = helper.getView(R.id.tv_item_my_next);
        if (helper.getAdapterPosition() == 4) {
            helper.setText(R.id.tv_item_my_next, item.getImageRightUrl());
            tvRight.setCompoundDrawables(null, null, null, null);
        } else {
            helper.setText(R.id.tv_item_my_next, "");
            Drawable drawable = mContext.getResources().getDrawable(Integer.parseInt(item.getImageRightUrl()));
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvRight.setCompoundDrawables(null, null, drawable, null);
        }


    }
}
