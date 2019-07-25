package com.alfredo.android.a21pointsandroid.restapi.callback;

import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.model.User;

import java.util.ArrayList;

public interface PointsAPICallBack extends RestAPICallBack {
    void onPostPoints(Points points);
    void onGetPoints(ArrayList<Points> points);
    void onGetPointsWeek(Points points);
}
