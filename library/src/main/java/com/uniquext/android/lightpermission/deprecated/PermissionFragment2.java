package com.uniquext.android.lightpermission.deprecated;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;

import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.PermissionCallback;
import com.uniquext.android.lightpermission.annotation.ResultType;

import java.util.ArrayList;
import java.util.List;

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
@RequiresApi(api = Build.VERSION_CODES.M)
public class PermissionFragment2 extends Fragment {

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
        this.requestPermissions(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        List<String> deniedList = new ArrayList<>();
        List<String> noRequestList = new ArrayList<>();
        classifyResult(deniedList, noRequestList, permissions, grantResults);
        dealCallback(requestCode, deniedList, noRequestList);
    }

    @ResultType
    private int reprocessResult(String permission, int grantResults) {
        if (grantResults == PackageManager.PERMISSION_GRANTED && LightPermission.hasPermission(getContext(), permission)) {
            return ResultType.GRANTED;
        } else if (!shouldShowRequestPermissionRationale(permission) || TextUtils.isEmpty(permission)) {
            return ResultType.NO_REQUEST;
        } else {
            return ResultType.DENIED;
        }
    }

    @SuppressLint("SwitchIntDef")
    private void classifyResult(List<String> deniedList, List<String> noRequestList,
                                @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < permissions.length; ++i) {
            switch (reprocessResult(permissions[i], grantResults[i])) {
                case ResultType.DENIED:
                    deniedList.add(permissions[i]);
                    break;
                case ResultType.NO_REQUEST:
                    noRequestList.add(permissions[i]);
                    break;
            }
        }
    }

    private void dealCallback(int requestCode, List<String> deniedList, List<String> noRequestList) {
        PermissionCallback callback = permissionCallback.get(requestCode);
        if (callback == null) return;
        if (deniedList.isEmpty() && noRequestList.isEmpty()) {
            callback.onGranted();
        } else if (!noRequestList.isEmpty()) {
            callback.onNeverRequest(noRequestList.toArray(new String[0]));
        } else {
            callback.onDenied(deniedList.toArray(new String[0]));
        }
    }

}
