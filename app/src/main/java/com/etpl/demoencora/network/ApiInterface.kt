package com.etpl.demoencora.network

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("search?q=title")
    fun getDNAData(): Call<JsonObject>

    @Multipart
    @POST("updateUserImage")
    fun updateUserImage(
        @Part("user_id") user_id: RequestBody,
        @Part profile_image: MultipartBody.Part
    ): Call<JsonObject>

}
