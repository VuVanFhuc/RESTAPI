package com.phucvvph34858.lab_asm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phucvvph34858.lab_asm.R;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tạo một Handler để chuyển đến màn hình đăng nhập sau 5 giây
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent để chuyển đến màn hình đăng nhập
                Intent intent = new Intent(welcome.this, LoginActivity.class);
                startActivity(intent);
                // Đảm bảo không quay lại màn hình chào mừng khi chuyển đến màn hình đăng nhập
                finish();
            }
        }, 5000); // 5 giây
    }
}
