package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/2/15.
 */

public class Record {
    /**
     * id : 1
     * pm_id : 1
     * status : 0
     * plandate : 2017/01/21 11:40:44
     * touser_id : 1
     * pcontent :
     * pfiles :
     * ptime : 2017/01/20 15:55:24
     */

    private int id;
    private int pm_id;
    private int status;
    private String plandate;
    private int touser_id;
    private String pcontent;
    private String pfiles;
    private String ptime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPm_id() {
        return pm_id;
    }

    public void setPm_id(int pm_id) {
        this.pm_id = pm_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPlandate() {
        return plandate;
    }

    public void setPlandate(String plandate) {
        this.plandate = plandate;
    }

    public int getTouser_id() {
        return touser_id;
    }

    public void setTouser_id(int touser_id) {
        this.touser_id = touser_id;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public String getPfiles() {
        return pfiles;
    }

    public void setPfiles(String pfiles) {
        this.pfiles = pfiles;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }
}
