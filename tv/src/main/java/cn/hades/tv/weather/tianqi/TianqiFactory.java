package cn.hades.tv.weather.tianqi;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.reflect.TypeToken;

import cn.hades.net.NetUtils;
import cn.hades.net.OnNetListener;
import cn.hades.net.base.NetCacheType;
import cn.hades.net.base.NetRequest;
import cn.hades.tv.R;
import cn.hades.tv.weather.base.BaseWeather;
import cn.hades.tv.weather.base.OnWeatherListener;
import cn.hades.tv.weather.base.WeatherFactory;
import io.reactivex.disposables.Disposable;

public class TianqiFactory implements WeatherFactory, OnNetListener<TianqiBean> {

    private static final String TAG = "TianqiFactory";
    private static final String key1 = "Url1";
    private static final String key2 = "Url2";
    private static final String defaultUrl = "https://yiketianqi.com/free/day?appid=43656176&appsecret=I42og6Lm";

    public static void setUrl(final String url1, final String url2) {
        SPUtils.getInstance(TAG).put(key1, url1);
        SPUtils.getInstance(TAG).put(key2, url2);
    }

    private Disposable disposable;
    private final OnWeatherListener onWeatherListener;

    public TianqiFactory(OnWeatherListener onWeatherListener) {
        this.onWeatherListener = onWeatherListener;
    }

    @Override
    public String getUrl1() {
        return SPUtils.getInstance(TAG).getString(key1, defaultUrl);
    }

    @Override
    public String getUrl2() {
        return SPUtils.getInstance(TAG).getString(key2, defaultUrl);
    }

    @Override
    public NetRequest getRequest() {
        NetRequest netRequest = new NetRequest();
        netRequest.setKey(TAG);
        netRequest.setUrl1(getUrl1());
        netRequest.setUrl2(getUrl2());
        netRequest.setCacheType(NetCacheType.ONLY_CACHE);
        netRequest.setCacheTime(60 * 1000);
        return netRequest;
    }

    @Override
    public void startGetWeather() {
        NetUtils.instance.get(getRequest(), TianqiFactory.this);
    }

    @Override
    public void stopGetWeather() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void setWeather(BaseWeather weather) {
        if (onWeatherListener != null) {
            onWeatherListener.onWeather(weather);
        }
    }

    @Override
    public TypeToken<TianqiBean> getTypeToken() {
        return new TypeToken<TianqiBean>() {
        };
    }

    @Override
    public String decodeDataStr(String s) {
        return s;
    }

    @Override
    public boolean checkData(TianqiBean tianqiBean) {
        return tianqiBean != null && tianqiBean.getWeatherCity() != null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onCache(TianqiBean tianqiBean) {
        setWeather(tianqiBean);
    }

    @Override
    public void onNetStart(Disposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public void onNetWin(TianqiBean tianqiBean) {
        setWeather(tianqiBean);
    }

    @Override
    public void onNetFail(Throwable throwable) {
        setWeather(null);
    }

    @Override
    public void onNetEnd() {

    }

    @Override
    public void onEnd() {

    }


    public static int getResIdByWeaImg(String weaImg) {
        int icon = R.mipmap.ic_weather_err;
        if (weaImg != null) {
            switch (weaImg) {
                case "xue":
                    icon = R.mipmap.ic_weather_xue;
                    break;
                case "lei":
                    icon = R.mipmap.ic_weather_lei;
                    break;
                case "shachen":
                    icon = R.mipmap.ic_weather_shachen;
                    break;
                case "wu":
                    icon = R.mipmap.ic_weather_wu;
                    break;
                case "bingbao":
                    icon = R.mipmap.ic_weather_bingbao;
                    break;
                case "yun":
                    icon = R.mipmap.ic_weather_yun;
                    break;
                case "yu":
                    icon = R.mipmap.ic_weather_yu;
                    break;
                case "yin":
                    icon = R.mipmap.ic_weather_yin;
                    break;
                case "qing":
                    icon = R.mipmap.ic_weather_qing;
                    break;
                default:
                    break;
            }
        }
        return icon;
    }

}
