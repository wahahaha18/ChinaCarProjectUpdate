package com.ycl.car.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.MaintainPlan;
import com.ycl.car.model.PM;

import java.util.List;

/**
 * 维修计划
 * Created by y11621546 on 2017/2/15.
 */

public class PMAdapter extends BaseQuickAdapter<MaintainPlan, BaseViewHolder> {
    public PMAdapter(List<MaintainPlan> data) {
        super(R.layout.layout_item_maintain_plan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MaintainPlan item) {
        StringBuilder info = new StringBuilder();
        if (!item.getFiles().isEmpty()) {
            for (int i = 0; i < item.getFiles().size(); i++) {
                if (i != 0) {
                    info.append("、");
                }
                info.append(item.getFiles().get(i).getFname());

            }
        }
//        if (item.getStatus() == 0) {
//            helper.setVisible(R.id.tv_note, true);
//            helper.setVisible(R.id.tv_deal, true);
//        } else {
//            helper.setVisible(R.id.tv_note, true);
//            helper.setVisible(R.id.tv_deal, false);
//        }
        helper.setVisible(R.id.tv_deal, true);
        helper.setVisible(R.id.tv_note, false);
        helper.setText(R.id.tv01, String.valueOf(helper.getLayoutPosition() + 1))//序号
                .setText(R.id.tv02, item.getEqname())//设备名称
                .setText(R.id.tv03, item.getEqno())//设备编号
                .setText(R.id.tv04, item.getPlandate())//计划保养时间
                .setText(R.id.tv05, item.getPmark())//注意事项
                .setText(R.id.tv06, info)//操作指导
                .setText(R.id.tv07, item.getPmcontent())//保养内容
//                .setText(R.id.tv08, item.getEquip().getEqname());//操作
                .addOnClickListener(R.id.tv_note)
                .addOnClickListener(R.id.tv_deal)
        ;
    }
}
