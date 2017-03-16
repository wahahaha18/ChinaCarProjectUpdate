package com.ycl.car.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.Msg;

import java.util.List;

/**
 * 消息通知适配器
 * Created by y11621546 on 2017/1/22.
 */

public class MessageAdapter extends BaseQuickAdapter<Msg, BaseViewHolder> {
    public MessageAdapter(List<Msg> data) {
        super(R.layout.layout_item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Msg item) {
        if (item.getIsread() == 0) {
            helper.setVisible(R.id.tv_status, true);
            helper.setText(R.id.tv_status, "未读");
            helper.setTextColor(R.id.tv_status, Color.RED);
        } else {
            helper.setVisible(R.id.tv_status, true);
            helper.setText(R.id.tv_status, "已读");
            helper.setTextColor(R.id.tv_status, Color.GRAY
            );
        }
        helper.setText(R.id.tv_title, item.getPtitle())
                .setText(R.id.tv_time, item.getTime());

    }
}
