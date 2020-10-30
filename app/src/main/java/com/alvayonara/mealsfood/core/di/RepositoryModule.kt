package com.alvayonara.mealsfood.core.di

import com.alvayonara.mealsfood.core.data.source.FoodRepository
import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(foodRepository: FoodRepository): IFoodRepository
}