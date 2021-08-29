package com.zref.cache

import io.realm.Realm
import org.koin.dsl.module

val cacheModule = module {
    single { getRealm() }
    single { MealsCache(get()) }
}