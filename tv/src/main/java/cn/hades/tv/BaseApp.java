package cn.hades.tv;

import android.app.Application;

public abstract class BaseApp extends Application {

    protected abstract void init();

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

}
