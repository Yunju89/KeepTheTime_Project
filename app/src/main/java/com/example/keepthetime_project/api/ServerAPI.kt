package com.example.keepthetime_project.api

import android.content.Context
import android.webkit.DateSorter
import com.example.keepthetime_project.utils.ContextUtil
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ServerAPI {

    companion object{

        private var retrofit : Retrofit? = null
        private var BASE_URL = "https://keepthetime.xyz"

        fun apiList(context: Context) : APIList = getRetrofit(context).create(APIList::class.java)

        fun getRetrofit(context : Context) : Retrofit{

//            retrofit 객체 생성 전, 자동 토큰 첨부 세팅 interceptor

            val interceptor = Interceptor{
                with(it){
//                    기존 request에 헤더 추가

                    val newRequest = request().newBuilder()
                        .header("X-HTTP-Token", ContextUtil.getLoginUserToken(context))
                        .build()

                    proceed(newRequest)
                }
            }

//            인터셉트 활용하도록 세팅

            val myClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

//            Date 자료형으로 파싱 => String yyyy-MM-dd HH:mm:ss

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(
                    Date::class.java,    // Date 클래스로 파싱 요청
                    DateDeserializer()
                ).create()

            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))  // gson 라이브러리 결합, Date 파싱요청
                    .client(myClient)   // 인터셉터 부착해둔 클라이언트로 통신
                    .build()
            }



            return retrofit!!
        }



    }
}