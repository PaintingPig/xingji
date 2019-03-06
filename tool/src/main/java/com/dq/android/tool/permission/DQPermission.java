package com.dq.android.tool.permission;

import android.app.Activity;
import android.content.Intent;

public class DQPermission<T extends Activity> {
    //需要赋予的权限列表
    private volatile String[] permissions;
    //权限赋予成功之后的回调
    private OnPermissionGrantCallback onPermissionGrantCallback;
    //是否需要自己处理失败回调
    private boolean dealWithFailSelf;

    private T currentActivity;

    public static DQPermission getInstance() {
        return DQPermissionHolder.permission;
    }

    /**
     * 请求权限
     *
     * @param activity，当前页面的activity
     * @param permissions，需要请求的权限集合
     */
    public synchronized void requestPermission(T activity, OnPermissionGrantCallback onPermissionGrantCallback, String... permissions) {
        requestPermission(activity, false, onPermissionGrantCallback, permissions);
    }

    /**
     * 请求权限
     *
     * @param activity，当前页面的activity
     * @param permissions，需要请求的权限集合
     * @param dealWithFailSelf:      是否需要自己处理获取权限失败的操作，默认false
     */
    public synchronized void requestPermission(T activity, boolean dealWithFailSelf, OnPermissionGrantCallback onPermissionGrantCallback, String... permissions) {
        this.dealWithFailSelf = dealWithFailSelf;
        this.currentActivity = activity;
        this.permissions = permissions;
        if (permissions == null || permissions.length == 0) {
            onPermissionGrantCallback.onSuccess();
            return;
        }
        this.onPermissionGrantCallback = onPermissionGrantCallback;
        activity.startActivity(new Intent(activity, PermissionActivity.class));
    }

    //获取需要赋予的权限
    protected String[] getPermissions() {
        return permissions;
    }

    //回调授权成功
    protected void onGrantPermissionSuccess() {
        if (onPermissionGrantCallback != null) {
            onPermissionGrantCallback.onSuccess();
        }
    }

    //回调授权失败
    protected void onGrantPermissionFail(String[] unGrantedPermission) {
        if (onPermissionGrantCallback != null) {
            onPermissionGrantCallback.onFail(unGrantedPermission);
        }
    }

    //获取调用授权的Activity
    public T getCurrentActivity() {
        return currentActivity;
    }

    //是否自行处理授权失败的操作
    protected boolean isDealWithFailSelf() {
        return dealWithFailSelf;
    }

    public interface OnPermissionGrantCallback {
        void onSuccess();

        void onFail(String[] unGrantedPermission);
    }

    private static class DQPermissionHolder {
        protected static DQPermission permission = new DQPermission();
    }
    protected void releaseResource(){
        currentActivity = null;
    }

}
