package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.Repair;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/13.
 */

public class EqHandleNoAdapter extends BaseQuickAdapter<Repair, BaseViewHolder> {
    public EqHandleNoAdapter(List<Repair> data) {
        super(R.layout.layout_item_eq_handle_no, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Repair item) {
        helper.setText(R.id.tv01, item.getEqno())
                .setText(R.id.tv02, item.getEqname())
                .setText(R.id.tv03, item.getAddtime())
                .setText(R.id.tv04, item.getRtitle())
                .setText(R.id.tv05, item.getRinfo())

        ;
//        helper.convertView.setBackgroundColor(Color.rgb((item.getStr_color() & 0xff0000) >> 16, (item.getStr_color() & 0x00ff00) >> 8, (item.getStr_color() & 0x0000ff)));
    }
}
