# LightPermission
一个轻量级的android权限管理器。
<br>目前最新版2.0支持链式调用

# 添加依赖
```
allprojects {
    repositories {
        maven {
            url 'https://oss.sonatype.org/content/groups/public'
        }
    }
}

implementation 'com.uniquext.android:permission:2.0.0-SNAPSHOT'
```

# 使用方法
### 示例
```
LightPermission
        .with(this)
        .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .result(new PermissionCallback() {

            @Override
            public void onGranted() {
                //  已获取相机和存储权限
            }

            @Override
            public void onDenied(String[] permissions) {
                //  被拒绝的权限集合
            }

            @Override
            public void onNoRequest(String[] permissions) {
                //  被设置为不再询问的权限集合
            }

        });
```
### 解释说明
`with(this)`
* 该`this`可以是`FragmentActivity`或`Fragment`（当使用`androidx.fragment.app`包时）；
* 亦可以是`Activity`或`android.app.Fragment`（当使用`android.app`包时）

`permissions`
需要请求的权限，可以是一个也可以是多个，但至少要有一个

`result(new PermissionCallback())`
请求结果回调
* `onGranted` 当所有请求的权限都被赋予的时候调用
* `onDenied` 当存在有被拒绝的权限时调用，形参`permissions`为被拒绝的权限集合
* `onNoRequest` 当存在有拒绝的权限时且用户设置为不再询问时调用，形参`permissions`为被设置为不再询问的权限集合

# 历史版本
[查看历史版本](https://github.com/AndroidSupport/LightPermission/releases)

# 注意事项
* 1.x 版本的使用说明点击[此处](https://github.com/AndroidSupport/LightPermission/blob/master/README_v1.md)
* 该库目前处于测试阶段，但本人在实际项目中使用时暂未出现问题，欢迎大家贡献代码和意见
* 如果您觉得还不错，请点个星星~

# License
```
Copyright Haitao Peng

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
