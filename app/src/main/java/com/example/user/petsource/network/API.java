package com.example.user.petsource.network;

import com.example.user.petsource.model.Login;
import com.example.user.petsource.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by USER on 01/11/2016.
 */

public interface API {

    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    @FormUrlEncoded
    @POST("users/login")
    Call<Login> logIn(@Field("username") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/")
    Call<User> register(@Field("username") String email, @Field("password") String password, @Field("name") String name,
                         @Field("phonenum") String phonenum);

    class Factory{

        public static final String BASE_URL = "http://192.168.32.1:2403/";
        public static API service;

        public static API getInstance(){
            if(service == null){
                Gson gson = new GsonBuilder().create();

                Retrofit retro =  new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson)).build();

                service = retro.create(API.class);
            }
            return service;
        }
    }
}
