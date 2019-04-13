package com.alfredo.android.a21pointsandroid.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.restapi.callback.RegisterAPICallback;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;


public class MainMenu extends AppCompatActivity {
    // UI references.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        // Set up the mainMenu form.
        Button mAddPointsButton = (Button) findViewById(R.id.add_points_button);
        mAddPointsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initiates the activity
            }
        });
    }
}
