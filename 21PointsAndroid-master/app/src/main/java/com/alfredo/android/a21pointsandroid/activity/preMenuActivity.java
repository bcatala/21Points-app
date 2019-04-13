package com.alfredo.android.a21pointsandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;

public class preMenuActivity extends AppCompatActivity {
    private Button GotoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premenu);

        Button mGotoMenu = (Button) findViewById(R.id.GotoMenu);
        mGotoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // setContentView(R.layout.activity_main);

            }

        });
    }
}
