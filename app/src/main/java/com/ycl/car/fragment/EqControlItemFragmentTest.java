package com.ycl.car.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
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

public class EqControlItemFragmentTest extends BasePageFragment {
    int text = 0;
    private final String TAG = EqControlItemFragmentTest.class.getName();
    String params;
    private static final long delay = 5 * 1000;
    private static final long period = 5 * 1000;
    @BindView(R.id.ll)
    AbsoluteLayout ll;
    private StringBuilder robotPointValueBuilder = new StringBuilder();
    private StringBuilder rollingBedpointValueBuilder = new StringBuilder();
    private StringBuilder stlBedPointValueBuilder = new StringBuilder();
    private StringBuilder stlTopBedPointValueBuilder = new StringBuilder();
    Timer timer;
    List<Map<View, DrawPic>> mapArrayList = new ArrayList<>();

    public static EqControlItemFragmentTest newInstance(String params) {

        Bundle args = new Bundle();
        args.putString("params", params);
        EqControlItemFragmentTest fragment = new EqControlItemFragmentTest();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void fetchData() {
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
                            if ("Robot".equals(drawPics.get(i).getItemType())) {
                                robotPointValueBuilder.append(drawPics.get(i).getPointName());
                                robotPointValueBuilder.append(",");
                            } else if ("STLBed".equals(drawPics.get(i).getItemType())) {
                                stlBedPointValueBuilder.append(drawPics.get(i).getPointName());
                                stlBedPointValueBuilder.append(",");
                                stlTopBedPointValueBuilder.append(drawPics.get(i).getRollingBed().getPointName());
                                stlTopBedPointValueBuilder.append(",");
                            } else if ("RollingBed".equals(drawPics.get(i).getItemType())) {
                                rollingBedpointValueBuilder.append(drawPics.get(i).getPointName());
                                rollingBedpointValueBuilder.append(",");
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
                            AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(getDpValue(drawPic.getW()), getDpValue(drawPic.getH()), (int) drawPic.getX(), (int) drawPic.getY());


                            if (0 == drawPic.getStrBorderStyle()) {
                                view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_glassroom, null);
                                ll.addView(view, layoutParams);
                            } else {
                                view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_glassroom_dash, null);
                                ll.addView(view, layoutParams);
                            }

                        } else if ("Robot".equals(drawPic.getItemType())) {
                            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_robot, null);
                            TextView tvTitle = (TextView) view.findViewById(R.id.tv_label);
                            tvTitle.setText(drawPic.getChartLabel());
                            ll.addView(view, new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, (int) drawPic.getX(), (int)
                                    drawPic.getY()));

                        } else if ("RollingBed".equals(drawPic.getItemType())) {
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
                            AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(getDpValue(156), ViewGroup.LayoutParams.WRAP_CONTENT, (int) drawPic.getX(), (int) drawPic.getY());
                            ll.addView(view, params);
                        } else if ("STLBed".equals(drawPic.getItemType())) {
                            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_rollingbed1, null);
                            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                            tv_title.setText(drawPic.getChartLabel());
                            ImageView imageView = (ImageView) view.findViewById(R.id.img);
                            View view1 = view.findViewById(R.id.view);
                            TextView tvTitle1 = (TextView) view1.findViewById(R.id.tv_title);
                            ImageView imageView1 = (ImageView) view1.findViewById(R.id.img);
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
                                return;
                            }
                            if (drawPic.getStandardDirection() == 0) {
                                imageView.setImageResource(R.mipmap.icon_62);
                            } else {
                                imageView.setImageResource(R.mipmap.icon_63);

                            }
                            AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(getDpValue(156), getDpValue(drawPic.getH()), (int) drawPic.getX(), (int) drawPic.getY());
                            ll.addView(view, params);
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
                try {
                    initRobot(robotPointValueBuilder.toString().substring(0, robotPointValueBuilder.length() - 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    initRollingBed(rollingBedpointValueBuilder.toString().substring(0, rollingBedpointValueBuilder.length() - 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    initSTLBed(stlTopBedPointValueBuilder.toString().substring(0, stlTopBedPointValueBuilder.length() - 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    initSTLBedTop(stlBedPointValueBuilder.toString().substring(0, stlBedPointValueBuilder.length() - 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                if (!TextUtils.isEmpty(rollingBedpointValueBuilder.toString())) {
//                    initRobot(rollingBedpointValueBuilder.toString().substring(0, robotPointValueBuilder.length() - 1));
//                }
//                if (!TextUtils.isEmpty(stlTopBedPointValueBuilder.toString())) {
//                    initRobot(stlTopBedPointValueBuilder.toString().substring(0, robotPointValueBuilder.length() - 1));
//                }
//                if (!TextUtils.isEmpty(stlBedPointValueBuilder.toString())) {
//                    initRobot(stlBedPointValueBuilder.toString().substring(0, robotPointValueBuilder.length() - 1));
//                }
//                initRollingBed(rollingBedpointValueBuilder.toString().substring(0, rollingBedpointValueBuilder.length() - 1));
//                initSTLBed(stlTopBedPointValueBuilder.toString().substring(0, stlTopBedPointValueBuilder.length() - 1));
//                initSTLBedTop(stlBedPointValueBuilder.toString().substring(0, stlBedPointValueBuilder.length() - 1));
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, delay, period);

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
                                                                           imageModel.setBackgroundColor(Color.WHITE);

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
                                               int height = ((int) drawPic.getH() - viewTop.getHeight() - view1.getHeight()) / 100;
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
                                                           imageModel.setBackgroundColor(Color.WHITE);

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
                                                                       imageModel.setBackgroundColor(Color.WHITE);

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
        View view = inflater.inflate(R.layout.fragment_control_item_test, container, false);
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
