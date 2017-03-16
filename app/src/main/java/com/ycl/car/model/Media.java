package com.ycl.car.model;

/**
 * 多媒体管理实体
 * Created by y11621546 on 2017/3/13.
 */

public class Media {
    /**
     * id : 1
     * isshow : 1
     * imgsrc : /file/multimedia/multimedia.png
     * sort : 10
     * mname : 场景1
     * sen : scene1
     * mtype : 0
     */

    private int id;
    private int isshow;
    private String imgsrc;
    private int sort;
    private String mname;
    private String sen;
    private int mtype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsshow() {
        return isshow;
    }

    public void setIsshow(int isshow) {
        this.isshow = isshow;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getSen() {
        return sen;
    }

    public void setSen(String sen) {
        this.sen = sen;
    }

    public int getMtype() {
        return mtype;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }
}
