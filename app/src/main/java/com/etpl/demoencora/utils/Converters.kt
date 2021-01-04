package com.etpl.demoencora.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
 @TypeConverter
 fun fromString(value: String?): List<String> {
  var listType: Type = object : TypeToken<List<String?>?>() {}.type
  return Gson().fromJson(value, listType)
 }

 @TypeConverter
 fun fromArrayList(list: List<String?>?): String {
  var gson = Gson()
  return gson.toJson(list)
 }

}

