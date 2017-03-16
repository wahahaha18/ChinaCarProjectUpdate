package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class Msg {
    /**
     * id : 3
     * ptitle : 测试标题3
     * pushcontent : null
     * time : 2017-03-02
     * isread : 1
     */

    private int id;
    private String ptitle;
    private Object pushcontent;
    private String time;
    private int isread;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public Object getPushcontent() {
        return pushcontent;
    }

    public void setPushcontent(Object pushcontent) {
        this.pushcontent = pushcontent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIsread() {
        return isread;
    }

    public void setIsread(int isread) {
        this.isread = isread;
    }
}
