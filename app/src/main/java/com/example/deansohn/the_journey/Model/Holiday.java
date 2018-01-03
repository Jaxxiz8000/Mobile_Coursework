package com.example.deansohn.the_journey.Model;

import java.io.Serializable;

/**
 * Created by DESOHN on 04/12/2017.
 */

public class Holiday implements Serializable {

    private Integer id;
    private String holiday_name;
    private String holStartDate;
    private String holEndDate;
    private String holiday_description;

    public Holiday() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoliday_name() {
        return holiday_name;
    }

    public void setHoliday_name(String holiday_name) {
        this.holiday_name = holiday_name;
    }

    public String getHoliday_description() {
        return holiday_description;
    }

    public void setHoliday_description(String holiday_description) {
        this.holiday_description = holiday_description;
    }

    public String getHolStartDate() {
        return holStartDate;
    }

    public void setHolStartDate(String holStartDate) {
        this.holStartDate = holStartDate;
    }

    public String getHolEndDate() {
        return holEndDate;
    }

    public void setHolEndDate(String holEndDate) {
        this.holEndDate = holEndDate;
    }

}
