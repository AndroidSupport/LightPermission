package com.uniquext.android.lightpermission;

import com.uniquext.android.lightpermission.callback.DeniedCallback;
import com.uniquext.android.lightpermission.callback.GrantedCallback;
import com.uniquext.android.lightpermission.callback.NoLongerAskCallback;

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
 * @version 1.3
 * @date 2019/3/29  11:54
 */
public class PermissionCallback implements GrantedCallback, DeniedCallback, NoLongerAskCallback {

    @Override
    public void onDenied(String[] permissions) {

    }

    @Override
    public void onGranted() {

    }

    @Override
    public void onNeverRequest(String[] permissions) {

    }
}
