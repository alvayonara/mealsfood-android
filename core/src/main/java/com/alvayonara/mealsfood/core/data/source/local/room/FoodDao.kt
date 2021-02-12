package com.alvayonara.mealsfood.core.data.source.local.room

import androidx.room.*
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodDetailEntity
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodListEntity
import com.alvayonara.mealsfood.core.domain.model.Food
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.concurrent.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM foodList")
    fun getListFood(): Flowable<List<FoodListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoodList(food: List<FoodListEntity>): Completable

    @Query("SELECT * FROM foodDetail")
    fun getFavoriteFood(): Flowable<List<FoodDetailEntity>>

    // "Use 'Exists' which returns either 0 or 1." -Ananthi
    // Source: https://stackoverflow.com/a/50270682
//    @Query("CASE WHEN EXISTS (SELECT 1 FROM foodDetail WHERE idMeal =:idMeal) THEN TRUE ELSE FALSE AS bool")
//    @Query("SELECT EXISTS(SELECT * FROM foodDetail WHERE idMeal=:idMeal)")
    @Query("SELECT COUNT(*) FROM foodDetail WHERE idMeal=:idMeal")
    fun checkIsFavoriteFood(idMeal: String): Flowable<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteFood(food: FoodDetailEntity): Completable

    @Delete
    fun deleteFavoriteFood(food: FoodDetailEntity): Completable
}
