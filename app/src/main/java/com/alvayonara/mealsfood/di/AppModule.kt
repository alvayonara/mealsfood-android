package com.alvayonara.mealsfood.di

import com.alvayonara.mealsfood.core.domain.usecase.FoodInteractor
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideFoodUseCase(foodInteractor: FoodInteractor): FoodUseCase
}