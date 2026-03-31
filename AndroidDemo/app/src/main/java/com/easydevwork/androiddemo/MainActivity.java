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

        // 设置欢迎文本
        welcomeText.setText("欢迎使用 Android Demo!\n由 OpenClaw + DeepSeek 创建");

        // 按钮点击事件
        clickButton.setOnClickListener(v -> {
            clickCount++;
            String message = "按钮被点击了 " + clickCount + " 次!";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            
            // 更新按钮文本
            clickButton.setText("点击次数: " + clickCount);
            
            // 改变文本颜色
            if (clickCount % 5 == 0) {
                int color = getResources().getColor(
                    clickCount % 10 == 0 ? R.color.purple_500 : R.color.teal_700,
                    getTheme()
                );
                welcomeText.setTextColor(color);
            }
        });

        // 长按按钮重置
        clickButton.setOnLongClickListener(v -> {
            clickCount = 0;
            clickButton.setText("点击我");
            welcomeText.setTextColor(getResources().getColor(R.color.black, getTheme()));
            Toast.makeText(MainActivity.this, "计数器已重置", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}