package com.ycl.car.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.AlarmRealTime;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/13.
 */

public class AlarmRealTimeAdapter extends BaseQuickAdapter<AlarmRealTime, BaseViewHolder> {
    public AlarmRealTimeAdapter(List<AlarmRealTime> data) {
        super(R.layout.layout_item_alarm_real, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AlarmRealTime item) {
        helper.setText(R.id.tv_alarm_number, String.valueOf(helper.getLayoutPosition() + 1))
                .setText(R.id.tv_alarm_date, item.getStr_time_on_())
                .setText(R.id.tv_alarm_level, item.getLevel_())
                .setText(R.id.tv_alarm_area, (CharSequence) item.getStr_zone())
                .setText(R.id.tv_alarm_info, item.getMessage_())
                .setText(R.id.tv_operate, "Y".equals(item.getIs_on_()) ? "已处理" : "处理")
                .setText(R.id.tv_state, "Y".equals(item.getIs_confirmed_()) ? "报警" : "未报警")
        ;
        helper.convertView.setBackgroundColor(Color.rgb((item.getStr_color() & 0xff0000) >> 16, (item.getStr_color() & 0x00ff00) >> 8, (item.getStr_color() & 0x0000ff)));
    }
}
