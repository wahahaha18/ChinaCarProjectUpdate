package com.ycl.car.model;

import java.util.List;

/**
 * 新闻
 * Created by y11621546 on 2017/1/18.
 */

public class MainNews {

    /**
     * a : 0
     * b : [{"StrAddTime":"2016-11-29 14:10:10","id":5,"menu_id":50,"title_":"研发中心要闻3","summary":"研发中心要闻2","context":"研发中心要闻2","img":null,"vido":null,"link":null,"addtime":"2016/11/29 14:10:10","sort":0},{"StrAddTime":"2016-11-29 14:09:05","id":3,"menu_id":50,"title_":"研发中心要闻1","summary":"研发中心要闻1","context":"研发中心要闻1研发中心要闻1研发中心要闻1研发中心要闻1","img":null,"vido":null,"link":null,"addtime":"2016/11/29 14:09:05","sort":0},{"StrAddTime":"2016-11-29 14:09:05","id":4,"menu_id":50,"title_":"研发中心要闻2","summary":"研发中心要闻2","context":"研发中心要闻2研发中心要闻2研发中心要闻2研发中心要闻2研发中心要闻2","img":null,"vido":null,"link":null,"addtime":"2016/11/29 14:09:05","sort":0},{"StrAddTime":"2016-11-28 13:36:46","id":1,"menu_id":50,"title_":"研发中心要闻","summary":"<h1>研发中心要闻,研发中心要闻<\/h1>","context":"<h1>研发中心要闻,研发中心要闻<\/h1><\/br><h2>研发中心要闻,研发中心要闻<\/h2>","img":"1","vido":"1","link":"1","addtime":"2016/11/28 13:36:46","sort":0}]
     */

    private String a;
    private List<BBean> b;

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

    public static class BBean {
        /**
         * StrAddTime : 2016-11-29 14:10:10
         * id : 5
         * menu_id : 50
         * title_ : 研发中心要闻3
         * summary : 研发中心要闻2
         * context : 研发中心要闻2
         * img : null
         * vido : null
         * link : null
         * addtime : 2016/11/29 14:10:10
         * sort : 0
         */

        private String StrAddTime;
        private int id;
        private int menu_id;
        private String title_;
        private String summary;
        private String context;
        private Object img;
        private Object vido;
        private Object link;
        private String addtime;
        private int sort;

        public String getStrAddTime() {
            return StrAddTime;
        }

        public void setStrAddTime(String StrAddTime) {
            this.StrAddTime = StrAddTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }

        public String getTitle_() {
            return title_;
        }

        public void setTitle_(String title_) {
            this.title_ = title_;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public Object getVido() {
            return vido;
        }

        public void setVido(Object vido) {
            this.vido = vido;
        }

        public Object getLink() {
            return link;
        }

        public void setLink(Object link) {
            this.link = link;
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
