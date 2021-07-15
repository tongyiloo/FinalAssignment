package com.example.finalassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public  static final String DBNAME = "DigitalLibrary.db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    //To create table on DB
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, emailAddress TEXT, password TEXT)");
    }

    //Drop the table if already exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
    }


    //To insert data
    public Boolean insertData(String username, String emailAddress, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("emailAddress", emailAddress);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        if (result==1) return false;
        else
            return true;
    }

    //To check the username
    public Boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //To check the password
    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE emailAddress = ? and password = ?", new String[]{email, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


}
