package com.zref.masakapa.listfood.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zref.masakapa.databinding.ItemFoodBinding
import com.zref.model.Meal
import kotlin.random.Random

class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(meal: Meal) = with(binding) {
        Glide.with(imageView)
            .load(meal.image)
            .into(imageView)

        textTitle.text = meal.name
        textCategory.text = meal.category
        ratingBar.rating = Random.nextDouble(5.0).toFloat()
    }
}