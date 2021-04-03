package com.app.formationjcikhniss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * user authentication
 * @author Aymen Masmoudi[23.03.2021]last update[03.04.2021]
 * */
public class LoginActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private Button btnLogin;

    private SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE);

        etUserName = findViewById(R.id.et_user_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String userPassword = etPassword.getText().toString();
                if (userName.equals("aymen") && userPassword.equals("jci")) {
                    //1-save login in sharedPreferences
                    SharedPreferences.Editor editor = userPreferences.edit();
                    editor.putString("userName", userName);
                    editor.putString("userPassword", userPassword);
                    editor.apply();
                    //2-start HomeScreenActivity
                    Intent homescreen = new Intent(LoginActivity.this, HomeScreenActivity.class);
                    startActivity(homescreen);
                    //3-close LoginActivity
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}