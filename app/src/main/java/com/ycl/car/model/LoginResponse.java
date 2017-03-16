package com.ycl.car.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录
 * Created by y11621546 on 2017/1/21.
 */

public class LoginResponse {


    /**
     * a : 0
     * b : {"id":1,"u_name":"user1@mail.com","u_pwd":"eabd8ce9404507aa8c22714d3f5eada9","u_role":null,"alias_":"a1","company_":"b1","gender_":"女","roleid":1}
     * c : [{"childs":[{"childs":[{"childs":[{"childs":[],"id":55,"name_":"设备1","p_id_":18,"level_":4,"param":"{\"diagrame_name\":\"2\"}","sen":null,"imgs":null},{"childs":[],"id":56,"name_":"设备2","p_id_":18,"level_":4,"param":null,"sen":null,"imgs":null}],"id":18,"name_":"输送设备","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":19,"name_":"前处理电泳","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":20,"name_":"空调","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":21,"name_":"烘干及闪干","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":22,"name_":"湿式喷房","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":23,"name_":"机器人","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":24,"name_":"集中供漆","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":25,"name_":"干式喷房","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":26,"name_":"控制","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":27,"name_":"报表","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null}],"id":12,"name_":"设备管理","p_id_":5,"level_":2,"param":null,"sen":"sb","imgs":"/Content/menu/28.png"},{"childs":[{"childs":[],"id":28,"name_":"订单管理","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":29,"name_":"车体识别","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":30,"name_":"安全管理","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":31,"name_":"成本分析","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":32,"name_":"班制管理","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":33,"name_":"生产报表","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null}],"id":13,"name_":"生产管理","p_id_":5,"level_":2,"param":null,"sen":"sc","imgs":"/Content/menu/29.png"},{"childs":[{"childs":[],"id":34,"name_":"实时报警","p_id_":14,"level_":3,"param":null,"sen":"wx_ssbj","imgs":null},{"childs":[],"id":35,"name_":"设备开动率","p_id_":14,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":36,"name_":"维修资料","p_id_":14,"level_":3,"param":null,"sen":"wx_zl","imgs":null},{"childs":[],"id":37,"name_":"智能备件库","p_id_":14,"level_":3,"param":null,"sen":"wx_znbjk","imgs":null},{"childs":[],"id":38,"name_":"PM预防性维修计划","p_id_":14,"level_":3,"param":null,"sen":"wx_pm","imgs":null},{"childs":[],"id":39,"name_":"TPM点检","p_id_":14,"level_":3,"param":null,"sen":"wx_tpm","imgs":null},{"childs":[],"id":40,"name_":"报表","p_id_":14,"level_":3,"param":null,"sen":null,"imgs":null}],"id":14,"name_":"维修管理","p_id_":5,"level_":2,"param":"p=1&s=2","sen":"wx","imgs":"/Content/menu/30.png"},{"childs":[{"childs":[],"id":41,"name_":"质量检查","p_id_":15,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":42,"name_":"品质追溯","p_id_":15,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":43,"name_":"暗灯系统","p_id_":15,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":44,"name_":"报表","p_id_":15,"level_":3,"param":null,"sen":null,"imgs":null}],"id":15,"name_":"质量管理","p_id_":5,"level_":2,"param":null,"sen":"zl","imgs":"/Content/menu/31.png"},{"childs":[{"childs":[],"id":45,"name_":"单个能源","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":46,"name_":"单体设备","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":47,"name_":"总耗量","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":48,"name_":"能源供应状态","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":49,"name_":"报表","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null}],"id":16,"name_":"能源管理","p_id_":5,"level_":2,"param":null,"sen":"ny","imgs":"/Content/menu/32.png"},{"childs":[],"id":17,"name_":"多媒体控制","p_id_":5,"level_":2,"param":null,"sen":"dmt","imgs":"/Content/menu/33.png"}],"id":5,"name_":"管理","p_id_":null,"level_":1,"param":null,"sen":null,"imgs":null}]
     * "d":{"isalrm":1,"ispm":0,"isrepair":0}
     */

    private String a;
    private BBean b;
    private List<CBean> c;
    private DBean d;

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public BBean getB() {
        return b;
    }

    public void setB(BBean b) {
        this.b = b;
    }

    public List<CBean> getC() {
        return c;
    }

    public void setC(List<CBean> c) {
        this.c = c;
    }

    public static class DBean {
        private int isalrm;
        private int ispm;
        private int isrepair;

        public int getIsalrm() {
            return isalrm;
        }

        public void setIsalrm(int isalrm) {
            this.isalrm = isalrm;
        }

        public int getIspm() {
            return ispm;
        }

        public void setIspm(int ispm) {
            this.ispm = ispm;
        }

        public int getIsrepair() {
            return isrepair;
        }

