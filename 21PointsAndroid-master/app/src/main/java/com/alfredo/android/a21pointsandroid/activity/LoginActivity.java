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
import com.alfredo.android.a21pointsandroid.model.Weight;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIService;
import com.alfredo.android.a21pointsandroid.restapi.callback.LoginAPICallBack;
import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.restapi.callback.PointsAPICallBack;
import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.restapi.RestAPIManager;
import com.alfredo.android.a21pointsandroid.model.UserToken;
import com.alfredo.android.a21pointsandroid.restapi.callback.UserAPICallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.BloodApiCallBack;

import retrofit2.Call;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginAPICallBack, PointsAPICallBack,BloodApiCallBack {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private Button GotoMenu;
    private Points points;
    private String email;
    private String password;
    private Blood blood;
    private Weight weight;
    private String Weight;
    private String Diastolic;
    private String Systolic;
    private Integer id;


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

        }
    }

    @Override
    public void onGetBlood(Blood blood) {

        Log.d("21Points", "onGetBloos OK " + blood.getId());

        this.blood = blood;
        this.id=blood.getId();
        this.Diastolic=blood.getDiastolic().toString();
        this.Systolic=blood.getSystolic().toString();

        Intent i = new Intent(LoginActivity.this, preMenuActivity.class);

        i.putExtra("email", this.email);
        i.putExtra("c", this.password);
        i.putExtra("Diastolic", this.Diastolic);
        i.putExtra("Systolic", this.Systolic);
        startActivity(i);

        new AlertDialog.Builder(this)
                .setTitle("Blood")
                .setMessage(blood.toString())
                .show();
    }

    //@Override
    public void onGetWeight(Weight weight) {

        Log.d("21Points", "onGetWeight OK " + weight.getId());

        this.weight = weight;
        this.id=weight.getId();
        this.Weight=weight.getWeight().toString();

        Intent i = new Intent(LoginActivity.this, preMenuActivity.class);

        i.putExtra("email", this.email);
        i.putExtra("c", this.password);
        i.putExtra("Weight", this.Weight);
        startActivity(i);

        new AlertDialog.Builder(this)
                .setTitle("Weight")
                .setMessage(weight.toString())
                .show();
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
    public void onGetPoints(Points points) {

        Log.d("21Points", "onGetPoints OK " + points.getId());

        this.points = points;

        new AlertDialog.Builder(this)
                .setTitle("Points")
                .setMessage(points.toString())
                .show();
    }
    @Override
    public void onPostBlood(Blood blood) {

        //Log.d("21Points", "onPostPoints OK " + points.getId());

       // RestAPIManager.getInstance().getPointsById(points.getId(), this);

    }

    @Override
    public void onPostPoints(Points points) {

        Log.d("21Points", "onPostPoints OK " + points.getId());

        RestAPIManager.getInstance().getPointsById(points.getId(), this);

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


        RestAPIManager.getInstance().postPoints(new Points("2019-03-14",1,1,1), this);
        RestAPIManager.getInstance().getBlood(4677,this);



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

