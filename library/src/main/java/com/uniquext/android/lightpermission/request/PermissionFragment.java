package com.uniquext.android.lightpermission.request;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.fragment.app.Fragment;

import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.annotation.ResultType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    private SparseArray<CustomPermissionCallback> permissionCallback = new SparseArray<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    void requestPermissions(@NonNull CustomPermissionCallback callback, @NonNull String[] permissions) {
        final int requestCode = calculateRequestCode(permissions);
        permissionCallback.put(requestCode, callback);
        this.requestPermissions(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        List<String> deniedList = new ArrayList<>();
        List<String> prohibitList = new ArrayList<>();
        for (int i = 0; i < permissions.length; ++i) {
            switch (reprocessResult(permissions[i], grantResults[i])) {
                case ResultType.DENIED:
                    deniedList.add(permissions[i]);
                    break;
                case ResultType.PROHIBIT:
                    prohibitList.add(permissions[i]);
                    break;
                case ResultType.GRANTED:
                default:
                    break;
            }
        }
        distributionCallback(requestCode, deniedList, prohibitList);
    }

    @ResultType
    private int reprocessResult(String permission, int grantResults) {
        if (grantResults == PackageManager.PERMISSION_GRANTED && LightPermission.hasPermission(requireContext(), permission)) {
            return ResultType.GRANTED;
        } else if (!shouldShowRequestPermissionRationale(permission) || TextUtils.isEmpty(permission)) {
            return ResultType.PROHIBIT;
        } else {
            return ResultType.DENIED;
        }
    }

    private void distributionCallback(int requestCode, @NonNull List<String> deniedList, @NonNull List<String> prohibitList) {
        CustomPermissionCallback callback = permissionCallback.get(requestCode);
        if (callback == null) return;
        if (deniedList.isEmpty() && prohibitList.isEmpty()) {
            callback.onGranted();
        } else if (!prohibitList.isEmpty()) {
            callback.onProhibited(prohibitList.toArray(new String[0]));
        } else {
            callback.onDenied(deniedList.toArray(new String[0]));
        }
    }

    /**
     * 生成 requestCode
     * 一般情况下不会出现同时多个request的情况，但不排除
     * 尽量保证唯一性，避免多个request请求时出现相同的requestCode
     * 计算权限集hash值并对FFFF做与运算（requestCode <= 65535
     * @param permissions 权限集
     * @return 请求码
     */
    private int calculateRequestCode(@NonNull String[] permissions) {
        int requestCode = Arrays.hashCode(permissions) & 0xFFFF;
        if (permissionCallback.indexOfKey(requestCode) < 0) {
            return requestCode;
        }
        requestCode = requestCode & new Random().nextInt(0xFFFF);
        return permissionCallback.indexOfKey(requestCode) < 0 ? requestCode : (requestCode + 1) % 0xFFFF;
    }

}
