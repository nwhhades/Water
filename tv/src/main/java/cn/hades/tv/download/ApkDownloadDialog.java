package cn.hades.tv.download;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.AppUtils;
import com.hjq.toast.Toaster;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import cn.hades.tv.install.ApkReceiver;
import cn.hades.tv.install.InstallEvent;
import cn.hades.tv.install.InstallUtils;

public class ApkDownloadDialog extends DownloadDialog {

    private static final String TAG = "ApkDownloadDialog";

    private ApkReceiver apkReceiver;

    public ApkDownloadDialog(String title, String content, String fileUrl, String fileMd5, String fileName, String filePath) {
        super(title, content, fileUrl, fileMd5, fileName, filePath);
    }

    @Override
    public void showFragment(@NonNull FragmentManager manager) {
        super.showFragment(manager);
        Log.d(TAG, "showFragment: 注册监听");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClose(boolean isCancel) {
        EventBus.getDefault().unregister(this);
        Log.d(TAG, "onClose: 解除监听");
        super.onClose(isCancel);
    }

    @Override
    public void onDownWin(File file) {
        super.onDownWin(file);
        try {
            //获取安装包信息
            AppUtils.AppInfo appInfo = AppUtils.getApkInfo(file);
            if (appInfo != null) {
                //获取的信息保存起来
                String apkPackageName = appInfo.getPackageName();
                //开始安装
                int code = InstallUtils.install(file);
                if (code == 1) {
                    //方式二 取消用户手动hide的权限
                    onViewInstallStart();
                    //添加安装进度的监听
                    Activity activity = getActivity();
                    if (activity != null) {
                        apkReceiver = new ApkReceiver(apkPackageName, activity);
                        getLifecycle().addObserver(apkReceiver);
                        return;
                    }
                }
            } else {
                Log.d(TAG, "onDownSuccess: 读取APK信息失败");
                Toaster.show("APK文件错误");
            }
            //自动关闭
            hideFragment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewWin() {

    }

    public void onViewInstallStart() {
        if (viewBinding != null && isVisible()) {
            viewBinding.tvContent.setText("应用安装中，请耐心等待。");
            viewBinding.btn1.setText("安装中...");
            viewBinding.btn1.setEnabled(false);
            viewBinding.btn2.setEnabled(false);
            viewBinding.tvProgress.setText("");
            viewBinding.lpiProgress.setIndeterminate(true);
        }
    }

    public void onViewInstallEnd() {
        hideFragment();
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInstallEvent(InstallEvent event) {
        Log.d(TAG, "onInstallEvent: 收到了安装成功事件");
        if (apkReceiver != null) {
            if (event.getPackageName().equals(apkReceiver.getApkPackageName())) {
                Toaster.show(event.getName() + " - 安装成功");
                //安装成功了
                onViewInstallEnd();
            }
        }
    }

    @SuppressWarnings("unused")
    public static class Builder {
        private String title;
        private String content;
        private String fileUrl;
        private String fileMd5;
        private String fileName = "";
        private String filePath;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public Builder setFileMd5(String fileMd5) {
            this.fileMd5 = fileMd5;
            return this;
        }

        public Builder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public ApkDownloadDialog build() {
            return new ApkDownloadDialog(title, content, fileUrl, fileMd5, fileName, filePath);
        }
    }

}
