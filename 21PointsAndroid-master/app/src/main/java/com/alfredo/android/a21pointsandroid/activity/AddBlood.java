package com.alfredo.android.a21pointsandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.Blood;
import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;
import com.alfredo.android.a21pointsandroid.restapi.callback.BloodApiCallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.PointsAPICallBack;

import java.util.ArrayList;

public class AddBlood extends AppCompatActivity implements BloodApiCallBack {

        private User user;
        private String notes;
        private int systolic,dystolic;
        public Blood bloodaux;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_addblood);

            Button postblood = (Button) findViewById(R.id.add_blood);
            postblood.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    bloodaux.setDiastolic(2);
                    bloodaux.setSystolic(2);
                    bloodaux.setUser(user);
                    RestAPIManager.getInstance().postBlood(bloodaux,getContext());
                }
            });




            //user = getUserInfo(getIntent().getStringExtra("user"));

           // final TextView tvNotes= findViewById(R.id.points_notes);

            //final CheckBox checkBoxExercise = findViewById(R.id.exercise_flag);
            //final CheckBox checkBoxMeals = findViewById(R.id.meals_flag);
           // final CheckBox checkBoxAlcohol = findViewById(R.id.alcohol_flag);

           // Button mPostPointsButton = (Button) findViewById(R.id.post_points);
           // mPostPointsButton.setOnClickListener(new View.OnClickListener() {
               // @Override
                //public void onClick(View view) {

                 //   getPoints(checkBoxExercise, checkBoxMeals, checkBoxAlcohol, tvNotes);
                  /// }
            //7/});

        }

    private BloodApiCallBack getContext() {
            return this;
    }

    @Override
    public void onPostBlood(Blood blood) {



    }

    @Override
    public void onGetBlood(ArrayList<Blood> points) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
