package com.zref.network.response


import com.google.gson.annotations.SerializedName

data class MealsCategoryResponse(
    @SerializedName("meals")
    var meals: List<Meal?>?
) {
    data class Meal(
        @SerializedName("strCategory")
        var strCategory: String?
    )
}