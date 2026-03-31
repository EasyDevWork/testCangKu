#!/bin/bash

echo "🔧 Android项目构建验证脚本"
echo "=============================="
echo ""

# 检查项目结构
echo "1. 检查项目结构..."
if [ ! -f "AndroidDemo/build.gradle" ]; then
    echo "❌ 错误: 缺少 build.gradle"
    exit 1
fi

if [ ! -f "AndroidDemo/app/build.gradle" ]; then
    echo "❌ 错误: 缺少 app/build.gradle"
    exit 1
fi

if [ ! -f "AndroidDemo/settings.gradle" ]; then
    echo "❌ 错误: 缺少 settings.gradle"
    exit 1
fi

echo "✅ 项目结构完整"
echo ""

# 检查关键文件
echo "2. 检查关键文件..."
FILES=(
    "AndroidDemo/gradle/wrapper/gradle-wrapper.properties"
    "AndroidDemo/gradle.properties"
    "AndroidDemo/gradlew"
    "AndroidDemo/app/src/main/java/com/easydevwork/androiddemo/MainActivity.java"
    "AndroidDemo/app/src/main/res/layout/activity_main.xml"
    "AndroidDemo/app/src/main/AndroidManifest.xml"
)

for file in "${FILES[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ 缺少: $file"
    fi
done
echo ""

# 检查build.gradle配置
echo "3. 检查构建配置..."
if grep -q "google()" "AndroidDemo/build.gradle"; then
    echo "✅ build.gradle 包含 google() 仓库"
else
    echo "❌ build.gradle 缺少 google() 仓库"
fi

if grep -q "com.android.tools.build:gradle" "AndroidDemo/build.gradle"; then
    echo "✅ build.gradle 包含 Android Gradle 插件"
else
    echo "❌ build.gradle 缺少 Android Gradle 插件"
fi
echo ""

# 检查代码语法
echo "4. 检查代码语法..."
if grep -q "findViewById" "AndroidDemo/app/src/main/java/com/easydevwork/androiddemo/MainActivity.java"; then
    echo "✅ MainActivity 使用正确的 findViewById"
else
    echo "❌ MainActivity 可能缺少 findViewById"
fi

if grep -q "R.layout.activity_main" "AndroidDemo/app/src/main/java/com/easydevwork/androiddemo/MainActivity.java"; then
    echo "✅ MainActivity 设置正确的布局"
else
    echo "❌ MainActivity 可能缺少布局设置"
fi
echo ""

# 检查布局文件
echo "5. 检查布局文件..."
if grep -q "welcomeText" "AndroidDemo/app/src/main/res/layout/activity_main.xml"; then
    echo "✅ 布局包含 welcomeText ID"
else
    echo "❌ 布局缺少 welcomeText ID"
fi

if grep -q "clickButton" "AndroidDemo/app/src/main/res/layout/activity_main.xml"; then
    echo "✅ 布局包含 clickButton ID"
else
    echo "❌ 布局缺少 clickButton ID"
fi
echo ""

echo "📋 验证完成!"
echo ""
echo "🚀 在Android Studio中使用:"
echo "   1. 拉取最新代码: git pull origin main"
echo "   2. 在Android Studio中打开 AndroidDemo 目录"
echo "   3. 等待Gradle同步完成"
echo "   4. 点击运行按钮 ▶️"
echo ""
echo "💡 如果还有问题，请检查:"
echo "   - Android Studio版本 (建议最新版)"
echo "   - 网络连接 (需要访问Google仓库)"
echo "   - Java版本 (需要JDK 11+)"
echo ""
echo "🔗 GitHub仓库: https://github.com/EasyDevWork/testCangKu"