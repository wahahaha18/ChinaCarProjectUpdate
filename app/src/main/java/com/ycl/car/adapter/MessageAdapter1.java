package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.Msg;
import com.ycl.car.model.Msg1;

import java.util.List;

/**
 * 消息通知适配器
 * Created by y11621546 on 2017/1/22.
 */

public class MessageAdapter1 extends BaseQuickAdapter<Msg1, BaseViewHolder> {
    public MessageAdapter1(List<Msg1> data) {
        super(R.layout.layout_item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Msg1 item) {
        helper.setText(R.id.tv_title, item.getMtitle())
                .setText(R.id.tv_time, item.getTime());

    }
}
