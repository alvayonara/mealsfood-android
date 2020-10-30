package com.alvayonara.mealsfood.core.data.source.local

import com.alvayonara.mealsfood.core.data.source.local.room.FoodDao
import androidx.lifecycle.LiveData
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodEntity
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val foodDao: FoodDao) {

    fun getListFood(): Flowable<List<FoodEntity>> = foodDao.getListFood()

    fun getFavoriteFood(): Flowable<List<FoodEntity>> = foodDao.getFavoriteFood()

    fun insertFood(foodList: List<FoodEntity>) = foodDao.insertFood(foodList)

    fun setFavoriteFood(food: FoodEntity, newState: Boolean) {
        food.isFavorite = newState
        foodDao.updateFavoriteFood(food)
    }
}