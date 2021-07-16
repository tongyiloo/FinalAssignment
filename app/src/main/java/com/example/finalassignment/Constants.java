package com.example.finalassignment;

public class Constants {

    //db name
    public static final String DB_NAME = "DigitalLibraryApp.db";
    //db version
    public static final int DB_VERSION = 1;
    //db table
    public static final String TABLE_NAME = "USER_LIBRARY_TABLE";
    //table columns
    public static final String C_LID = "LID";
    //    public static final String C_ID = "ID";
    public static final String C_EMAILADDRESS = "emailAddress";
    public static final String C_TITLE = "TITLE";
    public static final String C_DESCRIPTION = "DESCRIPTION";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_ADD_TIMESTAMP = "ADD_TIMESTAMP";
    public static final String C_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP";

    //create query for table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_LID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_TITLE + " TEXT,"
            + C_DESCRIPTION + " TEXT,"
            + C_IMAGE + " TEXT,"
            + C_ADD_TIMESTAMP + " TEXT,"
            + C_UPDATE_TIMESTAMP + " TEXT"
            + ");";


    public static final String TABLE_NAME1 = "USER_TABLE";
    public static final String C_ID = "ID";
    public static final String C_USERNAME = "USERNAME";
    public static final String C_PASSWORD = "PASSWORD";

    public static final String CREATE_TABLE1 = "CREATE TABLE " + TABLE_NAME1 + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_USERNAME + " TEXT,"
            + C_PASSWORD + " TEXT"
            + ");";
}

