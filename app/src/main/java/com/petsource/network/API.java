package com.petsource.network;

import com.petsource.model.Info;
import com.petsource.model.Login;
import com.petsource.model.Pet;
import com.petsource.model.Rescue;
import com.petsource.model.Shop;
import com.petsource.model.User;
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

//    @GET("users/{id}")
//    Call<User> getUser(@Path("id") String id);

    @GET("pets/")
    Call<List<Pet>> getPets(@Query("userid") String userid);

    @GET("infouser/")
    Call<List<Info>> checkAccount(@Query("userid") String userid);

    @GET("pets/{id}")
    Call<Pet> getPet(@Path("id") String id);

    @GET("shop/")
    Call<List<Shop>> getSalon();

    @GET("shop/")
    Call<List<Shop>> getSalon(@Query("iduser") String iduser);

    @GET("rescue/")
    Call<List<Rescue>> getRescue();

//    @FormUrlEncoded
//    @POST("users/login")
//    Call<Login> logIn(@Field("username") String email, @Field("password") String password);

//    @FormUrlEncoded
//    @POST("users/")
//    Call<User> register(@Field("username") String email, @Field("password") String password, @Field("name") String name,
//                         @Field("phonenum") String phonenum);

    @FormUrlEncoded
    @POST("shop/")
    Call<Shop> addShop(@Field("userid") String uid, @Field("startdate") String startdate, @Field("starttime") String starttime,
                       @Field("enddate") String enddate, @Field("endtime") String endtime, @Field("latitude") double latitude,
                       @Field("longitude") double longitude, @Field("isCare") int isCare, @Field("price") String price);

    @FormUrlEncoded
    @POST("infouser/")
    Call<Info> registerAccount(@Field("userid") String uid, @Field("name") String name, @Field("email") String email,
                               @Field("url") String url, @Field("joindate") String date,  @Field("isStaff") int isStaff,
                               @Field("isApprove") int isApprove);

    @FormUrlEncoded
    @PUT("users/")
    Call<Info> updateAccount(@Query("userid") String userid, @Field("address") String address, @Field("city") String city,
                             @Field("birthday") String birthday, @Field("job") String job, @Field("isStaff") int isStaff,
                             @Field("isApprove") int isApprove);

    @FormUrlEncoded
    @POST("pets/")
    Call<Pet> registerPet(@Field("name") String name, @Field("birthdate") String birthdate, @Field("race") String race,
                          @Field("userid") String userid, @Field("isMale") int isMale, @Field("isDog") int isDog,
                          @Field("isCertified") int isCertified);

    class Factory{

        public static final String BASE_URL = "https://petsource.herokuapp.com/";
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
