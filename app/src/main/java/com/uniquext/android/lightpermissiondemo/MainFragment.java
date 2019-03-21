package com.uniquext.android.lightpermissiondemo;

import android.Manifest;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.PermissionCallback;

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
 * @date 2018/11/28  17:37
 */
public class MainFragment extends Fragment implements PermissionCallback {

    /**
     * 唯一实例
     * @return MainFragment
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        root.findViewById(R.id.textview).setOnClickListener(v -> {
            if (LightPermission.hasPermissions(getContext(), Manifest.permission.CAMERA)) {
                Toast.makeText(getContext(), "有权限", Toast.LENGTH_SHORT).show();
            } else {
                LightPermission.requestPermissions(this, 1, Manifest.permission.CAMERA);
            }
        });
        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LightPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onDoNotAskPermissions(int requestCode) {
        Toast.makeText(getContext(), "不再提醒", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode) {
        Toast.makeText(getContext(), "拒绝", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        Toast.makeText(getContext(), "同意", Toast.LENGTH_SHORT).show();
    }
}
