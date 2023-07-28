package cn.hades.tv.weather.tianqi;

import cn.hades.tv.weather.base.BaseWeather;

public class TianqiBean extends BaseWeather {

    private String city;
    private String wea;
    private String wea_img;
    private String tem;
    private String tem_day;
    private String tem_night;
    private String win;//风向
    private String win_speed;//风力等级
    private String win_meter;//风速
    private String air;//空气质量
    private String update_time;//更新时间

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWea_img() {
        return wea_img;
    }

    public void setWea_img(String wea_img) {
        this.wea_img = wea_img;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getTem_day() {
        return tem_day;
    }

    public void setTem_day(String tem_day) {
        this.tem_day = tem_day;
    }

    public String getTem_night() {
        return tem_night;
    }

    public void setTem_night(String tem_night) {
        this.tem_night = tem_night;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWin_speed() {
        return win_speed;
    }

    public void setWin_speed(String win_speed) {
        this.win_speed = win_speed;
    }

    public String getWin_meter() {
        return win_meter;
    }

    public void setWin_meter(String win_meter) {
        this.win_meter = win_meter;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public String getWeatherCity() {
        return city;
    }

    @Override
    public String getWeatherWea() {
        return wea;
    }

    @Override
    public String getWeatherWeaImg() {
        return wea_img;
    }

    @Override
    public String getWeatherTem() {
        return tem;
    }

    @Override
    public String getWeatherTemDay() {
        return tem_day;
    }

    @Override
    public String getWeatherTemNight() {
        return tem_night;
    }

}
