package cn.hades.tv.weather;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import cn.hades.tv.R;
import cn.hades.tv.databinding.LayoutTianqiViewBinding;
import cn.hades.tv.weather.base.BaseWeather;
import cn.hades.tv.weather.base.OnWeatherListener;
import cn.hades.tv.weather.tianqi.TianqiFactory;

public class TianqiView extends FrameLayout implements LifecycleEventObserver, OnWeatherListener {

    private LayoutTianqiViewBinding binding;
    private TianqiFactory tianqiFactory;

    public TianqiView(@NonNull Context context) {
        this(context, null);
    }

    public TianqiView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TianqiView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        binding = LayoutTianqiViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        tianqiFactory = new TianqiFactory(TianqiView.this);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        switch (event) {
            case ON_RESUME:
                tianqiFactory.startGetWeather();
                break;
            case ON_PAUSE:
                tianqiFactory.stopGetWeather();
                break;
            default:
                break;
        }
    }

    @Override
    public void onWeather(BaseWeather weather) {
        if (weather == null) {
            binding.ivWeatherWea.setImageResource(R.mipmap.ic_weather_err);
            binding.tvWeatherWea.setText("");
            binding.tvWeatherCity.setText("");
            binding.tvWeatherTem.setText("");
            binding.tvWeatherWendu.setText("");
            binding.tvWeatherErr.setText("暂无数据");
        } else {
            binding.ivWeatherWea.setImageResource(TianqiFactory.getResIdByWeaImg(weather.getWeatherWeaImg()));
            binding.tvWeatherWea.setText(weather.getWeatherWea());
            binding.tvWeatherCity.setText(weather.getWeatherCity());
            binding.tvWeatherTem.setText(weather.getWeatherTem());
            String text = weather.getWeatherTemNight() + " ~ " + weather.getWeatherTemDay();
            binding.tvWeatherWendu.setText(text);
            binding.tvWeatherErr.setText("");
        }
    }

    public void setColor(@ColorRes int res_color) {
        if (binding != null) {
            binding.ivWeatherWea.setColorFilter(ContextCompat.getColor(getContext(), res_color), PorterDuff.Mode.SRC_IN);
            binding.tvWeatherWea.setTextColor(ResourcesCompat.getColor(getResources(), res_color, null));
            binding.tvWeatherCity.setTextColor(ResourcesCompat.getColor(getResources(), res_color, null));
            binding.tvWeatherTem.setTextColor(ResourcesCompat.getColor(getResources(), res_color, null));
            binding.tvWeatherWendu.setTextColor(ResourcesCompat.getColor(getResources(), res_color, null));
            binding.tvWeatherErr.setTextColor(ResourcesCompat.getColor(getResources(), res_color, null));
        }
    }

}
