package com.zref.masakapa.listfood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zref.core.Sort
import com.zref.model.Meal
import com.zref.repository.MealsRepository
import com.zref.repository.common.ResultSimple
import kotlinx.coroutines.launch

class ListFoodViewModel(
    private val mealsRepository: MealsRepository
): ViewModel() {
    val listFood = MutableLiveData<List<Meal>?>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val categoryList = MutableLiveData<List<String>?>()

    init {
        viewModelScope.launch {
            isLoading.value = true
            when(val result = mealsRepository.getHomeMeals()) {
                is ResultSimple.Success -> {
                    isLoading.value = false
                    listFood.value = result.data
                }
                is ResultSimple.Error -> {
                    isLoading.value = false
                    errorMessage.value = result.message
                }
            }

            when(val result = mealsRepository.getCategories()) {
                is ResultSimple.Success -> {
                    categoryList.value = result.data
                }
            }
        }
    }

    fun findMeals(
        search: String = "",
        category: String = "",
        sort: Sort = Sort.ASCENDING
    ) = viewModelScope.launch {
        isLoading.value = true
        when(val result = mealsRepository.findMeals(search, category, sort)) {
            is ResultSimple.Success -> {
                isLoading.value = false
                listFood.value = result.data
            }
            is ResultSimple.Error -> {
                isLoading.value = false
                errorMessage.value = result.message
            }
        }
    }
}