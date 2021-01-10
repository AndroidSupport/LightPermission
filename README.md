# LightPermission
A lightweight android permission manager.
<br>The latest version 2.0 supports Chain-style, currently.
<br>[Chinese](https://github.com/AndroidSupport/LightPermission/blob/master/README_zh.md)

# Dependency
```
allprojects {
    repositories {
        maven {
            url 'https://oss.sonatype.org/content/groups/public'
        }
    }
}

implementation 'com.uniquext.android:permission:2.0.1'
```

# Instructions
### Demo
```
LightPermission
        .with(this)
        .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .result(new PermissionCallback() {

            @Override
            public void onGranted() {
                //  Camera and storage permissions have been acquired
            }

            @Override
            public void onDenied(String[] permissions) {
                //  Permission set rejected on request
            }

            @Override
            public void onNeverRequest(String[] permissions) {
                //  The permission set is set to "Do not ask again after prohibition"
                new AppSettingsDialog.Builder(this) .show();
            }

        });
```
### Explanation
`with(this)`
* The `this` parameter can be a `FragmentActivity` or `Fragment` (when using `androidx.fragment.app` package).
* And can also be a `Activity` or `android.app.Fragment` (when using `android.app` package).

`permissions`
The permission which to be requested, at least one in number.

`result(new PermissionCallback())`
permission callback
* `onGranted` Called when all requested permissions are granted.
* `onDenied` Called when there is a denied permission, the parameter `permissions` is the denied permission set.
* `onLongerAsk` Called when there is a denied permissions and those were set to no longer ask, the parameter `permissions` is the set of permissions that were set to no longer ask.

# History
[View historical version](https://github.com/AndroidSupport/LightPermission/releases)

# Tips
* Click [Here](https://github.com/AndroidSupport/LightPermission/blob/master/README_v1.md) to look instructions of version 1.x.
* The library is currently in beta, but I have not encountered any issue in actual projects.
* Welcome everyone to contribute code and comments.
* If you feel ok, please click Star.

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
