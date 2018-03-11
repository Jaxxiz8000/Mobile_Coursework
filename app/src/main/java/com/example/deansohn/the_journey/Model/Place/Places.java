package com.example.deansohn.the_journey.Model.Place;

/**
 * Created by DESOHN on 10/03/2018.
 */

public class Places {

    private Integer places_id;
    private String places_name;
    private String association_key;

    public String getAssociation_key() {
        return association_key;
    }

    public void setAssociation_key(String association_key) {
        this.association_key = association_key;
    }

    private String places_address;

    public void setPlaces_id(Integer places_id) {

        this.places_id = places_id;
    }

    public void setPlaces_name(String places_name) {

        this.places_name = places_name;
    }

    public void setPlaces_address(String places_address) {

        this.places_address = places_address;
    }

    public Integer getPlaces_id() {

        return places_id;
    }

    public String getPlaces_address() {

        return places_address;
    }
}
