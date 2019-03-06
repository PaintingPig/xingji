package com.dq.android.tool.permission;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dq.android.tool.Constants;
import com.dq.android.tool.ui.PermissionDialog;

public class PermissionActivity extends AppCompatActivity {
    private DQPermission permission = DQPermission.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isAllGranted = true;
        StringBuilder unGrantedPermissions = new StringBuilder();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false;
                unGrantedPermissions.append(permissions[i]);
                unGrantedPermissions.append("|");
            }
        }
        if (!isAllGranted) {
            if (!this.permission.isDealWithFailSelf()) {
                showPermissionDialog();
            } else {
                finish();
                this.permission.onGrantPermissionFail(unGrantedPermissions.toString().split("\\|"));
            }
            return;
        }
        finish();
    }

    //请求授权
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(DQPermission.getInstance().getPermissions(), Constants.GRANT_PERMISSION_FLAG);
            return;
        }
        permission.onGrantPermissionSuccess();
    }

    //显示授权对话框
    private void showPermissionDialog() {
        PermissionDialog.getInstance().showDialog(this, new PermissionDialog.OnDialogClickListener() {
            @Override
            public void cancel() {
                finish();
                permission.getCurrentActivity().finish();
            }

            @Override
            public void confirm() {
                requestPermission();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        permission.releaseResource();
        PermissionDialog.getInstance().release();
    }
}
