package com.alvayonara.mealsfood.core.data.source.local.room

import androidx.room.*
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodDetailEntity
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodListEntity
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodRecentSearchEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FoodDao {

    @Query("SELECT * FROM foodList")
    fun getListFood(): Flowable<List<FoodListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoodList(food: List<FoodListEntity>): Completable

    @Query("SELECT * FROM foodDetail")
    fun getFavoriteFood(): Flowable<List<FoodDetailEntity>>

    @Query("SELECT COUNT(*) FROM foodDetail WHERE idMeal=:idMeal")
    fun checkIsFavoriteFood(idMeal: String): Flowable<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteFood(food: FoodDetailEntity): Completable

    @Delete
    fun deleteFavoriteFood(food: FoodDetailEntity): Completable

    @Query("SELECT * FROM foodSearch WHERE id IN (SELECT MAX(id) FROM foodSearch GROUP BY strMeal) ORDER BY id DESC LIMIT 5")
    fun getRecentSearchFood(): Flowable<List<FoodRecentSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentSearchFood(recentSearch: FoodRecentSearchEntity): Completable
}
