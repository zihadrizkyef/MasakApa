package com.zref.cache

import io.realm.Realm
import io.realm.RealmConfiguration

fun getRealm(): Realm {
    val config = RealmConfiguration.Builder().build()
    return Realm.getInstance(config)
}