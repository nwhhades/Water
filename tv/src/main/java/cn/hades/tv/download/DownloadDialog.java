package cn.hades.tv.download;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.hades.tv.base.BaseDialogFragment;
import cn.hades.tv.databinding.FragmentDownloadDialogBinding;

public class DownloadDialog extends BaseDialogFragment<FragmentDownloadDialogBinding> {


    @Override
    public FragmentDownloadDialogBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentDownloadDialogBinding.inflate(inflater, container, false);
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClose(boolean isCancel) {

    }

}
