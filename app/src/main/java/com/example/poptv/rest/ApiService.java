package com.example.poptv.rest;

import com.example.poptv.model.TvModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("popular?api_key=0dde3e9896a8c299d142e214fcb636f8&language=en-US&page=1") //endpoint
    Call<TvModel> getData();
}