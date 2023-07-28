package cn.hades.water;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;

import cn.hades.tv.base.BaseActivity;
import cn.hades.water.databinding.ActivityBgBinding;

public class BgActivity extends BaseActivity<ActivityBgBinding> implements View.OnClickListener {

    public static void start() {
        ActivityUtils.startActivity(BgActivity.class);
    }

    @Override
    public boolean enableAppBackground() {
        return true;
    }

    @Override
    public ActivityBgBinding getViewBinding() {
        return ActivityBgBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        viewBinding.btn1.setOnClickListener(this);
        viewBinding.btn2.setOnClickListener(this);
        viewBinding.btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            saveAppBackground(null);
        } else if (v.getId() == R.id.btn2) {
            saveAppBackground("http://pic1.win4000.com/wallpaper/f/57468bbf45802.jpg");
        } else {
            saveAppBackground(R.drawable.bg_app_1);
        }
    }

}