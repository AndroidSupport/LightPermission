package com.uniquext.android.lightpermission;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;

public class AppSettingsDialog {

    private AlertDialog alertDialog;

    private AppSettingsDialog(final Builder builder) {
        alertDialog = new AlertDialog.Builder(builder.context, builder.themeResId)
                .setTitle(TextUtils.isEmpty(builder.title) ? builder.context.getString(R.string.settings_dialog_title) : builder.title)
                .setMessage(TextUtils.isEmpty(builder.message) ? builder.context.getString(R.string.settings_dialog_description) : builder.message)
                .setPositiveButton(builder.context.getString(R.string.settings_dialog_position), (dialog, which) -> {
                    if (builder.onPositiveClick != null) {
                        builder.onPositiveClick.onClick(dialog, which);
                    }
                })
                .setNegativeButton(builder.context.getString(R.string.settings_dialog_negative), (dialog, which) -> {
                    if (builder.onNegativeClick != null) {
                        builder.onNegativeClick.onClick(dialog, which);
                    }
                })
                .create();
    }

    private static void startSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }

    public void show() {
        alertDialog.show();
    }

    public static class Builder {

        private Context context;
        @StyleRes
        private int themeResId = R.style.AppSettingDialog;

        private CharSequence title;
        private CharSequence message;
        private DialogInterface.OnClickListener onPositiveClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startSettings(context);
                dialog.dismiss();
            }
        };
        private DialogInterface.OnClickListener onNegativeClick = (dialog, which) -> dialog.dismiss();

        public Builder(Context context) {
            this.context = context;
        }
        public Builder(Context context, @StyleRes int themeResId) {
            this.context = context;
            this.themeResId = themeResId;
        }

        public Builder theme(@StyleRes int themeResId) {
            this.themeResId = themeResId;
            return this;
        }

        public Builder title(@Nullable CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder message(@Nullable CharSequence message) {
            this.message = message;
            return this;
        }

        public Builder onPositiveClick(@Nullable DialogInterface.OnClickListener onClickListener) {
            this.onPositiveClick = onClickListener;
            return this;
        }

        public Builder onNegativeClick(@Nullable DialogInterface.OnClickListener onClickListener) {
            this.onNegativeClick = onClickListener;
            return this;
        }

        public AppSettingsDialog build() {
            return new AppSettingsDialog(this);
        }

        public void show() {
            build().show();
        }

    }

}
