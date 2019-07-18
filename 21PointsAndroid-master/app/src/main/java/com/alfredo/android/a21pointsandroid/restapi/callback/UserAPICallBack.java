package com.alfredo.android.a21pointsandroid.restapi.callback;

import com.alfredo.android.a21pointsandroid.model.User;

public interface UserAPICallBack extends RestAPICallBack {
    void onGetUserInfo(User user);
    void onGetUser(User body);
}
