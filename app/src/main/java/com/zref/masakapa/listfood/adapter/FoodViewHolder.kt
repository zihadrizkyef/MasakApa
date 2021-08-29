package com.zref.masakapa.listfood.adapter

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zref.core.Extra
import com.zref.masakapa.R
import com.zref.masakapa.databinding.ItemFoodBinding
import com.zref.model.Meal
import kotlin.random.Random

class FoodViewHolder(
    private val binding: ItemFoodBinding,
    private val navController: NavController
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(meal: Meal) = with(binding) {
        Glide.with(imageView)
            .load(meal.image)
            .into(imageView)

        textTitle.text = meal.name
        textCategory.text = meal.category
        ratingBar.rating = meal.rating ?: 0F

        root.setOnClickListener {
            navigateToDetail(meal)
        }
    }

    private fun navigateToDetail(meal: Meal) {
        val arguments = Bundle()
        arguments.putString(Extra.ID, meal.id)
        navController.navigate(
            R.id.listFoodFragment_to_detailFoodFragment,
            arguments
        )
    }
}