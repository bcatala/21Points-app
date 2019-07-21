package com.alfredo.android.a21pointsandroid.restapi.callback;

import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.model.Weight;

import java.util.ArrayList;

public interface WeightAPICallBack extends RestAPICallBack {

        void onGetWeight(ArrayList<Weight> weight);
        void onPostWeight(Weight weight);

}
