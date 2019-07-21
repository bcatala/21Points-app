package com.alfredo.android.a21pointsandroid.restapi;

import com.alfredo.android.a21pointsandroid.model.Blood;
import com.alfredo.android.a21pointsandroid.model.User;
import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.model.UserData;
import com.alfredo.android.a21pointsandroid.model.UserToken;
import com.alfredo.android.a21pointsandroid.model.Weight;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPIService {

    @POST("/api/points")
    Call<Points> postPoints(@Body Points points, @Header("Authorization") String token);
    @GET("/api/points/{id}")
        Call<Points> getPointsById(@Path("id") Integer id, @Header("Authorization") String token );
    @GET("/api/points")
    Call<ArrayList<Points>> getPoints(@Header("Authorization") String token );


    @POST("/api/authenticate")
    Call<UserToken> requestToken(@Body UserData userData);
    @POST("/api/register")
    Call<Void> register(@Body UserData userData);


    @GET("/api/account")
    Call<User> getUserInfo(@Header("Authorization") String token);
    @GET("/api/account")
    Call<User> getUserAccount(@Header("Authorization") String token);


    @GET("/api/blood-pressures")
    Call<ArrayList<Blood>> getBlood( @Header("Authorization") String token );
    @POST("/api/blood-pressures")
    Call<Blood> postBlood(@Header("Authorization") String token, @Body Blood blood);


    @GET("/api/weights")
    Call<ArrayList<Weight>> getWeight(@Header("Authorization") String token );
    @POST("/api/blood-pressures")
    Call<Blood> postWeight(@Header("Authorization") String token, @Body Weight weight);
}