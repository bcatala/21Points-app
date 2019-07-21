package com.alfredo.android.a21pointsandroid.model;

public class Weight {
    private Integer id;

    private String timestamp;

    private Integer weight;

    private User user;

    public Weight(String timestamp, Integer weight, User user) {
        this.timestamp = timestamp;
        this.weight = weight;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
