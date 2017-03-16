package com.ycl.car.model;

import java.util.List;

/**
 * 帖子
 * Created by y11621546 on 2017/1/21.
 */

public class Post {
    /**
     * a : 0
     * b : [{"User":{"id":2,"u_name":"user2@mail.com","u_pwd":"c8837b23ff8aaa8a2dde915473ce0991","u_role":null,"alias_":"22","company_":"22","gender_":"33"},"Likes":"0","Noteds":"0","StrAddTime":"2016-12-03 15:56:01","StrLastUpdateTime":"2016-12-03 15:56:01","id":24,"user_id":2,"title_":null,"content_":"新增一帖新增一帖新增一帖新增一帖新增一帖","add_time":"2016/12/03 15:56:01","last_update_time":"2016/12/03 15:56:01","likes_counting":"0","summary_of_last_note":"the summary","note_id":null,"is_fresh":null,"is_hot":null,"is_typical":null,"sort":null},{"User":{"id":1,"u_name":"user1@mail.com","u_pwd":"eabd8ce9404507aa8c22714d3f5eada9","u_role":null,"alias_":"a","company_":"b","gender_":"男"},"Likes":"1","Noteds":"0","StrAddTime":"2016-11-30 09:50:50","StrLastUpdateTime":"2016-12-05 15:08:31","id":1,"user_id":1,"title_":null,"content_":"内容1","add_time":"2016/11/30 09:50:50","last_update_time":"2016/12/05 15:08:31","likes_counting":null,"summary_of_last_note":"sommary1","note_id":null,"is_fresh":1,"is_hot":null,"is_typical":null,"sort":6},{"User":{"id":2,"u_name":"user2@mail.com","u_pwd":"c8837b23ff8aaa8a2dde915473ce0991","u_role":null,"alias_":"22","company_":"22","gender_":"33"},"Likes":"0","Noteds":"0","StrAddTime":"2016-11-30 09:50:50","StrLastUpdateTime":"2016-11-21 09:50:50","id":5,"user_id":2,"title_":null,"content_":"内容5","add_time":"2016/11/30 09:50:50","last_update_time":"2016/11/21 09:50:50","likes_counting":null,"summary_of_last_note":"sommary5","note_id":null,"is_fresh":null,"is_hot":null,"is_typical":null,"sort":10},{"User":{"id":1,"u_name":"user1@mail.com","u_pwd":"eabd8ce9404507aa8c22714d3f5eada9","u_role":null,"alias_":"a","company_":"b","gender_":"男"},"Likes":"0","Noteds":"0","StrAddTime":"2016-11-30 09:50:50","StrLastUpdateTime":"2016-11-30 09:50:50","id":6,"user_id":1,"title_":null,"content_":"内容6","add_time":"2016/11/30 09:50:50","last_update_time":"2016/11/30 09:50:50","likes_counting":null,"summary_of_last_note":"sommary6","note_id":null,"is_fresh":null,"is_hot":null,"is_typical":null,"sort":11},{"User":{"id":1,"u_name":"user1@mail.com","u_pwd":"eabd8ce9404507aa8c22714d3f5eada9","u_role":null,"alias_":"a","company_":"b","gender_":"男"},"Likes":"1","Noteds":"2","StrAddTime":"2016-11-21 09:50:50","StrLastUpdateTime":"2016-11-29 09:50:50","id":2,"user_id":1,"title_":null,"content_":"内容2","add_time":"2016/11/21 09:50:50","last_update_time":"2016/11/29 09:50:50","likes_counting":null,"summary_of_last_note":"sommary2","note_id":null,"is_fresh":null,"is_hot":1,"is_typical":null,"sort":2},{"User":{"id":2,"u_name":"user2@mail.com","u_pwd":"c8837b23ff8aaa8a2dde915473ce0991","u_role":null,"alias_":"22","company_":"22","gender_":"33"},"Likes":"0","Noteds":"0","StrAddTime":"2016-11-10 09:50:50","StrLastUpdateTime":"2016-11-10 09:50:50","id":4,"user_id":2,"title_":null,"content_":"内容4","add_time":"2016/11/10 09:50:50","last_update_time":"2016/11/10 09:50:50","likes_counting":null,"summary_of_last_note":"sommary4","note_id":null,"is_fresh":null,"is_hot":null,"is_typical":null,"sort":7},{"User":{"id":1,"u_name":"user1@mail.com","u_pwd":"eabd8ce9404507aa8c22714d3f5eada9","u_role":null,"alias_":"a","company_":"b","gender_":"男"},"Likes":"0","Noteds":"0","StrAddTime":"2016-11-03 09:50:50","StrLastUpdateTime":"2016-11-30 09:50:50","id":3,"user_id":1,"title_":null,"content_":"内容3","add_time":"2016/11/03 09:50:50","last_update_time":"2016/11/30 09:50:50","likes_counting":null,"summary_of_last_note":"sommary3","note_id":null,"is_fresh":null,"is_hot":null,"is_typical":1,"sort":3}]
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
         * User : {"id":2,"u_name":"user2@mail.com","u_pwd":"c8837b23ff8aaa8a2dde915473ce0991","u_role":null,"alias_":"22","company_":"22","gender_":"33"}
         * Likes : 0
         * Noteds : 0
         * StrAddTime : 2016-12-03 15:56:01
         * StrLastUpdateTime : 2016-12-03 15:56:01
         * id : 24
         * user_id : 2
         * title_ : null
         * content_ : 新增一帖新增一帖新增一帖新增一帖新增一帖
         * add_time : 2016/12/03 15:56:01
         * last_update_time : 2016/12/03 15:56:01
         * likes_counting : 0
         * summary_of_last_note : the summary
         * note_id : null
         * is_fresh : null
         * is_hot : null
         * is_typical : null
         * sort : null
         */
        private boolean isZan;
        private int zanNum;

