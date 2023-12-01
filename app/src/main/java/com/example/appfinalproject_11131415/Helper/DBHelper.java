package com.example.appfinalproject_11131415.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public  static final String DB_NAME = "Signup.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase myDatabase) {
        myDatabase.execSQL("CREATE TABLE allUsers(name TEXT primary key, mobile_phone TEXT, email TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int oldVersion, int newVersion) {
        //myDatabase.execSQL("DROP TABLE IF EXISTS allUsers");
    }
    public Boolean insertData(String name, String phone, String email, String password){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobile_phone", phone);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = myDatabase.insert("allUsers", null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean checkName(String name){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * FROM allUsers WHERE name = ?", new String[]{name});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean checkPhone(String phone){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * FROM allUsers WHERE phone = ?", new String[]{phone});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean checkEmail(String email){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * FROM allUsers WHERE email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean checkPassword(String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * FROM allUsers WHERE password = ?", new String[]{password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkNamePassword(String name, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * FROM allUsers WHERE name = ? AND password = ?", new String[]{name, password});

        if (cursor.getCount() > 0 ){
            return true;
        } else {
            return false;
        }

    }

}
