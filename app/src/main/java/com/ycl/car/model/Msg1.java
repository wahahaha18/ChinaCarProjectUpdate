package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class Msg1 {
    /**
     * id : 3
     * mtitle : 测试信息3
     * time : 2017-03-01
     * type : 1
     * hits : 2
     * adduser : user1@mail.com
     */

    private int id;
    private String mtitle;
    private String time;
    private int type;
    private int hits;
    private String adduser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }
}
