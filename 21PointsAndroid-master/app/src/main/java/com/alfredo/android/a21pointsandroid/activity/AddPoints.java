package com.alfredo.android.a21pointsandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;

public class AddPoints extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpoints);

/*
        RestAPIManager.getInstance().postPoints(new Points("4110-03-14",1,0,1, "alfreoaprova", user), this);
*/
    }
}
