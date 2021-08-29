package com.zref.masakapa.listfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.zref.masakapa.databinding.ItemFoodBinding
import com.zref.model.Meal

class FoodAdapter(
    private val navController: NavController,
    private val list: ArrayList<Meal>
) : RecyclerView.Adapter<FoodViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodBinding.inflate(inflater, parent, false)
        return FoodViewHolder(binding, navController)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(list[position])
    }
}