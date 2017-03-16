package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.TpmCheck;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/15.
 */

public class TpmCheckAdapter extends BaseQuickAdapter<TpmCheck, BaseViewHolder> {
    public TpmCheckAdapter(List<TpmCheck> data) {
        super(R.layout.layout_item_tpm_check, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TpmCheck item) {
        if (item.getStatus() == 0) {
            helper.setVisible(R.id.tv_deal, true);
            helper.setVisible(R.id.tv_note, true);
        } else {
            helper.setVisible(R.id.tv_deal, false);
            helper.setVisible(R.id.tv_note, true);
        }
        helper.setText(R.id.tv01, String.valueOf(helper.getLayoutPosition() + 1))
                .setText(R.id.tv02, item.getSystemname())
                .setText(R.id.tv03, item.getJcd())
                .setText(R.id.tv04, item.getEqno())
                .setText(R.id.tv05, item.getPlandate())
                .setText(R.id.tv06, item.getTmethod())
                .setText(R.id.tv07, item.getTstatus())
                .setText(R.id.tv08, item.getWorkinfo())
                .addOnClickListener(R.id.tv_deal)
                .addOnClickListener(R.id.tv_note)
        ;
    }
}
