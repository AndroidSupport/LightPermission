package com.uniquext.android.lightpermission.deprecated;

import android.app.FragmentManager;
import android.os.Build;

import com.uniquext.android.lightpermission.PermissionCallback;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;

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
@RequiresApi(api = Build.VERSION_CODES.N)
public class ChainPermission2 {

    private static final String TAG = PermissionFragment2.class.getName();

    private String[] mPermissionRequest;
    private WeakReference<PermissionFragment2> mFragmentWeakReference;

    public ChainPermission2(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) == null) {
            mFragmentWeakReference = new WeakReference<>(new PermissionFragment2());
            fragmentManager.beginTransaction().add(mFragmentWeakReference.get(), TAG).commitNow();
        } else {
            mFragmentWeakReference = new WeakReference<>((PermissionFragment2) fragmentManager.findFragmentByTag(TAG));
        }
    }

    public ChainPermission2 permissions(@NonNull @Size(min = 1) String... permissions) {
        this.mPermissionRequest = permissions;
        return this;
    }

    public void result(@NonNull PermissionCallback callback) {
        if (mPermissionRequest == null) {
            throw new RuntimeException("No requested permission.");
        } else {
            int requestCode = Arrays.hashCode(mPermissionRequest) & 0xffff;
            mFragmentWeakReference.get().addPermissionCallback(requestCode, callback);
            mFragmentWeakReference.get().requestPermissions(requestCode, mPermissionRequest);
        }
    }
}
