package com.example.user.petsource.network;

import com.example.user.petsource.model.Login;
import com.example.user.petsource.model.Pet;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by USER on 01/11/2016.
 */

public interface API {

    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    @GET("pets/")
    Call<List<Pet>> getPets(@Query("userid") String userid);

    @GET("pets/{id}")
    Call<Pet> getPet(@Path("id") String id);

    @FormUrlEncoded
    @POST("users/login")
    Call<Login> logIn(@Field("username") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/")
    Call<User> register(@Field("username") String email, @Field("password") String password, @Field("name") String name,
                         @Field("phonenum") String phonenum);

    @FormUrlEncoded
    @PUT("users/{id}")
    Call<User> updateAccount(@Path("id") String userid, @Field("isStaff") int isStaff, @Field("isApprove") int isApprove,
                             @Field("joinDate") String joinDate, @Field("address") String address, @Field("city") String city,
                             @Field("birthday") String birthday, @Field("job") String job);

    @FormUrlEncoded
    @POST("pets/")
    Call<Pet> registerPet(@Field("name") String name, @Field("birthdate") String birthdate, @Field("race") String race,
                          @Field("userid") String userid, @Field("isMale") int isMale, @Field("isDog") int isDog,
                          @Field("isCertified") int isCertified);

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
