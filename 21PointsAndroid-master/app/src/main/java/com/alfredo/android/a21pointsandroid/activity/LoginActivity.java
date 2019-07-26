package com.alfredo.android.a21pointsandroid.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.model.Blood;
import com.alfredo.android.a21pointsandroid.model.PointsWeek;
import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.model.Weight;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIService;
import com.alfredo.android.a21pointsandroid.restapi.callback.BloodApiCallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.LoginAPICallBack;
import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.restapi.callback.PointsAPICallBack;
import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;
import com.alfredo.android.a21pointsandroid.model.UserToken;
import com.alfredo.android.a21pointsandroid.restapi.callback.UserAPICallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.WeightAPICallBack;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements WeightAPICallBack,LoginAPICallBack, PointsAPICallBack, UserAPICallBack,BloodApiCallBack {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private Button GotoMenu;
    public static ArrayList<Points> points;
    public static ArrayList<Blood> bloodArray;
    public static ArrayList<Weight> weightsarray;
    public Points points3;
    private String email;
    private String password;
    public static User user;
    public static String token;
    public static PointsWeek pointsWeek;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //Intent intent = new Intent(LoginActivity.this, preMenuActivity.class);
            //intent.putExtra("email", email);
            //intent.putExtra("c", password);


            //startActivity(intent);
            this.email=email;
            this.password=password;

            //RestAPIManager.getInstance().getUserInfo(user);
            //RestAPIManager.getInstance().getPointsById(5, this);
            RestAPIManager.getInstance().getUserToken(email, password, this);
            // setContentView(R.layout.activity_premenu);
            //RestAPIManager.getInstance().getUserAccount(this, token);

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.length() > 3;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 3;
    }

    @Override
    public void onGetPoints(ArrayList<Points> points2) {

        //Log.d("21Points", "onGetPoints OK " + points.getId());



        points = points2;


       RestAPIManager.getInstance().getBlood(1,this);

        new AlertDialog.Builder(this)
                .setTitle("Points")
                .setMessage(points.toString())
                .show();
    }

    @Override
    public void onGetPointsWeek(PointsWeek points) {

        this.pointsWeek = points;
        Intent i = new Intent(LoginActivity.this, preMenuActivity.class);

        i.putExtra("email", this.email);
        i.putExtra("c", this.password);
        i.putExtra("user", this.user.convertString());

        startActivity(i);
    }

    @Override
    public void onPostPoints(Points points) {

        Log.d("21Points", "onPostPoints OK " + points.getId());

        //RestAPIManager.getInstance().getPointsById(this, points.getId());

    }

    @Override
    public void onLoginSuccess(UserToken userToken) {


        Log.d("21Points", "onLoginSuccess OK " + userToken.getIdToken());

        new AlertDialog.Builder(this)
                .setTitle("Token")
                .setMessage("token: "+ userToken.getIdToken())

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



        //RestAPIManager.getInstance().getUserInfo();

        token = userToken.getIdToken();

        RestAPIManager.getInstance().getUserAccount(this, token);

        //RestAPIManager.getInstance().postPoints(new Points("4419-03-14",1,1,1), this);


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
    public void onGetUser(User body){
        this.user = body;
        System.out.println("HOLA");
        RestAPIManager.getInstance().getPoints(this,token,1);
       // RestAPIManager.getInstance().getPointsById(this, user.getId());
        //RestAPIManager.getInstance().getBlood(user.getId(), this);

    }

    @Override
    public void onGetUserInfo(User body){

    }

    public void onPostBlood(Blood blood){

    }
    public void onGetBlood(ArrayList<Blood> blood){

        bloodArray=blood;

        RestAPIManager.getInstance().getWeight(this);


    }

    @Override
    public void onGetWeight(ArrayList<Weight> weight) {


        weightsarray=weight;



        RestAPIManager.getInstance().getPointsByWeek("2019-07-26", this);



    }

    @Override
    public void onPostWeight(Weight weight) {

    }
}

