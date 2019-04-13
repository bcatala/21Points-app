package com.alfredo.android.a21pointsandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;

public class preMenuActivity {
    private Button GotoMenu;

    protected void onCreate(Bundle savedInstanceState) {



        GotoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // setContentView(R.layout.activity_main);

            }

        });
    }
}
