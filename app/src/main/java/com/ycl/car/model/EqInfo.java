package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/2/16.
 */

public class EqInfo {
    /**
     * id : 1
     * eqno : eq_01
     * eqname : 滚床1
     * eqspeci : 型号
     * eqfactory : 厂家
     * eqdepartment_id : 1
     * installocation : 安装地点
     * useless : 使用情况
     * eqtype_id : 1
     * eqwksp_id : 1
     * eqsystem_id : 1
     * ext1 : null
     * ext2 : null
     * ext3 : null
     * sort : 1
     * adduser_id : 1
     */

    private int id;
    private String eqno;
    private String eqname;
    private String eqspeci;
    private String eqfactory;
    private int eqdepartment_id;
    private String installocation;
    private String useless;
    private int eqtype_id;
    private int eqwksp_id;
    private int eqsystem_id;
    private Object ext1;
    private Object ext2;
    private Object ext3;
    private int sort;
    private int adduser_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEqspeci() {
        return eqspeci;
    }

    public void setEqspeci(String eqspeci) {
        this.eqspeci = eqspeci;
    }

    public String getEqfactory() {
        return eqfactory;
    }

    public void setEqfactory(String eqfactory) {
        this.eqfactory = eqfactory;
    }

    public int getEqdepartment_id() {
        return eqdepartment_id;
    }

    public void setEqdepartment_id(int eqdepartment_id) {
        this.eqdepartment_id = eqdepartment_id;
    }

    public String getInstallocation() {
        return installocation;
    }

    public void setInstallocation(String installocation) {
        this.installocation = installocation;
    }

    public String getUseless() {
        return useless;
    }

    public void setUseless(String useless) {
        this.useless = useless;
    }

    public int getEqtype_id() {
        return eqtype_id;
    }

    public void setEqtype_id(int eqtype_id) {
        this.eqtype_id = eqtype_id;
    }

    public int getEqwksp_id() {
        return eqwksp_id;
    }

    public void setEqwksp_id(int eqwksp_id) {
        this.eqwksp_id = eqwksp_id;
    }

    public int getEqsystem_id() {
        return eqsystem_id;
    }

    public void setEqsystem_id(int eqsystem_id) {
        this.eqsystem_id = eqsystem_id;
    }

    public Object getExt1() {
        return ext1;
    }

    public void setExt1(Object ext1) {
        this.ext1 = ext1;
    }

    public Object getExt2() {
        return ext2;
    }

    public void setExt2(Object ext2) {
        this.ext2 = ext2;
    }

    public Object getExt3() {
        return ext3;
    }

    public void setExt3(Object ext3) {
        this.ext3 = ext3;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getAdduser_id() {
        return adduser_id;
    }

    public void setAdduser_id(int adduser_id) {
        this.adduser_id = adduser_id;
    }
}
