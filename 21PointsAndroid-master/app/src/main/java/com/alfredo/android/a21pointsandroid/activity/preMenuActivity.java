package com.alfredo.android.a21pointsandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.model.UserData;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;

public class preMenuActivity extends AppCompatActivity {
    private Button GotoMenu;
    private TextView username_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premenu);

        Button mGotoMenu = (Button) findViewById(R.id.GotoMenu);

        String a = getIntent().getStringExtra("email");
        String b = getIntent().getStringExtra("c");
        final String a1 = getIntent().getStringExtra("Points");
        final String a2 = getIntent().getStringExtra("Systolic");
        final String a3 = getIntent().getStringExtra("Diastolic");
        final String a4 = getIntent().getStringExtra("Weight");

         TextView textView4= findViewById(R.id.textView4);
         textView4.setText(a);
       //RestAPIManager.getInstance().getUserToken(getIntent().getExtras().getString(a), getIntent().getExtras().getString(b), R.layout.activity_login);


        mGotoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(preMenuActivity.this, MainMenu.class);
            i.putExtra("username",getIntent().getStringExtra("email"));
            i.putExtra("Diastolic", a3);
            i.putExtra("Systolic", a2);
            i.putExtra("Points",a1);
            i.putExtra("Weight",a4);
            startActivity(i);
            }

        });
    }
}
