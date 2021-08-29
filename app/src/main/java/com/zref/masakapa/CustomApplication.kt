package com.zref.masakapa

import android.app.Application
import com.zref.cache.cacheModule
import com.zref.network.networkModule
import com.zref.repository.repositoryModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CustomApplication)
            modules(
                appModule,
                cacheModule,
                networkModule,
                repositoryModule
            )
        }

        Realm.init(this)
    }
}