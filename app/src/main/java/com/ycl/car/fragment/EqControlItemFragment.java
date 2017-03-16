package com.ycl.car.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.ycl.car.R;
import com.ycl.car.model.DrawPic;
import com.ycl.car.utils.HttpManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/2/23.
 */

public class EqControlItemFragment extends BasePageFragment {
    int text = 0;
    private final String TAG = EqControlItemFragment.class.getName();
    String params;
    private static final long delay = 5 * 1000;
    private static final long period = 5 * 1000;
    @BindView(R.id.ll)
    AbsoluteLayout ll;
    private StringBuilder robotPointValueBuilder = new StringBuilder();
    private StringBuilder rollingBedpointValueBuilder = new StringBuilder();
    private StringBuilder stlBedPointValueBuilder = new StringBuilder();
    private StringBuilder stlTopBedPointValueBuilder = new StringBuilder();
    private StringBuilder stlBedExRotatePointValueBuilder = new StringBuilder();
    private StringBuilder stlTopBedExRotatePointValueBuilder = new StringBuilder();
    Timer timer;
    List<Map<View, DrawPic>> mapArrayList = new ArrayList<>();
    List<String> itemTypeList = new ArrayList<>();

    public static EqControlItemFragment newInstance(String params) {

        Bundle args = new Bundle();
        args.putString("params", params);
        EqControlItemFragment fragment = new EqControlItemFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void fetchData() {

    }

    private int getDpValue(double value) {
        int dpValue = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, px2dip(getContext(), (float) value));
        Log.d("EqControlItemFragment", "dpValue:" + dpValue);
        return dpValue;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        params = getArguments().getString("params");

    }

