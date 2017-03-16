package com.ycl.car.model;

import java.util.List;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class Ledger {
    /**
     * a : 0
     * b : [{"eqno":"IM5","eqname":"名称15","eqspeci":"型号5","eqfactory":"厂家5","eqdepartment":"部门","installocation":"安装点","useless":"情况","eqtype":"类型","eqwksp":"车间","eqsystem":"系统2"},{"eqno":"IM4","eqname":"名称14","eqspeci":"型号4","eqfactory":"厂家4","eqdepartment":"部门","installocation":"安装点","useless":"情况","eqtype":"类型","eqwksp":"车间2","eqsystem":"系统"},{"eqno":"IM3","eqname":"名称13","eqspeci":"型号3","eqfactory":"厂家3","eqdepartment":"部门","installocation":"安装点","useless":"情况","eqtype":"类型2","eqwksp":"车间","eqsystem":"系统"},{"eqno":"IM2","eqname":"名称12","eqspeci":"型号2","eqfactory":"厂家2","eqdepartment":"部门2","installocation":"安装点","useless":"情况","eqtype":"类型","eqwksp":"车间","eqsystem":"系统"},{"eqno":"IM1","eqname":"名称11","eqspeci":"型号1","eqfactory":"厂家1","eqdepartment":"部门","installocation":"安装点","useless":"情况","eqtype":"类型","eqwksp":"车间","eqsystem":"系统"},{"eqno":"yhj_01","eqname":"testyhj","eqspeci":"型号移行机","eqfactory":"厂家移行机","eqdepartment":"部门1","installocation":"安装地点移行机","useless":"使用情况移行机","eqtype":"移行机","eqwksp":"车间1","eqsystem":"系统1"},{"eqno":"eq_02","eqname":"gc","eqspeci":"型号","eqfactory":"厂家","eqdepartment":"部门1","installocation":"安装地点","useless":"使用情况","eqtype":"滚床","eqwksp":"车间1","eqsystem":"系统1"},{"eqno":"eq_01","eqname":"滚床","eqspeci":"型号","eqfactory":"厂家","eqdepartment":"部门1","installocation":"安装地点","useless":"使用情况","eqtype":"滚床","eqwksp":"车间1","eqsystem":"系统1"}]
     * c : [{"id":1,"tpname":"滚床","sort":1},{"id":2,"tpname":"移行机","sort":2},{"id":7,"tpname":"类型","sort":0},{"id":8,"tpname":"类型2","sort":0}]
     * d : [{"id":1,"wname":"车间1","sys":[{"id":1,"eqwksp_id":1,"sname":"系统1","sort":1}]},{"id":6,"wname":"车间","sys":[{"id":9,"eqwksp_id":6,"sname":"系统","sort":0},{"id":11,"eqwksp_id":6,"sname":"系统2","sort":0}]},{"id":7,"wname":"车间2","sys":[{"id":10,"eqwksp_id":7,"sname":"系统","sort":0}]}]
     */

    private String a;
    private List<BBean> b;
    private List<CBean> c;
    private List<DBean> d;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public List<BBean> getB() {
        return b;
    }

    public void setB(List<BBean> b) {
        this.b = b;
    }

    public List<CBean> getC() {
        return c;
    }

    public void setC(List<CBean> c) {
        this.c = c;
    }

    public List<DBean> getD() {
        return d;
    }

    public void setD(List<DBean> d) {
        this.d = d;
    }

    public static class BBean {
        /**
         * eqno : IM5
         * eqname : 名称15
         * eqspeci : 型号5
         * eqfactory : 厂家5
         * eqdepartment : 部门
         * installocation : 安装点
         * useless : 情况
         * eqtype : 类型
         * eqwksp : 车间
         * eqsystem : 系统2
         */

        private String eqno;
        private String eqname;
        private String eqspeci;
        private String eqfactory;
        private String eqdepartment;
        private String installocation;
        private String useless;
        private String eqtype;
        private String eqwksp;
        private String eqsystem;

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

        public String getEqdepartment() {
            return eqdepartment;
        }

        public void setEqdepartment(String eqdepartment) {
            this.eqdepartment = eqdepartment;
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

        public String getEqtype() {
            return eqtype;
        }

        public void setEqtype(String eqtype) {
            this.eqtype = eqtype;
        }

        public String getEqwksp() {
            return eqwksp;
        }

        public void setEqwksp(String eqwksp) {
            this.eqwksp = eqwksp;
        }

        public String getEqsystem() {
            return eqsystem;
        }

        public void setEqsystem(String eqsystem) {
            this.eqsystem = eqsystem;
        }
    }

    public static class CBean {
        /**
         * id : 1
         * tpname : 滚床
         * sort : 1
         */

        private int id;
        private String tpname;
        private int sort;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTpname() {
            return tpname;
        }

        public void setTpname(String tpname) {
            this.tpname = tpname;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }

    public static class DBean {
        /**
         * id : 1
         * wname : 车间1
         * sys : [{"id":1,"eqwksp_id":1,"sname":"系统1","sort":1}]
         */

        private int id;
        private String wname;
        private List<SysBean> sys;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWname() {
            return wname;
        }

        public void setWname(String wname) {
            this.wname = wname;
        }

        public List<SysBean> getSys() {
            return sys;
        }

        public void setSys(List<SysBean> sys) {
            this.sys = sys;
        }

        public static class SysBean {
            /**
             * id : 1
             * eqwksp_id : 1
             * sname : 系统1
             * sort : 1
             */

            private int id;
            private int eqwksp_id;
            private String sname;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getEqwksp_id() {
                return eqwksp_id;
            }

            public void setEqwksp_id(int eqwksp_id) {
                this.eqwksp_id = eqwksp_id;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
