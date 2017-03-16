package com.ycl.car.utils;

/**
 * Created by y11621546 on 2017/3/7.
 */

import com.squareup.otto.Bus;

public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
    }
}
