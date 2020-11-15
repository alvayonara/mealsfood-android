package com.alvayonara.mealsfood.core.data.source.local

import com.alvayonara.mealsfood.core.data.source.local.entity.FoodEntity
import com.alvayonara.mealsfood.core.data.source.local.room.FoodDao
import io.reactivex.Flowable

class LocalDataSource(private val foodDao: FoodDao) {

    fun getListFood(): Flowable<List<FoodEntity>> = foodDao.getListFood()

    fun getFavoriteFood(): Flowable<List<FoodEntity>> = foodDao.getFavoriteFood()

    fun insertFood(foodList: List<FoodEntity>) = foodDao.insertFood(foodList)

    fun setFavoriteFood(food: FoodEntity, newState: Boolean) {
        food.isFavorite = newState
        foodDao.updateFavoriteFood(food)
    }
}