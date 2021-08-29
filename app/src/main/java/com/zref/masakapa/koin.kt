package com.zref.masakapa

import com.zref.masakapa.listfood.ListFoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ListFoodViewModel(get()) }
}