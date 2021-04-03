package com.app.formationjcikhniss.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * control sqlite creation and update
 * @author Aymen Masmoudi[03.04.2021]
 * */
public class DBHelper extends SQLiteOpenHelper {

    //database name and version
    private static final String DB_NAME = "formation.db";
    private static final int DB_VERSION = 1;

    //table name
    public static final String TABLE_USERS = "TABLE_USERS";

    //table columns
    public static final String id = "id";
    public static final String name = "name";
    public static final String email = "email";

    //create table
    private static final String CREATE_USERS_TABLE =
            "Create Table IF NOT EXISTS "+ TABLE_USERS +
                    "("+
                    id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    name+" STRING, "+
                    email +" STRING);";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    }
}
