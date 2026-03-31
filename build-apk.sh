#!/bin/bash

# Android Demo APK 构建脚本
# 使用方法: ./build-apk.sh

echo "=== Android Demo APK 构建脚本 ==="
echo ""

# 检查是否在项目目录
if [ ! -f "AndroidDemo/build.gradle" ]; then
    echo "错误: 请在项目根目录运行此脚本"
    echo "项目结构:"
    echo "  testCangKu/"
    echo "  ├── AndroidDemo/          # Android项目"
    echo "  ├── README.md"
    echo "  └── build-apk.sh         # 本脚本"
    exit 1
fi

echo "1. 检查Android SDK环境..."
if [ -z "$ANDROID_HOME" ]; then
    echo "警告: ANDROID_HOME 环境变量未设置"
    echo "请设置: export ANDROID_HOME=/path/to/android/sdk"
    exit 1
fi

echo "2. 检查Java环境..."
java -version >/dev/null 2>&1
if [ $? -ne 0 ]; then
    echo "错误: Java未安装或未配置"
    echo "请安装Java 11或更高版本"
    exit 1
fi

echo "3. 进入Android项目目录..."
cd AndroidDemo

echo "4. 授予gradlew执行权限..."
chmod +x gradlew

echo "5. 开始构建APK..."
echo "这可能需要几分钟时间，请耐心等待..."
echo ""

# 构建debug APK
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ APK构建成功!"
    echo ""
    echo "生成的APK文件位置:"
    echo "  AndroidDemo/app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "APK文件信息:"
    APK_PATH="app/build/outputs/apk/debug/app-debug.apk"
    if [ -f "$APK_PATH" ]; then
        echo "  - 文件大小: $(du -h "$APK_PATH" | cut -f1)"
        echo "  - 包名: com.easydevwork.androiddemo"
        echo "  - 版本: 1.0 (code: 1)"
        echo ""
        echo "安装到设备:"
        echo "  adb install $APK_PATH"
    fi
else
    echo ""
    echo "❌ APK构建失败"
    echo "请检查:"
    echo "  1. Android SDK是否正确安装"
    echo "  2. 是否接受所有Android SDK许可证"
    echo "  3. 网络连接是否正常"
fi