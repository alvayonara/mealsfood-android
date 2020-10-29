package com.alvayonara.mealsfood.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodEntity

@Dao
interface FoodDao {

    @Query("SELECT * FROM food")
    fun getListFood(): LiveData<List<FoodEntity>>

    @Transaction
    @Query("SELECT * FROM food WHERE id = :foodId")
    fun getFoodDetailById(foodId: String): LiveData<List<FoodEntity>>

    @Query("SELECT * FROM food where isFavorite = 1")
    fun getFavoriteFood(): LiveData<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: List<FoodEntity>)

    @Update
    fun updateFood(food: FoodEntity)

    @Update
    fun updateFavoriteFood(food: FoodEntity)
}
