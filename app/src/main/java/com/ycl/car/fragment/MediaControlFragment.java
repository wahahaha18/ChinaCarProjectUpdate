package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.ycl.car.R;
import com.ycl.car.adapter.FragmentAdapter;
import com.ycl.car.contract.MediaControlContract;
import com.ycl.car.presenter.MediaControlPresenter;
import com.ycl.car.view.ProhibitScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 多媒体控制
 * Created by y11621546 on 2017/3/13.
 */

public class MediaControlFragment extends BasePageFragment {
    @BindView(R.id.tl)
    SlidingTabLayout tl;
    @BindView(R.id.vp)
    ProhibitScrollViewPager vp;
    private String[] titles = new String[]{"中控室", "展示区"};

    public static MediaControlFragment newInstance(Bundle bundle) {

        MediaControlFragment fragment = new MediaControlFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_control, container, false);
        ButterKnife.bind(this, view);
        List<Fragment> fragmentList = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putString("type", "0");
        fragmentList.add(MediaControlLeftFragment.newInstance(bundle));
        bundle.putString("type", "1");
        fragmentList.add(MediaControlLeftFragment.newInstance(bundle));
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        vp.setAdapter(adapter);
        tl.setViewPager(vp, titles);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitleRight("");
        setTitle("多媒体管理");
    }


}
