package com.uniquext.android.lightpermission.chain;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.uniquext.android.lightpermission.PermissionCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
 * @date 2019/3/29  11:27
 */
public class PermissionFragment extends Fragment {


    private SparseArray<PermissionCallback> permissionCallback = new SparseArray<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    public void addPermissionCallback(int requestCode, PermissionCallback callback) {
        permissionCallback.put(requestCode, callback);
    }

    public void requestPermissions(int requestCode, @NonNull @Size(min = 1) String... permissions) {
        Log.e("####", "requestPermissions " + requestCode);
        this.requestPermissions(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int i = 0; i < permissions.length; ++i) {
            Log.e("### PermissionsResult", permissions[i] + reprocessResult(permissions[i], grantResults[i]));
            switch (reprocessResult(permissions[i], grantResults[i])) {
                case ResultType.DENIED:
                    permissionCallback.get(requestCode).onPermissionsDenied(requestCode);
                    break;
                case ResultType.GRANTED:
                    permissionCallback.get(requestCode).onPermissionsGranted(requestCode);
                    break;
                case ResultType.NO_LONGER_ASK:
                    permissionCallback.get(requestCode).onPermissionNoLongerAsk(requestCode, permissions[i]);
                    break;
            }
        }
    }


    private boolean hasPermissions(@NonNull @Size(min = 1) String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else if (requireContext().getApplicationInfo().targetSdkVersion < Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (PermissionChecker.checkSelfPermission(requireContext(), permission)
                        != PermissionChecker.PERMISSION_GRANTED) {
                    return false;
                }
            }
        } else {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @ResultType
    private int reprocessResult(String permission, int grantResults) {
        if (grantResults == PackageManager.PERMISSION_GRANTED && hasPermissions(permission)) {
            return ResultType.GRANTED;
        } else if (!shouldShowRequestPermissionRationale(permission)) {
            return ResultType.NO_LONGER_ASK;
        } else {
            return ResultType.DENIED;
        }
    }

}
