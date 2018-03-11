package com.example.deansohn.the_journey.DB;

/**
 * Created by DESOHN on 04/12/2017.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class db_AO {

    protected SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context mContext;

    public db_AO(Context context) {
        this.mContext = context;
        dbHelper = DBHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DBHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }
}
