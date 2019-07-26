package com.alfredo.android.a21pointsandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointsWeek {

    @SerializedName("week")
    @Expose
    private String week;
    @SerializedName("points")
    @Expose
    private Integer points;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}