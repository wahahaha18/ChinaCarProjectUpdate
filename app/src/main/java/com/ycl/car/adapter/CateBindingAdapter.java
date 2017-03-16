//package com.ycl.car.adapter;
//
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.ycl.car.BR;
//import com.ycl.car.R;
//import com.ycl.car.model.LoginResponse;
//
//import java.util.List;
//
///**
// * 管理Adapter
// * Created by y11621546 on 2017/2/9.
// */
//
//public class CateBindingAdapter extends BaseQuickAdapter<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX, CateBindingAdapter.BindingHolder> {
//    public CateBindingAdapter(List<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX> data) {
//        super(R.layout.layout_item_cate, data);
//    }
//
//    @Override
//    protected void convert(BindingHolder helper, LoginResponse.CBean.ChildsBeanXX.ChildsBeanX item) {
//        ViewDataBinding binding = helper.getBinding();
//        binding.setVariable(BR.itema, item);
//        binding.executePendingBindings();
//    }
//
//
//    @Override
//    protected BindingHolder createBaseViewHolder(View view) {
//        return new BindingHolder(view);
//    }
//
//    @Override
//    protected View getItemView(int layoutResId, ViewGroup parent) {
//        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutResId, parent, false);
//        if (binding == null) {
//            return super.getItemView(layoutResId, parent);
//        }
//        View view = binding.getRoot();
//        view.setTag(R.id.BaseQuickAdapter_databinding_support);
//        return view
//                ;
//    }
//
//    public class BindingHolder extends BaseViewHolder {
//
//
//        public BindingHolder(View view) {
//            super(view);
//        }
//
//        public ViewDataBinding getBinding() {
//            return (ViewDataBinding) getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
//        }
//
//    }
//
//
//}
