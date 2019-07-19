package com.alfredo.android.a21pointsandroid.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;
import com.alfredo.android.a21pointsandroid.restapi.callback.PointsAPICallBack;

public class AddPoints extends AppCompatActivity implements PointsAPICallBack {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpoints);

        user = getUserInfo(getIntent().getStringExtra("user"));



        Button mPostPointsButton = (Button) findViewById(R.id.post_points);
        mPostPointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postPoints();
            }
        });

    }

    private void postPoints(){
        java.util.Date fecha = new java.util.Date();
        RestAPIManager.getInstance().postPoints(new Points( fecha.toString(),1,0,1, "Guille treballa", user), this);
    }
    private synchronized User getUserInfo(String user) {
        String[] elements = user.split("/");

        return new User(Integer.valueOf(elements[0]), elements[1], elements[2], elements[3], elements[4], Boolean.getBoolean(elements[5]), elements[6], "ignore");
    }

    @Override
    public void onPostPoints(Points points) {

        Log.d("21Points", "onPostPoints OK " + points.getId());

        RestAPIManager.getInstance().getPointsById(this,"aaa", user.getId());

    }

    @Override
    public synchronized void onGetPoints(Points points) {

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

}
