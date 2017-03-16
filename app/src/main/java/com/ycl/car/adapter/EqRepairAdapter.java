package com.ycl.car.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.EqMainInfo;

import java.util.List;

/**
 * 维修信息Adapter
 * Created by y11621546 on 2017/2/16.
 */

public class EqRepairAdapter extends BaseQuickAdapter<EqMainInfo, BaseViewHolder> {


    public EqRepairAdapter(List<EqMainInfo> data) {
        super(R.layout.layout_item_eq_repair, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EqMainInfo item) {
        helper.setText(R.id.tv_title, item.getRtitle())
                .setText(R.id.tv_content, item.getAddtime() + "  " + item.getRinfo());
    }
}
