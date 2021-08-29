package com.zref.masakapa.detailfood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zref.model.Meal
import com.zref.repository.MealsRepository
import com.zref.repository.common.ResultSimple
import kotlinx.coroutines.launch

class DetailFoodViewModel(
    private val mealsRepository: MealsRepository
) : ViewModel() {
    val mealDetail = MutableLiveData<Meal>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun getMealDetail(id: Int) = viewModelScope.launch {
        isLoading.value = true
        when(val result = mealsRepository.getMealById(id)) {
            is ResultSimple.Success -> {
                mealDetail.value = result.data!!
                isLoading.value = false
            }
            is ResultSimple.Error -> {
                isLoading.value = false
                errorMessage.value = result.message
            }
        }
    }
}