    @Override
    public void onResume() {
        super.onResume();
        final JSONObject jsonObject = JSON.parseObject(params);
        Log.d("EqControlItemFragment", jsonObject.getString("diagrame_name"));
        mapArrayList.clear();
        HttpManager.getInstance().getHttpService().diagramItemsForLoop(jsonObject.getString("diagrame_name"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, List<DrawPic>>() {

                    @Override
                    public List<DrawPic> apply(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Logger.json(jsonObject.toJSONString());
                        return JSON.parseArray(jsonObject.getJSONArray("b").toJSONString(), DrawPic.class);
                    }
                })
                .doOnNext(new Consumer<List<DrawPic>>() {
                    @Override
                    public void accept(List<DrawPic> drawPics) throws Exception {
                        for (int i = 0; i < drawPics.size(); i++) {
                            itemTypeList.add(drawPics.get(i).getItemType());
                            if ("Robot".equals(drawPics.get(i).getItemType())) {
                                //截取机器人getPointName字符串
                                subStringRobot(drawPics, i);
                            } else if ("STLBed".equals(drawPics.get(i).getItemType())) {
                                //截取STLBed的getPointName字符串
                                subStringSTLBed(drawPics, i);
                            } else if ("RollingBed".equals(drawPics.get(i).getItemType())) {
                                //截取RollingBed的getPointName
                                subStringRBed(drawPics, i);
                            } else if ("STLBedExRotate".equals(drawPics.get(i).getItemType())) {
                                //截取STLBedExRotate的getPointName
                                subStringSTLBedRotate(drawPics, i);
                            }
                        }
                    }
                })
                .flatMap(new Function<List<DrawPic>, ObservableSource<DrawPic>>() {
                    @Override
                    public ObservableSource<DrawPic> apply(List<DrawPic> drawPics) throws Exception {

                        return Observable.fromArray(drawPics.toArray(new DrawPic[]{}));
                    }
                })

                .subscribe(new Consumer<DrawPic>() {
                    @Override
                    public void accept(DrawPic drawPic) throws Exception {
                        Log.d("EqControlItemFragment", drawPic.toString());
                        Map<View, DrawPic> map = new HashMap<View, DrawPic>();
                        View view = null;
                        if ("GlassRoom".equals(drawPic.getItemType())) {
                            //如果ItemType为矩形就初始化矩形
                            if (initGlassRoom(drawPic) == null) return;
                            view = initGlassRoom(drawPic);

                        } else if ("Robot".equals(drawPic.getItemType())) {
                            //// TODO: 2017/3/14 测试
                            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_test,null);
                            ImageView iv_auto = (ImageView) view.findViewById(R.id.iv_auto);
                            TextView tvauto = (TextView) view.findViewById(R.id.tvauto);
                            iv_auto.setImageResource(R.mipmap.eq_fan_green);
                            iv_auto.setLayoutParams(new LinearLayout.LayoutParams((int) (drawPic.getW() * 2),(int) (drawPic.getW() * 2)-10));
                            tvauto.setLayoutParams(new LinearLayout.LayoutParams((int) drawPic.getW() * 2, (int) ((drawPic.getH() - drawPic.getW() + 10)) * 2));
                            tvauto.setText("test and Test");
                            AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams((int) drawPic.getW() * 2,(int) drawPic.getH() * 2,(int) drawPic.getX() * 2,(int) drawPic.getY() * 2);
                            ll.addView(view,layoutParams);
                            //**************************************
                            //如果有ItemType为Robot就初始化Robot
                            //view = initRobotView(drawPic);//
//                            if (view == null) return;

                        } else if ("RollingBed".equals(drawPic.getItemType())) {
                            //如果有ItemType为RollingBed就初始化Robot
                            view = initRBedView(drawPic);
                            if (view == null) return;
                        } else if ("STLBed".equals(drawPic.getItemType())) {
                            //如果有ItemType为STLBed就初始化Robot
                            view = initSTBedView(drawPic);
                            if (view == null) return;
                        } else if ("STLBedExRotate".equals(drawPic.getItemType())) {
                            //如果有ItemType为STLBedExRotate就初始化Robot
//                            if (initSTBedRotateView(drawPic) == null) return;
                            view = initSTBedRotateView(drawPic);
                            if (view == null) return;
                        }


                        map.put(view, drawPic);
                        mapArrayList.add(map);
                    }
                })
        ;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                if (!TextUtils.isEmpty(robotPointValueBuilder.toString())) {
//                    initRobot(robotPointValueBuilder.toString().substring(0, robotPointValueBuilder.length() - 1));
//                }
                if (itemTypeList.contains("Robot")) {
                    //获取"Robot"的点值，进行变化
                    pointVlaueOfRobot();
                }
                if (itemTypeList.contains("STLBed")) {
                    //获取"STLBed"的点值，进行变化
                    pointVlaueOfSTLbed();
                }
                if (itemTypeList.contains("RollingBed")) {
                    //获取"RollingBed"的点值，进行变化
                    pointVlaueOfRBed();
                }
                if (itemTypeList.contains("STLBedExRotate")) {
                    //获取"STLBedExRotate"的点值，进行变化
                    pointVlaueOfSTLBedRotate();
                }

            }
        };
        timer = new Timer();
        timer.schedule(timerTask, delay, period);

    }

    private void pointVlaueOfSTLBedRotate() {
        try {
            initSTLBedExRotate(stlBedExRotatePointValueBuilder.toString().substring(0, stlBedExRotatePointValueBuilder.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            initSTLBedExRotateTop(stlTopBedExRotatePointValueBuilder.toString().substring(0, stlTopBedExRotatePointValueBuilder.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pointVlaueOfRBed() {
        try {
            initRollingBed(rollingBedpointValueBuilder.toString().substring(0, rollingBedpointValueBuilder.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pointVlaueOfSTLbed() {
        try {
            initSTLBedTop(stlBedPointValueBuilder.toString().substring(0, stlBedPointValueBuilder.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            initSTLBed(stlTopBedPointValueBuilder.toString().substring(0, stlTopBedPointValueBuilder.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pointVlaueOfRobot() {
        try {
//            initRobot(robotPointValueBuilder.toString().substring(0, robotPointValueBuilder.length() - 1));
            //测试用的
            initRobotTest(robotPointValueBuilder.toString().substring(0, robotPointValueBuilder.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRobotTest(String params) {
        Log.e(TAG, "initRobotTest: params:" +params);
        HttpManager.getInstance().getHttpService().getDHPointValues2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Log.e(TAG, "accept: " +"jsonObject:"+jsonObject.toJSONString());
                        if (jsonObject.getString("a").equals("0")){
                            for (Map<View,DrawPic> map :mapArrayList){
                                for (Map.Entry<View,DrawPic> map1:map.entrySet()){
                                    View view = map1.getKey();
                                    DrawPic drawPic = map1.getValue();
                                    if (drawPic.getItemType().equals("Robot")){
                                        ImageView iv_auto = (ImageView) view.findViewById(R.id.iv_auto);
                                        if (jsonObject.getJSONObject("b").containsKey(drawPic.getPointName())){
                                            JSONArray jsonArray = jsonObject.getJSONObject("b").getJSONArray(drawPic.getPointName());
                                            if (jsonArray.size() > 2 && jsonArray != null && jsonArray.get(0).equals("2") && jsonArray.get(1).equals("1")){
                                                if (jsonArray.get(2).toString().startsWith("1",1)){
                                                    iv_auto.setImageResource(R.mipmap.eq_fan_red);
                                                }else if (jsonArray.get(2).toString().startsWith("1",0)){
                                                    iv_auto.setImageResource(R.mipmap.eq_fan_yellow);
                                                }else {
                                                    iv_auto.setImageResource(R.mipmap.eq_fan_green);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }

    @Nullable
    private View initSTBedRotateView(DrawPic drawPic) {
        View view;
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_rollingbed_rotate, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(drawPic.getChartLabel());
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        View view1 = view.findViewById(R.id.view);//滑动滚床View
        FrameLayout.LayoutParams layoutParamsRingView = new FrameLayout.LayoutParams(156 * 2, 220);
        view1.setLayoutParams(layoutParamsRingView);
        ImageView imageRing = (ImageView) view.findViewById(R.id.image_ring);//圆环
        FrameLayout.LayoutParams layoutParamsRing = new FrameLayout.LayoutParams(250, 250);
        layoutParamsRing.gravity = Gravity.CENTER;
        imageRing.setLayoutParams(layoutParamsRing);
        View view3 = view1.findViewById(R.id.ll_rolling_bed);//滚床
        View view2 = view.findViewById(R.id.fr_top);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(156 * 2, 60 * 2);
        layoutParams3.gravity = Gravity.CENTER;
        view3.setLayoutParams(layoutParams3);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(120 * 2, (int) (drawPic.getH() - 220) * 2);
        layoutParams.gravity = Gravity.CENTER;
        view2.setLayoutParams(layoutParams);
        TextView tvTitle1 = (TextView) view1.findViewById(R.id.tv_title);
        ImageView imageView1 = (ImageView) view1.findViewById(R.id.img);
        LinearLayout viewTop = (LinearLayout) view.findViewById(R.id.view_top);
        if (drawPic.getStandardDirection() == 0) {
            imageView1.setImageResource(R.mipmap.icon_53);
        } else {
            imageView1.setImageResource(R.mipmap.icon_52);
        }
        tvTitle1.setText(drawPic.getRollingBed().getChartLabel());
        if (!TextUtils.isEmpty(drawPic.getStrBins()) && drawPic.getStrBins().split(",").length > 0) {
            view1.setVisibility(View.VISIBLE);
        } else {
            view1.setVisibility(View.GONE);
            return null;
        }
        if (drawPic.getStandardDirection() == 0) {
            imageView.setImageResource(R.mipmap.icon_62);
        } else {
            imageView.setImageResource(R.mipmap.icon_63);
        }
        viewTop.setLayoutParams(new LinearLayout.LayoutParams(156 * 2, 60 * 2));
        AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, (int) drawPic.getX() * 2, (int) drawPic.getY() * 2);
        ll.addView(view, params);
        return view;
    }

    @Nullable
    private View initSTBedView(DrawPic drawPic) {
        View view;
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_rollingbed1, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(drawPic.getChartLabel());
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        View view1 = view.findViewById(R.id.view);
        View view2 = view.findViewById(R.id.fr_top);
        view1.setLayoutParams(new FrameLayout.LayoutParams(156 * 2, 60 * 2));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(120 * 2, (int) (drawPic.getH() - 120) * 2);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        view2.setLayoutParams(layoutParams);
        TextView tvTitle1 = (TextView) view1.findViewById(R.id.tv_title);
        ImageView imageView1 = (ImageView) view1.findViewById(R.id.img);
        LinearLayout viewTop = (LinearLayout) view.findViewById(R.id.view_top);
        if (drawPic.getStandardDirection() == 0) {
            imageView1.setImageResource(R.mipmap.icon_53);
        } else {
            imageView1.setImageResource(R.mipmap.icon_52);
        }
        tvTitle1.setText(drawPic.getRollingBed().getChartLabel());
        if (!TextUtils.isEmpty(drawPic.getStrBins()) && drawPic.getStrBins().split(",").length > 0) {
            view1.setVisibility(View.VISIBLE);
        } else {
            view1.setVisibility(View.GONE);
            return null;
        }
        if (drawPic.getStandardDirection() == 0) {
            imageView.setImageResource(R.mipmap.icon_62);
        } else {
            imageView.setImageResource(R.mipmap.icon_63);

        }
        viewTop.setLayoutParams(new LinearLayout.LayoutParams(156 * 2, 60 * 2));

        AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(156 * 2, (int) drawPic.getH() * 2, (int) drawPic.getX() * 2, (int) drawPic.getY() * 2);
        ll.addView(view, params);
        return view;
    }

    @NonNull
    private View initRBedView(DrawPic drawPic) {
        View view;
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_rollingbed, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        if (drawPic.getStandardDirection() == 0) {
            imageView.setImageResource(R.mipmap.icon_53);
        } else {
            imageView.setImageResource(R.mipmap.icon_52);
        }
        if (2 == drawPic.getRotateAngle()) {//向右转90度
            RotateAnimation rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setFillAfter(true);
            view.setAnimation(rotate);
        } else if (4 == drawPic.getRotateAngle()) {//向左转90度
            RotateAnimation rotate = new RotateAnimation(0, -90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setFillAfter(true);
            view.setAnimation(rotate);
        }
        tv_title.setText(drawPic.getChartLabel());
//                            AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(getDpValue(156), ViewGroup.LayoutParams.WRAP_CONTENT, (int) drawPic.getX(), (int) drawPic.getY());
        AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(156 * 2, 60 * 2, (int) drawPic.getX() * 2, (int) drawPic.getY() * 2);
        ll.addView(view, params);
        return view;
    }

    @NonNull
    private View initRobotView(DrawPic drawPic) {
        View view;
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_robot, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_label);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setLayoutParams(new LinearLayout.LayoutParams((int) drawPic.getW() * 2, (int) drawPic.getW() * 2 - 10));
        tvTitle.setLayoutParams(new LinearLayout.LayoutParams((int) drawPic.getW() * 2, (int) ((drawPic.getH() - drawPic.getW() + 10)) * 2));
        tvTitle.setText(drawPic.getChartLabel());
        ll.addView(view, new AbsoluteLayout.LayoutParams((int) drawPic.getW() * 2, (int) drawPic.getH() * 2, (int) drawPic.getX() * 2, (int)
                drawPic.getY() * 2));
        return view;
    }

    private View initGlassRoom(DrawPic drawPic) {
        View view;
        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(getDpValue(drawPic.getW() * 2), getDpValue(drawPic.getH() * 2), (int) drawPic.getX() * 2, (int) drawPic.getY() * 2);
        if (0 == drawPic.getStrBorderStyle()) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_glassroom, null);
            ll.addView(view, layoutParams);
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_glassroom_dash, null);
            ll.addView(view, layoutParams);
        }
        return view;
    }

    private void subStringSTLBedRotate(List<DrawPic> drawPics, int i) {
        stlBedExRotatePointValueBuilder.append(drawPics.get(i).getRollingBed().getPointName());
        stlBedExRotatePointValueBuilder.append(",");
        stlTopBedExRotatePointValueBuilder.append(drawPics.get(i).getPointName());
        stlTopBedExRotatePointValueBuilder.append(",");
    }

    private void subStringRBed(List<DrawPic> drawPics, int i) {
        rollingBedpointValueBuilder.append(drawPics.get(i).getPointName());
        rollingBedpointValueBuilder.append(",");
    }

    private void subStringSTLBed(List<DrawPic> drawPics, int i) {
        stlBedPointValueBuilder.append(drawPics.get(i).getPointName());
        stlBedPointValueBuilder.append(",");
        stlTopBedPointValueBuilder.append(drawPics.get(i).getRollingBed().getPointName());
        stlTopBedPointValueBuilder.append(",");
    }

    private void subStringRobot(List<DrawPic> drawPics, int i) {
        robotPointValueBuilder.append(drawPics.get(i).getPointName());
        robotPointValueBuilder.append(",");
    }

    //初始化Robot
    private void initRobot(String params) {
        Log.d(TAG, "initRobot() called with: params = [" + params + "]");
        HttpManager.getInstance().getHttpService().getDHPointValues2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   JSONObject jsonObject1 = JSON.parseObject(responseBody.string());
                                   Log.d("EqControlItemFragment2", jsonObject1.toJSONString());
                                   for (Map<View, DrawPic> map : mapArrayList) {
                                       for (Map.Entry<View, DrawPic> m : map.entrySet()) {
                                           View view = m.getKey();
                                           DrawPic drawPic = m.getValue();
                                           ImageView imageView = (ImageView) view.findViewById(R.id.image);
                                           if ("Robot".equals(drawPic.getItemType())) {

                                               if ("0".equals(jsonObject1.getString("a"))) {
                                                   if (jsonObject1.getJSONObject("b").containsKey(drawPic.getPointName())) {
                                                       JSONArray array = jsonObject1.getJSONObject("b").getJSONArray(drawPic.getPointName());
                                                       if (!array.isEmpty() && array.size() > 2) {
                                                           if ("2".equals(array.get(0)) && "1".equals(array.get(1))) {
                                                               if (array.get(2).toString().startsWith("1", 1)) {
                                                                   imageView.setImageResource(R.mipmap.icon_28);
                                                               } else if (array.get(2).toString().startsWith("0", 0)) {
                                                                   // TODO: 2017/3/14 待查证
                                                                   imageView.setImageResource(R.mipmap.icon_30);
                                                               } else {
                                                                   imageView.setImageResource(R.mipmap.icon_29);
                                                               }

                                                           }
                                                       }
                                                   }
                                               }

                                           }

                                       }
                                   }


                               }

                           }

                );
    }

    //初始化STLBedTop
    private void initSTLBedTop(String params) {
        Log.d(TAG, "initRobot() called with: params = [" + params + "]");
        HttpManager.getInstance().getHttpService().getDHPointValues2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   JSONObject jsonObject1 = JSON.parseObject(responseBody.string());
                                   for (Map<View, DrawPic> map : mapArrayList) {
                                       for (Map.Entry<View, DrawPic> m : map.entrySet()) {
                                           View view = m.getKey();
                                           DrawPic drawPic = m.getValue();
                                           if ("STLBed".equals(drawPic.getItemType())) {
                                               ImageView imageWarn = (ImageView) view.findViewById(R.id.image_warn);
                                               ImageView imageModel = (ImageView) view.findViewById(R.id.image_model);
                                               ImageView imageCenter = (ImageView) view.findViewById(R.id.image_center);
                                               if ("0".equals(jsonObject1.getString("a"))) {
                                                   if (jsonObject1.getJSONObject("b").containsKey(drawPic.getPointName())) {
                                                       JSONArray array = jsonObject1.getJSONObject("b").getJSONArray(drawPic.getPointName());
                                                       if (!array.isEmpty() && array.size() > 2) {
                                                           if ("2".equals(array.get(0)) && "1".equals(array.get(1))) {
                                                               for (DrawPic.PointValueColBean pointValueColBean : drawPic.getPointValueCol()) {
                                                                   if ("WarningB".equals(pointValueColBean.getKey())) {//入口封闭
                                                                       int valueB = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueB)) {
                                                                           imageWarn.setBackgroundColor(Color.RED);
                                                                       } else {
                                                                           if ("WarningC".equals(pointValueColBean.getKey())) {
                                                                               int valueC = Integer.parseInt(pointValueColBean.getValue());
                                                                               if (array.get(2).toString().startsWith("1", valueC)) {
                                                                                   imageWarn.setBackgroundColor(Color.YELLOW);
                                                                               } else {
                                                                                   imageWarn.setBackgroundColor(Color.GREEN);
                                                                               }
                                                                           }
                                                                       }
                                                                   }

                                                                   if ("IsAuto".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           imageModel.setBackgroundColor(Color.GREEN);
                                                                       }
                                                                   }
                                                                   if ("IsManual".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           imageModel.setBackgroundColor(Color.GRAY);

                                                                       }
                                                                   }
                                                                   if ("IsPrepairing".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           imageModel.setBackgroundColor(Color.parseColor("#FFA500"));

                                                                       }
                                                                   }
                                                                   if ("IsVoidMode".equals(pointValueColBean.getKey())) {//
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           imageModel.setBackgroundColor(Color.GREEN);

                                                                       }
                                                                   }
                                                                   if ("FWDRotatingHighSpeed".equals(pointValueColBean.getKey())) {//正向高速
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (drawPic.getStandardDirection() == 0) {
                                                                               imageCenter.setImageResource(R.mipmap.icon_64);

                                                                           }

                                                                       }
                                                                   }
                                                                   if ("REVRotatingHighSpeed".equals(pointValueColBean.getKey())) {//反向高速
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (drawPic.getStandardDirection() == 0) {
                                                                               imageCenter.setImageResource(R.mipmap.icon_65);

                                                                           }
                                                                       }
                                                                   }
                                                                   if ("FWDRotatingLowSpeed".equals(pointValueColBean.getKey())) {//正向低速
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (drawPic.getStandardDirection() == 0) {
                                                                               imageCenter.setImageResource(R.mipmap.icon_66);

                                                                           }
                                                                       }
                                                                   }
                                                                   if ("REVRotatingLowSpeed".equals(pointValueColBean.getKey())) {//反向低速
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (drawPic.getStandardDirection() == 0) {
                                                                               imageCenter.setImageResource(R.mipmap.icon_67);

                                                                           }

                                                                       }
                                                                   }
                                                               }

                                                           }
                                                       }
                                                   }
                                               }

                                           }

                                       }
                                   }


                               }

                           }

                );
    }

    //初始化STLBedTop
    private void initSTLBedExRotateTop(String params) {
        Log.d(TAG, "initRobot() called with: params = [" + params + "]");
        HttpManager.getInstance().getHttpService().getDHPointValues2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   JSONObject jsonObject1 = JSON.parseObject(responseBody.string());
                                   for (Map<View, DrawPic> map : mapArrayList) {
                                       for (Map.Entry<View, DrawPic> m : map.entrySet()) {
                                           View view = m.getKey();
                                           DrawPic drawPic = m.getValue();
                                           if ("STLBedExRotate".equals(drawPic.getItemType())) {
                                               ImageView imageWarn = (ImageView) view.findViewById(R.id.image_warn);
                                               ImageView imageModel = (ImageView) view.findViewById(R.id.image_model);
                                               ImageView imageCenter = (ImageView) view.findViewById(R.id.image_center);
                                               if ("0".equals(jsonObject1.getString("a"))) {
                                                   if (jsonObject1.getJSONObject("b").containsKey(drawPic.getPointName())) {
                                                       JSONArray array = jsonObject1.getJSONObject("b").getJSONArray(drawPic.getPointName());
                                                       if (!array.isEmpty() && array.size() > 2) {
                                                           if ("2".equals(array.get(0)) && "1".equals(array.get(1))) {
                                                               for (DrawPic.PointValueColBean pointValueColBean : drawPic.getPointValueCol()) {
                                                                   if ("WarningB".equals(pointValueColBean.getKey())) {//入口封闭
                                                                       int valueB = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueB)) {
                                                                           imageWarn.setBackgroundColor(Color.RED);
                                                                       } else {
                                                                           if ("WarningC".equals(pointValueColBean.getKey())) {
                                                                               int valueC = Integer.parseInt(pointValueColBean.getValue());
                                                                               if (array.get(2).toString().startsWith("1", valueC)) {
                                                                                   imageWarn.setBackgroundColor(Color.YELLOW);
                                                                               } else {
                                                                                   imageWarn.setBackgroundColor(Color.GREEN);
                                                                               }
                                                                           }
                                                                       }
                                                                   }

                                                                   if ("IsAuto".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           imageModel.setBackgroundColor(Color.GREEN);
                                                                       }
                                                                   }
                                                                   if ("IsManual".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           imageModel.setBackgroundColor(Color.GRAY);

                                                                       }
                                                                   }
                                                                   if ("IsPrepairing".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           imageModel.setBackgroundColor(Color.parseColor("#FFA500"));

                                                                       }
                                                                   }
                                                                   if ("IsVoidMode".equals(pointValueColBean.getKey())) {//
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           imageModel.setBackgroundColor(Color.GREEN);

                                                                       }
                                                                   }
                                                                   if ("FWDRotatingHighSpeed".equals(pointValueColBean.getKey())) {//正向高速
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (drawPic.getStandardDirection() == 0) {
                                                                               imageCenter.setImageResource(R.mipmap.icon_64);

                                                                           }

                                                                       }
                                                                   }
                                                                   if ("REVRotatingHighSpeed".equals(pointValueColBean.getKey())) {//反向高速
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (drawPic.getStandardDirection() == 0) {
                                                                               imageCenter.setImageResource(R.mipmap.icon_65);

                                                                           }
                                                                       }
                                                                   }
                                                                   if ("FWDRotatingLowSpeed".equals(pointValueColBean.getKey())) {//正向低速
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (drawPic.getStandardDirection() == 0) {
                                                                               imageCenter.setImageResource(R.mipmap.icon_66);

                                                                           }
                                                                       }
                                                                   }
                                                                   if ("REVRotatingLowSpeed".equals(pointValueColBean.getKey())) {//反向低速
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (drawPic.getStandardDirection() == 0) {
                                                                               imageCenter.setImageResource(R.mipmap.icon_67);

                                                                           }

                                                                       }
                                                                   }
                                                               }

                                                           }
                                                       }
                                                   }
                                               }

                                           }

                                       }
                                   }


                               }

                           }

                );
    }

    //初始化Robot
    private void initSTLBed(final String params) {
        Log.d(TAG, "initRobot() called with: params = [" + params + "]");
        HttpManager.getInstance().getHttpService().getDHPointValues2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   JSONObject jsonObject1 = JSON.parseObject(responseBody.string());
                                   Log.d("EqControlItemFragment2", jsonObject1.toJSONString());
                                   for (Map<View, DrawPic> map : mapArrayList) {
                                       for (Map.Entry<View, DrawPic> m : map.entrySet()) {
                                           View view = m.getKey();
                                           DrawPic drawPic = m.getValue();
                                           if ("STLBed".equals(drawPic.getItemType())) {
                                               View view1 = view.findViewById(R.id.view);
                                               Log.d("EqControlItemFragment", "view1.getLeft()+view1.getTop():" + (view1.getLeft() + view1.getTop()));
                                               View viewTop = view.findViewById(R.id.view_top);

//                                               int height = ((int) drawPic.getH() - viewTop.getHeight() - view1.getHeight()) / (drawPic.getStrBins().split(",").length);
                                               initSTLRollingBed(drawPic.getRollingBed().getPointName(), view1, drawPic);

                                               if (TextUtils.isEmpty(drawPic.getStrBins())) return;
                                               String[] posi = drawPic.getStrBins().split(",");
                                               int height = ((int) drawPic.getH() - 120) * 2 / 100;
                                               Log.d("EqControlItemFragment", "height:" + height);
                                               ViewGroup.MarginLayoutParams params1 = (ViewGroup.MarginLayoutParams) view1.getLayoutParams();
                                               if ("0".equals(jsonObject1.getString("a"))) {
                                                   if (jsonObject1.getJSONObject("b").containsKey(drawPic.getRollingBed().getPointName())) {
                                                       JSONArray array = jsonObject1.getJSONObject("b").getJSONArray(drawPic.getRollingBed().getPointName());
                                                       Log.d("EqControlItemFragment", "array:" + array);
                                                       if (!array.isEmpty() && array.size() > 2) {
                                                           if ("2".equals(array.get(0)) && "1".equals(array.get(1))) {

                                                               for (DrawPic.PointValueColBean pointValueColBean : drawPic.getPointValueCol()) {
                                                                   if ("Position1".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 1) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[0]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position1");

                                                                       }

                                                                   }
                                                                   if ("Position2".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 2) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[1]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position2");
                                                                       }

                                                                   }
                                                                   if ("Position3".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 3) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[2]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position4".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 4) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[3]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position5".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 5) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[4]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position6".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 6) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[5]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position7".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 7) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[6]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position8".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 8) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[7]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                               }
                                                           }
                                                       }
                                                   }
                                               }

