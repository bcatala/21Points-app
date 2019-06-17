package com.alfredo.android.a21pointsandroid.restapi.callback;

import com.alfredo.android.a21pointsandroid.model.Blood;
import com.alfredo.android.a21pointsandroid.model.User;

public interface BloodApiCallBack extends RestAPICallBack{

        void onPostBlood(Blood blood);
        void onGetBlood(Blood blood);

}
