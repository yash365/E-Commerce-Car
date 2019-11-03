package com.example.ecommercecar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ecommercecar.database.CarContract.*;

import androidx.annotation.Nullable;

public class CarDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "carlist.db";
    public static final int DATABASE_VERSION = 1;


    public CarDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CAR_TABLE =
                    "CREATE TABLE " +
                    CarEntry.TABLE_NAME +  " (" +
                    CarEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CarEntry.COLUMN_CAR_IMAGE + " BLOB NOT NULL, " +
                    CarEntry.COLUMN_CAR_NAME + " TEXT NOT NULL, " +
                    CarEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                    CarEntry.COLUMN_MODEL_YEAR + " INTEGER NOT NULL, " +
                    CarEntry.COLUMN_KM_USED + " INTEGER NOT NULL, " +
                    CarEntry.COLUMN_SELLER_NAME + " TEXT NOT NULL, " +
                    CarEntry.COLUMN_LOCATION + " TEXT NOT NULL, " +
                    CarEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";

        db.execSQL(SQL_CREATE_CAR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CarEntry.TABLE_NAME);
        onCreate(db);
    }
}
