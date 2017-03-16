package com.ycl.car.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.ContantValue;
import com.ycl.car.R;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.TabItem;

import java.util.List;

/**
 * Tab adapter
 * Created by y11621546 on 2017/1/17.
 */

public class ControlAdapter extends BaseQuickAdapter<LoginResponse.CBean.ChildsBeanXX, BaseViewHolder> {
    public ControlAdapter(List<LoginResponse.CBean.ChildsBeanXX> data) {
        super(R.layout.fragment_item_enterprise, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoginResponse.CBean.ChildsBeanXX item) {
        helper.setText(R.id.tv_title_tab, item.getName_());
        ImageView imageView = helper.getView(R.id.img_tab);
        Glide.with(mContext).load(ContantValue.BASE_URL + item.getImgs()).into(imageView);
    }
}
