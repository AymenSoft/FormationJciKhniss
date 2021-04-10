package com.app.formationjcikhniss;

import android.app.Application;

import com.app.formationjcikhniss.sqlite.DBHelper;
import com.bugsnag.android.Bugsnag;

/**
 * start some code before starting application
 * @author Aymen Masmoudi[03.04.2021]last update[10.04.2021]
 * */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //start bugsnag
        Bugsnag.start(this);

        //create sqlite database
        new DBHelper(this);

    }
}
