package com.alvayonara.mealsfood.core.di

import android.content.Context
import androidx.room.Room
import com.alvayonara.mealsfood.core.data.source.local.room.FoodDao
import com.alvayonara.mealsfood.core.data.source.local.room.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FoodDatabase = Room.databaseBuilder(
        context,
        FoodDatabase::class.java, "Food.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideFoodDao(database: FoodDatabase): FoodDao = database.foodDao()
}