package com.zref.network

import com.zref.network.response.MealsCategoryResponse
import com.zref.network.response.MealsListResponse

class MealsNetwork(private val mealsDataSource: MealsDataSource) {
    suspend fun getHomeList(): MealsListResponse =
        mealsDataSource.getAllMeals()

    suspend fun searchMeals(search: String): MealsListResponse =
        mealsDataSource.searchMeals(search)

    suspend fun getMealsCategory(): MealsCategoryResponse =
        mealsDataSource.getCategoryList()
}