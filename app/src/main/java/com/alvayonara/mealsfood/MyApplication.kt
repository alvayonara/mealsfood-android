package com.alvayonara.mealsfood

import android.app.Application
import com.alvayonara.mealsfood.core.BuildConfig
import com.alvayonara.mealsfood.core.di.databaseModule
import com.alvayonara.mealsfood.core.di.networkModule
import com.alvayonara.mealsfood.core.di.repositoryModule
import com.alvayonara.mealsfood.di.useCaseModule
import com.alvayonara.mealsfood.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}