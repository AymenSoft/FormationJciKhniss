package com.app.formationjcikhniss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * say hello to users
 * make some tests
 * @author Aymen Masmoudi[23.03.2021]last update[23.03.2021]
 * */
public class SplashScreenActivity extends AppCompatActivity {

    //to call runnable
    private Handler handler;
    //start new activity
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent startLogin = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(startLogin);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        handler.postDelayed(runnable, 5000);

    }
}