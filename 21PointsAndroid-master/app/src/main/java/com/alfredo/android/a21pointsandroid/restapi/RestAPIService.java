package com.alfredo.android.a21pointsandroid.restapi;

import com.alfredo.android.a21pointsandroid.model.Points;
import com.alfredo.android.a21pointsandroid.model.UserData;
import com.alfredo.android.a21pointsandroid.model.UserToken;

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
    Call<Points> getPointsById(@Path("id") Integer id, @Header("Authorization") String token);
    @POST("/api/authenticate")
    Call<UserToken> requestToken(@Body UserData userData);
    @POST("/api/register")
    Call<Void> register(@Body UserData userData);
}