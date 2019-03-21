package com.uniquext.android.lightpermission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.uniquext.android.lightpermission.helper.PermissionHelper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

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
 * @date 2018/11/28  15:22
 */
public final class LightPermission {

    /**
     * Host异常
     */
    private static final String CAN_NOT_FOUND_HOST =
            "Can ont found host! The host must be extends Activity, Fragment or SupportFragment.";
    /**
     * Callback异常
     */
    private static final String CAN_NOT_FOUND_PERMISSION_CALLBACK =
            "Can ont found permission callback! The host must be implements PermissionCallback.";

    private LightPermission() {
    }

    /**
     * 权限判断
     *
     * @param context     上下文
     * @param permissions 权限集合
     * @return 是否拥有所有权限
     */
    public static boolean hasPermissions(@NonNull Context context, @NonNull @Size(min = 1) String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else if (context.getApplicationInfo().targetSdkVersion < Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (PermissionChecker.checkSelfPermission(context, permission)
                        != PermissionChecker.PERMISSION_GRANTED) {
                    return false;
                }
            }
        } else {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 权限结果数组处理
     *
     * @param grantResults 结果
     * @return 是否有权限
     */
    private static boolean judgmentGrantResults(int[] grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * activity 权限结果处理
     *
     * @param activity    activity
     * @param requestCode 请求码
     * @param permissions 权限集合
     */
    public static void requestPermissions(@NonNull Activity activity,
                                          int requestCode,
                                          @NonNull @Size(min = 1) String... permissions) {
        requestPermissions(PermissionHelper.newInstance(activity), requestCode, permissions);
    }

    /**
     * 在fragment中请求权限
     *
     * @param fragment    v4 fragment
     * @param requestCode 请求码
     * @param permissions 权限集合
     */
    public static void requestPermissions(@NonNull Fragment fragment,
                                          int requestCode,
                                          @NonNull @Size(min = 1) String... permissions) {
        requestPermissions(PermissionHelper.newInstance(fragment), requestCode, permissions);
    }

    /**
     * 在fragment中请求权限
     *
     * @param fragment    fragment
     * @param requestCode 请求码
     * @param permissions 权限集合
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestPermissions(@NonNull android.app.Fragment fragment,
                                          int requestCode,
                                          @NonNull @Size(min = 1) String... permissions) {
        requestPermissions(PermissionHelper.newInstance(fragment), requestCode, permissions);
    }

    /**
     * 请求权限
     *
     * @param helper      helper
     * @param requestCode requestCode
     * @param permissions permissions
     */
    private static void requestPermissions(@NonNull PermissionHelper helper, int requestCode, @NonNull @Size(min = 1) String... permissions) {
        helper.requestPermissions(requestCode, permissions);
    }

    /**
     * 权限结果处理
     *
     * @param requestCode  请求码
     * @param permissions  权限集合
     * @param grantResults 结果集合
     * @param object       host & callback
     */
    public static void onRequestPermissionsResult(int requestCode,
                                                  @NonNull String[] permissions,
                                                  @NonNull int[] grantResults,
                                                  @NonNull Object object) {
        if (object instanceof PermissionCallback) {
            boolean result = judgmentGrantResults(grantResults);
            PermissionHelper helper = createPermissionHelper(object);
            PermissionResponseBody body = new PermissionResponseBody(result, requestCode, permissions);
            helper.onRequestPermissionsResult(body, (PermissionCallback) object);
        } else {
            throw new RuntimeException(CAN_NOT_FOUND_PERMISSION_CALLBACK);
        }
    }

    /**
     * 构造对应host的帮助类
     *
     * @param object host
     * @return helper
     */
    private static PermissionHelper createPermissionHelper(@NonNull Object object) {
        if (object instanceof Activity) {
            return PermissionHelper.newInstance((Activity) object);
        } else if (object instanceof Fragment) {
            return PermissionHelper.newInstance((Fragment) object);
        } else if (object instanceof android.app.Fragment) {
            return PermissionHelper.newInstance((android.app.Fragment) object);
        } else {
            throw new RuntimeException(CAN_NOT_FOUND_HOST);
        }
    }
}
