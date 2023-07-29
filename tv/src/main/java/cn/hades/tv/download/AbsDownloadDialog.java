package cn.hades.tv.download;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjq.toast.Toaster;

import java.io.File;

import cn.hades.tv.R;
import cn.hades.tv.base.BaseDialogFragment;
import cn.hades.tv.databinding.FragmentDownloadDialogBinding;
import cn.hades.tv.download.iface.IDownloadView;

public abstract class AbsDownloadDialog extends BaseDialogFragment<FragmentDownloadDialogBinding> implements IDownloadView, View.OnClickListener {

    private static final String TAG = "AbsDownloadDialog";

    protected final String title;
    protected final String content;
    protected final String fileUrl;
    protected final String fileMd5;
    protected final String fileName;
    protected final String filePath;

    public AbsDownloadDialog(String title, String content, String fileUrl, String fileMd5, String fileName, String filePath) {
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
        this.fileMd5 = fileMd5;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            if (isDowning()) {
                stopDown();
            } else {
                startDown();
            }
        } else {
            hideFragment();
        }
    }

    @Override
    public FragmentDownloadDialogBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentDownloadDialogBinding.inflate(inflater, container, false);
    }

    @Override
    public void initView() {
        viewBinding.tvTitle.setText(title);
        viewBinding.tvContent.setText(content);
        viewBinding.btn1.setOnClickListener(this);
        viewBinding.btn2.setOnClickListener(this);
        initTask(fileUrl, fileMd5, fileName, filePath);
    }

    @Override
    public void onClose(boolean isCancel) {
        Log.d(TAG, "onClose: " + isCancel);
        stopDown();
    }

    @Override
    public void onDownStart() {
        onViewStart();
    }

    @Override
    public void onDownProgress(long current, long total) {
        float progress = (100f * current) / total;
        onViewProgress((int) progress);
    }

    @Override
    public void onDownWin(File file) {
        onViewWin();
    }

    @Override
    public void onDownFail(Exception e) {
        onViewFail(e);
    }

    @Override
    public void onDownEnd() {
        onViewEnd();
    }

    @Override
    public void onViewProgress(int progress) {
        if (viewBinding != null && isVisible()) {
            viewBinding.lpiProgress.setProgress(progress);
            viewBinding.tvProgress.setText(getString(R.string.progress, progress));
        }
    }

    @Override
    public void onViewStart() {
        if (viewBinding != null && isVisible()) {
            viewBinding.btn1.setText(R.string.pause);
        }
    }

    @Override
    public void onViewFail(Exception e) {
        Toaster.showShort("下载出错:" + e.getMessage());
        if (viewBinding != null && isVisible()) {
            viewBinding.btn1.setText(R.string.retry);
        }
    }

    @Override
    public void onViewEnd() {
        if (viewBinding != null && isVisible()) {
            viewBinding.btn1.setText(R.string.resume);
        }
    }

}
