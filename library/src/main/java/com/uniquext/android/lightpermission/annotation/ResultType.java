package com.uniquext.android.lightpermission.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

import static com.uniquext.android.lightpermission.annotation.ResultType.DENIED;
import static com.uniquext.android.lightpermission.annotation.ResultType.GRANTED;
import static com.uniquext.android.lightpermission.annotation.ResultType.NO_REQUEST;

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
 * @date 2019/3/29  11:30
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({DENIED, GRANTED, NO_REQUEST})
public @interface ResultType {
    int DENIED = 0b00;
    int GRANTED = 0b01;
    int NO_REQUEST = 0b10;
}