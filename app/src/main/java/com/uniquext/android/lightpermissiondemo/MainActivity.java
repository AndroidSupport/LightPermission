package com.uniquext.android.lightpermissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.uniquext.android.lightpermission.PermissionCallback;
import com.uniquext.android.lightpermission.chain.TestPermission;

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
        TestPermission
                .with(this)
                .permission(Manifest.permission.CAMERA)
                .result(new PermissionCallback() {
                    @Override
                    public void onPermissionNoLongerAsk(int requestCode, String permission) {
                        Log.e("####", "onPermissionNoLongerAsk_" + requestCode);
                    }

                    @Override
                    public void onPermissionsDenied(int requestCode) {
                        Log.e("####", "onPermissionsDenied_" + requestCode);
                    }

                    @Override
                    public void onPermissionsGranted(int requestCode) {
                        Log.e("####", "onPermissionsGranted_" + requestCode);
                    }
                })
                .request();
    }

    private void test2() {
        TestPermission.with(this).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

}