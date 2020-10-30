package com.alvayonara.mealsfood.di

import com.alvayonara.mealsfood.core.di.CoreComponent
import com.alvayonara.mealsfood.dashboard.DashboardFragment
import com.alvayonara.mealsfood.detail.DetailFoodActivity
import com.alvayonara.mealsfood.favorite.FavoriteFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: DashboardFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailFoodActivity)
}