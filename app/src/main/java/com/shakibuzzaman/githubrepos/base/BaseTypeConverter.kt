package com.shakibuzzaman.githubrepos.base

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class BaseTypeConverter<T : Any> {
    private val gson = Gson()

    @TypeConverter
    fun stringToAny(data: String?): T {
        if (data.isNullOrEmpty() || data.equals("null", ignoreCase = true))
            return Any() as T
        val anyType = object : TypeToken<T>() {}.type
        return gson.fromJson<T>(data, anyType)
    }

    @TypeConverter
    fun anyToString(any: T?): String {
        return gson.toJson(any)
    }
}