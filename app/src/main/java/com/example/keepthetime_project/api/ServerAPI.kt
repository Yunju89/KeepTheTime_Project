package com.example.keepthetime_project.api

import android.content.Context
import com.example.keepthetime_project.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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


            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(myClient)   // 인터셉터 부착해둔 클라이언트로 통신
                    .build()
            }



            return retrofit!!
        }



    }
}