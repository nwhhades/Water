package cn.hades.water;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.blankj.utilcode.util.PathUtils;

import cn.hades.tv.base.BaseActivity;
import cn.hades.tv.download.DownloadDialog;
import cn.hades.water.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements View.OnClickListener {

    private final Handler handler = new Handler(Looper.myLooper());
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            autoRun();
            handler.postDelayed(runnable, 5000);
        }
    };

    private void autoRun() {
        hideLoadingView();
    }

    @Override
    public boolean enableAppBackground() {
        return true;
    }

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        viewBinding.tianqi.setColor(R.color.black);
        getLifecycle().addObserver(viewBinding.tianqi);
        handler.post(runnable);
        viewBinding.btn1.setOnClickListener(this);
        viewBinding.btn2.setOnClickListener(this);
        viewBinding.btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            showLoadingView("加载数据中...");
        } else if (v.getId() == R.id.btn2) {
            BgActivity.start();
        } else {
            DownloadDialog downloadDialog = new DownloadDialog.Builder()
                    .setTitle("爱奇艺")
                    .setContent("下载安装中...")
                    .setFileUrl("https://cdn.tvmars.com/huoxing/mars_2.0.6/2.0.6new/mars_common_official_2.0.6_169_release-signed.apk")
                    .setFileMd5("")
                    .setFilePath(PathUtils.getCachePathExternalFirst())
                    .build();
            downloadDialog.showFragment(getSupportFragmentManager());
        }
    }

}