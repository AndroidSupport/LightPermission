package com.uniquext.android.lightpermissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquext.android.lightpermission.request.PermissionCallback;
import com.uniquext.android.lightpermission.LightPermission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
public class MainFragment extends Fragment {

    /**
     * 唯一实例
     *
     * @return MainFragment
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        root.findViewById(R.id.textview).setOnClickListener(v -> test());
        return root;
    }

    private void test() {
        LightPermission
                .with(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request(new PermissionCallback() {
                    @Override
                    public void onProhibited(String[] permissions) {
                        for (String permission : permissions) {
                            Log.e("####", "onProhibited " + permission);
                        }
                    }

                    @Override
                    public void onDenied(String[] permissions) {
                        for (String permission : permissions) {
                            Log.e("####", "onDenied " + permission);
                        }
                    }

                    @Override
                    public void onGranted() {
                        Log.e("####", "onGranted_");
                    }
                });
    }

}
