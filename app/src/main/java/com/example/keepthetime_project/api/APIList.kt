package com.example.keepthetime_project.api

import com.example.keepthetime_project.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface APIList {

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email : String,
        @Field("password") pw : String
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignup(
        @Field("email") email: String,
        @Field("password") pw : String,
        @Field("nick_name") nickName : String
    ) : Call<BasicResponse>

    @GET("/user/check")
    fun getRequestUserCheck(
        @Query("type") type : String,
        @Query("value") email : String
    ) : Call<BasicResponse>

    @GET("/user")
    fun getRequestMyInfo(
    ) : Call<BasicResponse>

    @GET("/user/friend")
    fun getRequestFriendList(
        @Query("type") type : String
    ) : Call<BasicResponse>


    @GET("/search/user")
    fun getRequestSearchUser(
        @Query("nickname") nickname : String
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user/friend")
    fun postRequestAddFriend(
        @Field("user_id") userId : Int
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user/friend")
    fun putRequestAcceptOrDenyFriendRequest(
        @Field("user_id") userId : Int,
        @Field("type") type: String
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/appointment")
    fun postRequestAddAppointment(
        @Field("title") title : String,
        @Field("datetime") datetime : String,
        @Field("place") place : String,
        @Field("latitude") latitude : Double,
        @Field("longitude") longitude : Double,
    ) : Call<BasicResponse>

    @GET("/appointment")
    fun getRequestMyAppointment(
    ) : Call<BasicResponse>


}