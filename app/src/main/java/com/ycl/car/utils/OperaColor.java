package com.ycl.car.utils;


import android.graphics.Color;

/**
 * Created by y11621546 on 2017/2/20.
 */

public class OperaColor {


    /**
     * * Returns the HEX value representing the colour in the default sRGB
     * ColorModel. * * @return the HEX value of the colour in the default sRGB
     * ColorModel
     */
    public String getHex(int color) {
        return toHex((color & 0xff0000) >> 16, (color & 0x00ff00) >> 8, (color & 0x0000ff));
    }


    /**
     * * Returns a web browser-friendly HEX value representing the colour in the
     * default sRGB * ColorModel. * * @param r red * @param g green * @param b
     * blue * @return a browser-friendly HEX value
     */
    public static String toHex(int r, int g, int b) {
        return "#" + toBrowserHexValue(r) + toBrowserHexValue(g)
                + toBrowserHexValue(b);
    }

    private static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(
                Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }
}
