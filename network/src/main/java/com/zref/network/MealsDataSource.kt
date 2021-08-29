package com.zref.network

import com.zref.network.response.MealsListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MealsDataSource {
    @GET("search.php?s=")
    suspend fun getAllMeals(): MealsListResponse

    @GET("search.php")
    suspend fun searchMeals(
        @Query("s") search: String
    ): MealsListResponse
}