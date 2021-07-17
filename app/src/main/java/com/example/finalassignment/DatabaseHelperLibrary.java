package com.example.finalassignment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelperLibrary extends SQLiteOpenHelper {

    public DatabaseHelperLibrary(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
        db.execSQL(Constants.CREATE_TABLE1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME1);
        onCreate(db);
    }

    //insert info USER_LIBRARY_TABLE function
    public long insertInfo(String title, String description, String image, String addTimeStamp, String updateTimeStamp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_TITLE, title);
        values.put(Constants.C_DESCRIPTION, description);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);
        long lid = db.insert(Constants.TABLE_NAME, null, values);
        return lid;
    }

    //insert info USER_TABLE function
    public Boolean insertUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_USERNAME, username);
        values.put(Constants.C_PASSWORD, password);
        long id = db.insert(Constants.TABLE_NAME1, null, values);
        if (id==1) return false;
        else
            return true;
    }

    //To check the username
    public Boolean checkusername(String username){
        //String query = "SELECT * FROM " + Constants.TABLE_NAME1 + " WHERE " + Constants.C_USERNAME + " =?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE USERNAME = ?", new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //To check the password
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE USERNAME = ? and PASSWORD = ?", new String[]{username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    // update information from USER_LIBRARY_TABLE function
    public void updateInfo(String lid, String title, String description, String image, String addTimeStamp, String updateTimeStamp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_TITLE, title);
        values.put(Constants.C_DESCRIPTION, description);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(Constants.TABLE_NAME, values, Constants.C_LID+ " = ?", new String[]{lid});
    }

    //delete information from USER_LIBRARY_TABLE function
    public void deleteInfo(String lid){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.C_LID + " = ?", new String[]{lid});
    }

    public ArrayList<Model> getAllData(String orderBy){
        ArrayList<Model> arrayList = new ArrayList<>();

        //query for select all info in database
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //select all info from database new get the data from columns
        if(cursor.moveToNext()){
            do {
                // do is used because first it gets the data from columns then move to next condition
                Model model = new Model(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_LID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_TITLE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADD_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATE_TIMESTAMP))
                );
                arrayList.add(model);
            }while
            (cursor.moveToNext());
        }
        return arrayList;
    }

}
