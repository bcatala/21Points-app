package com.alfredo.android.a21pointsandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.model.UserToken;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;
import com.alfredo.android.a21pointsandroid.restapi.callback.LoginAPICallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.PointsAPICallBack;


public class preMenuActivity extends AppCompatActivity implements LoginAPICallBack, PointsAPICallBack {

    private Button GotoMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premenu);
        // RestAPIManager.getInstance().getUserToken(email, password, this);
        GotoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);

            }

        });
    }

    @Override
    public void onLoginSuccess(UserToken userToken) {

    }

    @Override
    public void onPostPoints(Points points) {

    }

    @Override
    public void onGetPoints(Points points) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}