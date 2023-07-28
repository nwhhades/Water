package cn.hades.tv;

import android.app.Application;

import com.hjq.toast.Toaster;

import cn.hades.net.NetUtils;
import cn.hades.tv.toaster.BigBlackToastStyle;

public abstract class BaseApp extends Application {

    protected abstract void init();

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initToaster();
        initNet();
    }

    private void initToaster() {
        Toaster.init(BaseApp.this, new BigBlackToastStyle());
    }

    private void initNet() {
        NetUtils.init(BaseApp.this);
    }

}
