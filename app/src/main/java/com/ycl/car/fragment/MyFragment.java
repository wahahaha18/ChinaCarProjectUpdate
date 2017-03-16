package com.ycl.car.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.ContantValue;
import com.ycl.car.MainActivity;
import com.ycl.car.MyApp;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.MyPageItemAdapter;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.MyItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的
 * <p>
 * Created by y11621546 on 2017/1/16.
 */

public class MyFragment extends BasePageFragment {

    @BindView(R.id.toolbar_head)
    ImageView toolbarHead;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.rv_fix)
    RecyclerView rvFix;
    private SharedPreferences sharedPreferences;

    LoginResponse.BBean user;
    private String number = "10086";

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = MyApp.getInstance().getSharedPreferences();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_six, container, false);
        ButterKnife.bind(this, view);
        List<MyItem> myItemList = new ArrayList<>();
        myItemList.add(new MyItem(R.mipmap.gerenziliao1_2x, "个人资料", String.valueOf(R.mipmap.jiantou1_2x)));
        myItemList.add(new MyItem(R.mipmap.wodetiezi1_2x, "我的帖子 ", String.valueOf(R.mipmap.jiantou1_2x)));
        myItemList.add(new MyItem(R.mipmap.wodeshoucang1_2x, "我的收藏", String.valueOf(R.mipmap.jiantou1_2x)));
        myItemList.add(new MyItem(R.mipmap.xiaoxitongzhi1_2x, "消息通知", String.valueOf(R.mipmap.jiantou1_2x)));
        myItemList.add(new MyItem(R.mipmap.lianxiwomen1_2x, "联系我们", number));
        myItemList.add(new MyItem(R.mipmap.guanyuzhongqi1_2x, "关于中汽", String.valueOf(R.mipmap.jiantou1_2x)));
        myItemList.add(new MyItem(R.mipmap.shezhi1_2x, "设置", String.valueOf(R.mipmap.jiantou1_2x)));
        MyPageItemAdapter adapter = new MyPageItemAdapter(myItemList);
        rvFix.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFix.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvFix.setAdapter(adapter);
        rvFix.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        if (sharedPreferences.contains("user")) {
                            bundle.putString("sen", "user_info");
                        } else {
                            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        if (sharedPreferences.contains("user")) {
                            bundle.putString("sen", "post");
                        } else {
                            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (sharedPreferences.contains("user")) {
                            bundle.putString("sen", "collection");
                        } else {
                            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        if (sharedPreferences.contains("user")) {
                            bundle.putString("sen", "message");
                        } else {
                            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                .setTitle("是否拨打电话")
                                .setMessage(number)
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                                        startActivity(intent);
                                    }
                                });
                        builder.show();
                        break;
                    case 5:
                        if (sharedPreferences.contains("user"))
                            bundle.putString("sen", "about");
                        break;
                    case 6:
                        bundle.putString("sen", "setting");
                        break;
                }
                if (position != 4) {
                    NextActivity.start(getContext(), bundle);
                }

//                if (position == 5 || position == 6) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("position", position);
//                    NextActivity.start(getContext(), bundle);
//                } else if (position == 4) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
//                            .setTitle("是否拨打电话")
//                            .setMessage(number)
//                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//
//                                }
//                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
//                                    startActivity(intent);
//                                }
//                            });
//                    builder.show();
//
//                } else {
//                    if (sharedPreferences.contains("user")) {
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("position", position);
//                        NextActivity.start(getContext(), bundle);
//                    } else {
//                        Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
//                    }
//                }


            }
        });
        ((MainActivity) getActivity()).setOnToolbarRightClickListenr(new MainActivity.onToolbarRightClickListenr() {
            @Override
            public void onToolbarClick(View view) {
                String text = ((TextView) view).getText().toString().trim();
                if ("退出登录".equals(text)) {
                    if (sharedPreferences.contains("user")) {
                        sharedPreferences.edit().remove("user").apply();
                        sharedPreferences.edit().remove("control").apply();
                        tvAccount.setText("");
                        setTitleRight("管理登录");
                    }
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("sen", "login");
                    bundle.putString(ContantValue.TOOLBAR_TITLE, "管理登录");
                    bundle.putString(ContantValue.TOOLBAR_RIGHT, "登录");
                    NextActivity.start(getContext(), bundle);
                }

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("MyFragment.onResume");
        if (sharedPreferences.contains("user")) {
            user = JSON.parseObject(sharedPreferences.getString("user", ""), LoginResponse.BBean.class);
            tvAccount.setText("账号:" + user.getU_name());
            setTitleRight("退出登录");
        } else {
            setTitleRight("管理登录");
            tvAccount.setText("");
        }
    }

    @Override
    public void fetchData() {

    }
}
