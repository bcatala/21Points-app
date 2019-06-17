package com.alfredo.android.a21pointsandroid.restapi;

import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.model.Blood;
import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.model.Weight;
import com.alfredo.android.a21pointsandroid.restapi.callback.BloodApiCallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.RegisterAPICallback;
import com.alfredo.android.a21pointsandroid.model.UserData;
import com.alfredo.android.a21pointsandroid.model.UserToken;
import com.alfredo.android.a21pointsandroid.restapi.callback.LoginAPICallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.PointsAPICallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.UserAPICallBack;
import com.alfredo.android.a21pointsandroid.restapi.callback.WeightApiCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIManager {

    //private static final String BASE_URL = "http://" + "your_ip:8080/";
    private static final String BASE_URL = "http://" + "android.byted.xyz/";
    private static RestAPIManager ourInstance;
    private Retrofit retrofit;
    private RestAPIService restApiService;
    private UserToken userToken;


    public static RestAPIManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new RestAPIManager();
        }
        return ourInstance;
    }

    private RestAPIManager() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restApiService = retrofit.create(RestAPIService.class);

    }

    public synchronized void getUserToken(final String username, String password, final LoginAPICallBack restAPICallBack) {
        UserData userData = new UserData(username, password);
        Call<UserToken> call = restApiService.requestToken(userData);


        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {

                if (response.isSuccessful()) {
                    userToken = response.body();
                    restAPICallBack.onLoginSuccess(userToken);
                } else {
                    restAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                restAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void postPoints(Points points, final PointsAPICallBack pointsAPICallBack) {
        final Points newUserPoints = points;
        Call<Points> call = restApiService.postPoints(newUserPoints, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<Points>() {
            @Override
            public void onResponse(Call<Points> call, Response<Points> response) {

                if (response.isSuccessful()) {
                    pointsAPICallBack.onPostPoints(response.body());
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }

    public  synchronized void getUserInfo(final UserAPICallBack userAPICallBack){
        Call<User> call = restApiService.getUserInfo("Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userAPICallBack.onGetUserInfo(response.body());
                } else {
                    userAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userAPICallBack.onFailure(t);
            }
        });
    }


    public synchronized void getPointsById( Integer id , final PointsAPICallBack pointsAPICallBack) {
        Call<Points> call = restApiService.getPointsById(id, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<Points>() {
            @Override
            public void onResponse(Call<Points> call, Response<Points> response) {

                if (response.isSuccessful()) {
                    pointsAPICallBack.onGetPoints(response.body());
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void register(String username, String email, String password, final RegisterAPICallback registerAPICallback) {
        UserData userData = new UserData(username, email, password);
        Call<Void> call = restApiService.register(userData);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    registerAPICallback.onSuccess();
                } else {
                    registerAPICallback.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registerAPICallback.onFailure(t);
            }
        });
    }

    public synchronized void getBlood(Integer id , final BloodApiCallBack bloodAPICallBack) {
        Call<Blood> call = restApiService.getBlood(id, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<Blood>() {
            @Override
            public void onResponse(Call<Blood> call, Response<Blood> response) {

                if (response.isSuccessful()) {
                    bloodAPICallBack.onGetBlood(response.body());
                } else {
                    bloodAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Blood> call, Throwable t) {
                bloodAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void getWeight(Integer id , final WeightApiCallBack weightAPICallBack) {
        Call<Weight> call = restApiService.getWeight(id, "Bearer " + userToken.getIdToken());

        call.enqueue(new Callback<Weight>() {
            @Override
            public void onResponse(Call<Weight> call, Response<Weight> response) {

                if (response.isSuccessful()) {
                    weightAPICallBack.onGetWeight(response.body());
                } else {
                    weightAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Weight> call, Throwable t) { weightAPICallBack.onFailure(t); }
        });
    }
}