        public void setIsrepair(int isrepair) {
            this.isrepair = isrepair;
        }
    }

    public static class BBean implements Parcelable {
        /**
         * id : 1
         * u_name : user1@mail.com
         * u_pwd : eabd8ce9404507aa8c22714d3f5eada9
         * u_role : null
         * alias_ : a1
         * company_ : b1
         * gender_ : 女
         * roleid : 1
         */

        private int id;
        private String u_name;
        private String u_pwd;
        private String u_role;
        private String alias_;
        private String company_;
        private String gender_;
        private int roleid;

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

        public String getU_role() {
            return u_role;
        }

        public void setU_role(String u_role) {
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

        public int getRoleid() {
            return roleid;
        }

        public void setRoleid(int roleid) {
            this.roleid = roleid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.u_name);
            dest.writeString(this.u_pwd);
            dest.writeString(this.u_role);
            dest.writeString(this.alias_);
            dest.writeString(this.company_);
            dest.writeString(this.gender_);
            dest.writeInt(this.roleid);
        }

        public BBean() {
        }

        protected BBean(Parcel in) {
            this.id = in.readInt();
            this.u_name = in.readString();
            this.u_pwd = in.readString();
            this.u_role = in.readString();
            this.alias_ = in.readString();
            this.company_ = in.readString();
            this.gender_ = in.readString();
            this.roleid = in.readInt();
        }

        public static final Parcelable.Creator<BBean> CREATOR = new Parcelable.Creator<BBean>() {
            @Override
            public BBean createFromParcel(Parcel source) {
                return new BBean(source);
            }

            @Override
            public BBean[] newArray(int size) {
                return new BBean[size];
            }
        };
    }

    public static class CBean implements Parcelable {
        /**
         * childs : [{"childs":[{"childs":[{"childs":[],"id":55,"name_":"设备1","p_id_":18,"level_":4,"param":"{\"diagrame_name\":\"2\"}","sen":null,"imgs":null},{"childs":[],"id":56,"name_":"设备2","p_id_":18,"level_":4,"param":null,"sen":null,"imgs":null}],"id":18,"name_":"输送设备","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":19,"name_":"前处理电泳","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":20,"name_":"空调","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":21,"name_":"烘干及闪干","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":22,"name_":"湿式喷房","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":23,"name_":"机器人","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":24,"name_":"集中供漆","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":25,"name_":"干式喷房","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":26,"name_":"控制","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":27,"name_":"报表","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null}],"id":12,"name_":"设备管理","p_id_":5,"level_":2,"param":null,"sen":"sb","imgs":"/Content/menu/28.png"},{"childs":[{"childs":[],"id":28,"name_":"订单管理","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":29,"name_":"车体识别","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":30,"name_":"安全管理","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":31,"name_":"成本分析","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":32,"name_":"班制管理","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":33,"name_":"生产报表","p_id_":13,"level_":3,"param":null,"sen":null,"imgs":null}],"id":13,"name_":"生产管理","p_id_":5,"level_":2,"param":null,"sen":"sc","imgs":"/Content/menu/29.png"},{"childs":[{"childs":[],"id":34,"name_":"实时报警","p_id_":14,"level_":3,"param":null,"sen":"wx_ssbj","imgs":null},{"childs":[],"id":35,"name_":"设备开动率","p_id_":14,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":36,"name_":"维修资料","p_id_":14,"level_":3,"param":null,"sen":"wx_zl","imgs":null},{"childs":[],"id":37,"name_":"智能备件库","p_id_":14,"level_":3,"param":null,"sen":"wx_znbjk","imgs":null},{"childs":[],"id":38,"name_":"PM预防性维修计划","p_id_":14,"level_":3,"param":null,"sen":"wx_pm","imgs":null},{"childs":[],"id":39,"name_":"TPM点检","p_id_":14,"level_":3,"param":null,"sen":"wx_tpm","imgs":null},{"childs":[],"id":40,"name_":"报表","p_id_":14,"level_":3,"param":null,"sen":null,"imgs":null}],"id":14,"name_":"维修管理","p_id_":5,"level_":2,"param":"p=1&s=2","sen":"wx","imgs":"/Content/menu/30.png"},{"childs":[{"childs":[],"id":41,"name_":"质量检查","p_id_":15,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":42,"name_":"品质追溯","p_id_":15,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":43,"name_":"暗灯系统","p_id_":15,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":44,"name_":"报表","p_id_":15,"level_":3,"param":null,"sen":null,"imgs":null}],"id":15,"name_":"质量管理","p_id_":5,"level_":2,"param":null,"sen":"zl","imgs":"/Content/menu/31.png"},{"childs":[{"childs":[],"id":45,"name_":"单个能源","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":46,"name_":"单体设备","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":47,"name_":"总耗量","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":48,"name_":"能源供应状态","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":49,"name_":"报表","p_id_":16,"level_":3,"param":null,"sen":null,"imgs":null}],"id":16,"name_":"能源管理","p_id_":5,"level_":2,"param":null,"sen":"ny","imgs":"/Content/menu/32.png"},{"childs":[],"id":17,"name_":"多媒体控制","p_id_":5,"level_":2,"param":null,"sen":"dmt","imgs":"/Content/menu/33.png"}]
         * id : 5
         * name_ : 管理
         * p_id_ : null
         * level_ : 1
         * param : null
         * sen : null
         * imgs : null
         */

