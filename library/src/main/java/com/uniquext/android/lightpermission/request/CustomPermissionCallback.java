package com.uniquext.android.lightpermission.request;

import com.uniquext.android.lightpermission.callback.DenyCallback;
import com.uniquext.android.lightpermission.callback.GrantCallback;
import com.uniquext.android.lightpermission.callback.ProhibitCallback;

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
 * ━━━━━━━━━━感觉萌萌哒━━━━━━━━━━
 *
 * @author penghaitao
 * @description $
 * @date 2/28/22  1:39 PM
 */
class CustomPermissionCallback  {

    GrantCallback grantCallback;
    DenyCallback denyCallback;
    ProhibitCallback prohibitCallback;

    protected void onDenied(String[] permissions) {
        if (denyCallback != null) {
            denyCallback.onDenied(permissions);
        }
    }

    protected void onGranted() {
        if (grantCallback != null) {
            grantCallback.onGranted();
        }
    }

    protected void onProhibited(String[] permissions) {
        if (prohibitCallback != null) {
            prohibitCallback.onProhibited(permissions);
        }
    }

}
