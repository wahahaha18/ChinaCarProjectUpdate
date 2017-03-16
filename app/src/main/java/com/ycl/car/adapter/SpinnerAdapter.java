package com.ycl.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;
import com.ycl.car.model.Ledger;

import java.util.List;

/**
 * Created by y11621546 on 2017/3/6.
 */

public class SpinnerAdapter<T> extends BaseAdapter {
    private List<T> tList;
    private Context context;

    @Override
    public int getCount() {
        return tList.size();
    }

    @Override
    public Object getItem(int i) {
        return tList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.adapter_mytopactionbar_spinner, null);
        if (view == null) {
//            TextView textView = view.findViewById()
        }
        return view;
    }
}
