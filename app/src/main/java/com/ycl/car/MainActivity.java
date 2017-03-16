package com.ycl.car;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ycl.car.fragment.CommunityFragment;
import com.ycl.car.fragment.ControlFragment;
import com.ycl.car.fragment.EnterpriseFragment;
import com.ycl.car.fragment.FirstFragment;
import com.ycl.car.fragment.MyFragment;
import com.ycl.car.fragment.NewsFragment;
import com.ycl.car.model.TabEntity;
import com.ycl.car.utils.BusProvider;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AutoLayoutActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.tl_bottom)
    CommonTabLayout tlBottom;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private SharedPreferences sharedPreferences;
    private static MainActivity instance;
    //    ActivityMainBinding binding;
    private String[] mTitles_user = {"首页", "企业", "新闻", "社区", "管理", "我的"};
    private int[] mIconUnselectIds_user = {
            R.mipmap.shouye2_2x, R.mipmap.qiye2_2x, R.mipmap.xinwen2_2x, R.mipmap.shequ2_2x,
            R.mipmap.guanli2_2x, R.mipmap.wode2_2x};
    private int[] mIconSelectIds_user = {
            R.mipmap.shouye1_2x, R.mipmap.qiye1_2x, R.mipmap.xinwen1_2x, R.mipmap.shequ1_2x,
            R.mipmap.guanli1_2x, R.mipmap.wode1_2x};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<CustomTabEntity>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private onToolbarRightClickListenr onToolbarRightClickListenr;
    IntentReceiver receiver;
    LocalBroadcastManager localBroadcastManager;

    //toolbar右侧点击事件
    public void setOnToolbarRightClickListenr(MainActivity.onToolbarRightClickListenr onToolbarRightClickListenr) {
        this.onToolbarRightClickListenr = onToolbarRightClickListenr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        instance = this;
        for (int i = 0; i < mTitles_user.length; i++) {
            mTabEntities.add(new TabEntity(mTitles_user[i], mIconSelectIds_user[i], mIconUnselectIds_user[i]));
        }
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        initTab(0);
        fragmentList.add(FirstFragment.newInstance());
        fragmentList.add(EnterpriseFragment.newInstance());
        fragmentList.add(NewsFragment.newInstance());
        fragmentList.add(CommunityFragment.newInstance());
        fragmentList.add(ControlFragment.newInstance());
        fragmentList.add(MyFragment.newInstance());
//        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
//        viewpager.setAdapter(adapter);
        tlBottom.setTabData(mTabEntities);

        toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onToolbarRightClickListenr != null) {
                    onToolbarRightClickListenr.onToolbarClick(v);
                }
            }
        });
        tlBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                initTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", 9);
                NextActivity.start(MainActivity.this, bundle);

            }
        });
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        receiver = new IntentReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("asdfasfas");
//        registerReceiver(receiver, intentFilter);
        BusProvider.getInstance().register(this);

    }


    public class IntentReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MyReceiver", "asfdasdfasfdfsasdf" + intent.getAction());
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("MainActivity", "onNewIntent() called with: intent = [" + intent + "]");

    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(receiver);
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivity", "data.getExtras():" + data.getExtras());
    }

    //    初始化tab
    private void initTab(int index) {
//        viewpager.setCurrentItem(index);


        switch (index) {
            case 0:
                toolbarRight.setText("");
                toolbarTitle.setText("中汽工程");
                Drawable drawable = getResources().getDrawable(R.mipmap.qiyewenhua1_2x);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                toolbarTitle.setCompoundDrawables(drawable, null, null, null);
                fab.setVisibility(View.GONE);
                addFragment(FirstFragment.newInstance(), false);
                break;
            case 1:
                toolbarRight.setText("");
                toolbarTitle.setText("企业");
                toolbarTitle.setCompoundDrawables(null, null, null, null);
                fab.setVisibility(View.GONE);
                addFragment(EnterpriseFragment.newInstance(), false);
                break;
            case 2:
                toolbarRight.setText("");
                toolbarTitle.setText("新闻");
                toolbarTitle.setCompoundDrawables(null, null, null, null);
                fab.setVisibility(View.GONE);
                addFragment(NewsFragment.newInstance(), false);
                break;
            case 3:
                toolbarRight.setText("");
                toolbarTitle.setText("社区");
                toolbarTitle.setCompoundDrawables(null, null, null, null);
                fab.setVisibility(View.VISIBLE);
                addFragment(CommunityFragment.newInstance(), false);
                break;
            case 4:
                toolbarRight.setText("");
                toolbarTitle.setText("管理");
                toolbarTitle.setCompoundDrawables(null, null, null, null);
                fab.setVisibility(View.GONE);
                addFragment(ControlFragment.newInstance(), false);
                break;
            case 5:
                toolbarTitle.setText("");
                fab.setVisibility(View.GONE);
                if (sharedPreferences.contains("user")) {
                    toolbarRight.setText("退出登录");
                } else {
                    toolbarRight.setText("管理登录");
                }
                toolbarTitle.setCompoundDrawables(null, null, null, null);
                addFragment(MyFragment.newInstance(), false);
                break;
        }

    }

    public TextView getToolbarRight() {
        return toolbarRight;
    }

    public TextView getToolbarTitle() {
        return toolbarTitle;
    }

    public interface onToolbarRightClickListenr {
        void onToolbarClick(View view);
    }

    //添加fragment
    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        if (fragment == null) return;
        if (isAddToBackStack) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }

    }
}
