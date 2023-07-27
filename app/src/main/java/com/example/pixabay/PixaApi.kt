package com.example.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getPictures(
        @Query("q") keyWord: String,
        @Query("key") key: String = "38435203-7e5f69eb1ef973ec984e52b96",
        @Query("per_page") perPage: Int = 3,
        @Query("page") page: Int
    ) : Call<PixaModel>
}