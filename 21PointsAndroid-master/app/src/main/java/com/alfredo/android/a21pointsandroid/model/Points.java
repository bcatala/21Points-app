
package com.alfredo.android.a21pointsandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Points {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("exercise")
    @Expose
    private Integer exercise;
    @SerializedName("meals")
    @Expose
    private Integer meals;
    @SerializedName("alcohol")
    @Expose
    private Integer alcohol;
    @SerializedName("notes")
    @Expose
    private Object notes;
    @SerializedName("user")
    @Expose
    private User user;

    public Points(String date, Integer exercise, Integer meals, Integer alcohol, String notes, User user) {
        this.date = date;
        this.exercise = exercise;
        this.meals = meals;
        this.alcohol = alcohol;
        this.notes = notes;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getExercise() {
        return exercise;
    }

    public void setExercise(Integer exercise) {
        this.exercise = exercise;
    }

    public Integer getMeals() {
        return meals;
    }

    public void setMeals(Integer meals) {
        this.meals = meals;
    }

    public Integer getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Points{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", exercise=" + exercise +
                ", meals=" + meals +
                ", alcohol=" + alcohol +
                ", notes=" + notes +
                ", user=" + user +
                '}';
    }
}
