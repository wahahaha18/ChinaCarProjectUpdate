package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.CateAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by y11621546 on 2017/2/9.
 */

public class CategoryNextFragment extends BasePageFragment {
    private static final String TITLE = "title";
    @BindView(R.id.rv_cate)
    RecyclerView rvCate;
    private String title = TITLE;


    public static CategoryNextFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(TITLE, title);
        CategoryNextFragment fragment = new CategoryNextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(TITLE, TITLE);
    }

    @Override
    public void fetchData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cate, container, false);
        ButterKnife.bind(this, view);
        rvCate.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCate.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        getToolbarRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(getActivity()).initiateScan();
            }
        });
        rvCate.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", 9);
                Next1Activity.start(getActivity(), bundle);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitleRight("");
        setTitle(title);

    }
}
