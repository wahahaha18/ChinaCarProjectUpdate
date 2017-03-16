package com.ycl.car.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.ContantValue;
import com.ycl.car.R;
import com.ycl.car.model.Media;

import java.util.List;

/**
 * Created by y11621546 on 2017/3/13.
 */

public class MediaControlAdapter extends BaseQuickAdapter<Media, BaseViewHolder> {
    public MediaControlAdapter(List<Media> data) {
        super(R.layout.layout_item_media_, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Media item) {
        if (item == null) return;
        Glide.with(mContext).load(ContantValue.BASE_URL + item.getImgsrc()).into((ImageView) helper.getView(R.id.iamge));
        helper.setText(R.id.title, item.getMname());

    }
}
