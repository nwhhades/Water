package cn.hades.tv.install;

import android.util.Log;

import com.blankj.utilcode.util.AppUtils;

import cn.hades.tv.utils.SysUtils;

import java.io.File;

public class InstallUtils {

    private static final String TAG = "InstallUtils";

    public static int install(final File file) {
        Log.d(TAG, "install: 开始安装 " + file);
        if (SysUtils.isSysApp()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(800);
                            Log.d(TAG, "run: 静默安装");
                            SilentInstallUtils.silentInstall(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                return 1;
            }
        }
        //简单的安装
        Log.d(TAG, "install: 简单安装");
        AppUtils.installApp(file);
        return 2;
    }

}
