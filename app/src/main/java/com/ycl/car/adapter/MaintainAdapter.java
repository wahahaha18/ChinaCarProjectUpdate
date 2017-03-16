package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.Maintain;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/14.
 */

public class MaintainAdapter extends BaseQuickAdapter<Maintain, BaseViewHolder> {


    public MaintainAdapter(List<Maintain> data) {
        super(R.layout.layout_item_maintain, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Maintain item) {
        helper.setText(R.id.tv_file_name, item.getDname() + "." + item.getDext());
        if (item.getDext().contains("pdf")) {
            helper.setImageResource(R.id.img_maintain, R.mipmap.a56);
        } else if (item.getDext().contains("doc") || item.getDext().contains("docx")) {
            helper.setImageResource(R.id.img_maintain, R.mipmap.a54);
        } else if (item.getDext().contains("xlsx") || item.getDext().contains("xls")) {
            helper.setImageResource(R.id.img_maintain, R.mipmap.a55);
        } else if (item.getDext().contains("pptx") || item.getDext().contains("ppt")) {
            helper.setImageResource(R.id.img_maintain, R.mipmap.a58);
        } else {
            helper.setImageResource(R.id.img_maintain, R.mipmap.a57);
        }
    }
}
