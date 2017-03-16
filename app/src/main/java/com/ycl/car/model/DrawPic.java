package com.ycl.car.model;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/23.
 */

public class DrawPic {

    /**
     * StandardDirection : 1
     * RollingBed : {"ChartLabel":"RB nameffdd","StandardDirection":0,"PointName":"RB pointfff","RotateAngle":0,"PointValue":null,"PointValueCol":[{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}],"PointValueOptions":[],"ItemType":"RollingBed","X":0,"Y":0,"W":0,"H":0}
     * ChartLabel : sample name
     * PointName : sample point
     * StrBins : 10,20,30
     * PointValueCol : [{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Position1","Value":"16","Value_bool":false},{"Key":"Position2","Value":"17","Value_bool":false},{"Key":"Position3","Value":"18","Value_bool":false},{"Key":"Position4","Value":"19","Value_bool":false},{"Key":"Position5","Value":"20","Value_bool":false},{"Key":"Position6","Value":"21","Value_bool":false},{"Key":"Position7","Value":"22","Value_bool":false},{"Key":"Position8","Value":"23","Value_bool":false}]
     * PointValueOptions : []
     * ItemType : STLBed
     * X : 186.25
     * Y : 115.293333333333
     * W : 126.0
     * H : 432.0
     */
    /**
     * //     * ChartLabel : H Point
     * //     * StandardDirection : 0
     * //     * PointName : OVEN.PLC.PROGRAM:RCP2HFO.HMI_HFO_001_AK03C_B02.ACTRL[1]
     * //     * RotateAngle : 0
     * //     * PointValueCol : [{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}]
     * //     * PointValueOptions : []
     * //     * ItemType : RollingBed
     * //     * X : 102.25
     * //     * Y : 50.2933333333333
     * //     * W : 115.0
     * //     * H : 70.0
     * //     * TextLabel : 实线监测室
     * //     * StrBorderStyle : 0
     * //
     */

    private RollingBedBean RollingBed;
    private RotationBean rotation;
    /**
     * RollingBed : {"StandardDirection":0,"ChartLabel":"RB name","PointName":"RB point","RotateAngle":0,"PointValue":null,"PointValueCol":[{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}],"PointValueOptions":[],"ItemType":"RollingBed","X":0,"Y":0,"W":0,"H":0}
     * Rotation : {"PointName":"RT point Name","PointValue":null,"PointValueCol":[{"Key":"WarningC","Value":"0","Value_bool":false},{"Key":"WarningB","Value":"1","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"2","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"3","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"0","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"5","Value_bool":false},{"Key":"Ready","Value":"6","Value_bool":false}],"PointValueOptions":[],"ItemType":"Rotation","X":0,"Y":0,"W":0,"H":0}
     * PointValueOptions : []
     * X : 134.25
     * Y : 66.2933333333333
     * W : 156.0
     * H : 432.0
     */

    private RotationBean Rotation;

    public RotationBean getRotation() {
        return rotation;
    }

    public void setRotation(RotationBean rotation) {
        this.rotation = rotation;
    }

    public static class RotationBean {

        /**
         * PointName : RT point Name
         * PointValue : null
         * PointValueCol : [{"Key":"WarningC","Value":"0","Value_bool":false},{"Key":"WarningB","Value":"1","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"2","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"3","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"0","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"5","Value_bool":false},{"Key":"Ready","Value":"6","Value_bool":false}]
         * PointValueOptions : []
         * ItemType : Rotation
         * X : 0
         * Y : 0
         * W : 0
         * H : 0
         */

        private String PointName;
        private Object PointValue;
        private String ItemType;
        private int X;
        private int Y;
        private int W;
        private int H;
        private List<PointValueColBean> PointValueCol;
        private List<?> PointValueOptions;

        public String getPointName() {
            return PointName;
        }

        public void setPointName(String PointName) {
            this.PointName = PointName;
        }

        public Object getPointValue() {
            return PointValue;
        }

        public void setPointValue(Object PointValue) {
            this.PointValue = PointValue;
        }

        public String getItemType() {
            return ItemType;
        }

        public void setItemType(String ItemType) {
            this.ItemType = ItemType;
        }

        public int getX() {
            return X;
        }

