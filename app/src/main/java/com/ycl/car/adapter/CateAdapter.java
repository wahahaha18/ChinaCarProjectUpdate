package com.ycl.car.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理Adapter
 * Created by y11621546 on 2017/2/9.
 */

public class CateAdapter extends BaseQuickAdapter<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX, BaseViewHolder> {
    public CateAdapter(List<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX> data) {
        super(R.layout.layout_item_cate, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoginResponse.CBean.ChildsBeanXX.ChildsBeanX item) {

        helper.setText(R.id.tv_item_cate_title, item.getName_());

    }


}
