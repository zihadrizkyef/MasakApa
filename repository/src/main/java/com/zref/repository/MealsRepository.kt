package com.zref.repository

import com.zref.cache.MealsCache
import com.zref.core.Sort
import com.zref.core.extension.debugPrintStackTrace
import com.zref.model.Meal
import com.zref.network.MealsNetwork
import com.zref.repository.common.ResultSimple
import com.zref.repository.common.toMealsEntity

class MealsRepository(
    private val cache: MealsCache,
    private val network: MealsNetwork
) {
    suspend fun getHomeMeals(): ResultSimple<List<Meal>> =
        try {
            val response = network.getHomeList()
            val meals = response.toMealsEntity()
            cache.saveMeals(meals)
            ResultSimple.Success(meals)
        } catch (e: Exception) {
            e.debugPrintStackTrace()
            ResultSimple.Error("Gagal mengambil data")
        }

    fun findMeals(
        search: String = "",
        category: String = "",
        sort: Sort
    ): ResultSimple<List<Meal>> {
        return ResultSimple.Success(cache.searchMeals(search, category, sort))
    }
}