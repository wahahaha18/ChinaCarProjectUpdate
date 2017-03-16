package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/1/17.
 */

public class TabItem {
    private String title;
    private int imageUrl;

    public TabItem(String title, int imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
