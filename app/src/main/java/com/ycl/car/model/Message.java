package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/3/3.
 */

public class Message {
    private int wranNum;
    private int PMNum;
    private int TPMNum;
    private int mainTainNum;

    public int getWranNum() {
        return wranNum;
    }

    public void setWranNum(int wranNum) {
        this.wranNum = wranNum;
    }

    public int getPMNum() {
        return PMNum;
    }

    public void setPMNum(int PMNum) {
        this.PMNum = PMNum;
    }

    public int getTPMNum() {
        return TPMNum;
    }

    public void setTPMNum(int TPMNum) {
        this.TPMNum = TPMNum;
    }

    public int getMainTainNum() {
        return mainTainNum;
    }

    public void setMainTainNum(int mainTainNum) {
        this.mainTainNum = mainTainNum;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    private int msgNum;
}
