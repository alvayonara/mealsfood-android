package com.alvayonara.mealsfood.core.data.source.local

import com.alvayonara.mealsfood.core.data.source.local.entity.FoodDetailEntity
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodListEntity
import com.alvayonara.mealsfood.core.data.source.local.entity.FoodRecentSearchEntity
import com.alvayonara.mealsfood.core.data.source.local.room.FoodDao
import io.reactivex.Flowable

class LocalDataSource(private val foodDao: FoodDao) {

    fun getListFood(): Flowable<List<FoodListEntity>> = foodDao.getListFood()

    fun insertFoodList(foodList: List<FoodListEntity>) = foodDao.insertFoodList(foodList)

    fun getFavoriteFood(): Flowable<List<FoodDetailEntity>> = foodDao.getFavoriteFood()

    fun checkIsFavoriteFood(idMeal: String): Flowable<Int> = foodDao.checkIsFavoriteFood(idMeal)

    fun insertFavoriteFood(food: FoodDetailEntity) = foodDao.insertFavoriteFood(food)

    fun deleteFavoriteFood(food: FoodDetailEntity) = foodDao.deleteFavoriteFood(food)

    fun getRecentSearchFood(): Flowable<List<FoodRecentSearchEntity>> =
        foodDao.getRecentSearchFood()

    fun insertRecentSearchFood(search: FoodRecentSearchEntity) =
        foodDao.insertRecentSearchFood(search)
}