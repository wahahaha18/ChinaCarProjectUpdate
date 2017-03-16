package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/2/14.
 */

public class PartLibrary {

    /**
     * o : {"id":1,"spno":"sp_001","spname":"备件1","spbrand":"品牌","spmaterial":"材质","spmodel":"型号","sptp_id":1,"splifespan":1000,"spdeliveryperiod":70,"spunit":"个","spremarks":null,"spcurrentc":1,"spadvicec":0,"splowestc":0,"addtime":"2017/01/04 16:21:47","inlasttime":null,"outlasttime":null,"sort":1,"adduser_id":1}
     * type : {"id":1,"tpname":"必备","addtime":"2017/01/04 16:19:51","sort":1}
     */

    private OBean o;
    private TypeBean type;

    public OBean getO() {
        return o;
    }

    public void setO(OBean o) {
        this.o = o;
    }

    public TypeBean getType() {
        return type;
    }

    public void setType(TypeBean type) {
        this.type = type;
    }

    public static class OBean {
        /**
         * id : 1
         * spno : sp_001
         * spname : 备件1
         * spbrand : 品牌
         * spmaterial : 材质
         * spmodel : 型号
         * sptp_id : 1
         * splifespan : 1000
         * spdeliveryperiod : 70
         * spunit : 个
         * spremarks : null
         * spcurrentc : 1
         * spadvicec : 0
         * splowestc : 0
         * addtime : 2017/01/04 16:21:47
         * inlasttime : null
         * outlasttime : null
         * sort : 1
         * adduser_id : 1
         */

        private int id;
        private String spno;
        private String spname;
        private String spbrand;
        private String spmaterial;
        private String spmodel;
        private int sptp_id;
        private int splifespan;
        private int spdeliveryperiod;
        private String spunit;
        private Object spremarks;
        private int spcurrentc;
        private int spadvicec;
        private int splowestc;
        private String addtime;
        private Object inlasttime;
        private Object outlasttime;
        private int sort;
        private int adduser_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSpno() {
            return spno;
        }

        public void setSpno(String spno) {
            this.spno = spno;
        }

        public String getSpname() {
            return spname;
        }

        public void setSpname(String spname) {
            this.spname = spname;
        }

        public String getSpbrand() {
            return spbrand;
        }

        public void setSpbrand(String spbrand) {
            this.spbrand = spbrand;
        }

        public String getSpmaterial() {
            return spmaterial;
        }

        public void setSpmaterial(String spmaterial) {
            this.spmaterial = spmaterial;
        }

        public String getSpmodel() {
            return spmodel;
        }

        public void setSpmodel(String spmodel) {
            this.spmodel = spmodel;
        }

        public int getSptp_id() {
            return sptp_id;
        }

        public void setSptp_id(int sptp_id) {
            this.sptp_id = sptp_id;
        }

        public int getSplifespan() {
            return splifespan;
        }

        public void setSplifespan(int splifespan) {
            this.splifespan = splifespan;
        }

        public int getSpdeliveryperiod() {
            return spdeliveryperiod;
        }

        public void setSpdeliveryperiod(int spdeliveryperiod) {
            this.spdeliveryperiod = spdeliveryperiod;
        }

        public String getSpunit() {
            return spunit;
        }

        public void setSpunit(String spunit) {
            this.spunit = spunit;
        }

        public Object getSpremarks() {
            return spremarks;
        }

        public void setSpremarks(Object spremarks) {
            this.spremarks = spremarks;
        }

        public int getSpcurrentc() {
            return spcurrentc;
        }

        public void setSpcurrentc(int spcurrentc) {
            this.spcurrentc = spcurrentc;
        }

        public int getSpadvicec() {
            return spadvicec;
        }

        public void setSpadvicec(int spadvicec) {
            this.spadvicec = spadvicec;
        }

        public int getSplowestc() {
            return splowestc;
        }

        public void setSplowestc(int splowestc) {
            this.splowestc = splowestc;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public Object getInlasttime() {
            return inlasttime;
        }

        public void setInlasttime(Object inlasttime) {
            this.inlasttime = inlasttime;
        }

        public Object getOutlasttime() {
            return outlasttime;
        }

        public void setOutlasttime(Object outlasttime) {
            this.outlasttime = outlasttime;
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

    public static class TypeBean {
        /**
         * id : 1
         * tpname : 必备
         * addtime : 2017/01/04 16:19:51
         * sort : 1
         */

        private int id;
        private String tpname;
        private String addtime;
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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
