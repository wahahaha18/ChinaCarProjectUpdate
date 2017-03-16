package com.ycl.car.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ycl.car.DetailActivity;
import com.ycl.car.MainActivity;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.NextActivity;
import com.ycl.car.model.LoginResponse;

/**
 * viewpager+fragment LazyLoad
 * Created by y11621546 on 2017/1/19.
 */

public abstract class BasePageFragment extends Fragment {

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    /**
     * 设置 toolbar title
     *
     * @param title
     */
    public void setTitle(String title) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getToolbarTitle().setText(title);
        } else if (getActivity() instanceof NextActivity) {
            ((NextActivity) getActivity()).getToolbarTitle().setText(title);
        } else if (getActivity() instanceof Next1Activity) {
            ((Next1Activity) getActivity()).getToolbarTitle().setText(title);
        } else if (getActivity() instanceof DetailActivity) {
            ((DetailActivity) getActivity()).getToolbarTitle().setText(title);
        }
    }


    /**
     * 设置toolbar right
     *
     * @param right
     */
    public void setTitleRight(String right) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getToolbarRight().setText(right);
        } else if (getActivity() instanceof NextActivity) {
            ((NextActivity) getActivity()).getToolbarRight().setText(right);
        } else if (getActivity() instanceof Next1Activity) {
            ((Next1Activity) getActivity()).getToolbarRight().setText(right);
        } else if (getActivity() instanceof DetailActivity) {
            ((DetailActivity) getActivity()).getToolbarRight().setText(right);
        }
    }

    /**
     * 获取toolbar view
     *
     * @return
     */
    public TextView getToolbarRightView() {
        if (getActivity() instanceof MainActivity) {
            return ((MainActivity) getActivity()).getToolbarRight();
        } else if (getActivity() instanceof NextActivity) {
            return ((NextActivity) getActivity()).getToolbarRight();
        } else if (getActivity() instanceof Next1Activity) {
            return ((Next1Activity) getActivity()).getToolbarRight();
        } else if (getActivity() instanceof DetailActivity) {
            return ((DetailActivity) getActivity()).getToolbarRight();
        }
        return null;
    }

    //获取用户信息
    public LoginResponse.BBean getUser() {
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        LoginResponse.BBean userInfo = null;
        if (sharedPreferences.contains("user")) {
            userInfo = JSON.parseObject(sharedPreferences.getString("user", "{}"), LoginResponse.BBean.class);
        } else {
            userInfo = new LoginResponse.BBean();
        }
        return userInfo;
    }


}
