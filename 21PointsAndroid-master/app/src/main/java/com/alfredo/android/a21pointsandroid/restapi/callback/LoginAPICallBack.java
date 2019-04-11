package com.alfredo.android.a21pointsandroid.restapi.callback;

import com.alfredo.android.a21pointsandroid.model.UserToken;

public interface LoginAPICallBack extends RestAPICallBack {
    void onLoginSuccess(UserToken userToken);
}