//                                               view1.layout(view1.getLeft(), view1.getTop() + 30, view1.getRight(), view1.getBottom() + 30);

                                           }

                                       }
                                   }


                               }

                           }

                );
    }

    //初始化Robot
    private void initSTLBedExRotate(final String params) {
        Log.d(TAG, "initRobot() called with: params = [" + params + "]");
        HttpManager.getInstance().getHttpService().getDHPointValues2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   JSONObject jsonObject1 = JSON.parseObject(responseBody.string());
                                   Log.d("EqControlItemFragment2", jsonObject1.toJSONString());
                                   for (Map<View, DrawPic> map : mapArrayList) {
                                       for (Map.Entry<View, DrawPic> m : map.entrySet()) {
                                           View view = m.getKey();
                                           DrawPic drawPic = m.getValue();
                                           if ("STLBedExRotate".equals(drawPic.getItemType())) {
                                               View view1 = view.findViewById(R.id.view);
                                               Log.d("EqControlItemFragment", "view1.getLeft()+view1.getTop():" + (view1.getLeft() + view1.getTop()));
                                               View viewTop = view.findViewById(R.id.view_top);

//                                               int height = ((int) drawPic.getH() - viewTop.getHeight() - view1.getHeight()) / (drawPic.getStrBins().split(",").length);
                                               initSTLRollingBed(drawPic.getRollingBed().getPointName(), view1, drawPic);

                                               if (TextUtils.isEmpty(drawPic.getStrBins())) return;
                                               String[] posi = drawPic.getStrBins().split(",");
                                               int height = ((int) drawPic.getH() - (220 + 120)) * 2 / 100;
                                               Log.d("EqControlItemFragment", "height:" + height);
                                               ViewGroup.MarginLayoutParams params1 = (ViewGroup.MarginLayoutParams) view1.getLayoutParams();
                                               if ("0".equals(jsonObject1.getString("a"))) {
                                                   if (jsonObject1.getJSONObject("b").containsKey(drawPic.getRollingBed().getPointName())) {
                                                       JSONArray array = jsonObject1.getJSONObject("b").getJSONArray(drawPic.getRollingBed().getPointName());
                                                       Log.d("EqControlItemFragment", "array:" + array);
                                                       if (!array.isEmpty() && array.size() > 2) {
                                                           if ("2".equals(array.get(0)) && "1".equals(array.get(1))) {

                                                               for (DrawPic.PointValueColBean pointValueColBean : drawPic.getPointValueCol()) {
                                                                   if ("Position1".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 1) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[0]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position1");

                                                                       }

                                                                   }
                                                                   if ("Position2".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 2) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[1]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position2");
                                                                       }

                                                                   }
                                                                   if ("Position3".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 3) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[2]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position4".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 4) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[3]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position5".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 5) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[4]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position6".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 6) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[5]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position7".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 7) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[6]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                                   if ("Position8".equals(pointValueColBean.getKey())) {
                                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                           if (posi.length < 8) return;
                                                                           params1.leftMargin = view1.getLeft();
                                                                           params1.topMargin = height * Integer.parseInt(posi[7]);
                                                                           view1.setLayoutParams(params1);
                                                                           Log.d("EqControlItemFragment", "Position3");
                                                                       }

                                                                   }
                                                               }
                                                           }
                                                       }
                                                   }
                                               }

