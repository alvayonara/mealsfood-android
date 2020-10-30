package com.alvayonara.mealsfood.di

import com.alvayonara.mealsfood.core.domain.usecase.FoodInteractor
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideFoodUseCase(foodInteractor: FoodInteractor): FoodUseCase
}