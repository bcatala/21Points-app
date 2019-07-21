package com.alfredo.android.a21pointsandroid.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.model.UserToken;
import com.alfredo.android.a21pointsandroid.restapi.callback.LoginAPICallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.PointsAPICallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.RegisterAPICallback;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;
import com.alfredo.android.a21pointsandroid.restapi.callback.UserAPICallBack;

import java.util.ArrayList;


public class MainMenu extends AppCompatActivity implements PointsAPICallBack, UserAPICallBack {
    // UI references.
    private User user;
    private Points points2;
    private Intent i;

    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        user = getUserInfo(getIntent().getStringExtra("user"));

        //RestAPIManager.getInstance().postPoints(new Points("4110-03-14",1,0,1, "alfredoaprova", user), this);

        TextView textView4= findViewById(R.id.logged_name);
        textView4.setText("You are logged in as " + getIntent().getStringExtra("username"));

        //RestAPIManager.getInstance().getPointsById(user.getId(),this);

        TextView tvPoints = (TextView) findViewById(R.id.points);
        Integer suma=LoginActivity.points.get(LoginActivity.points.size()-1).getAlcohol()+LoginActivity.points.get(LoginActivity.points.size()-1).getAlcohol()+
        LoginActivity.points.get(LoginActivity.points.size()-1).getAlcohol();

        tvPoints.setText("Points: " + suma.toString());

        ProgressBar progressBarPoints = (ProgressBar) findViewById(R.id.ProgressPoint);
        progressBarPoints.setProgress((suma/3)*100);

        TextView tvblood = (TextView) findViewById(R.id.blood);
        String string2 = "Blood:   \nSystolic: " + LoginActivity.bloodArray.get(LoginActivity.points.size()-1).getSystolic()
                + "\n Distolic: "+ LoginActivity.bloodArray.get(LoginActivity.points.size()-1).getDiastolic();

        tvblood.setText(string2 );



        // Set up the mainMenu form.
        Button mAddPointsButton = (Button) findViewById(R.id.add_points_button);
        mAddPointsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainMenu.this, AddPoints.class);
                putdata(i);
                startActivity(i);
            }
        });

        Button mAddBloodButton = (Button) findViewById(R.id.add_blood);
        mAddBloodButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainMenu.this, AddBlood.class);
               // putdata(i);
                startActivity(i);
            }
        });

        Button mAddWeightButton = (Button) findViewById(R.id.add_weight_button);
        mAddWeightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, AddWeight.class);
                startActivity(i);
            }
        });
    }

    private void putdata(Intent i) {
        i.putExtra("user", this.user.convertString());
    }

    private synchronized User getUserInfo(String user) {
        String[] elements = user.split("/");

        return new User(Integer.valueOf(elements[0]), elements[1], elements[2], elements[3], elements[4], Boolean.getBoolean(elements[5]), elements[6], "ignore");
    }

    @Override
    public void onPostPoints(Points points) {

        Log.d("21Points", "onPostPoints OK " + points.getId());

       // RestAPIManager.getInstance().getPointsById(this,LoginActivity.token, user.getId());

    }

    @Override
    public synchronized void onGetPoints(ArrayList<Points> points) {

        Log.d("21Points", "onGetPoints OK " + points2.getId());

        this.points2 = points.get(0);

        new AlertDialog.Builder(this)
                .setTitle("Points")
                .setMessage(points.toString())
                .show();
    }

    @Override
    public void onFailure(Throwable t) {

        Log.d("21Points", "onFailure OK " + t.getMessage());


        new AlertDialog.Builder(this)
                .setTitle("Token Error")
                .setMessage(t.getMessage())

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onGetUserInfo(User body){

    }

    @Override
    public void onGetUser(User body){

    }
}