        public void setX(int X) {
            this.X = X;
        }

        public int getY() {
            return Y;
        }

        public void setY(int Y) {
            this.Y = Y;
        }

        public int getW() {
            return W;
        }

        public void setW(int W) {
            this.W = W;
        }

        public int getH() {
            return H;
        }

        public void setH(int H) {
            this.H = H;
        }

        public List<PointValueColBean> getPointValueCol() {
            return PointValueCol;
        }

        public void setPointValueCol(List<PointValueColBean> PointValueCol) {
            this.PointValueCol = PointValueCol;
        }

        public List<?> getPointValueOptions() {
            return PointValueOptions;
        }

        public void setPointValueOptions(List<?> PointValueOptions) {
            this.PointValueOptions = PointValueOptions;
        }

        public static class PointValueColBean {
            /**
             * Key : WarningC
             * Value : 0
             * Value_bool : false
             */

            private String Key;
            private String Value;
            private boolean Value_bool;

            public String getKey() {
                return Key;
            }

            public void setKey(String Key) {
                this.Key = Key;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public boolean isValue_bool() {
                return Value_bool;
            }

            public void setValue_bool(boolean Value_bool) {
                this.Value_bool = Value_bool;
            }
        }
    }


    public RollingBedBean getRollingBed() {
        return RollingBed;
    }

    public void setRollingBed(RollingBedBean RollingBed) {
        this.RollingBed = RollingBed;
    }


    public static class RollingBedBean {
        /**
         * ChartLabel : RB nameffdd
         * StandardDirection : 0
         * PointName : RB pointfff
         * RotateAngle : 0
         * PointValue : null
         * PointValueCol : [{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}]
         * PointValueOptions : []
         * ItemType : RollingBed
         * X : 0.0
         * Y : 0.0
         * W : 0.0
         * H : 0.0
         */

        private String ChartLabel;
        private int StandardDirection;
        private String PointName;
        private int RotateAngle;
        private Object PointValue;
        private String ItemType;
        private double X;
        private double Y;
        private double W;
        private double H;
        private List<PointValueColBean> PointValueCol;
        private List<?> PointValueOptions;

        public String getChartLabel() {
            return ChartLabel;
        }

        public void setChartLabel(String ChartLabel) {
            this.ChartLabel = ChartLabel;
        }

        public int getStandardDirection() {
            return StandardDirection;
        }

        public void setStandardDirection(int StandardDirection) {
            this.StandardDirection = StandardDirection;
        }

        public String getPointName() {
            return PointName;
        }

        public void setPointName(String PointName) {
            this.PointName = PointName;
        }

        public int getRotateAngle() {
            return RotateAngle;
        }

        public void setRotateAngle(int RotateAngle) {
            this.RotateAngle = RotateAngle;
        }

        public Object getPointValue() {
            return PointValue;
        }

        public void setPointValue(Object PointValue) {
            this.PointValue = PointValue;
        }

        public String getItemType() {
            return ItemType;
        }

        public void setItemType(String ItemType) {
            this.ItemType = ItemType;
        }

        public double getX() {
            return X;
        }

        public void setX(double X) {
            this.X = X;
        }

        public double getY() {
            return Y;
        }

        public void setY(double Y) {
            this.Y = Y;
        }

        public double getW() {
            return W;
        }

        public void setW(double W) {
            this.W = W;
        }

        public double getH() {
            return H;
        }

        public void setH(double H) {
            this.H = H;
        }

        public List<PointValueColBean> getPointValueCol() {
            return PointValueCol;
        }

        public void setPointValueCol(List<PointValueColBean> PointValueCol) {
            this.PointValueCol = PointValueCol;
        }

        public List<?> getPointValueOptions() {
            return PointValueOptions;
        }

        public void setPointValueOptions(List<?> PointValueOptions) {
            this.PointValueOptions = PointValueOptions;
        }

        public static class PointValueColBean {
            /**
             * Key : IsAuto
             * Value : 0
             * Value_bool : false
             */

            private String Key;
            private String Value;
            private boolean Value_bool;

            public String getKey() {
                return Key;
            }

