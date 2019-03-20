package com.uniquext.android.lightpermission.helper;

import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v4.app.Fragment;

import com.uniquext.android.lightpermission.PermissionCallback;
import com.uniquext.android.lightpermission.PermissionResponseBody;

import static com.uniquext.android.lightpermission.LightPermission.hasPermissions;

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
 * @date 2018/11/28  16:02
 */
public class SupportFragmentPermissionHelper extends PermissionHelper<Fragment> {

    SupportFragmentPermissionHelper(@NonNull Fragment host) {
        super(host);
    }

    @Override
    public void requestPermissions(int requestCode, @NonNull @Size(min = 1) String... permissions) {
        getHost().requestPermissions(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(@NonNull PermissionResponseBody permissionResponseBody,
                                           @NonNull PermissionCallback permissionCallback) {
        if (permissionResponseBody.isGranted()
                && hasPermissions(getHost().getContext(), permissionResponseBody.getPermissions())) {
            permissionCallback.onPermissionsGranted(permissionResponseBody.getRequestCode());
        } else {
            for (String permission : permissionResponseBody.getPermissions()) {
                if (!getHost().shouldShowRequestPermissionRationale(permission)) {
                    permissionCallback.onDoNotAskPermissions(permissionResponseBody.getRequestCode());
                    return;
                }
            }
            permissionCallback.onPermissionsDenied(permissionResponseBody.getRequestCode());
        }
    }

}
