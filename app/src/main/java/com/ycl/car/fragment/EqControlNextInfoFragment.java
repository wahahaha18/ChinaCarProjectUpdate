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
import com.ycl.car.model.LoginResponse;
import com.ycl.car.view.ProhibitScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备管理Next
 * Created by y11621546 on 2017/2/23.
 */

public class EqControlNextInfoFragment extends BasePageFragment {
    @BindView(R.id.tl)
    SlidingTabLayout tl;
    @BindView(R.id.vp)
    ProhibitScrollViewPager vp;
    private Bundle bundle;

    private List<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX.ChildsBean> childsBeanList;
    private String[] titles;

    public static EqControlNextInfoFragment newInstance(Bundle bundle) {
        EqControlNextInfoFragment fragment = new EqControlNextInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        childsBeanList = (List<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX.ChildsBean>) bundle.getSerializable("childs");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_next_info, container, false);
        ButterKnife.bind(this, view);
        List<Fragment> fragmentList = new ArrayList<>();
        if (childsBeanList.isEmpty()) return view;
        titles = new String[childsBeanList.size()];
        for (int i = 0; i < childsBeanList.size(); i++) {
            titles[i] = childsBeanList.get(i).getName_();
            fragmentList.add(EqControlItemFragment.newInstance(childsBeanList.get(i).getParam()));
        }
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        vp.setAdapter(adapter);
        tl.setViewPager(vp, titles);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(bundle.getString("title"));
        setTitleRight("");
    }
}
