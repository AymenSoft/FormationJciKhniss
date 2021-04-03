package com.app.formationjcikhniss.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.formationjcikhniss.models.Users;

import java.util.ArrayList;

/**
 * control users database data
 * @author Aymen Masmoudi[03.04.2021]
 * */
public class UsersDB {

    private SQLiteDatabase db;
    private DBHelper helper;

    public UsersDB(Context context){
        helper = new DBHelper(context);
    }

    public void open(){
        db=helper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    //insert new user
    public long insert(Users user){
        ContentValues values = new ContentValues();
        values.put(DBHelper.name, user.getName());
        values.put(DBHelper.email, user.getEmail());
        return db.insert(DBHelper.TABLE_USERS, null, values);
    }

    //update user
    public long update(Users user){
        ContentValues values = new ContentValues();
        values.put(DBHelper.name, user.getName());
        values.put(DBHelper.email, user.getEmail());
        return db.update(DBHelper.TABLE_USERS,
                values,
                DBHelper.id + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    //remove user
    public long remove(int id){
        return db.delete(DBHelper.TABLE_USERS,
                DBHelper.id + " = ?",
                new String[]{String.valueOf(id)});
    }

    //remove all users
    public long removeAll(){
        return db.delete(DBHelper.TABLE_USERS, null, null);
    }

    //get all users
    public ArrayList<Users>getAllUsers(){
        ArrayList<Users> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM TABLE_USERS", null);
        if (cursor != null) {
            while (cursor.moveToNext()){
                Users user = new Users();
                user.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.id)));
                user.setName(cursor.getString(cursor.getColumnIndex(DBHelper.name)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.email)));
                list.add(user);
            }
            cursor.close();
        }
        return list;
    }

}
