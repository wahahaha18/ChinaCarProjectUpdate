package com.ycl.car.adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.MainNews;
import com.ycl.car.model.Post;

import java.util.List;

/**
 * 帖子适配器
 * Created by y11621546 on 2017/1/18.
 */

public class PostAdapter extends BaseQuickAdapter<Post.BBean, BaseViewHolder> {
    public PostAdapter(List<Post.BBean> data) {
        super(R.layout.layout_item_post, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Post.BBean item) {
        Drawable drawable = mContext.getResources().getDrawable(R.mipmap.zan1_3x);
        Drawable drawable1 = mContext.getResources().getDrawable(R.mipmap.zan2_3x);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        TextView tvZan = helper.getView(R.id.tv_zan);

        if (item.isZan()) {
            tvZan.setCompoundDrawables(drawable1, null, null, null);

        } else {
            tvZan.setCompoundDrawables(drawable, null, null, null);

        }
        tvZan.setText(String.valueOf(item.getZanNum()));
        helper.addOnClickListener(R.id.tv_zan)
                .addOnClickListener(R.id.tv_comment);
        helper.setText(R.id.tv_title, (CharSequence) item.getTitle_())
                .setText(R.id.tv_time, item.getAdd_time())
                .setText(R.id.tv_content_title, (CharSequence) item.getTitle_())
                .setText(R.id.tv_content_content, item.getContent_())
        ;


    }
}
