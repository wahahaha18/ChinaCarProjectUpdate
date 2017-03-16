package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.ycl.car.R;
import com.ycl.car.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 维修资料
 * Created by y11621546 on 2017/2/14.
 */

public class MaintainInfoFragment extends BasePageFragment {
    @BindView(R.id.tl_1)
    SlidingTabLayout tl1;
    @BindView(R.id.pager)
    ViewPager pager;
    private Bundle bundle;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<CustomTabEntity>();

    public static MaintainInfoFragment newInstance(Bundle bundle) {
        MaintainInfoFragment fragment = new MaintainInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Override
    public void fetchData() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maintain_info, container, false);
        ButterKnife.bind(this, view);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(EquipmentFragment.newInstance());
        fragmentList.add(SparePartFragment.newInstance());
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        pager.setAdapter(adapter);
        tl1.setViewPager(pager, new String[]{"设备资料", "备件资料"});

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        setTitleRight("");
        setTitle(bundle.getString("title"));
    }

}
