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



}