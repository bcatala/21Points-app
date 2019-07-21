package com.alfredo.android.a21pointsandroid.restapi;

import android.widget.TextView;

import com.alfredo.android.a21pointsandroid.R;
import com.alfredo.android.a21pointsandroid.activity.LoginActivity;
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
import com.alfredo.android.a21pointsandroid.restapi.callback.WeightAPICallBack;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIManager {

    //private static final String BASE_URL = "http://" + "your_ip:8080/";
    private static final String BASE_URL = "http://android.byted.xyz/";
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


    /*public synchronized void getPointsById(  final PointsAPICallBack pointsAPICallBack,String token, Integer id) {
        Call<Points> call = restApiService.getPointsById(id,"Bearer " + LoginActivity.token);

        call.enqueue(new Callback<Points>() {
            @Override
            public void onResponse(Call<Points> call, Response<Points> response) {
                int a = 1;

                if (response.isSuccessful()) {
                    //pointsAPICallBack.onGetPoints(response.body());
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Points> call, Throwable t) {
                pointsAPICallBack.onFailure(t);
            }
        });
    }*/

    public synchronized void getPoints(  final PointsAPICallBack pointsAPICallBack,String token, Integer id) {
       Call<ArrayList<Points>> call = restApiService.getPoints( "Bearer " + LoginActivity.token);

        call.enqueue(new Callback<ArrayList<Points>>() {
            @Override
            public void onResponse(Call<ArrayList<Points>> call, Response<ArrayList<Points>> response) {
                int a = 1;

                if (response.isSuccessful()) {
                    pointsAPICallBack.onGetPoints(response.body());
                } else {
                    pointsAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Points>> call, Throwable t) {
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

    public synchronized void getUserAccount(final UserAPICallBack userAPICallBack, String token) {
        User user = new User();
        Call<User> call = restApiService.getUserAccount("Bearer " + LoginActivity.token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    userAPICallBack.onGetUser(response.body());

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

 /*   public synchronized void getPoints(final PointsAPICallBack pointsAPICallBack, String token) {
        Call<Points> call = restApiService.getPoints("Bearer " + userToken.getIdToken());

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
    }*/

    public synchronized void getBlood(Integer id , final BloodApiCallBack bloodAPICallBack) {
        Call<ArrayList<Blood>> call = restApiService.getBlood( "Bearer " + LoginActivity.token);

        call.enqueue(new Callback<ArrayList<Blood>>() {
            @Override
            public void onResponse(Call<ArrayList<Blood>> call, Response<ArrayList<Blood>> response) {

                if (response.isSuccessful()) {
                    bloodAPICallBack.onGetBlood(response.body());
                } else {
                    bloodAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Blood>> call, Throwable t) {
                bloodAPICallBack.onFailure(t);
            }
        });
    }

    public synchronized void postBlood (Blood blood, final BloodApiCallBack bloodAPICallBack) {
        final Blood newUserBlood = blood;
        Call<Blood> call = restApiService.postBlood( "Bearer " + LoginActivity.token ,blood);

        call.enqueue(new Callback<Blood>() {
            @Override
            public void onResponse(Call<Blood> call, Response<Blood> response) {

                if (response.isSuccessful()) {
                    bloodAPICallBack.onPostBlood(response.body());
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


    public synchronized void getWeight( final WeightAPICallBack WeightAPICallBack) {
        Call<ArrayList<Weight>> call = restApiService.getWeight( "Bearer " + LoginActivity.token);

        call.enqueue(new Callback<ArrayList<Weight>>() {
            @Override
            public void onResponse(Call<ArrayList<Weight>> call, Response<ArrayList<Weight>> response) {

                if (response.isSuccessful()) {
                    WeightAPICallBack.onGetWeight(response.body());
                } else {
                    WeightAPICallBack.onFailure(new Throwable("ERROR " + response.code() + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Weight>> call, Throwable t) {
                WeightAPICallBack.onFailure(t);
            }
        });
    }


}