package com.zref.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Meal(
    @SerializedName("idMeal") @PrimaryKey var id: String? = null,
    @SerializedName("meal") var name: String? = null,
    @SerializedName("mealThumb") var image: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("area") var area: String? = null,
    @SerializedName("ingredient") var ingredients: RealmList<Ingredient>? = null,
    @SerializedName("instructions") var instructions: String? = null,
    @SerializedName("youtube") var youtube: String? = null,
    @SerializedName("drinkAlternate") var drinkAlternate: String? = null,
    @SerializedName("creativeCommonsConfirmed") var creativeCommonsConfirmed: String? = null,
    @SerializedName("tags") var tags: String? = null,
    @SerializedName("source") var source: String? = null,
    @SerializedName("imageSource") var imageSource: String? = null,
    @SerializedName("dateModified") var dateModified: String? = null
) : RealmObject()