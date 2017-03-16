package com.ycl.car.model;

/**
 * 我的
 * Created by y11621546 on 2017/1/18.
 */

public class MyItem {
    private int imageUrl;
    private String title;
    private String imageRightUrl;

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageRightUrl() {
        return imageRightUrl;
    }

    public void setImageRightUrl(String imageRightUrl) {
        this.imageRightUrl = imageRightUrl;
    }

    public MyItem(int imageUrl, String title, String imageRightUrl) {
        this.imageUrl = imageUrl;

        this.title = title;
        this.imageRightUrl = imageRightUrl;
    }
}
