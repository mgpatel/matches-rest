package com.mg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mayank.patel on 20/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Availability {
    private String title;
    private int dayIndex;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }
}
