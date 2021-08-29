package com.zref.cache

import com.zref.core.Sort
import com.zref.model.Meal
import io.realm.Case
import io.realm.Realm
import io.realm.Sort as RealmSort

class MealsCache(private val realm: Realm) {

    fun getAllMeals(): List<Meal> {
        val result = realm.where(Meal::class.java).findAll()
        return realm.copyFromRealm(result)
    }

    fun saveMeals(meals: List<Meal>) = with(realm) {
        beginTransaction()
        copyToRealmOrUpdate(meals)
        commitTransaction()
    }

    fun searchMeals(
        search: String = "",
        category: String = "",
        sort: Sort = Sort.ASCENDING
    ): List<Meal> {
        val query = realm.where(Meal::class.java)
        if (search.isNotEmpty()) {
            query.contains("name", search, Case.INSENSITIVE)
        }
        if (category.isNotEmpty()) {
            query.contains("category", category, Case.INSENSITIVE)
        }
        val realmSort = if (sort == Sort.ASCENDING) RealmSort.ASCENDING else RealmSort.DESCENDING
        query.sort("name", realmSort)

        val result = query.findAll()
        return realm.copyFromRealm(result)
    }
}