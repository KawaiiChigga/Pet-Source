package com.petsource.network;

import com.petsource.model.Info;
import com.petsource.model.Login;
import com.petsource.model.Pet;
import com.petsource.model.Rescue;
import com.petsource.model.Shop;
import com.petsource.model.Transaction;
import com.petsource.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
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

    @GET("pets/")
    Call<List<Pet>> getPets(@Query("userid") String userid);

    @GET("infouser/")
    Call<List<Info>> checkAccount(@Query("userid") String userid);

    @GET("pets/{id}")
    Call<Pet> getPet(@Path("id") String id);

    @GET("shop/")
    Call<List<Shop>> getSalon(@Query("isCare") int isCare);

    @GET("shop/")
    Call<List<Shop>> getSalonStaff(@Query("iduser") String idUser);

    @GET("shop/")
    Call<List<Shop>> getCare(@Query("isCare") int isCare);

    @GET("shop/")
    Call<List<Shop>> getStaff(@Query("iduser") String iduser, @Query("isCare") int isCare);

    @GET("rescue/")
    Call<List<Rescue>> getRescue();

    @GET("rescue/{id}")
    Call<Rescue> getRescueUser(@Path("id") String id);

    @GET("transaction/{id}")
    Call<Transaction> getATrans(@Path("id") String id);

    @GET("transaction/")
    Call<List<Transaction>> getTransUser(@Query ("iduser") String iduser);

    @GET("transaction/")
    Call<List<Transaction>> getTransShop(@Query ("idshop") String idshop);

    @FormUrlEncoded
    @POST("shop/")
    Call<Shop> addShop(@Field("iduser") String uid, @Field("startdate") String startdate, @Field("starttime") String starttime,
                       @Field("enddate") String enddate, @Field("endtime") String endtime, @Field("latitude") double latitude,
                       @Field("longitude") double longitude, @Field("isCare") int isCare, @Field("price") String price);

    @FormUrlEncoded
    @POST("rescue/")
    Call<Rescue> addRescue(@Field("petid") String petid, @Field("userid") String userid, @Field("latitude") double latitude,
                       @Field("longitude") double longitude, @Field("description") String description);

    @FormUrlEncoded
    @PUT("pets/{id}")
    Call<Pet> updatePet(@Path("id") String id, @Field("userid") String userid);

    @FormUrlEncoded
    @POST("infouser/")
    Call<Info> registerAccount(@Field("userid") String uid, @Field("name") String name, @Field("email") String email,
                               @Field("url") String url, @Field("joindate") String date,  @Field("isStaff") int isStaff,
                               @Field("isApprove") int isApprove);

    @FormUrlEncoded
    @PUT("infouser/{id}")
    Call<Info> updateAccount(@Path("id") String id, @Field("address") String address, @Field("city") String city,
                             @Field("birthday") String birthday, @Field("job") String job, @Field("isStaff") int isStaff,
                             @Field("isApprove") int isApprove);

    @FormUrlEncoded
    @POST("pets/")
    Call<Pet> registerPet(@Field("name") String name, @Field("birthdate") String birthdate, @Field("race") String race,
                          @Field("userid") String userid, @Field("isMale") int isMale, @Field("isDog") int isDog,
                          @Field("isCertified") int isCertified);

    @FormUrlEncoded
    @PUT("shop/{id}")
    Call<Shop> updateShop(@Path("id") String id, @Field("startdate") String startdate, @Field("starttime") String starttime,
                          @Field("enddate") String enddate, @Field("endtime") String endtime, @Field("latitude") double latitude,
                          @Field("longitude") double longitude, @Field("price") String price);

    @FormUrlEncoded
    @POST("transaction/")
    Call<Transaction> addTrans (@Field("iduser") String iduser, @Field("idpet") String idpet, @Field("date") String date,
                                @Field("idshop") String idshop, @Field("price") String price, @Field("type") int type,
                                @Field("isWashing") int isWashing, @Field("isNailclipping") int isNailclipping,
                                @Field("isTrimming") int isTrimming, @Field("startCare") String startCare,
                                @Field("endCare") String endCare, @Field("status") String status);

    @FormUrlEncoded
    @PUT("transaction/{id}")
    Call<Transaction> updateTrans (@Path("id")  String id, @Field("status") String status);

    @DELETE("transaction/{id}")
    Call<ResponseBody> delTrans (@Path("id") String id);

    @DELETE("rescue/{id}")
    Call<ResponseBody> delRescue (@Path("id") String id);

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
