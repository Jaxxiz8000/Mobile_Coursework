package com.example.deansohn.the_journey;

import com.example.deansohn.the_journey.DB.DBHelper;
import com.example.deansohn.the_journey.dummy.DummyContent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deansohn on 13/11/2017.
 */

public class HolidayContent implements Serializable {

    DBHelper mydb;
    /**
     * An array of Holiday Items.
     */

    public static final List<HolidayContent.HolidayItem> HOLIDAY_ITEMS = new ArrayList<>();

    /**
     * A map of Holiday items, by ID.
     */
    public static final Map<String, HolidayContent.HolidayItem> HOLIDAY_ITEM_MAP = new HashMap<>();

    private static void addItemToArrayAndMap(HolidayContent.HolidayItem item) {
        HOLIDAY_ITEMS.add(item);
        HOLIDAY_ITEM_MAP.put(item.holiday_item_id, item);
    }

    static HolidayItem addHolidayItem(String holidayName, String holidayDetails, String holidayStartDate, String holidayEndDate){

        final int position = HOLIDAY_ITEMS.size() + 1;

        HolidayItem newItem = new HolidayItem(String.valueOf(position), holidayName, holidayDetails,
                holidayStartDate, holidayEndDate);

        addItemToArrayAndMap(newItem);

        return newItem;

    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class HolidayItem implements Serializable {
        public final String holiday_item_id;
        public final String holiday_Name;
        public final String holiday_Details;
        public final String holiday_start_date;
        public final String holiday_end_date;

        HolidayItem(String id, String hol_name, String hol_details, String holStartDate, String holEndDate) {
            this.holiday_item_id = id;
            this.holiday_Name = hol_name;
            this.holiday_Details = hol_details;
            this.holiday_start_date = holStartDate;
            this.holiday_end_date = holEndDate;
        }

        @Override
        public String toString() {
            return holiday_Name;
        }
    }


}
