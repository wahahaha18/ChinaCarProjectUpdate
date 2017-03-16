package com.ycl.car.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.ContantValue;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.contract.FirstContract;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.Weather;
import com.ycl.car.presenter.FirstPresenter;
import com.ycl.car.view.PinchImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页
 * Created by y11621546 on 2017/1/16.
 */

public class FirstFragment extends BasePageFragment implements View.OnClickListener, FirstContract.View {

    @BindView(R.id.pic)
    PinchImageView pic;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_more1)
    TextView tvMore1;
    @BindView(R.id.tv_more2)
    TextView tvMore2;
    @BindView(R.id.tv_more3)
    TextView tvMore3;
    @BindView(R.id.tv_more4)
    TextView tvMore4;
    @BindView(R.id.tv_more5)
    TextView tvMore5;
    @BindView(R.id.tv_more6)
    TextView tvMore6;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.tv_more_msg)
    TextView tvMoreMsg;
    @BindView(R.id.tv_more_warn)
    TextView tvMoreWarn;
    @BindView(R.id.tv_more_pm)
    TextView tvMorePm;
    @BindView(R.id.tv_more_tpm)
    TextView tvMoreTpm;
    @BindView(R.id.tv_more_repair)
    TextView tvMoreRepair;
    @BindView(R.id.tv_more_notification)
    TextView tvMoreNotification;
    @BindView(R.id.layout_notification)
    View layoutNotification;
    @BindView(R.id.layout_alarm)
    LinearLayout layoutAlarm;
    @BindView(R.id.layout_pm)
    LinearLayout layoutPm;
    @BindView(R.id.layout_tpm)
    LinearLayout layoutTpm;
    @BindView(R.id.layout_repair)
    LinearLayout layoutRepair;
    private LoginResponse.BBean userInfo;
    SharedPreferences sharedPreferences;
    private KProgressHUD kProgressHUD;
    private FirstPresenter firstPresenter;

    public static FirstFragment newInstance() {

        Bundle args = new Bundle();

        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", "{}"), LoginResponse.BBean.class);
        firstPresenter = new FirstPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);

        pic.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getContext()).load(R.mipmap.scene).into(pic);

        tvMore1.setOnClickListener(this);
        tvMore2.setOnClickListener(this);
        tvMore3.setOnClickListener(this);
        tvMore4.setOnClickListener(this);
        tvMore5.setOnClickListener(this);
        tvMore6.setOnClickListener(this);


        return view;
    }


    //    @Override
//    public void onResume() {
//        super.onResume();
//        Drawable drawable = getResources().getDrawable(R.mipmap.qiyewenhua1_2x);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        getBinding().toolbarTitle.setCompoundDrawables(drawable, null, null, null);
//        content.title.set("中汽工程");
//        content.right.set("");
//        getBinding().setToolbar(content);
//    }


    @Override
    public void onResume() {
        super.onResume();
        firstPresenter.getWeatherInfo();
        if (sharedPreferences.contains("user")) {
            firstPresenter.getNotificationInfo(String.valueOf(userInfo.getId()));
            if (layoutNotification.getVisibility() == View.GONE) {
                layoutNotification.setVisibility(View.VISIBLE);

            }
            if (sharedPreferences.contains("notification")) {
                String notiStr = sharedPreferences.getString("notification", null);
                if (TextUtils.isEmpty(notiStr)) return;
                JSONObject jsonObject = JSON.parseObject(notiStr);
                if ("1".equals(jsonObject.getString("isalrm"))) {
                    layoutAlarm.setVisibility(View.VISIBLE);
                } else {
                    layoutAlarm.setVisibility(View.GONE);
                }
                if ("1".equals(jsonObject.getString("ispm"))) {
                    layoutPm.setVisibility(View.VISIBLE);
                    layoutTpm.setVisibility(View.VISIBLE);
                } else {
                    layoutPm.setVisibility(View.GONE);
                    layoutTpm.setVisibility(View.GONE);
                }
                if ("1".equals(jsonObject.getString("isrepair"))) {
                    layoutRepair.setVisibility(View.VISIBLE);
                } else {
                    layoutRepair.setVisibility(View.GONE);
                }
            }
        } else {
            if (layoutNotification.getVisibility() == View.VISIBLE) {
                layoutNotification.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override

    public void fetchData() {

    }

    @Override
    public void onClick(View view) {
        if (!sharedPreferences.contains("user")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setTitle("请先登录")
                    .setCancelable(false)
                    .setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", 7);
                            bundle.putString(ContantValue.TOOLBAR_TITLE, "管理登录");
                            bundle.putString(ContantValue.TOOLBAR_RIGHT, "登录");
                            NextActivity.start(getContext(), bundle);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
            return;
        }
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_more1:
                bundle.putInt("position", 3);
                bundle.putInt("type", 1);
                NextActivity.start(getContext(), bundle);
                break;
            case R.id.tv_more2:
                bundle.putString("sen", "wx_ssbj");
                bundle.putString("title", "实时报警");
//                bundle.putInt("type", 1);
//                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
                break;
            case R.id.tv_more3:

                bundle.putString("sen", "wx_pm");
                bundle.putString("title", "PM预防性维修计划");
                bundle.putInt("type", 1);
//                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
                break;
            case R.id.tv_more4:
                bundle.putString("sen", "wx_tpm");
                bundle.putString("title", "TPM点检");
                bundle.putInt("type", 1);
//                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
                break;
            case R.id.tv_more5:
                bundle.putString("sen", "wx_wx");
                bundle.putString("title", "设备维修");
                bundle.putInt("type", 1);
//                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
                break;
            case R.id.tv_more6:
                bundle.putInt("position", 3);
                bundle.putInt("type", 2);
                NextActivity.start(getContext(), bundle);
                break;
        }

    }

    @Override
    public void setPresenter(FirstContract.Presenter presenter) {

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

    @Override
    public void onWeatherSucceess(Weather weather) {
        if (weather == null) return;
        if (weather.getWeather().contains("晴")) {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_16);
            drawable.setBounds(0, 0, drawable.getMinimumWidth() / 2, drawable.getMinimumHeight() / 2);
            tvWeather.setCompoundDrawables(drawable, null, null, null);
        }
        tvWeather.setText(weather.getWeather());
        tvTop.setText(TextUtils.isEmpty(weather.getTemp()) ? "" : "温度 " + weather.getTemp());
        tvCenter.setText(TextUtils.isEmpty(weather.getHumidity()) ? "" : "湿度 " + weather.getHumidity());
        tvBottom.setText(TextUtils.isEmpty(weather.getAqi_quality()) ? "" : "空气质量 " + weather.getAqi_quality());


    }

    @Override
    public void onNotifiCationSucceess(Weather.WeatherBean weatherBean) {
        if (weatherBean == null) return;
        tvMoreMsg.setText(weatherBean.getMsgNum());
        tvMoreNotification.setText(weatherBean.getPushNum());
        tvMoreWarn.setText(weatherBean.getWarnNum());
        tvMorePm.setText(weatherBean.getPMNum());
        tvMoreTpm.setText(weatherBean.getTPMNum());
        tvMoreRepair.setText(weatherBean.getMaintainNum());

    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败,请稍候重试", Toast.LENGTH_SHORT).show();
    }
}
