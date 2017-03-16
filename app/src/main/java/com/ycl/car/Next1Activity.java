package com.ycl.car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.squareup.otto.Subscribe;
import com.ycl.car.fragment.AlarmRealTimeFragment;
import com.ycl.car.fragment.CategoryNextFragment;
import com.ycl.car.fragment.ChartFragment;
import com.ycl.car.fragment.EqControlNextInfoFragment;
import com.ycl.car.fragment.EqStartingRateFragment;
import com.ycl.car.fragment.HandleFragment;
import com.ycl.car.fragment.HistoryAlarmFragment;
import com.ycl.car.fragment.LedgerFragment;
import com.ycl.car.fragment.MaintainEqFragment;
import com.ycl.car.fragment.MaintainInfoFragment;
import com.ycl.car.fragment.PMFragment;
import com.ycl.car.fragment.QrCodeEqDetailFragment;
import com.ycl.car.fragment.SparePartLibraryFragment;
import com.ycl.car.fragment.TPMFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 管理二级内页
 */
public class Next1Activity extends AutoLayoutActivity {

    private static final String BUNDLE = "bundle";
    private static final String TITLE = "title";
    Bundle bundle;
    Fragment fragment;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_container_next)
    FrameLayout fragmentContainerNext;
    @BindView(R.id.activity_next)
    LinearLayout activityNext;

    public TextView getToolbarTitle() {
        return toolbarTitle;
    }

    public TextView getToolbarRight() {
        return toolbarRight;
    }

    public static void start(Context context, Bundle bundle) {
        Intent starter = new Intent(context, Next1Activity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        starter.putExtra(BUNDLE, bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getBundleExtra(BUNDLE);
//        BusProvider.getInstance().register(this);
        if (bundle == null) return;
//        toolbarTitle.setText(getIntent().getBundleExtra(BUNDLE).getString(TITLE, TITLE));
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.fanhui1_2x);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getFragments().size() < 2) {
                    finish();
                } else {
                    onBackPressed();
                }

            }
        });
//        Log.e("Next1Activity", "bundle："+bundle.getString("sen", ""));
        switch (bundle.getString("sen", "")) {


            case ""://9
                fragment = CategoryNextFragment.newInstance(bundle.getString(TITLE, TITLE));
                break;
            case "wx_ssbj"://34
                fragment = AlarmRealTimeFragment.newInstance(bundle);
                break;
            case "wx_yj_ls"://58
                fragment = HistoryAlarmFragment.newInstance(bundle);
                break;
            case "wx_zl"://36
                fragment = MaintainInfoFragment.newInstance(bundle);
                break;
            case "40"://40
                fragment = ChartFragment.newInstance();
                break;
            case "wx_wx"://60
                fragment = MaintainEqFragment.newInstance(bundle);
                break;
            case "wx_sb"://59
                fragment = LedgerFragment.newInstance(bundle);
                break;
            case "wx_znbjk"://37
                fragment = SparePartLibraryFragment.newInstance(bundle);
                break;
            case "wx_pm"://38
                fragment = PMFragment.newInstance();
                break;
            case "wx_tpm"://39
                fragment = TPMFragment.newInstance();
                break;
            case "qrcode"://-1
                fragment = QrCodeEqDetailFragment.newInstance(bundle);
                break;
            case "sb_dt"://18
                fragment = EqControlNextInfoFragment.newInstance(bundle);
                break;
            case "webview"://
                fragment = EqStartingRateFragment.newInstance(bundle);
                break;
            default:
                fragment = CategoryNextFragment.newInstance(bundle.getString(TITLE, TITLE));

        }
        if (fragment == null) return;
        addFragment(fragment, false);

    }

    @Subscribe
    public void test(String result) {
        if (TextUtils.isEmpty(result)) return;
        JSONObject jsonObject = JSON.parseObject(result);
        String a = jsonObject.getString("a");
        if (TextUtils.isEmpty(a)) return;
        Bundle bundle = new Bundle();
        if ("1".equals(a)) {//维修
            bundle.putString("id", jsonObject.getString("b"));
            bundle.putString("flag", jsonObject.getString("repair"));

        } else if ("2".equals(a)) {//PM
            bundle.putString("id", jsonObject.getString("b"));
            bundle.putString("flag", jsonObject.getString("pm"));

        } else if ("3".equals(a)) {//TPM
            bundle.putString("id", jsonObject.getString("b"));
            bundle.putString("flag", jsonObject.getString("tpm"));
        }
        addFragment(HandleFragment.newInstance(bundle), false);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        BusProvider.getInstance().post(new MessageEvent(""));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (getSupportFragmentManager().getFragments().size() < 2) {
                finish();
            } else {
                onBackPressed();
            }

        }
        return true;
    }

    //添加fragment
    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        if (fragment == null) return;

        if (isAddToBackStack) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_next, fragment).addToBackStack("").commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_next, fragment).commit();
        }

    }


}