            public void setKey(String Key) {
                this.Key = Key;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public boolean isValue_bool() {
                return Value_bool;
            }

            public void setValue_bool(boolean Value_bool) {
                this.Value_bool = Value_bool;
            }
        }
    }


    @Override
    public String toString() {
        return "DrawPic{" +
                "ChartLabel='" + ChartLabel + '\'' +
                ", StandardDirection=" + StandardDirection +
                ", PointName='" + PointName + '\'' +
                ", RotateAngle=" + RotateAngle +
                ", ItemType='" + ItemType + '\'' +
                ", X=" + X +
                ", Y=" + Y +
                ", W=" + W +
                ", H=" + H +
                ", TextLabel='" + TextLabel + '\'' +
                ", StrBorderStyle=" + StrBorderStyle +
                ", PointValueCol=" + PointValueCol +
                ", PointValueOptions=" + PointValueOptions +
                '}';
    }

    /**
     * ChartLabel : H Point
     * StandardDirection : 0
     * PointName : OVEN.PLC.PROGRAM:RCP2HFO.HMI_HFO_001_AK03C_B02.ACTRL[1]
     * RotateAngle : 0
     * PointValueCol : [{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}]
     * PointValueOptions : []
     * ItemType : RollingBed
     * X : 102.25
     * Y : 50.2933333333333
     * W : 115.0
     * H : 70.0
     * TextLabel : 实线监测室
     * StrBorderStyle : 0
     */

    private String ChartLabel;
    private int StandardDirection;
    private String PointName;
    private int RotateAngle;
    private String ItemType;
    private double X;
    private double Y;
    private double W;
    private double H;
    private String TextLabel;

    public String getStrBins() {
        return StrBins;
    }

    public void setStrBins(String strBins) {
        StrBins = strBins;
    }

    private String StrBins;
    private int StrBorderStyle;
    private List<PointValueColBean> PointValueCol;
    private List<?> PointValueOptions;

    public String getChartLabel() {
        return ChartLabel;
    }

    public void setChartLabel(String ChartLabel) {
        this.ChartLabel = ChartLabel;
    }

    public int getStandardDirection() {
        return StandardDirection;
    }

    public void setStandardDirection(int StandardDirection) {
        this.StandardDirection = StandardDirection;
    }

    public String getPointName() {
        return PointName;
    }

    public void setPointName(String PointName) {
        this.PointName = PointName;
    }

    public int getRotateAngle() {
        return RotateAngle;
    }

    public void setRotateAngle(int RotateAngle) {
        this.RotateAngle = RotateAngle;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String ItemType) {
        this.ItemType = ItemType;
    }

    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    public double getW() {
        return W;
    }

    public void setW(double W) {
        this.W = W;
    }

    public double getH() {
        return H;
    }

    public void setH(double H) {
        this.H = H;
    }

    public String getTextLabel() {
        return TextLabel;
    }

    public void setTextLabel(String TextLabel) {
        this.TextLabel = TextLabel;
    }

    public int getStrBorderStyle() {
        return StrBorderStyle;
    }

    public void setStrBorderStyle(int StrBorderStyle) {
        this.StrBorderStyle = StrBorderStyle;
    }

    public List<PointValueColBean> getPointValueCol() {
        return PointValueCol;
    }

    public void setPointValueCol(List<PointValueColBean> PointValueCol) {
        this.PointValueCol = PointValueCol;
    }

    public List<?> getPointValueOptions() {
        return PointValueOptions;
    }

    public void setPointValueOptions(List<?> PointValueOptions) {
        this.PointValueOptions = PointValueOptions;
    }

    public static class PointValueColBean {
        /**
         * Key : IsAuto
         * Value : 0
         * Value_bool : false
         */

        private String Key;
        private String Value;
        private boolean Value_bool;

        public String getKey() {
            return Key;
        }

        public void setKey(String Key) {
            this.Key = Key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public boolean isValue_bool() {
            return Value_bool;
        }

        public void setValue_bool(boolean Value_bool) {
            this.Value_bool = Value_bool;
        }

        @Override
        public String toString() {
            return "PointValueColBean{" +
                    "Key='" + Key + '\'' +
                    ", Value='" + Value + '\'' +
                    ", Value_bool=" + Value_bool +
                    '}';
        }
    }
}
