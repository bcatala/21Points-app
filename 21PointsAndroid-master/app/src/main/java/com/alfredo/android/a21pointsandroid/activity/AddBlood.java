package com.alfredo.android.a21pointsandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.restapi.callback.PointsAPICallBack;

public class AddBlood extends AppCompatActivity{

        private User user;
        private String notes;
        private int systolic,dystolic;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_addblood);

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
}
