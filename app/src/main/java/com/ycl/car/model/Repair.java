package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class Repair {
    /**
     * id : 5
     * addtime : 2017/02/04 15:52:36
     * equip_id : 2
     * adduser_id : 1
     * touser_id : 1
     * rtitle :  12312
     * rinfo : 3123213123123
     * rstatus : 2
     * status : 1
     * ranswer : 试试
     * rimgs : /Content/repair/20170302/131329197738182343.jpg
     * rtime : 2017/03/02 17:16:13
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
    /**
     * eqno : eq_01
     * eqname : 滚床
     * ranswer : null
     * rimgs : null
     */

    private String eqno;
    private String eqname;

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

    public String getEqno() {
        return eqno;
    }

    public void setEqno(String eqno) {
        this.eqno = eqno;
    }

    public String getEqname() {
        return eqname;
    }

    public void setEqname(String eqname) {
        this.eqname = eqname;
    }
}
