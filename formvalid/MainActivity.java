package com.example.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnProceed, btnCancel;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnProceed = findViewById(R.id.btnProceed);
        btnCancel = findViewById(R.id.btnCancel);
        tvError = findViewById(R.id.tvError);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void validateLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            tvError.setText("Please enter your email");
            tvError.setVisibility(View.VISIBLE);
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tvError.setText("Please enter a valid email");
            tvError.setVisibility(View.VISIBLE);
        } else if (TextUtils.isEmpty(password)) {
            tvError.setText("Please enter your password");
            tvError.setVisibility(View.VISIBLE);
        } else if (password.length() < 8) {
            tvError.setText("Password must be at least 8 characters long");
            tvError.setVisibility(View.VISIBLE);
        } else {
            tvError.setVisibility(View.GONE);
            proceedToNextPage();
        }
    }

    private void proceedToNextPage() {
        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MainActivity2.class); // Navigating to MainActivity2
        startActivity(intent);
    }

    private void clearFields() {
        etEmail.setText("");
        etPassword.setText("");
        tvError.setVisibility(View.GONE);
    }
}
