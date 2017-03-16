package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/2/17.
 */

public class EqMainInfo {
    /**
     * id : 3
     * addtime : 2017/01/11 10:07:23
     * equip_id : 1
     * adduser_id : 2
     * touser_id : 1
     * rtitle : 测试问题2
     * rinfo : 测试问题描述2
     * rstatus : 0
     * status : 0
     * ranswer :
     * rimgs :
     * rtime : 2017/01/10 18:39:05
     */

    private int id;
    private String addtime;
    private int equip_id;
    private int adduser_id;
    private int touser_id;
    private String rtitle;
    private String rinfo;
    private int rstatus;
    private int status;
    private String ranswer;
    private String rimgs;
    private String rtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public int getAdduser_id() {
        return adduser_id;
    }

    public void setAdduser_id(int adduser_id) {
        this.adduser_id = adduser_id;
    }

    public int getTouser_id() {
        return touser_id;
    }

    public void setTouser_id(int touser_id) {
        this.touser_id = touser_id;
    }

    public String getRtitle() {
        return rtitle;
    }

    public void setRtitle(String rtitle) {
        this.rtitle = rtitle;
    }

    public String getRinfo() {
        return rinfo;
    }

    public void setRinfo(String rinfo) {
        this.rinfo = rinfo;
    }

    public int getRstatus() {
        return rstatus;
    }

    public void setRstatus(int rstatus) {
        this.rstatus = rstatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRanswer() {
        return ranswer;
    }

    public void setRanswer(String ranswer) {
        this.ranswer = ranswer;
    }

    public String getRimgs() {
        return rimgs;
    }

    public void setRimgs(String rimgs) {
        this.rimgs = rimgs;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }
}
