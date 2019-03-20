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
implementation 'com.uniquext.android:permission:1.0.0'
```

# 使用方法
**1.在`onRequestPermissionsResult`中执行`LightPermission`回调**
```
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    LightPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
}
```
**2.判断权限**
```
LightPermission.hasPermissions(Context context, String... permissions)
```
**3.申请权限**
```
LightPermission.requestPermissions(Activity activity, int requestCode, String... permissions);
LightPermission.requestPermissions(android.app.Fragment fragment, int requestCode, String... permissions);
LightPermission.requestPermissions(android.support.v4.app.Fragment fragment, int requestCode, String... permissions);
```
**4.结果回调**
<br>实现`PermissionCallback`
```
@Override
public void onDoNotAskPermissions(int requestCode) {
    Toast.makeText(this, "不再提醒", Toast.LENGTH_SHORT).show();
}

@Override
public void onPermissionsDenied(int requestCode) {
    Toast.makeText(this, "拒绝", Toast.LENGTH_SHORT).show();
}

@Override
public void onPermissionsGranted(int requestCode) {
    Toast.makeText(this, "同意", Toast.LENGTH_SHORT).show();
}
```

# 注意事项
在`android.app.Fragment`中使用时，SDK版本至少需要23