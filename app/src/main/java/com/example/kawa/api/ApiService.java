package com.example.kawa.api;

import com.example.kawa.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api")
    Call<UserResponse> apiGetUserResponse(@Query("inc") String inc, @Query("results") String resultCount);

}
