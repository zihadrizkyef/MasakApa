package com.zref.repository.common

import com.zref.model.Category
import com.zref.model.Ingredient
import com.zref.model.Meal
import com.zref.network.response.MealsCategoryResponse
import com.zref.network.response.MealsListResponse
import io.realm.RealmList

fun MealsListResponse.toMealsEntity(): List<Meal> {
    val list = arrayListOf<Meal>()
    this.meals?.forEach {
        val meals = Meal(
            it!!.idMeal ?: "",
            it.strMeal ?: "",
            it.strMealThumb ?: "",
            it.strCategory ?: "",
            it.strArea ?: "",
            it.generateIngredients(),
            it.strInstructions ?: "",
            it.strYoutube ?: "",
            it.strDrinkAlternate ?: "",
            it.strCreativeCommonsConfirmed ?: "",
            it.strTags ?: "",
            it.strSource ?: "",
            it.strImageSource ?: "",
            it.dateModified ?: ""
        )
        list.add(meals)
    }
    return list
}

fun MealsListResponse.Meal.generateIngredients(): RealmList<Ingredient> {
    val ingredients = RealmList<Ingredient>()
    ingredients.add(Ingredient(this.strIngredient1 ?: "", this.strMeasure1 ?: ""))
    ingredients.add(Ingredient(this.strIngredient2 ?: "", this.strMeasure2 ?: ""))
    ingredients.add(Ingredient(this.strIngredient3 ?: "", this.strMeasure3 ?: ""))
    ingredients.add(Ingredient(this.strIngredient4 ?: "", this.strMeasure4 ?: ""))
    ingredients.add(Ingredient(this.strIngredient5 ?: "", this.strMeasure5 ?: ""))
    ingredients.add(Ingredient(this.strIngredient6 ?: "", this.strMeasure6 ?: ""))
    ingredients.add(Ingredient(this.strIngredient7 ?: "", this.strMeasure7 ?: ""))
    ingredients.add(Ingredient(this.strIngredient8 ?: "", this.strMeasure8 ?: ""))
    ingredients.add(Ingredient(this.strIngredient9 ?: "", this.strMeasure9 ?: ""))
    ingredients.add(Ingredient(this.strIngredient10 ?: "", this.strMeasure10 ?: ""))
    ingredients.add(Ingredient(this.strIngredient11 ?: "", this.strMeasure11 ?: ""))
    ingredients.add(Ingredient(this.strIngredient12 ?: "", this.strMeasure12 ?: ""))
    ingredients.add(Ingredient(this.strIngredient13 ?: "", this.strMeasure13 ?: ""))
    ingredients.add(Ingredient(this.strIngredient14 ?: "", this.strMeasure14 ?: ""))
    ingredients.add(Ingredient(this.strIngredient15 ?: "", this.strMeasure15 ?: ""))
    ingredients.add(Ingredient(this.strIngredient16 ?: "", this.strMeasure16 ?: ""))
    ingredients.add(Ingredient(this.strIngredient17 ?: "", this.strMeasure17 ?: ""))
    ingredients.add(Ingredient(this.strIngredient18 ?: "", this.strMeasure18 ?: ""))
    ingredients.add(Ingredient(this.strIngredient19 ?: "", this.strMeasure19 ?: ""))
    ingredients.add(Ingredient(this.strIngredient20 ?: "", this.strMeasure20 ?: ""))
    return ingredients
}

fun MealsCategoryResponse.toCategoriesEntity(): List<Category> {
    val list = arrayListOf<Category>()
    this.meals?.forEach {
        val category = Category()
        category.name = it?.strCategory?:""
        list.add(category)
    }
    return list
}