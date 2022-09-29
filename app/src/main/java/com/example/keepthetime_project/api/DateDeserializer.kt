package com.example.keepthetime_project.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

// String => Date 변환 방법 명시 클래스
class DateDeserializer : JsonDeserializer<Date> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {

//        String => Date 변환
        val dateStr = json?.asString
        val simpleDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = simpleDate.parse(dateStr)  // string 타입을 date 형태로

        return date!!
    }

}