        public int getZanNum() {
            return zanNum;
        }

        public void setZanNum(int zanNum) {
            this.zanNum = zanNum;
        }

        public boolean isZan() {
            return isZan;
        }

        public void setZan(boolean zan) {
            isZan = zan;
        }

        private UserBean User;
        private String Likes;
        private String Noteds;
        private String StrAddTime;
        private String StrLastUpdateTime;
        private int id;
        private int user_id;
        private Object title_;
        private String content_;
        private String add_time;
        private String last_update_time;
        private String likes_counting;
        private String summary_of_last_note;
        private Object note_id;
        private Object is_fresh;
        private Object is_hot;
        private Object is_typical;
        private Object sort;


        public UserBean getUser() {
            return User;
        }

        public void setUser(UserBean User) {
            this.User = User;
        }

        public String getLikes() {
            return Likes;
        }

        public void setLikes(String Likes) {
            this.Likes = Likes;
        }

        public String getNoteds() {
            return Noteds;
        }

        public void setNoteds(String Noteds) {
            this.Noteds = Noteds;
        }

        public String getStrAddTime() {
            return StrAddTime;
        }

        public void setStrAddTime(String StrAddTime) {
            this.StrAddTime = StrAddTime;
        }

        public String getStrLastUpdateTime() {
            return StrLastUpdateTime;
        }

        public void setStrLastUpdateTime(String StrLastUpdateTime) {
            this.StrLastUpdateTime = StrLastUpdateTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public Object getTitle_() {
            return title_;
        }

        public void setTitle_(Object title_) {
            this.title_ = title_;
        }

        public String getContent_() {
            return content_;
        }

        public void setContent_(String content_) {
            this.content_ = content_;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getLast_update_time() {
            return last_update_time;
        }

        public void setLast_update_time(String last_update_time) {
            this.last_update_time = last_update_time;
        }

        public String getLikes_counting() {
            return likes_counting;
        }

        public void setLikes_counting(String likes_counting) {
            this.likes_counting = likes_counting;
        }

        public String getSummary_of_last_note() {
            return summary_of_last_note;
        }

        public void setSummary_of_last_note(String summary_of_last_note) {
            this.summary_of_last_note = summary_of_last_note;
        }

        public Object getNote_id() {
            return note_id;
        }

        public void setNote_id(Object note_id) {
            this.note_id = note_id;
        }

        public Object getIs_fresh() {
            return is_fresh;
        }

        public void setIs_fresh(Object is_fresh) {
            this.is_fresh = is_fresh;
        }

        public Object getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(Object is_hot) {
            this.is_hot = is_hot;
        }

        public Object getIs_typical() {
            return is_typical;
        }

        public void setIs_typical(Object is_typical) {
            this.is_typical = is_typical;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public static class UserBean {
            /**
             * id : 2
             * u_name : user2@mail.com
             * u_pwd : c8837b23ff8aaa8a2dde915473ce0991
             * u_role : null
             * alias_ : 22
             * company_ : 22
             * gender_ : 33
             */

            private int id;
            private String u_name;
            private String u_pwd;
            private Object u_role;
            private String alias_;
            private String company_;
            private String gender_;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getU_name() {
                return u_name;
            }

            public void setU_name(String u_name) {
                this.u_name = u_name;
            }

            public String getU_pwd() {
                return u_pwd;
            }

            public void setU_pwd(String u_pwd) {
                this.u_pwd = u_pwd;
            }

            public Object getU_role() {
                return u_role;
            }

            public void setU_role(Object u_role) {
                this.u_role = u_role;
            }

            public String getAlias_() {
                return alias_;
            }

            public void setAlias_(String alias_) {
                this.alias_ = alias_;
            }

            public String getCompany_() {
                return company_;
            }

            public void setCompany_(String company_) {
                this.company_ = company_;
            }

            public String getGender_() {
                return gender_;
            }

            public void setGender_(String gender_) {
                this.gender_ = gender_;
            }
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "a='" + a + '\'' +
                ", b=" + b +
                '}';
    }
}
