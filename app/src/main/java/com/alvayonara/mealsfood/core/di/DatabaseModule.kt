package com.alvayonara.mealsfood.core.di

import android.content.Context
import androidx.room.Room
import com.alvayonara.mealsfood.core.data.source.local.room.FoodDao
import com.alvayonara.mealsfood.core.data.source.local.room.FoodDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): FoodDatabase = Room.databaseBuilder(
        context,
        FoodDatabase::class.java, "Food.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideFoodDao(database: FoodDatabase): FoodDao = database.foodDao()
}