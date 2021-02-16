package com.alvayonara.mealsfood.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodDetailEntity
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodListEntity
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodRecentSearchEntity

@Database(
    entities = [FoodListEntity::class, FoodDetailEntity::class, FoodRecentSearchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
}