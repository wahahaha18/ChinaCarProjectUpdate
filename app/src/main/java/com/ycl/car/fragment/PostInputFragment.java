package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ycl.car.Next1Activity;
import com.ycl.car.NextActivity;
import com.ycl.car.R;

/**
 * Created by y11621546 on 2017/2/10.
 */

public class PostInputFragment extends BasePageFragment {
    public static PostInputFragment newInstance() {

        Bundle args = new Bundle();

        PostInputFragment fragment = new PostInputFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_input, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("发布新贴");
        setTitleRight("发表");
    }

    @Override
    public void fetchData() {

    }
}
