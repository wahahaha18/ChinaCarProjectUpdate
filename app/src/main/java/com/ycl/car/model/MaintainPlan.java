package com.ycl.car.model;

import java.util.List;

/**
 * 维修计划
 * Created by y11621546 on 2017/2/15.
 */

public class MaintainPlan {


    /**
     * pmid : 1
     * eqname : 滚床
     * eqno : eq_01
     * pmcontent : 检查电机轴承，并进行润滑，若发现轴承损坏， 则予以更换
     * pmark : 备注
     * status : 0
     * islist : 0
     * ptime : null
     * pcontent : null
     * files : []
     * plandate : 2017-03-07
     */

    private int pmid;
    private String eqname;
    private String eqno;
    private String pmcontent;
    private String pmark;
    private int status;
    private int islist;
    private Object ptime;
    private Object pcontent;
    private String plandate;
    private List<FileBean> files;

    public static class FileBean {

        /**
         * fname : Book1
         * filext : xlsx
         * fileurl : /file/Book1.xlsx
         */

        private String fname;
        private String filext;
        private String fileurl;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getFilext() {
            return filext;
        }

        public void setFilext(String filext) {
            this.filext = filext;
        }

        public String getFileurl() {
            return fileurl;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }
    }

    public int getPmid() {
        return pmid;
    }

    public void setPmid(int pmid) {
        this.pmid = pmid;
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

    public String getPmcontent() {
        return pmcontent;
    }

    public void setPmcontent(String pmcontent) {
        this.pmcontent = pmcontent;
    }

    public String getPmark() {
        return pmark;
    }

    public void setPmark(String pmark) {
        this.pmark = pmark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIslist() {
        return islist;
    }

    public void setIslist(int islist) {
        this.islist = islist;
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

    public List<FileBean> getFiles() {
        return files;
    }

    public void setFiles(List<FileBean> files) {
        this.files = files;
    }
}
