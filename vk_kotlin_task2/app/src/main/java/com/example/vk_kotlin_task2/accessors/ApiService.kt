package com.example.vk_kotlin_task2.accessors

import com.example.vk_kotlin_task2.models.CatModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("cats")
    suspend fun getCats(
        @Query("tags") tags: String = "gif",
        @Query("limit") limit: Int = 5,
        @Query("skip") skip: Int = 0
    ): List<CatModel>
}