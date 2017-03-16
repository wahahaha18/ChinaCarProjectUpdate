package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycl.car.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于中汽
 * Created by y11621546 on 2017/1/18.
 */

public class AboutFragment extends BasePageFragment {

    private static final String content = "中国汽车工业工程有限公司由原机械工业部第四,第五两个设计元合并而成.现为中国机械工业集团全资子公司";
    @BindView(R.id.text)
    TextView text;

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        text.setText(content);
        setTitle("关于中汽");

    }

    @Override
    public void fetchData() {

    }


}
