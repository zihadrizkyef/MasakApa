package com.zref.repository

import org.koin.dsl.module

val repositoryModule = module {

    single { MealsRepository(get(), get()) }

}