//                                               view1.layout(view1.getLeft(), view1.getTop() + 30, view1.getRight(), view1.getBottom() + 30);

                                           }

                                       }
                                   }


                               }

                           }

                );
    }

    //初始化STLRollingBed
    private void initSTLRollingBed(String params, final View view, final DrawPic drawPic) {
        Log.d(TAG, "initSTLRollingBed() called with: params = [" + params + "]");
        HttpManager.getInstance().getHttpService().getDHPointValues2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   JSONObject jsonObject1 = JSON.parseObject(responseBody.string());
                                   Log.d(TAG, "accept() called with: responseBody = [" + responseBody.string() + "]");

                                   ImageView imageLockEntry = (ImageView) view.findViewById(R.id.image_lock_entry);
                                   ImageView imageLockExit = (ImageView) view.findViewById(R.id.image_lock_exit);
                                   ImageView imageCenterCar = (ImageView) view.findViewById(R.id.image_center_car);
                                   ImageView imageWarn = (ImageView) view.findViewById(R.id.image_warn);
                                   ImageView imageModel = (ImageView) view.findViewById(R.id.image_model);
                                   ImageView imageCenter = (ImageView) view.findViewById(R.id.image_center);
                                   if ("0".equals(jsonObject1.getString("a"))) {
                                       if (jsonObject1.getJSONObject("b").containsKey(drawPic.getRollingBed().getPointName())) {
                                           JSONArray array = jsonObject1.getJSONObject("b").getJSONArray(drawPic.getRollingBed().getPointName());
                                           if (!array.isEmpty() && array.size() > 2 && "2".equals(array.get(0)) && "1".equals(array.get(1))) {
                                               for (DrawPic.RollingBedBean.PointValueColBean pointValueColBean : drawPic.getRollingBed().getPointValueCol()) {
                                                   if ("EntryClosed".equals(pointValueColBean.getKey())) {//入口封闭
                                                       int value = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", value)) {
                                                           imageLockEntry.setVisibility(View.VISIBLE);
                                                       } else {
                                                           imageLockEntry.setVisibility(View.INVISIBLE);
                                                       }
                                                   }
                                                   if ("ExitClosed".equals(pointValueColBean.getKey())) {//出口封闭
                                                       int value = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", value)) {
//                                                           imageLockExit.setImageResource(R.mipmap.icon_32);
                                                           imageLockExit.setVisibility(View.VISIBLE);
                                                       } else {
//                                                           imageLockExit.setImageBitmap(null);
                                                           imageLockExit.setVisibility(View.INVISIBLE);
                                                       }
                                                   }
                                                   if ("WarningB".equals(pointValueColBean.getKey())) {//入口封闭
                                                       int valueB = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueB)) {
                                                           imageWarn.setBackgroundColor(Color.RED);
                                                           Log.d("EqControlItemFragment", "红色");
                                                       } else {
                                                           if ("WarningC".equals(pointValueColBean.getKey())) {
                                                               int valueC = Integer.parseInt(pointValueColBean.getValue());
                                                               if (array.get(2).toString().startsWith("1", valueC)) {
                                                                   imageWarn.setBackgroundColor(Color.YELLOW);
                                                                   Log.d("EqControlItemFragment", "黄色");
                                                               } else {
                                                                   imageWarn.setBackgroundColor(Color.GREEN);
                                                                   Log.d("EqControlItemFragment", "绿色");
                                                               }
                                                           }
                                                       }


                                                   }

                                                   if ("IsAuto".equals(pointValueColBean.getKey())) {
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                           imageModel.setBackgroundColor(Color.GREEN);
                                                       }
                                                   }
                                                   if ("IsManual".equals(pointValueColBean.getKey())) {
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                           imageModel.setBackgroundColor(Color.GRAY);

                                                       }
                                                   }
                                                   if ("IsPrepairing".equals(pointValueColBean.getKey())) {
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                           imageModel.setBackgroundColor(Color.parseColor("#FFA500"));

                                                       }
                                                   }
                                                   if ("IsVoidMode".equals(pointValueColBean.getKey())) {//
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                           imageModel.setBackgroundColor(Color.GREEN);

                                                       }
                                                   }
                                                   if ("Exists".equals(pointValueColBean.getKey())) {//
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
//                                                           imageCenterCar.setImageResource(R.mipmap.icon_31);
                                                           imageCenterCar.setVisibility(View.VISIBLE);
                                                       } else {
                                                           imageCenterCar.setVisibility(View.INVISIBLE);
//                                                           imageCenterCar.setImageBitmap(null);
                                                       }
                                                   }
                                                   if ("FWDRotatingHighSpeed".equals(pointValueColBean.getKey())) {//正向高速
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                           if (drawPic.getRollingBed().getStandardDirection() == 0) {
                                                               imageCenter.setImageResource(R.mipmap.icon_60);

                                                           }

                                                       }
                                                   }
                                                   if ("REVRotatingHighSpeed".equals(pointValueColBean.getKey())) {//反向高速
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                           if (drawPic.getRollingBed().getStandardDirection() == 0) {
                                                               imageCenter.setImageResource(R.mipmap.icon_50);

                                                           }
                                                       }
                                                   }
                                                   if ("FWDRotatingLowSpeed".equals(pointValueColBean.getKey())) {//正向低速
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                           if (drawPic.getRollingBed().getStandardDirection() == 0) {
                                                               imageCenter.setImageResource(R.mipmap.icon_61);

                                                           }
                                                       }
                                                   }
                                                   if ("REVRotatingLowSpeed".equals(pointValueColBean.getKey())) {//反向低速
                                                       int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                       if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                           if (drawPic.getRollingBed().getStandardDirection() == 0) {
                                                               imageCenter.setImageResource(R.mipmap.icon_51);

                                                           }

                                                       }
                                                   }
                                               }

