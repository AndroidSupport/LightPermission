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
implementation 'com.uniquext.android:permission:2.0.1'
```

# 使用方法
**1.案例**
```
LightPermission
        .with(this)
        .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .result(new PermissionCallback() {

            @Override
            public void onGranted() {
                Toast.makeText(this, "同意", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String[] permissions) {
                Toast.makeText(this, "拒绝", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNeverRequest(String[] permissions) {
                Toast.makeText(this, "禁止后不再询问", Toast.LENGTH_SHORT).show();
            }

        });
```
**2.判断权限**
```
LightPermission.hasPermissions(Context context, String... permissions)
```

# 注意事项
在`android.app.Fragment`中使用时，SDK版本至少需要23