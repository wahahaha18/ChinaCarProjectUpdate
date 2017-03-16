package com.ycl.car.utils;

import android.view.View;
import android.widget.Toast;

/**
 * Created by y11621546 on 2017/2/21.
 */

public class Handler {
    public static void showToast(View v) {
        Toast.makeText(v.getContext(), "asdfaf", Toast.LENGTH_SHORT).show();
    }
}
