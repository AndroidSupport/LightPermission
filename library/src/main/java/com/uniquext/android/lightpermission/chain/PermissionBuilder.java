package com.uniquext.android.lightpermission.chain;

import android.util.Log;

import com.uniquext.android.lightpermission.PermissionCallback;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.fragment.app.FragmentManager;

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
public class PermissionBuilder {

    private static final String TAG = PermissionFragment.class.getSimpleName();

    private int requestCode;
    private String[] requestPermission;
    private PermissionCallback callback;

    private WeakReference<PermissionFragment> mFragmentWeakReference;

    public PermissionBuilder(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) == null) {
            Log.e("####", "PermissionFragment == null");
            mFragmentWeakReference = new WeakReference<>(new PermissionFragment());
            fragmentManager.beginTransaction().add(mFragmentWeakReference.get(), TAG).commitNowAllowingStateLoss();
        } else {
            Log.e("####", "PermissionFragment != null");
            mFragmentWeakReference = new WeakReference<>((PermissionFragment) fragmentManager.findFragmentByTag(TAG));
        }
    }

    public PermissionBuilder permission(@NonNull @Size(min = 1) String... permissions) {
        this.requestPermission = permissions;
        return this;
    }

    public PermissionBuilder result(@NonNull PermissionCallback callback) {
        this.callback = callback;
        return this;
    }

    public void request() {
        int requestCode = Arrays.hashCode(requestPermission) & 0xffff;
        mFragmentWeakReference.get().addPermissionCallback(requestCode, callback);
        mFragmentWeakReference.get().requestPermissions(requestCode, requestPermission);
    }
}
