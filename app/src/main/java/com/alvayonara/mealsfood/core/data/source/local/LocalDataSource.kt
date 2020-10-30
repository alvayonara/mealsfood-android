package com.alvayonara.mealsfood.core.data.source.local

import com.alvayonara.mealsfood.core.data.source.local.room.FoodDao
import androidx.lifecycle.LiveData
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodEntity
import io.reactivex.Flowable

class LocalDataSource private constructor(private val foodDao: FoodDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(foodDao: FoodDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(foodDao)
            }
    }

    fun getListFood(): Flowable<List<FoodEntity>> = foodDao.getListFood()

    fun getFavoriteFood(): Flowable<List<FoodEntity>> = foodDao.getFavoriteFood()

    fun insertFood(foodList: List<FoodEntity>) = foodDao.insertFood(foodList)

    fun setFavoriteFood(food: FoodEntity, newState: Boolean) {
        food.isFavorite = newState
        foodDao.updateFavoriteFood(food)
    }
}