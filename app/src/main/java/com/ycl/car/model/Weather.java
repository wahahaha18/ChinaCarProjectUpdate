package com.ycl.car.model;

/**
 * 天气
 * Created by y11621546 on 2017/1/17.
 */

public class Weather {
    /**
     * weather : 晴
     * temp : 15
     * temphigh : 13
     * templow : 3
     * humidity : 12
     * winddirect : 西南风
     * windpower : 2级
     * windspeed : 22.0
     * aqi : 71
     * aqi_primarypollutant : PM10
     * aqi_quality : 良
     * updatetime : 2017-03-02 16:07:04
     */

    private WeatherBean weatherBean;

    public WeatherBean getWeatherBean() {
        return weatherBean;
    }

    public void setWeatherBean(WeatherBean weatherBean) {
        this.weatherBean = weatherBean;
    }

    private String weather;
    private String temp;
    private String temphigh;
    private String templow;
    private String humidity;
    private String winddirect;
    private String windpower;
    private String windspeed;
    private String aqi;
    private String aqi_primarypollutant;
    private String aqi_quality;
    private String updatetime;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemphigh() {
        return temphigh;
    }

    public void setTemphigh(String temphigh) {
        this.temphigh = temphigh;
    }

    public String getTemplow() {
        return templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getAqi_primarypollutant() {
        return aqi_primarypollutant;
    }

    public void setAqi_primarypollutant(String aqi_primarypollutant) {
        this.aqi_primarypollutant = aqi_primarypollutant;
    }

    public String getAqi_quality() {
        return aqi_quality;
    }

    public void setAqi_quality(String aqi_quality) {
        this.aqi_quality = aqi_quality;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public static class WeatherBean {
        private String warnNum;

        public String getPushNum() {
            return pushNum;
        }

        public void setPushNum(String pushNum) {
            this.pushNum = pushNum;
        }

        private String pushNum;
        private String PMNum;
        private String TPMNum;
        private String maintainNum;

        public String getWarnNum() {
            return warnNum;
        }

        public void setWarnNum(String warnNum) {
            this.warnNum = warnNum;
        }

        public String getPMNum() {
            return PMNum;
        }

        public void setPMNum(String PMNum) {
            this.PMNum = PMNum;
        }

        public String getTPMNum() {
            return TPMNum;
        }

        public void setTPMNum(String TPMNum) {
            this.TPMNum = TPMNum;
        }

        public String getMaintainNum() {
            return maintainNum;
        }

        public void setMaintainNum(String maintainNum) {
            this.maintainNum = maintainNum;
        }

        public String getMsgNum() {
            return msgNum;
        }

        public void setMsgNum(String msgNum) {
            this.msgNum = msgNum;
        }

        private String msgNum;
    }
}
