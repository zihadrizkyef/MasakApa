package com.zref.repository

import com.zref.cache.MealsCache
import com.zref.core.Sort
import com.zref.core.extension.debugPrintStackTrace
import com.zref.model.Category
import com.zref.model.Meal
import com.zref.network.MealsNetwork
import com.zref.repository.common.ResultSimple
import com.zref.repository.common.toCategoriesEntity
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
            val meals = cache.getAllMeals()
            if (meals.isNotEmpty()) {
                ResultSimple.Success(meals)
            } else {
                e.debugPrintStackTrace()
                ResultSimple.Error("Gagal mengambil data")
            }
        }

    fun findMeals(
        search: String = "",
        category: String = "",
        sort: Sort
    ): ResultSimple<List<Meal>> {
        return ResultSimple.Success(cache.getMeals(search, category, sort))
    }

    suspend fun getCategories(): ResultSimple<List<String>> =
        try {
            val response = network.getMealsCategory()
            val category = response.toCategoriesEntity()
            cache.saveCategory(category)
            ResultSimple.Success(category.map { it.name })
        } catch (e: Exception) {
            val categories = cache.getCategories()
            if (categories.isNotEmpty()) {
                ResultSimple.Success(categories.map { it.name })
            } else {
                e.debugPrintStackTrace()
                ResultSimple.Error("Gagal mengambil data")
            }
        }
}