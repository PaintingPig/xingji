package com.dq.android.tool.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.android.tool.R;


public class MessageDialog extends BaseDialog {

    public static final int CONTENT_MODE_CUSTOM = -1;      // customize the content
    public static final int CONTENT_MODE_TEXT = 0;         // default
    public static final int CONTENT_MODE_EDIT = 1;         // edit text dialog
    public static final int CONTENT_MODE_LIST = 2;         // list dialog

    public int contentMode = CONTENT_MODE_TEXT;

    protected TextView titleView;
    protected Button confirmButton;
    protected Button cancelButton;
    protected TextView contentView;
    protected EditText editTextView;
    protected View buttonDividerView;

    protected LinearLayout textContainer;
    protected LinearLayout listContainer;
    protected LinearLayout editContainer;
    protected LinearLayout customContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_layout_message;
    }

    @Override
    protected void setupView(View rootView) {
        initViews(rootView);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void initViews(View view){
        titleView = view.findViewById(R.id.title);
        confirmButton = view.findViewById(R.id.confirm);
        cancelButton = view.findViewById(R.id.cancel);
        contentView = view.findViewById(R.id.content);
        editTextView = view.findViewById(R.id.edit);
        buttonDividerView = view.findViewById(R.id.button_divider);
        textContainer = view.findViewById(R.id.text_container);
        listContainer = view.findViewById(R.id.list_container);
        editContainer = view.findViewById(R.id.edit_container);
        customContainer = view.findViewById(R.id.custom_container);
    }

    public void setTitle(String title) {
        if (titleView != null) {
            titleView.setText(title);
        }
    }

    public void setContent(String content) {
        contentView.setText(content);
    }

    public void setConfirmText(String text) {
        if (confirmButton != null) {
            confirmButton.setText(text);
        }
    }

    public void setCancelText(String text) {
        if (cancelButton != null) {
            cancelButton.setText(text);
        }
    }


    public void setConfirmOnClickListener(View.OnClickListener confirmOnClickListener) {
        if (confirmButton != null && confirmOnClickListener != null) {
            confirmButton.setOnClickListener(confirmOnClickListener);
        }
    }

    public void setCancelOnClickListener(View.OnClickListener cancelOnClickListener) {
        if (cancelButton != null && cancelOnClickListener != null) {
            cancelButton.setOnClickListener(cancelOnClickListener);
        }
    }


    /**
     * *******************************************************************
     * SETTING DIALOG STYLE
     * *******************************************************************
     */
    public void hideTitle() {
        titleView.setVisibility(View.GONE);
        textContainer.setBackgroundResource(R.drawable.view_dialog_top_shape);
    }

    public void hideCancelButton() {
        cancelButton.setVisibility(View.GONE);
        buttonDividerView.setVisibility(View.GONE);
        confirmButton.setBackgroundResource(R.drawable.view_dialog_bottom);
    }

    public void hideConfirmButton() {
        confirmButton.setVisibility(View.GONE);
        buttonDividerView.setVisibility(View.GONE);
        cancelButton.setBackgroundResource(R.drawable.view_dialog_bottom);
    }

    public void setConfirmTextColor(int color) {
        confirmButton.setTextColor(color);
    }

    public void setCancelTextColor(int color) {
        cancelButton.setTextColor(color);
    }

    public void setConfirmButtonBackground(int backgroundResource) {
        confirmButton.setBackgroundResource(backgroundResource);
    }

    public void setCancelButtonBackground(int backgroundResource) {
        cancelButton.setBackgroundResource(backgroundResource);
    }

    /**
     * *******************************************************************
     * CONTENT MODE
     * *******************************************************************
     */
    public void setContentMode(int mode) {
        contentMode = mode;

        if (contentMode == CONTENT_MODE_EDIT) {
            textContainer.setVisibility(View.GONE);
            editContainer.setVisibility(View.VISIBLE);
        }
    }

    // LIST DIALOG
    public void addItem(String text, View.OnClickListener clickListener) {
        TextView itemView = (TextView) LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_common_list_item, null);
        itemView.setText(text);
        itemView.setOnClickListener(clickListener);
        listContainer.addView(itemView);

        // set mode to list
        listContainer.setVisibility(View.VISIBLE);
        textContainer.setVisibility(View.GONE);
    }

    public void addListDivider() {
        View dividerView = new View(getContext());
        dividerView.setBackgroundColor(getContext().getResources()
                .getColor(R.color.list_item_divider_color));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        dividerView.setLayoutParams(params);
        listContainer.addView(dividerView);
    }

    // EDIT DIALOG
    public String getEditValue() {
        return editTextView.getText().toString();
    }

    public void setEditValue(String value) {
        editTextView.setText(value);
        Editable etext = editTextView.getText();
        Selection.setSelection(etext, etext.length());
    }

    public void setMaxLength(int max) {
        editTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
    }

    public static MessageDialog newInstance() {
        MessageDialog messageDialog = new MessageDialog();
        Bundle args = new Bundle();
        args.putString("param", "param");
        messageDialog.setArguments(args);
        return messageDialog;
    }

    public static void goSettingPermission(final Activity activity, final String content) {
        final MessageDialog messageDialog = new MessageDialog();
        messageDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                messageDialog.hideTitle();
                messageDialog.setContent(content);
                messageDialog.setConfirmText("去设置");
                messageDialog.setCancelText("取消");
                messageDialog.setConfirmOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        messageDialog.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                        intent.setData(uri);
                        activity.startActivity(intent);
                    }
                });
            }
        });
        messageDialog.show(((AppCompatActivity) activity).getSupportFragmentManager(), "messagedialog");
    }

}
