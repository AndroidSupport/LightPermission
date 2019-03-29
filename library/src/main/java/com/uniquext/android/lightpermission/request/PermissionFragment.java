package com.uniquext.android.lightpermission.request;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;

import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.PermissionCallback;
import com.uniquext.android.lightpermission.annotation.ResultType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    void addPermissionCallback(int requestCode, PermissionCallback callback) {
        permissionCallback.put(requestCode, callback);
    }

    void requestPermissions(int requestCode, @NonNull @Size(min = 1) String... permissions) {
        this.requestPermissions(permissions, requestCode);
    }

    @SuppressLint("SwitchIntDef")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        StringBuilder deniedPermissionBuilder = new StringBuilder();
        StringBuilder noAskPermissionBuilder = new StringBuilder();
        for (int i = 0; i < permissions.length; ++i) {
            switch (reprocessResult(permissions[i], grantResults[i])) {
                case ResultType.DENIED:
                    deniedPermissionBuilder.append(permissions[i]).append(",");
                    break;
                case ResultType.NO_REQUEST:
                    noAskPermissionBuilder.append(permissions[i]).append(",");
                    break;
            }
        }
        if (TextUtils.isEmpty(deniedPermissionBuilder) && TextUtils.isEmpty(noAskPermissionBuilder)) {
            permissionCallback.get(requestCode).onGranted();
        } else {
            if (!TextUtils.isEmpty(deniedPermissionBuilder)) {
                deniedPermissionBuilder.deleteCharAt(deniedPermissionBuilder.lastIndexOf(","));
                permissionCallback.get(requestCode).onDenied(deniedPermissionBuilder.toString().split(","));
            }
            if (!TextUtils.isEmpty(noAskPermissionBuilder)) {
                noAskPermissionBuilder.deleteCharAt(noAskPermissionBuilder.lastIndexOf(","));
                permissionCallback.get(requestCode).onNoRequest(noAskPermissionBuilder.toString().split(","));
            }
        }
//
//        List<String> list = new ArrayList<>();
////       String[] b = list.stream().toArray(new String[list.size()]);
//        String[] a = list.toArray(new String[list.size()]);
    }

    @ResultType
    private int reprocessResult(String permission, int grantResults) {
        if (grantResults == PackageManager.PERMISSION_GRANTED && LightPermission.hasPermission(requireContext(), permission)) {
            return ResultType.GRANTED;
        } else if (!shouldShowRequestPermissionRationale(permission)) {
            return ResultType.NO_REQUEST;
        } else {
            return ResultType.DENIED;
        }
    }

}
