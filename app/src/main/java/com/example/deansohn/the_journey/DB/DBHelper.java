package com.example.deansohn.the_journey.DB;

/**
 * Created by DESOHN on 01/12/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "the_journey_db";
    private static final int DATABASE_VERSION = 1;

    static final String HOLIDAY_TABLE = "holiday";

    static final String ID_COLUMN = "id";
    static final String NAME_COLUMN = "holiday_name";
    static final String HOLIDAY_STARTDATE = "holiday_startDate";
    static final String HOLIDAY_ENDDATE = "holiday_endDate";
    static final String HOLIDAY_DESCRIPTION = "holiday_description";

    private static final String CREATE_HOLIDAY_TABLE = "CREATE TABLE "
            + HOLIDAY_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_COLUMN + " TEXT, " + HOLIDAY_DESCRIPTION + " TEXT, "
            + HOLIDAY_STARTDATE + " STRING, " + HOLIDAY_ENDDATE + " STRING"  + ")";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + HOLIDAY_TABLE;

    private static DBHelper instance;

    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_HOLIDAY_TABLE);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDestroy(db);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void onDestroy(SQLiteDatabase db) { db.execSQL(DROP_TABLE);}
}
