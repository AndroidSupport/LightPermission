package com.uniquext.android.lightpermissiondemo.kt

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.uniquext.android.lightpermission.LightPermission
import com.uniquext.android.lightpermission.PermissionCallback
import com.uniquext.android.lightpermissiondemo.R
import com.uniquext.android.lightpermissiondemo.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*

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
 * @date 2019/4/3  11:40
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview.setOnClickListener { test() }
        next.setOnClickListener { test2() }

    }

    private fun test() {
        LightPermission
                .with(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .result(object : PermissionCallback {

                    override fun onGranted() {
                        Log.e("####", "onGranted")
                    }

                    override fun onDenied(permissions: Array<String>) {
                        for (permission in permissions) {
                            Log.e("####", "onDenied $permission")
                        }
                    }

                    override fun onNoRequest(permissions: Array<String>) {
                        for (permission in permissions) {
                            Log.e("####", "onNoRequest $permission")
                        }
                    }

                })

    }

    private fun test2() {
        startActivity(Intent(this, SecondActivity::class.java))
    }


}