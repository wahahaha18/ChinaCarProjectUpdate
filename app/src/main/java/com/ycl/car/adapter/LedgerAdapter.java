package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.Ledger;

import java.util.List;

/**
 * Created by y11621546 on 2017/3/6.
 */

public class LedgerAdapter extends BaseQuickAdapter<Ledger.BBean, BaseViewHolder> {
    public LedgerAdapter(List<Ledger.BBean> data) {
        super(R.layout.layout_item_ledger, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Ledger.BBean item) {
        helper.setText(R.id.tv01, item.getEqno())
                .setText(R.id.tv02, item.getEqname())
                .setText(R.id.tv03, item.getEqspeci())
                .setText(R.id.tv04, item.getEqfactory())
                .setText(R.id.tv05, item.getEqdepartment())
                .setText(R.id.tv06, item.getInstallocation())
                .setText(R.id.tv07, item.getUseless())
                .setText(R.id.tv08, item.getEqtype())
                .setText(R.id.tv09, item.getEqwksp())
                .setText(R.id.tv10, item.getEqsystem());
    }
}
