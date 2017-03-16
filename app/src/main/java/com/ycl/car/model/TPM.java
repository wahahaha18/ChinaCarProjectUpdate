package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class TPM {
    /**
     * id : 3
     * jcd : 检查点
     * systemname : 系统1
     * eqname : 滚床
     * eqno : eq_01
     * tmethod : 点检方
     * workinfo : 工作内容
     * tstatus : 开机
     * ptime : null
     * pcontent : null
     * plandate : 2017-03-04 10:00
     * status
     */

    private int id;
    private String jcd;
    private String systemname;
    private String eqname;
    private String eqno;
    private String tmethod;
    private String workinfo;
    private String tstatus;
    private Object ptime;
    private Object pcontent;
    private String plandate;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJcd() {
        return jcd;
    }

    public void setJcd(String jcd) {
        this.jcd = jcd;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public String getEqname() {
        return eqname;
    }

    public void setEqname(String eqname) {
        this.eqname = eqname;
    }

    public String getEqno() {
        return eqno;
    }

    public void setEqno(String eqno) {
        this.eqno = eqno;
    }

    public String getTmethod() {
        return tmethod;
    }

    public void setTmethod(String tmethod) {
        this.tmethod = tmethod;
    }

    public String getWorkinfo() {
        return workinfo;
    }

    public void setWorkinfo(String workinfo) {
        this.workinfo = workinfo;
    }

    public String getTstatus() {
        return tstatus;
    }

    public void setTstatus(String tstatus) {
        this.tstatus = tstatus;
    }

    public Object getPtime() {
        return ptime;
    }

    public void setPtime(Object ptime) {
        this.ptime = ptime;
    }

    public Object getPcontent() {
        return pcontent;
    }

    public void setPcontent(Object pcontent) {
        this.pcontent = pcontent;
    }

    public String getPlandate() {
        return plandate;
    }

    public void setPlandate(String plandate) {
        this.plandate = plandate;
    }
}
