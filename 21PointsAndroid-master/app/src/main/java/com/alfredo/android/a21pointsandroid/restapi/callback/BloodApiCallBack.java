package com.alfredo.android.a21pointsandroid.restapi.callback;

import com.alfredo.android.a21pointsandroid.model.Blood;
import com.alfredo.android.a21pointsandroid.model.Points;

import java.util.ArrayList;

public interface BloodApiCallBack extends RestAPICallBack {

        void onPostBlood(Blood blood);
        void onGetBlood(ArrayList<Blood> points);
    }

