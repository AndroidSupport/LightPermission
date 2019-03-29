package com.uniquext.android.lightpermissiondemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.PermissionCallback;

import androidx.appcompat.app.AppCompatActivity;

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

    private void test() {
        LightPermission
                .with(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .result(new PermissionCallback() {
                    @Override
                    public void onNoRequest(String[] permissions) {
                        for (String permission : permissions) {
                            Log.e("####", "onNoRequest " + permission);
                        }
                    }

                    @Override
                    public void onDenied(String[] permissions) {
                        for (String permission : permissions) {
                            Log.e("####", "onDenied " + permission);
                        }
                    }

                    @Override
                    public void onGranted() {
                        Log.e("####", "onGranted");
                    }
                });
    }

    private void test2() {
        startActivity(new Intent(this, SecondActivity.class));
    }

}