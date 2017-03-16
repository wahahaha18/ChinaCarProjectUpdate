package com.ycl.car.utils;

import java.util.regex.Pattern;

/**
 * Created by y11621546 on 2017/3/8.
 */

public class RegUtil {
    /**
     * 校验IP
     *
     * @param ip
     * @return
     */
    public static boolean isIP(String ip) {
        String rexp = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
        Pattern pattern = Pattern.compile(rexp);
        return pattern.matcher(ip).find();
    }

    /**
     * 校验port
     *
     * @param port
     * @return
     */
    public static boolean isPort(String port) {
        String rexp = "([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5])";
        Pattern pattern = Pattern.compile(rexp);
        return pattern.matcher(port).find();
    }

    /**
     * 校验port
     *
     * @param http
     * @return
     */
    public static boolean isHttp(String http) {
        String rexp = "http(s)?://*?";
        Pattern pattern = Pattern.compile(rexp);
        return pattern.matcher(http).find();
    }
}
