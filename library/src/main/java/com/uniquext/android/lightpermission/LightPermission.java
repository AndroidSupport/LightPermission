package com.uniquext.android.lightpermission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.uniquext.android.lightpermission.deprecated.ChainPermission2;
import com.uniquext.android.lightpermission.request.ChainPermission;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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
 * @date 2019/3/29  11:27
 */
public class LightPermission {

    public static ChainPermission with(FragmentActivity activity) {
        return new ChainPermission(activity.getSupportFragmentManager());
    }

    public static ChainPermission with(Fragment fragment) {
        return new ChainPermission(fragment.getChildFragmentManager());
    }

    public static boolean hasPermission(@NonNull Context context, @NonNull String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else if (context.getApplicationInfo().targetSdkVersion < Build.VERSION_CODES.M) {
            return PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ChainPermission2 with(Activity activity) {
        return new ChainPermission2(activity.getFragmentManager());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ChainPermission2 with(android.app.Fragment fragment) {
        return new ChainPermission2(fragment.getFragmentManager());
    }

}