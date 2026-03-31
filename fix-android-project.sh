    <foreground android:drawable="@drawable/ic_android"/>
</adaptive-icon>
EOF

cat > AndroidDemo/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background"/>
    <foreground android:drawable="@drawable/ic_android"/>
</adaptive-icon>
EOF

echo "10. 创建proguard规则文件..."
mkdir -p AndroidDemo/app
cat > AndroidDemo/app/proguard-rules.pro << 'EOF'
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
EOF

echo ""
echo "✅ 修复完成!"
echo ""
echo "📋 修复内容:"
echo "   1. ✅ 修复了顶层 build.gradle - 添加了Android插件"
echo "   2. ✅ 修复了 app/build.gradle - 更新了依赖配置"
echo "   3. ✅ 创建了 gradle-wrapper.properties - 指定Gradle版本"
echo "   4. ✅ 创建了 gradle.properties - 配置构建参数"
echo "   5. ✅ 创建了 settings.gradle - 项目设置"
echo "   6. ✅ 创建了 gradlew 脚本 - 可执行构建脚本"
echo "   7. ✅ 创建了缺失的资源文件 - drawable和mipmap"
echo "   8. ✅ 创建了 proguard-rules.pro - 混淆规则"
echo ""
echo "🚀 下一步操作:"
echo "   1. 在Android Studio中重新打开项目"
echo "   2. 等待Gradle同步完成"
echo "   3. 点击运行按钮 ▶️"
echo ""
echo "💡 如果还有问题，请尝试:"
echo "   - File → Invalidate Caches and Restart"
echo "   - 检查网络连接，确保能访问Google和Maven仓库"
echo "   - 更新Android Studio到最新版本"
echo ""
echo "📁 备份文件已保存:"
echo "   - AndroidDemo/build.gradle.backup"
echo "   - AndroidDemo/app/build.gradle.backup"