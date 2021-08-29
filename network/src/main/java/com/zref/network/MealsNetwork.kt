package com.zref.network

import com.zref.network.response.MealsListResponse

class MealsNetwork(private val mealsDataSource: MealsDataSource) {
    suspend fun getHomeList(): MealsListResponse =
        mealsDataSource.getAllMeals()

    suspend fun searchMeals(search: String): MealsListResponse =
        mealsDataSource.searchMeals(search)
}