package com.alfredo.android.a21pointsandroid.restapi.callback;

import com.alfredo.android.a21pointsandroid.model.Weight;
import com.alfredo.android.a21pointsandroid.model.User;

public interface WeightApiCallBack {

    void onPostWeight(Weight weight);
    void onGetWeight(Weight weight);

}
