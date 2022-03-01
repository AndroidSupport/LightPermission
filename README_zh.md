# LightPermission
一个轻量级的android权限管理器。
<br>最低SDK版本支持为19

# 添加依赖
**1.在项目`build.gradle`中添加maven地址**
```
allprojects {
    repositories {
        maven {
            url 'https://oss.sonatype.org/content/groups/public'
        }
    }
}
```
**2.在模块`build.gradle`中添加依赖**
```
implementation 'com.uniquext.android:permission:2.1.2-SNAPSHOT'
```

# 使用方法
**1.案例**
```
LightPermission
        .with(this)
        .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .request(new PermissionCallback() {

            @Override
            public void onGranted() {
                // 同意
            }

            @Override
            public void onDenied(String[] permissions) {
                // 拒绝
            }

            @Override
            public void onProhibited(String[] permissions) {
                new AppSettingsDialog.Builder(this).show();
            }

        });
```
**2.v2.1.1-snapshot demo**
```
LightPermission
        .with(this)
        .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .grant(() -> {
            //  同意
        })
        .deny(permissions -> {
            //  拒绝
        })
        .prohibit(permissions -> {
            //  拒绝权限并禁止请求
            new AppSettingsDialog.Builder(this) .show();
        })
        .request();
```
**3.判断权限**
```
LightPermission.hasPermissions(Context context, String... permissions);
```

# 注意事项
在`android.app.Fragment`中使用时，SDK版本至少需要23