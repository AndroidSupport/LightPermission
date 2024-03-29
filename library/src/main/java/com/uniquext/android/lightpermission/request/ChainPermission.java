package com.uniquext.android.lightpermission.request;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.fragment.app.FragmentManager;

import com.uniquext.android.lightpermission.callback.DenyCallback;
import com.uniquext.android.lightpermission.callback.GrantCallback;
import com.uniquext.android.lightpermission.callback.ProhibitCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
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
public class ChainPermission {

    private static final String TAG = PermissionFragment.class.getName();

    private String[] mPermissionRequest;
    private WeakReference<PermissionFragment> mFragmentWeakReference;
    private CustomPermissionCallback permissionCallback;

    public ChainPermission(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) == null) {
            mFragmentWeakReference = new WeakReference<>(new PermissionFragment());
            fragmentManager.beginTransaction().add(mFragmentWeakReference.get(), TAG).commitNowAllowingStateLoss();
        } else {
            mFragmentWeakReference = new WeakReference<>((PermissionFragment) fragmentManager.findFragmentByTag(TAG));
        }
    }

    public ChainPermission permissions(@NonNull @Size(min = 1) String... permissions) {
        this.mPermissionRequest = filterPermission(Arrays.asList(permissions));
        return this;
    }

    public ChainPermission permissions(@NonNull @Size(min = 1) List<String> permissions) {
        this.mPermissionRequest = filterPermission(permissions);
        return this;
    }

    public ChainPermission grant(@NonNull GrantCallback callback) {
        if (permissionCallback == null) {
            permissionCallback = new CustomPermissionCallback();
        }
        permissionCallback.grantCallback = callback;
        return this;
    }

    public ChainPermission deny(@NonNull DenyCallback callback) {
        if (permissionCallback == null) {
            permissionCallback = new CustomPermissionCallback();
        }
        permissionCallback.denyCallback = callback;
        return this;
    }

    public ChainPermission prohibit(@NonNull ProhibitCallback callback) {
        if (permissionCallback == null) {
            permissionCallback = new CustomPermissionCallback();
        }
        permissionCallback.prohibitCallback = callback;
        return this;
    }

    public void request() {
        result(permissionCallback);
    }

    public void request(@NonNull final PermissionCallback callback) {
        result(new PermissionCallback(){
            @Override
            public void onGranted() {
                if (permissionCallback != null) {
                    permissionCallback.onGranted();
                }
                callback.onGranted();
            }

            @Override
            public void onDenied(String[] permissions) {
                if (permissionCallback != null) {
                    permissionCallback.onDenied(permissions);
                }
                callback.onDenied(permissions);
            }

            @Override
            public void onProhibited(String[] permissions) {
                if (permissionCallback != null) {
                    permissionCallback.onProhibited(permissions);
                }
                callback.onProhibited(permissions);
            }
        });
    }

    private void result(@NonNull CustomPermissionCallback callback) {
        if (mPermissionRequest == null) {
            throw new RuntimeException("No requested permission.");
        } else if (mPermissionRequest.length == 0) {
            callback.onGranted();
        } else {
            mFragmentWeakReference.get().requestPermissions(callback, mPermissionRequest);
        }
    }

    private String[] filterPermission(List<String> tempPermissions) {
        List<String> permissions = new ArrayList<>();
        for (String permission : tempPermissions) {
            if (!TextUtils.isEmpty(permission)) {
                permissions.add(permission);
            }
        }
        return permissions.toArray(new String[0]);
    }
}
