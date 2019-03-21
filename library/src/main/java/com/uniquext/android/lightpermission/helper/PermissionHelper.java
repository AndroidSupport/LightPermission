package com.uniquext.android.lightpermission.helper;

import android.app.Activity;
import android.os.Build;

import com.uniquext.android.lightpermission.PermissionCallback;
import com.uniquext.android.lightpermission.PermissionResponseBody;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
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
 * @param <T> Activity, Fragment, Support Fragment
 * @author UniqueXT
 * @version 1.0
 * @date 2018/11/28  15:58
 */
public abstract class PermissionHelper<T> {

    /**
     * host
     */
    private T mHost;

    PermissionHelper(@NonNull T host) {
        this.mHost = host;
    }

    /**
     * 构造ActivityPermissionHelper
     *
     * @param activity activity
     * @return PermissionHelper
     */
    @NonNull
    public static PermissionHelper<? extends Activity> newInstance(@NonNull Activity activity) {
        return new ActivityPermissionHelper(activity);
    }

    /**
     * 构造SupportFragmentPermissionHelper
     *
     * @param fragment v4 fragment
     * @return PermissionHelper
     */
    @NonNull
    public static PermissionHelper<? extends Fragment> newInstance(@NonNull Fragment fragment) {
        return new SupportFragmentPermissionHelper(fragment);
    }

    /**
     * 构造FragmentPermissionHelper
     *
     * @param fragment fragment
     * @return PermissionHelper
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    public static PermissionHelper<? extends android.app.Fragment> newInstance(@NonNull android.app.Fragment fragment) {
        return new FragmentPermissionHelper(fragment);
    }

    @NonNull
    T getHost() {
        return mHost;
    }

    /**
     * 请求权限
     *
     * @param requestCode 请求码
     * @param permissions 权限集合
     */
    public abstract void requestPermissions(int requestCode, @NonNull @Size(min = 1) String... permissions);

    /**
     * 请求结果处理
     *
     * @param permissionResponseBody ResponseBody
     * @param permissionCallback     回调
     */
    public abstract void onRequestPermissionsResult(@NonNull PermissionResponseBody permissionResponseBody,
                                                    @NonNull PermissionCallback permissionCallback);
}
