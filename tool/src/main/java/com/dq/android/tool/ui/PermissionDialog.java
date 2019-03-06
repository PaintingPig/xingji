package com.dq.android.tool.ui;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dq.android.tool.R;
import com.dq.android.tool.utils.Util_UI;

public class PermissionDialog {

    private  MessageDialog messageDialog;
    public static PermissionDialog getInstance(){
        return PermissionDialogHolder.permissionDialog;
    }

    public synchronized void showDialog(AppCompatActivity activity, final OnDialogClickListener dialogClickListener){
        if(messageDialog == null){
            messageDialog = new MessageDialog();
        }
        if(messageDialog.isVisible()){
            messageDialog.dismiss();
        }
        messageDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                messageDialog.setCancelText(Util_UI.getString(R.string.common_dialog_cancel));
                messageDialog.setConfirmText(Util_UI.getString(R.string.permission));
                messageDialog.setContent(Util_UI.getString(R.string.permission_tip));
                messageDialog.setCancelOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        messageDialog.dismiss();
                        dialogClickListener.cancel();
                    }
                });
                messageDialog.setConfirmOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        messageDialog.dismiss();
                        dialogClickListener.confirm();
                    }
                });
            }
        });
        messageDialog.show(activity.getSupportFragmentManager(),"permission");
    }


    protected static class PermissionDialogHolder{
        protected static PermissionDialog permissionDialog = new PermissionDialog();
    }

    public interface OnDialogClickListener{
        void cancel();
        void confirm();
    }

    //释放messagedialog
    public void release(){
        if(messageDialog != null && messageDialog.isVisible()){
            messageDialog.dismiss();
        }
        messageDialog = null;
    }

}
