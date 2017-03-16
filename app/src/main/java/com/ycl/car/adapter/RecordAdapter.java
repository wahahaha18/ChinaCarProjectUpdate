package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.Record;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/15.
 */

public class RecordAdapter extends BaseQuickAdapter<Record, BaseViewHolder> {
    public RecordAdapter(List<Record> data) {
        super(R.layout.layout_item_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Record item) {
        String state = "未检";
        if (item.getStatus() == 0) {
            state = "未检";
        } else if (item.getStatus() == 1) {
            state = "已检";
        }
//        else if (item.getStatus() == -1) {
//            state = "未到时";
//        }
        else if (item.getStatus() == 2) {
            state = "过期";
        }
        helper.setText(R.id.tv01, state)
                .setText(R.id.tv02, item.getPlandate())
                .setText(R.id.tv03, item.getPtime())
                .setText(R.id.tv04, item.getPcontent());
    }
}