        private int id;
        private String name_;
        private String p_id_;
        private int level_;
        private String param;
        private String sen;
        private String imgs;
        private ArrayList<ChildsBeanXX> childs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName_() {
            return name_;
        }

        public void setName_(String name_) {
            this.name_ = name_;
        }

        public String getP_id_() {
            return p_id_;
        }

        public void setP_id_(String p_id_) {
            this.p_id_ = p_id_;
        }

        public int getLevel_() {
            return level_;
        }

        public void setLevel_(int level_) {
            this.level_ = level_;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getSen() {
            return sen;
        }

        public void setSen(String sen) {
            this.sen = sen;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public ArrayList<ChildsBeanXX> getChilds() {
            return childs;
        }

        public void setChilds(ArrayList<ChildsBeanXX> childs) {
            this.childs = childs;
        }

        public static class ChildsBeanXX implements Parcelable {
            /**
             * childs : [{"childs":[{"childs":[],"id":55,"name_":"设备1","p_id_":18,"level_":4,"param":"{\"diagrame_name\":\"2\"}","sen":null,"imgs":null},{"childs":[],"id":56,"name_":"设备2","p_id_":18,"level_":4,"param":null,"sen":null,"imgs":null}],"id":18,"name_":"输送设备","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":19,"name_":"前处理电泳","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":20,"name_":"空调","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":21,"name_":"烘干及闪干","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":22,"name_":"湿式喷房","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":23,"name_":"机器人","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":24,"name_":"集中供漆","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":25,"name_":"干式喷房","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":26,"name_":"控制","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null},{"childs":[],"id":27,"name_":"报表","p_id_":12,"level_":3,"param":null,"sen":null,"imgs":null}]
             * id : 12
             * name_ : 设备管理
             * p_id_ : 5
             * level_ : 2
             * param : null
             * sen : sb
             * imgs : /Content/menu/28.png
             */

            private int id;
            private String name_;
            private int p_id_;
            private int level_;
            private String param;
            private String sen;
            private String imgs;
            private ArrayList<ChildsBeanX> childs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName_() {
                return name_;
            }

            public void setName_(String name_) {
                this.name_ = name_;
            }

            public int getP_id_() {
                return p_id_;
            }

            public void setP_id_(int p_id_) {
                this.p_id_ = p_id_;
            }

            public int getLevel_() {
                return level_;
            }

            public void setLevel_(int level_) {
                this.level_ = level_;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public String getSen() {
                return sen;
            }

            public void setSen(String sen) {
                this.sen = sen;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public ArrayList<ChildsBeanX> getChilds() {
                return childs;
            }

            public void setChilds(ArrayList<ChildsBeanX> childs) {
                this.childs = childs;
            }

            public static class ChildsBeanX implements Parcelable {
                /**
                 * childs : [{"childs":[],"id":55,"name_":"设备1","p_id_":18,"level_":4,"param":"{\"diagrame_name\":\"2\"}","sen":null,"imgs":null},{"childs":[],"id":56,"name_":"设备2","p_id_":18,"level_":4,"param":null,"sen":null,"imgs":null}]
                 * id : 18
                 * name_ : 输送设备
                 * p_id_ : 12
                 * level_ : 3
                 * param : null
                 * sen : null
                 * imgs : null
                 */

                private int id;
                private String name_;
                private int p_id_;
                private int level_;
                private String param;
                private String sen;
                private String imgs;
                private ArrayList<ChildsBean> childs;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName_() {
                    return name_;
                }

                public void setName_(String name_) {
                    this.name_ = name_;
                }

                public int getP_id_() {
                    return p_id_;
                }

                public void setP_id_(int p_id_) {
                    this.p_id_ = p_id_;
                }

                public int getLevel_() {
                    return level_;
                }

                public void setLevel_(int level_) {
                    this.level_ = level_;
                }

                public String getParam() {
                    return param;
                }

                public void setParam(String param) {
                    this.param = param;
                }

                public String getSen() {
                    return sen;
                }

                public void setSen(String sen) {
                    this.sen = sen;
                }

                public String getImgs() {
                    return imgs;
                }

                public void setImgs(String imgs) {
                    this.imgs = imgs;
                }

                public ArrayList<ChildsBean> getChilds() {
                    return childs;
                }

                public void setChilds(ArrayList<ChildsBean> childs) {
                    this.childs = childs;
                }

                public static class ChildsBean implements Serializable {
                    /**
                     * childs : []
                     * id : 55
                     * name_ : 设备1
                     * p_id_ : 18
                     * level_ : 4
                     * param : {"diagrame_name":"2"}
                     * sen : null
                     * imgs : null
                     */

                    private int id;
                    private String name_;
                    private int p_id_;
                    private int level_;
                    private String param;
                    private String sen;
                    private String imgs;
                    private List<?> childs;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName_() {
                        return name_;
                    }

                    public void setName_(String name_) {
                        this.name_ = name_;
                    }

                    public int getP_id_() {
                        return p_id_;
                    }

                    public void setP_id_(int p_id_) {
                        this.p_id_ = p_id_;
                    }

                    public int getLevel_() {
                        return level_;
                    }

                    public void setLevel_(int level_) {
                        this.level_ = level_;
                    }

                    public String getParam() {
                        return param;
                    }

                    public void setParam(String param) {
                        this.param = param;
                    }

                    public String getSen() {
                        return sen;
                    }

                    public void setSen(String sen) {
                        this.sen = sen;
                    }

                    public String getImgs() {
                        return imgs;
                    }

                    public void setImgs(String imgs) {
                        this.imgs = imgs;
                    }

                    public List<?> getChilds() {
                        return childs;
                    }

                    public void setChilds(List<?> childs) {
                        this.childs = childs;
                    }
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.name_);
                    dest.writeInt(this.p_id_);
                    dest.writeInt(this.level_);
                    dest.writeString(this.param);
                    dest.writeString(this.sen);
                    dest.writeString(this.imgs);
                    dest.writeList(this.childs);
                }

                public ChildsBeanX() {
                }

                protected ChildsBeanX(Parcel in) {
                    this.id = in.readInt();
                    this.name_ = in.readString();
                    this.p_id_ = in.readInt();
                    this.level_ = in.readInt();
                    this.param = in.readString();
                    this.sen = in.readString();
                    this.imgs = in.readString();
                    this.childs = new ArrayList<ChildsBean>();
                    in.readList(this.childs, ChildsBean.class.getClassLoader());
                }

                public static final Creator<ChildsBeanX> CREATOR = new Creator<ChildsBeanX>() {
                    @Override
                    public ChildsBeanX createFromParcel(Parcel source) {
                        return new ChildsBeanX(source);
                    }

                    @Override
                    public ChildsBeanX[] newArray(int size) {
                        return new ChildsBeanX[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.name_);
                dest.writeInt(this.p_id_);
                dest.writeInt(this.level_);
                dest.writeString(this.param);
                dest.writeString(this.sen);
                dest.writeString(this.imgs);
                dest.writeList(this.childs);
            }

            public ChildsBeanXX() {
            }

            protected ChildsBeanXX(Parcel in) {
                this.id = in.readInt();
                this.name_ = in.readString();
                this.p_id_ = in.readInt();
                this.level_ = in.readInt();
                this.param = in.readString();
                this.sen = in.readString();
                this.imgs = in.readString();
                this.childs = new ArrayList<ChildsBeanX>();
                in.readList(this.childs, ChildsBeanX.class.getClassLoader());
            }

            public static final Creator<ChildsBeanXX> CREATOR = new Creator<ChildsBeanXX>() {
                @Override
                public ChildsBeanXX createFromParcel(Parcel source) {
                    return new ChildsBeanXX(source);
                }

                @Override
                public ChildsBeanXX[] newArray(int size) {
                    return new ChildsBeanXX[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name_);
            dest.writeString(this.p_id_);
            dest.writeInt(this.level_);
            dest.writeString(this.param);
            dest.writeString(this.sen);
            dest.writeString(this.imgs);
            dest.writeList(this.childs);
        }

        public CBean() {
        }

        protected CBean(Parcel in) {
            this.id = in.readInt();
            this.name_ = in.readString();
            this.p_id_ = in.readString();
            this.level_ = in.readInt();
            this.param = in.readString();
            this.sen = in.readString();
            this.imgs = in.readString();
            this.childs = new ArrayList<ChildsBeanXX>();
            in.readList(this.childs, ChildsBeanXX.class.getClassLoader());
        }

        public static final Parcelable.Creator<CBean> CREATOR = new Parcelable.Creator<CBean>() {
            @Override
            public CBean createFromParcel(Parcel source) {
                return new CBean(source);
            }

            @Override
            public CBean[] newArray(int size) {
                return new CBean[size];
            }
        };
    }
}
