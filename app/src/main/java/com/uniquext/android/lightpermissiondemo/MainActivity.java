package com.uniquext.android.lightpermissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.request.PermissionCallback;
import com.uniquext.android.lightpermission.settings.AppSettingsDialog;

/**
 * 　 　　   へ　　　 　／|
 * 　　    /＼7　　　 ∠＿/
 * 　     /　│　　 ／　／
 * 　    │　Z ＿,＜　／　　   /`ヽ
 * 　    │　　　 　　ヽ　    /　　〉
 * 　     Y　　　　　   `　  /　　/
 * 　    ｲ●　､　●　　⊂⊃〈　　/
 * 　    ()　 へ　　　　|　＼〈
 * 　　    >ｰ ､_　 ィ　 │ ／／      去吧！
 * 　     / へ　　 /　ﾉ＜| ＼＼        比卡丘~
 * 　     ヽ_ﾉ　　(_／　 │／／           消灭代码BUG
 * 　　    7　　　　　　　|／
 * 　　    ＞―r￣￣`ｰ―＿
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @author UniqueXT
 * @version 1.0
 * @date 2018/11/28  17:35
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textview).setOnClickListener(v -> test());

        findViewById(R.id.next).setOnClickListener(v -> test2());

    }

    private static final String[] READ_PERMS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private void test() {
        LightPermission.with(this)
                .permissions(READ_PERMS)
                .request(new PermissionCallback() {

                    @Override
                    public void onGranted() {
                        Log.e("####", "有权限");
                    }

                    @Override
                    public void onDenied(String[] permissions) {
                        for (String permission : permissions) {
                            Log.e("####", "没有该权限 " + permission);
                        }
                    }

                    @Override
                    public void onProhibited(String[] permissions) {
                        Log.e("####", "拒绝闭关设置不再请求权限，只能去设置页更改");
                        new AppSettingsDialog.Builder(MainActivity.this)
//                                .title("TITLE")
//                                .message("MESSAGE")
                                .show();
                    }

                });
    }

    private void test2() {
        LightPermission.with(this)
                .permissions(READ_PERMS)
                .grant(() ->Log.e("####", "有权限"))
                .deny(permissions -> {
                    for (String permission : permissions) {
                        Log.e("####", "没有该权限 " + permission);
                    }
                })
                .prohibit(permissions -> Log.e("####", "拒绝闭关设置不再请求权限，只能去设置页更改"))
                .request();
    }

}