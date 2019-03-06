package com.dq.android.tool.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dq.android.tool.utils.Util_UI;


public abstract class BaseDialog extends DialogFragment {

    protected DialogInterface.OnShowListener mOnShowListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        setup(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Full Screen Area
        getDialog().getWindow()
                .setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        getDialog().getWindow()
                .getDecorView()
                .setPadding(Util_UI.dp2px(0), Util_UI.dp2px(0), Util_UI.dp2px(0), Util_UI.dp2px(0));

        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(lp);

        if (mOnShowListener != null) {
            getDialog().setOnShowListener(mOnShowListener);
        }
    }

    private void setup(View rootView) {
        setupView(rootView);
    }

    protected abstract int getLayoutId();

    protected abstract void setupView(View rootView);

    public DialogInterface.OnShowListener getOnShowListener() {
        return mOnShowListener;
    }

    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        mOnShowListener = onShowListener;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!isAdded() && null == manager.findFragmentByTag(tag)) {
            // isAdded()返回false，可知mHost为null，mHost为null，
            // 由于调用isAdd方法时Fragment沒有进行初始化对mHost进行赋值
            // Fragment被添加到FragmentManagerImpl的mAdded中，则可以
            // 通过tag或id來查找已提交的Fragment
            FragmentTransaction ft = manager.beginTransaction();
            //让FragmentManagerImpl立即执行mPendingActions中的任务
            manager.executePendingTransactions();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } else {
            FragmentTransaction ft = manager.beginTransaction();
            ft.show(this);
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
