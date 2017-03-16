package com.ycl.car.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.AlarmRealTime;
import com.ycl.car.model.PartLibrary;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/13.
 */

public class PartLibraryAdapter extends BaseQuickAdapter<PartLibrary, BaseViewHolder> {
    public PartLibraryAdapter(List<PartLibrary> data) {
        super(R.layout.layout_item_part_library, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PartLibrary item) {
        helper.setText(R.id.tv01, item.getO().getSpname())
                .setText(R.id.tv02, item.getO().getSpbrand())
                .setText(R.id.tv03, item.getO().getSpmaterial())
                .setText(R.id.tv04, item.getO().getSpmodel())
                .setText(R.id.tv05, item.getType().getTpname())
                .setText(R.id.tv06, String.valueOf(item.getO().getSplifespan()))
                .setText(R.id.tv07, String.valueOf(item.getO().getSpdeliveryperiod()))
                .setText(R.id.tv08, (CharSequence) item.getO().getSpremarks())
                .setText(R.id.tv09, String.valueOf(item.getO().getSpcurrentc()))
                .setText(R.id.tv10, String.valueOf(item.getO().getSpadvicec()))
                .setText(R.id.tv11, String.valueOf(item.getO().getSplowestc()))
                .setText(R.id.tv12, item.getO().getSpunit())
                .setText(R.id.tv13, (CharSequence) item.getO().getInlasttime())
                .setText(R.id.tv14, (CharSequence) item.getO().getOutlasttime())
        ;
//        helper.convertView.setBackgroundColor(Color.rgb((item.getStr_color() & 0xff0000) >> 16, (item.getStr_color() & 0x00ff00) >> 8, (item.getStr_color() & 0x0000ff)));
    }
}
