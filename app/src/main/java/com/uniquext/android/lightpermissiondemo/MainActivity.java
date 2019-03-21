package com.uniquext.android.lightpermissiondemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.PermissionCallback;
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
public class MainActivity extends AppCompatActivity implements PermissionCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textview).setOnClickListener(v -> {
            if (LightPermission.hasPermissions(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "有权限", Toast.LENGTH_SHORT).show();
            } else {
                LightPermission.requestPermissions(this, 1, Manifest.permission.CAMERA);
            }
        });

        findViewById(R.id.next).setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LightPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onDoNotAskPermissions(int requestCode) {
        Toast.makeText(this, "不再提醒", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode) {
        Toast.makeText(this, "拒绝", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        Toast.makeText(this, "同意", Toast.LENGTH_SHORT).show();
    }
}
