package com.app.formationjcikhniss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * say hello to users
 * make some tests
 * @author Aymen Masmoudi[23.03.2021]last update[03.04.2021]
 * */
public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferences userPreferences;
    private String userName, userPassword;

    //to call runnable
    private Handler handler;
    //start new activity
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (userName.equals("aymen") && userPassword.equals("jci")) {
                Intent homeScreen = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
                startActivity(homeScreen);
            }else {
                Intent login = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(login);
            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        userPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE);
        userName = userPreferences.getString("userName", "defValue");
        userPassword = userPreferences.getString("userPassword", "defValue");

        Log.e("sharedPreferences", userName+","+userPassword);

        handler = new Handler();
        handler.postDelayed(runnable, 5000);

    }
}