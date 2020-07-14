package com.uniquext.android.lightpermission.callback;

public interface DeniedCallback {
    /**
     * 无权限
     *
     * @param permissions 被拒绝的权限
     */
    void onDenied(String[] permissions);
}
