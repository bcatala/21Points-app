package com.alfredo.android.a21pointsandroid.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.R;

public class AddWeight extends AppCompatActivity {
    private int number_variable = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addweight);

        final TextView ed = findViewById(R.id.editText1);
        ed.setText(String.valueOf(number_variable));

        Button mAddWeight = findViewById(R.id.add_weight_1);
        Button mAddWeight2 = findViewById(R.id.add_weight_2);

       mAddWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed.setText(String.valueOf(number_variable++));
            }
        });
        mAddWeight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed.setText(String.valueOf(number_variable--));
            }
        });

    }

}
