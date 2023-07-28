package cn.hades.tv.weather.base;

import cn.hades.net.base.NetRequest;

public interface WeatherFactory {

    String getUrl1();

    String getUrl2();

    NetRequest getRequest();

    void startGetWeather();

    void stopGetWeather();

}
