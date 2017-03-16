package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/2/9.
 */

public class Collection {
    private String title;
    private String content;
    private boolean isCollect;

    public Collection(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }
}
