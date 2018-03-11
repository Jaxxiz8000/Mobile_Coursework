package com.example.deansohn.the_journey.DB;

/**
 * Created by DESOHN on 04/12/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.deansohn.the_journey.Model.Home_holiday.Holiday;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class holiday_data_AO extends db_AO {

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "dd-MM-yyyy", Locale.ENGLISH);

    public holiday_data_AO(Context context) {
        super(context);
    }

    public long addHoliday(Holiday holiday) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME_COLUMN, holiday.getHoliday_name());
        values.put(DBHelper.HOLIDAY_DESCRIPTION, holiday.getHoliday_description());
        values.put(DBHelper.HOLIDAY_STARTDATE, holiday.getHolStartDate());
        values.put(DBHelper.HOLIDAY_ENDDATE, holiday.getHolEndDate());

        return database.insert(DBHelper.HOLIDAY_TABLE, null, values);

    }

    public ArrayList<Holiday> getHolidays() {
        ArrayList<Holiday> holidays = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.HOLIDAY_TABLE,
                new String[] {
                            DBHelper.ID_COLUMN,
                            DBHelper.NAME_COLUMN,
                            DBHelper.HOLIDAY_DESCRIPTION,
                            DBHelper.HOLIDAY_STARTDATE,
                            DBHelper.HOLIDAY_ENDDATE }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Holiday holiday = new Holiday();
            holiday.setId(cursor.getInt(0));
            holiday.setHoliday_name(cursor.getString(1));
            holiday.setHoliday_description(cursor.getString(2));
            holiday.setHolStartDate(cursor.getString(3));
            holiday.setHolEndDate(cursor.getString(4));
            holidays.add(holiday);
        }
        return holidays;
    }


    public void deleteHolidayByID(int id) {
        database.delete(DBHelper.HOLIDAY_TABLE, DBHelper.ID_COLUMN+"="+id, null);
    }

    public void updateHoliday(Holiday holiday, int id) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME_COLUMN, holiday.getHoliday_name());
        values.put(DBHelper.HOLIDAY_DESCRIPTION, holiday.getHoliday_description());
        values.put(DBHelper.HOLIDAY_STARTDATE, holiday.getHolStartDate());
        values.put(DBHelper.HOLIDAY_ENDDATE, holiday.getHolEndDate());
        database.update(DBHelper.HOLIDAY_TABLE, values,DBHelper.ID_COLUMN+"="+id, null);
    }
}
