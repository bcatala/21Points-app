package com.alfredo.android.a21pointsandroid.restapi.callback;

import com.alfredo.android.a21pointsandroid.model.Points;

public interface PointsAPICallBack extends RestAPICallBack {
    void onPostPoints(Points points);
    void onGetPoints(Points points);
}
