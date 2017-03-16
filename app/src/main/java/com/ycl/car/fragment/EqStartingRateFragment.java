package com.ycl.car.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.ycl.car.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备开动率
 * Created by y11621546 on 2017/3/10.
 */

public class EqStartingRateFragment extends BasePageFragment {
    private Bundle bundle;

    public static EqStartingRateFragment newInstance(Bundle bundle) {

        EqStartingRateFragment fragment = new EqStartingRateFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.webview_start_rate)
    WebView webviewStartRate;

    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_rate, container, false);
        ButterKnife.bind(this, view);
        String params = bundle.getString("params", "");
        webviewStartRate.getSettings().setJavaScriptEnabled(true);
        webviewStartRate.getSettings().setSupportZoom(false);
        webviewStartRate.getSettings().setBuiltInZoomControls(false);
        webviewStartRate.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webviewStartRate.getSettings().setDefaultFontSize(18);
        Log.d("EqStartingRateFragment", params);
        if (!TextUtils.isEmpty(params)) {
            if (params.contains("?")) {
                webviewStartRate.loadUrl(params + "&equiptype=phone");
            } else {
                webviewStartRate.loadUrl(params + "?equiptype=phone");
            }

        } else {
            Toast.makeText(getActivity(), "参数不正确", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("设备开动率");
        setTitleRight("");
    }
}
