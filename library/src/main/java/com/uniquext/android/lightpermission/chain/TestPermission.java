package com.uniquext.android.lightpermission.chain;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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
public class TestPermission {
    public static PermissionBuilder with(FragmentActivity activity) {
        return new PermissionBuilder(activity.getSupportFragmentManager());
    }

    public static PermissionBuilder with(Fragment fragment) {
        return new PermissionBuilder(fragment.getChildFragmentManager());
    }

//    public PermissionBuilder with(Activity activity) {
//        return new PermissionBuilder(activity.getFragmentManager());
//    }
//    public PermissionBuilder with(android.app.Fragment fragment) {
//        return new PermissionBuilder(fragment.getFragmentManager());
//    }

}