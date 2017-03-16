package com.ycl.car.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ycl.car.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by y11621546 on 2017/3/6.
 */

public class MediaFragment extends BasePageFragment {
    @BindView(R.id.video)
    VideoView video;
    private String url;
    private MediaController mediaController;

    public static MediaFragment newInstance(String videlUrl) {

        Bundle args = new Bundle();
        args.putString("url", videlUrl);
        MediaFragment fragment = new MediaFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        mediaController = new MediaController(getContext());
        video.setVideoURI(Uri.parse(url));
        video.setMediaController(mediaController);
        video.start();
        video.requestFocus();

        return view;
    }
}
