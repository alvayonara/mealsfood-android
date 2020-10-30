package com.alvayonara.mealsfood.core.data.source.local.room

import androidx.room.*
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FoodDao {

    @Query("SELECT * FROM food")
    fun getListFood(): Flowable<List<FoodEntity>>

    @Query("SELECT * FROM food where isFavorite = 1")
    fun getFavoriteFood(): Flowable<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: List<FoodEntity>) : Completable

    @Update
    fun updateFavoriteFood(food: FoodEntity)
}
