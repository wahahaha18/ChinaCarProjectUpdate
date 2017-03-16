package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.car.R;

/**
 * 修改密码
 * Created by y11621546 on 2017/1/18.
 */

public class ModifyPassFragment extends BasePageFragment {


    public static ModifyPassFragment newInstance() {

        Bundle args = new Bundle();

        ModifyPassFragment fragment = new ModifyPassFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pass_modify, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitleRight("确认");
        setTitle("修改密码");
    }

    @Override
    public void fetchData() {

    }
}
