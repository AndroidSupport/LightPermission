package com.uniquext.android.lightpermission.callback;

public interface ProhibitCallback {
    /**
     * 不再请求权限
     *
     * @param permissions 不请求直接拒绝的权限
     */
    void onProhibited(String[] permissions);
}
