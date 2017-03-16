package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.R;
import com.ycl.car.adapter.MediaControlAdapter;
import com.ycl.car.contract.MediaControlContract;
import com.ycl.car.model.Media;
import com.ycl.car.presenter.MediaControlPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by y11621546 on 2017/3/13.
 */

public class MediaControlLeftFragment extends BasePageFragment implements MediaControlContract.View {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.btn_all_open)
    Button btnAllOpen;
    @BindView(R.id.btn_all_close)
    Button btnAllClose;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    private Bundle bundle;
    private MediaControlPresenter presenter;
    private KProgressHUD kProgressHUD;

    public static MediaControlLeftFragment newInstance(Bundle bundle) {

        MediaControlLeftFragment fragment = new MediaControlLeftFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        presenter = new MediaControlPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_control_left, container, false);
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        if (bundle == null) return view;
        presenter.getMediaList(String.valueOf(getUser().getId()), bundle.getString("type"));
        return view;
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void setPresenter(MediaControlContract.Presenter presenter) {

    }


    @Override
    public void onSuccess(List<Media> mediaList) {
        MediaControlAdapter adapter = new MediaControlAdapter(mediaList);
        rv.setAdapter(adapter);
    }

    @Override
    public void onError() {

    }

    @Override
    public void showLoading() {
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(getContext())
                    .setLabel("正在加载...")
                    .show();

        }

    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD != null && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }
    }

    @OnClick({R.id.btn_all_open, R.id.btn_all_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_all_open:
                break;
            case R.id.btn_all_close:
                break;
        }
    }
}
