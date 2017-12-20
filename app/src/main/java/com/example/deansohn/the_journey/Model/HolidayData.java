package com.example.deansohn.the_journey.Model;

import android.content.Context;

import com.example.deansohn.the_journey.DB.holiday_data_AO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DESOHN on 19/12/2017.
 */

public class HolidayData {

    private Context context;
    private static HolidayData instance;
    public static List<Holiday> holidays;

    holiday_data_AO holidayDAO = new holiday_data_AO(this.context);
    public static final Map<Integer, Holiday> HOLIDAY_ITEM_MAP = new HashMap<Integer, Holiday>();

    public HolidayData(Context context) {
        this.context = context;
        holidays = new ArrayList<>();
        //createData();
    }

    public static HolidayData getInstance(Context context) {
        if(instance == null) {
            instance = new HolidayData(context);
        }
        return instance;
    }

    private void createData() {

    }

    private static void addItemToArrayAndMap(Holiday item) {
        holidays.add(item);
        HOLIDAY_ITEM_MAP.put(item.getId(), item);
    }

    public ArrayList<Holiday> getHolidays() {

        holidays = holidayDAO.getHolidays();

        return (ArrayList<Holiday>) holidays;
    }
}

