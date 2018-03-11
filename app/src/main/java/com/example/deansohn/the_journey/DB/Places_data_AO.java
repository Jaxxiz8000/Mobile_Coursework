package com.example.deansohn.the_journey.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.deansohn.the_journey.Model.Place.Places;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;

/**
 * Created by DESOHN on 10/03/2018.
 */

public class Places_data_AO extends db_AO {

    public Places_data_AO(Context context) { super(context); }

    public long addPlaces(String place_name, String address) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.PLACES_NAME_COLUMN, place_name);
        contentValues.put(DBHelper.PLACES_ADDRESS, address);
        //contentValues.put(DBHelper.ASSOCIATION_KEY, association_key);

        return database.insert(DBHelper.PLACES_TABLE, null, contentValues);
    }

    public ArrayList<Places> getPlaces() {
        ArrayList<Places> places = new ArrayList<>();
        int limit = 10;

        Cursor cursor = database.query(DBHelper.PLACES_TABLE,
                new String[] {
                    DBHelper.PLACES_ID_COLUMN,
                    DBHelper.PLACES_NAME_COLUMN,
                    DBHelper.PLACES_ADDRESS }, null,null, null, null, String.valueOf(limit));

        while (cursor.moveToNext()) {
            Places place = new Places();

            place.setPlaces_id(cursor.getInt(0));
            place.setPlaces_name(cursor.getString(1));
            place.setPlaces_address(cursor.getString(2));

            places.add(place);

        }
        return places;
    }


}
