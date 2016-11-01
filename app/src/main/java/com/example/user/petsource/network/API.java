package com.example.user.petsource.network;

import com.example.user.petsource.users.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by USER on 01/11/2016.
 */

public interface API {

    @GET("users?$sort[username]={sort}")
    Call<List<User>> getUser(@Query("sort") int sort);


    class Factory{

        public static final String BASE_URL = "http://10.0.2.2:2403/";
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
