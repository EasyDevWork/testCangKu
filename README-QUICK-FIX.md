# Android Demo 快速修复指南

## 问题
```
Build file '/Users/clarence/androidCode/testCangKu/AndroidDemo/build.gradle' line: 2
Plugin [id: 'com.android.application'] was not found in any of the following sources:
```

## 原因
项目缺少Android Gradle插件配置和正确的仓库设置。

## 解决方案（三选一）

### 方案1：最简单的方法（推荐）
**直接创建新项目并复制代码**
1. 在Android Studio中创建新项目
2. 选择 "Empty Views Activity"
3. 配置包名为 `com.easydevwork.androiddemo`
4. 用下面的代码替换文件

### 方案2：修复现有项目
运行修复脚本：
```bash
cd /Users/clarence/androidCode/testCangKu
chmod +x fix-android-project.sh
./fix-android-project.sh
```

### 方案3：手动修复
编辑 `AndroidDemo/build.gradle`：

```gradle
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:8.2.2'
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
```

## 完整代码文件

### 1. MainActivity.java
```java
package com.easydevwork.androiddemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView welcomeText;
    private Button clickButton;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.welcomeText);
        clickButton = findViewById(R.id.clickButton);
        welcomeText.setText("欢迎使用 Android Demo!\n由 OpenClaw + DeepSeek 创建");

        clickButton.setOnClickListener(v -> {
            clickCount++;
            String message = "按钮被点击了 " + clickCount + " 次!";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            clickButton.setText("点击次数: " + clickCount);
        });

        clickButton.setOnLongClickListener(v -> {
            clickCount = 0;
            clickButton.setText("点击我");
            Toast.makeText(MainActivity.this, "计数器已重置", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
```

### 2. activity_main.xml (简化版)
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="24dp">

    <TextView
        android:id="@+id/welcomeText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Android Demo"
        android:textSize="24sp"
        android:layout_marginBottom="32dp"/>

    <Button
        android:id="@+id/clickButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="点击我"
        android:textSize="18sp"
        android:padding="16dp"/>

</LinearLayout>
```

### 3. AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="Android Demo"
        android:theme="@style/Theme.MaterialComponents.DayNight.NoActionBar">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

## 验证步骤

1. **同步Gradle**：点击大象图标或 File → Sync Project with Gradle Files
2. **清理项目**：Build → Clean Project
3. **重建项目**：Build → Rebuild Project
4. **运行**：点击绿色运行按钮

## 常见问题解决

### Q1: 还是找不到插件？
- 检查网络连接，确保能访问 `google()` 仓库
- 更新Android Studio到最新版本
- 检查Gradle版本：File → Project Structure → Project

### Q2: 代码有红色错误？
- 确保已导入正确的包：`import androidx.appcompat.app.AppCompatActivity;`
- 检查布局文件中的ID是否匹配
- 清理并重建项目

### Q3: 运行按钮灰色？
- 确保已选择设备或模拟器
- 等待Gradle同步完成
- 重启Android Studio

## 成功标志
- ✅ Gradle同步成功（底部状态栏显示）
- ✅ 代码无红色错误
- ✅ 运行按钮变为绿色
- ✅ 应用在设备上正常运行

## 技术支持
如果还有问题，请提供：
1. Android Studio版本
2. Gradle版本
3. 完整错误日志
4. 项目结构截图