package cn.hades.tv.widget.dialog;

import android.app.Dialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjq.toast.Toaster;

import cn.hades.tv.base.BaseDialogFragment;
import cn.hades.tv.databinding.FragmentLoadDialogBinding;

public class LoadDialog extends BaseDialogFragment<FragmentLoadDialogBinding> {

    private final String msg;

    public LoadDialog(String msg) {
        this.msg = msg;
    }

    public void showMsg() {
        if (msg != null) {
            Toaster.showShort(msg);
        }
    }

    @Override
    public FragmentLoadDialogBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentLoadDialogBinding.inflate(inflater, container, false);
    }

    @Override
    public void initView() {
        setCancelable(false);
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnKeyListener((dialog1, keyCode, event) -> {
                if (KeyEvent.ACTION_DOWN == event.getAction()) {
                    if (KeyEvent.KEYCODE_BACK == keyCode) {
                        showMsg();
                        return true;
                    }
                }
                return false;
            });
        }
        viewBinding.getRoot().setOnClickListener(v -> showMsg());
    }

    @Override
    public void onClose(boolean isCancel) {

    }

}
