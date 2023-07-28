package cn.hades.tv.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;

import cn.hades.tv.base.iface.IFragment;

public abstract class BaseDialogFragment<V extends ViewBinding> extends AppCompatDialogFragment implements IFragment<V> {

    private static final String TAG = "BaseDialogFragment";

    protected boolean isShow = false;

    protected boolean isCancel = false;

    protected V viewBinding;

    @Override
    public void showFragment(@NonNull FragmentManager manager) {
        if (isShow) return;
        isShow = true;
        show(manager, TAG + hashCode());
    }

    @Override
    public void hideFragment() {
        dismissAllowingStateLoss();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置View
        viewBinding = getViewBinding(inflater, container);
        View view = viewBinding.getRoot();
        initView();
        return view;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        isCancel = true;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        onClose(isCancel);
    }

}