//
                                           }
                                       }
                                   }


                               }

                           }

                );
    }

    //初始化RollingBed
    private void initRollingBed(String params) {
        Log.d(TAG, "initRollingBed() called with: params = [" + params + "]");
        HttpManager.getInstance().getHttpService().getDHPointValues2(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   JSONObject jsonObject1 = JSON.parseObject(responseBody.string());
                                   Log.d(TAG, "accept() called with: responseBody = [" + responseBody.string() + "]");
                                   for (Map<View, DrawPic> map : mapArrayList) {
                                       for (Map.Entry<View, DrawPic> m : map.entrySet()) {
                                           View view = m.getKey();
                                           DrawPic drawPic = m.getValue();
                                           if ("RollingBed".equals(drawPic.getItemType())) {
                                               ImageView imageLockEntry = (ImageView) view.findViewById(R.id.image_lock_entry);
                                               ImageView imageLockExit = (ImageView) view.findViewById(R.id.image_lock_exit);
                                               ImageView imageCenterCar = (ImageView) view.findViewById(R.id.image_center_car);
                                               ImageView imageWarn = (ImageView) view.findViewById(R.id.image_warn);
                                               ImageView imageModel = (ImageView) view.findViewById(R.id.image_model);
                                               ImageView imageCenter = (ImageView) view.findViewById(R.id.image_center);
                                               if ("0".equals(jsonObject1.getString("a"))) {
                                                   if (jsonObject1.getJSONObject("b").containsKey(drawPic.getPointName())) {
                                                       JSONArray array = jsonObject1.getJSONObject("b").getJSONArray(drawPic.getPointName());
                                                       if (!array.isEmpty() && array.size() > 2 && "2".equals(array.get(0)) && "1".equals(array.get(1))) {
                                                           for (DrawPic.PointValueColBean pointValueColBean : drawPic.getPointValueCol()) {
                                                               if ("EntryClosed".equals(pointValueColBean.getKey())) {//入口封闭
                                                                   int value = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", value)) {
                                                                       imageLockEntry.setImageResource(R.mipmap.icon_32);
                                                                   } else {
                                                                       imageLockEntry.setImageBitmap(null);
                                                                   }
                                                               }
                                                               if ("ExitClosed".equals(pointValueColBean.getKey())) {//出口封闭
                                                                   int value = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", value)) {
                                                                       imageLockExit.setImageResource(R.mipmap.icon_32);
                                                                   } else {
                                                                       imageLockExit.setImageBitmap(null);
                                                                   }
                                                               }
                                                               if ("WarningB".equals(pointValueColBean.getKey())) {//入口封闭
                                                                   int valueB = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueB)) {
                                                                       imageWarn.setBackgroundColor(Color.RED);
                                                                       Log.d("EqControlItemFragment", "红色");
                                                                   } else {
                                                                       if ("WarningC".equals(pointValueColBean.getKey())) {
                                                                           int valueC = Integer.parseInt(pointValueColBean.getValue());
                                                                           if (array.get(2).toString().startsWith("1", valueC)) {
                                                                               imageWarn.setBackgroundColor(Color.YELLOW);
                                                                               Log.d("EqControlItemFragment", "黄色");
                                                                           } else {
                                                                               imageWarn.setBackgroundColor(Color.GREEN);
                                                                               Log.d("EqControlItemFragment", "绿色");
                                                                           }
                                                                       }
                                                                   }


                                                               }

                                                               if ("IsAuto".equals(pointValueColBean.getKey())) {
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                       imageModel.setBackgroundColor(Color.GREEN);
                                                                   }
                                                               }
                                                               if ("IsManual".equals(pointValueColBean.getKey())) {
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                       imageModel.setBackgroundColor(Color.GRAY);

                                                                   }
                                                               }
                                                               if ("IsPrepairing".equals(pointValueColBean.getKey())) {
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                       imageModel.setBackgroundColor(Color.parseColor("#FFA500"));

                                                                   }
                                                               }
                                                               if ("IsVoidMode".equals(pointValueColBean.getKey())) {//
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                       imageModel.setBackgroundColor(Color.GREEN);

                                                                   }
                                                               }
                                                               if ("Exists".equals(pointValueColBean.getKey())) {//
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
//                                                                       imageCenterCar.setImageResource(R.mipmap.icon_31);
                                                                       imageCenterCar.setVisibility(View.VISIBLE);
                                                                   } else {
                                                                       imageCenterCar.setVisibility(View.INVISIBLE);
//                                                                       imageCenterCar.setImageBitmap(null);
                                                                   }
                                                               }
                                                               if ("FWDRotatingHighSpeed".equals(pointValueColBean.getKey())) {//正向高速
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                       if (drawPic.getStandardDirection() == 0) {
                                                                           imageCenter.setImageResource(R.mipmap.icon_60);

                                                                       }

                                                                   }
                                                               }
                                                               if ("REVRotatingHighSpeed".equals(pointValueColBean.getKey())) {//反向高速
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                       if (drawPic.getStandardDirection() == 0) {
                                                                           imageCenter.setImageResource(R.mipmap.icon_50);

                                                                       }
                                                                   }
                                                               }
                                                               if ("FWDRotatingLowSpeed".equals(pointValueColBean.getKey())) {//正向低速
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                       if (drawPic.getStandardDirection() == 0) {
                                                                           imageCenter.setImageResource(R.mipmap.icon_61);

                                                                       }
                                                                   }
                                                               }
                                                               if ("REVRotatingLowSpeed".equals(pointValueColBean.getKey())) {//反向低速
                                                                   int valueAuto = Integer.parseInt(pointValueColBean.getValue());
                                                                   if (array.get(2).toString().startsWith("1", valueAuto)) {
                                                                       if (drawPic.getStandardDirection() == 0) {
                                                                           imageCenter.setImageResource(R.mipmap.icon_51);

                                                                       }

                                                                   }
                                                               }
                                                           }

//
                                                       }
                                                   }
                                               }

                                           }

                                       }
                                   }


                               }

                           }

                );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_item, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
        if (timer != null) {
            timer.cancel();
        }


    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */

    public static int px2dip(Context context, float pxValue) {
        Log.d("EqControlItemFragment", "pxValue:" + pxValue);
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);

    }

    // 方法一
    public float getRawSize(int unit, float value) {
        Resources res = this.getResources();
        return TypedValue.applyDimension(unit, value, res.getDisplayMetrics());
    }

}
