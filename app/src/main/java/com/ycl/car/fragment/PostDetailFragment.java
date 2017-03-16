package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.R;
import com.ycl.car.adapter.PostItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的帖子（帖子详情页）
 * Created by y11621546 on 2017/1/18.
 */

public class PostDetailFragment extends BasePageFragment {
    private static final String text = "中国汽车工业工程有限公司由原机械工业部第四,第五两个设计元合并而成.现为中国机械工业集团全资子公司";
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.iamge)
    TextView iamge;
    @BindView(R.id.tv_content_content)
    TextView tvContentContent;
    @BindView(R.id.tv_content_title)
    TextView tvContentTitle;
    @BindView(R.id.rv_post)
    RecyclerView rvPost;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    public static PostDetailFragment newInstance() {

        Bundle args = new Bundle();

        PostDetailFragment fragment = new PostDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);
        ButterKnife.bind(this, view);
        rvPost.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPost.setNestedScrollingEnabled(false);
        scrollView.smoothScrollTo(0, 0);
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("sadfsaf" + i);
        }
        PostItemAdapter adapter = new PostItemAdapter(list);
        rvPost.setAdapter(adapter);
        rvPost.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), list.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void fetchData() {

    }


}
