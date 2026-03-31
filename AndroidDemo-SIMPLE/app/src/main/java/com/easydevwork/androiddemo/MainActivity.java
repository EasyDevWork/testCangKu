package com.easydevwork.androiddemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private TextView textView;
    private Button button;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        
        textView.setText("Android Demo App\n点击下方按钮测试");
        
        button.setOnClickListener(v -> {
            clickCount++;
            String message = "点击次数: " + clickCount;
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            button.setText("已点击 " + clickCount + " 次");
        });
        
        button.setOnLongClickListener(v -> {
            clickCount = 0;
            button.setText("点击我");
            Toast.makeText(this, "计数器已重置", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}