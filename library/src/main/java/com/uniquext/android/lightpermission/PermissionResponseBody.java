package com.uniquext.android.lightpermission;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

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
 * @date 2018/11/28  16:14
 */
public class PermissionResponseBody {

    /**
     * 系统判断结果
     */
    private boolean mGranted;
    /**
     * 请求码
     */
    private int mRequestCode;
    /**
     * 请求权限集合
     */
    private String[] mPermissions;

    PermissionResponseBody(boolean granted, int requestCode, @NonNull String[] permissions) {
        this.mGranted = granted;
        this.mRequestCode = requestCode;
        this.mPermissions = permissions;
    }

    public boolean isGranted() {
        return mGranted;
    }

    public void setGranted(boolean granted) {
        this.mGranted = granted;
    }

    public int getRequestCode() {
        return mRequestCode;
    }

    public void setRequestCode(int requestCode) {
        this.mRequestCode = requestCode;
    }

    public String[] getPermissions() {
        return mPermissions;
    }

    public void setPermissions(String[] permissions) {
        this.mPermissions = permissions;
    }
}
