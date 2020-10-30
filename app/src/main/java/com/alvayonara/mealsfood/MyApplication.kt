package com.alvayonara.mealsfood

import android.app.Application
import com.alvayonara.mealsfood.core.di.CoreComponent
import com.alvayonara.mealsfood.core.di.DaggerCoreComponent
import com.alvayonara.mealsfood.di.AppComponent
import com.alvayonara.mealsfood.di.DaggerAppComponent

open class MyApplication: Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}