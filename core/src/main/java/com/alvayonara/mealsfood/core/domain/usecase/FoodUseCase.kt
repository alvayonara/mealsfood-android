package com.alvayonara.mealsfood.core.domain.usecase

import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Food
import io.reactivex.Flowable

interface FoodUseCase {

    fun getListFood(): Flowable<Resource<List<Food>>>

    fun getListFoodByCategory(category: String): Flowable<Resource<List<Food>>>

    fun getListFoodByArea(area: String): Flowable<Resource<List<Food>>>

    fun getFoodDetailById(foodId: String): Flowable<Resource<List<Food>>>

    fun getFavoriteFood(): Flowable<List<Food>>

    fun checkIsFavoriteFood(idMeal: String): Flowable<Int>

    fun insertFavoriteFood(food: Food)

    fun deleteFavoriteFood(food: Food)
}