package com.alfredo.android.a21pointsandroid.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.Weight;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIService;
import com.alfredo.android.a21pointsandroid.restapi.callback.WeightAPICallBack;

import java.util.ArrayList;

public class AddWeight extends AppCompatActivity implements WeightAPICallBack {
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

        Button mPostWeight = findViewById(R.id.button_post);

        mPostWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number_variable--;
                RestAPIManager.getInstance().postWeight(new Weight(("2020-04-12 11:08:00"),number_variable,LoginActivity.user),getContext());
                Toast.makeText(AddWeight.this, "Sent!",Toast.LENGTH_LONG).show();
            }
        });

    }

    public WeightAPICallBack getContext(){
        return this;
    }

    @Override
    public void onGetWeight(ArrayList<Weight> weight) {

    }

    @Override
    public void onPostWeight(Weight weight) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
