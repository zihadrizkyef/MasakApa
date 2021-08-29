package com.zref.network

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single { getOkHttp(androidContext()) }
    single { getNetworkClient<MealsDataSource>(get()) }
    single { MealsNetwork(get()) }
}