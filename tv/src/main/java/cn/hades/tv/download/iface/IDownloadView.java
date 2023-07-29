package cn.hades.tv.download.iface;

import java.io.File;

public interface IDownloadView {

    void initTask(String url, String md5, String name, String path);

    void startDown();

    void stopDown();

    boolean isDowning();

    void onDownStart();//下载开始

    void onDownProgress(long current, long total);

    void onDownWin(File file);

    void onDownFail(Exception e);

    void onDownEnd();//下载结束


    void onViewStart();

    void onViewEnd();

    void onViewProgress(int progress);

    void onViewWin();

    void onViewFail(Exception e);

}
