package com.phucvvph34858.lab_asm.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.phucvvph34858.lab_asm.R;
import com.phucvvph34858.lab_asm.databinding.ActivityLoginBinding;
import com.phucvvph34858.lab_asm.model.Response;
import com.phucvvph34858.lab_asm.model.User;
import com.phucvvph34858.lab_asm.services.HttpRequest;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private HttpRequest httpRequest;
    TextView dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        httpRequest = new HttpRequest();
        userListener();

        // Ánh xạ TextView "Đăng ký"
        dangky = findViewById(R.id.Textdangky);
        // Gắn listener cho TextView "Đăng ký"
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để chuyển sang màn hình đăng ký
                startRegisterActivity();
            }
        });
    }

    private void userListener() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String _username = binding.edUsername.getText().toString().trim();
                String _password = binding.edPassword.getText().toString().trim();
                user.setUsername(_username);
                user.setPassword(_password);
                httpRequest.callAPI().login(user).enqueue(responseUser);
            }
        });
    }

    Callback<Response<User>> responseUser = new Callback<Response<User>>() {
        @Override
        public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("INFO", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", response.body().getToken());
                    editor.putString("refreshToken", response.body().getRefreshToken());
                    editor.putString("id", response.body().getData().get_id());
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish(); // Đóng LoginActivity sau khi chuyển sang HomeActivity
                }
            }
        }

        @Override
        public void onFailure(Call<Response<User>> call, Throwable t) {
            t.getMessage();
        }
    };

    // Phương thức để chuyển sang màn hình đăng ký
    private void startRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
