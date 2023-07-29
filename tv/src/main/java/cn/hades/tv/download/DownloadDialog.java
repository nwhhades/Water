package cn.hades.tv.download;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.RegexUtils;
import com.hjq.toast.Toaster;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;

import java.io.File;

public class DownloadDialog extends AbsDownloadDialog {

    private static final String TAG = "ApkDownloadDialog";

    public DownloadDialog(String title, String content, String fileUrl, String fileMd5, String fileName, String filePath) {
        super(title, content, fileUrl, fileMd5, fileName, filePath);
    }

    private DownloadTask downloadTask;

    private final DownloadListener1 downloadListener = new DownloadListener1() {
        @Override
        public void taskStart(@NonNull DownloadTask task, @NonNull Listener1Assist.Listener1Model model) {
            onDownStart();
        }

        @Override
        public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {

        }

        @Override
        public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {

        }

        @Override
        public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
            onDownProgress(currentOffset, totalLength);
        }

        @Override
        public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull Listener1Assist.Listener1Model model) {
            if (cause == EndCause.COMPLETED) {
                onDownWin(task.getFile());
            } else if (cause == EndCause.CANCELED) {
                onDownEnd();
            } else {
                if (realCause != null) {
                    onDownFail(realCause);
                } else {
                    onDownFail(new Exception("未知错误"));
                }
            }
        }
    };

    @Override
    public void initTask(String url, String md5, String name, String path) {
        Log.d(TAG, "initTask: \n" + url + "\n" + md5 + "\n" + name + "\n" + path);
        try {
            if (!RegexUtils.isURL(url)) return;
            downloadTask = new DownloadTask.Builder(url, path, name)
                    .setConnectionCount(1)
                    .setMinIntervalMillisCallbackProcess(50)
                    .setPassIfAlreadyCompleted(false)
                    .build();
            downloadTask.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startDown() {
        Log.d(TAG, "startDown: 开始下载");
        try {
            if (downloadTask != null) {
                downloadTask.enqueue(downloadListener);
            } else {
                Toaster.showShort("下载地址为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopDown() {
        Log.d(TAG, "stopDown: 停止下载");
        try {
            if (downloadTask != null) {
                downloadTask.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isDowning() {
        try {
            if (downloadTask != null) {
                return StatusUtil.Status.RUNNING.equals(StatusUtil.getStatus(downloadTask));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onDownWin(File file) {
        super.onDownWin(file);
        //TODO
    }

    @Override
    public void onViewWin() {
        Toaster.showShort("下载成功");
        hideFragment();
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

        public DownloadDialog build() {
            return new DownloadDialog(title, content, fileUrl, fileMd5, fileName, filePath);
        }
    }

}
