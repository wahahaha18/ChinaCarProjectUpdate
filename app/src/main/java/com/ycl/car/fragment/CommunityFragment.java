package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.ycl.car.R;
import com.ycl.car.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 社区
 * <p>
 * Created by y11621546 on 2017/1/16.
 */

public class CommunityFragment extends BasePageFragment {
    @BindView(R.id.tl_1)
    SlidingTabLayout tl1;
    @BindView(R.id.pager)
    ViewPager pager;

    public static CommunityFragment newInstance() {

        Bundle args = new Bundle();

        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        ButterKnife.bind(this, view);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NewestFragment.newInstance());
        fragmentList.add(HotFragment.newInstance());
        fragmentList.add(EssenceFragment.newInstance());
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        pager.setAdapter(adapter);
        tl1.setViewPager(pager, new String[]{"最新帖", "热门帖", "精华帖"});



        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getBinding().toolbarTitle.setCompoundDrawables(null, null, null, null);
//        content.title.set("社区");
//        content.right.set("");
//        getBinding().setToolbar(content);
//    }

    @Override
    public void fetchData() {

    }
}
