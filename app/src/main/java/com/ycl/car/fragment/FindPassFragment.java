package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.car.R;

/**
 * 找回密码
 * Created by y11621546 on 2017/1/18.
 */

public class FindPassFragment extends BasePageFragment {


    public static FindPassFragment newInstance() {

        Bundle args = new Bundle();

        FindPassFragment fragment = new FindPassFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_pass_find, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("密码找回");
        setTitleRight("下一步");
    }

    @Override
    public void fetchData() {

    }
}
