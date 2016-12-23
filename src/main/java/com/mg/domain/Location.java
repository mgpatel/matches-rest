package com.mg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Krishna on 20/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
