package com.alvayonara.mealsfood.core.data.source.local

import com.alvayonara.mealsfood.core.data.source.local.room.FoodDao
import androidx.lifecycle.LiveData
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodEntity

class LocalDataSource private constructor(private val foodDao: FoodDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(foodDao: FoodDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(foodDao)
            }
    }

    fun getListFood(): LiveData<List<FoodEntity>> = foodDao.getListFood()

    fun getFoodDetailById(foodId: String): LiveData<List<FoodEntity>> =
        foodDao.getFoodDetailById(foodId)

    fun getFavoriteFood(): LiveData<List<FoodEntity>> = foodDao.getFavoriteFood()

    fun insertFood(foodList: List<FoodEntity>) = foodDao.insertFood(foodList)

    fun updateFood(food: FoodEntity) = foodDao.updateFood(food)

    fun setFavoriteFood(food: FoodEntity, newState: Boolean) {
        food.isFavorite = newState
        foodDao.updateFavoriteFood(food)
    }
}