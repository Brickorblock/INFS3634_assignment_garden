package com.example.infs3634_assignment_garden;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//plant table, questions table

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "garden_database.db";
    public static String TABLE_PLANT = "plant";
    public static String plantCol_1 = "name";
    public static String plantCol_2 = "subject";
    public static String plantCol_3= "growthTotal";
    public static String plantCol_4= "growthLvl";
    public static String plantCol_5= "growthProgress";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PLANT +" (name TEXT,subject TEXT,growthTotal INTEGER, growthLvl INTEGER, growthProgress INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PLANT);
        onCreate(db);
    }

    public boolean insertData(String name, String subject, int growthTotal, int growthLvl, int growthProgress){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues plantValues = new ContentValues();
            plantValues.put(plantCol_1,name);
            plantValues.put(plantCol_2,subject);
            plantValues.put(plantCol_3,growthTotal);
            plantValues.put(plantCol_4,growthLvl);
            plantValues.put(plantCol_5,growthProgress);

            db.insert(TABLE_PLANT,null ,plantValues);

            return true;
    }

}
