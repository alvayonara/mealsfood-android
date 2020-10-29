package com.alvayonara.mealsfood.core.di

import android.content.Context
import com.alvayonara.mealsfood.core.data.source.FoodRepository
import com.alvayonara.mealsfood.core.data.source.local.LocalDataSource
import com.alvayonara.mealsfood.core.data.source.local.room.FoodDatabase
import com.alvayonara.mealsfood.core.data.source.remote.RemoteDataSource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiConfig
import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository
import com.alvayonara.mealsfood.core.domain.usecase.FoodInteractor
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import com.alvayonara.mealsfood.core.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): IFoodRepository {
        val database = FoodDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.foodDao())
        val appExecutors = AppExecutors()

        return FoodRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideFoodUseCase(context: Context): FoodUseCase {
        val repository = provideRepository(context)

        return FoodInteractor(repository)
    }
}
