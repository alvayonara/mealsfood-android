package com.alvayonara.mealsfood.core.domain.repository

import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.model.FoodRecentSearch
import io.reactivex.Flowable

interface IFoodRepository {

    fun getPopularFood(): Flowable<Resource<List<Food>>>

    fun getListFoodByCategory(strCategory: String): Flowable<Resource<List<Food>>>

    fun getListFoodByArea(strArea: String): Flowable<Resource<List<Food>>>

    fun getFoodDetailById(idMeal: String): Flowable<Resource<List<Food>>>

    fun searchFood(meal: String): Flowable<ApiResponse<List<Food>>>

    fun getFavoriteFood(): Flowable<List<Food>>

    fun checkIsFavoriteFood(idMeal: String): Flowable<Int>

    fun insertFavoriteFood(food: Food)

    fun deleteFavoriteFood(food: Food)

    fun getRecentSearchFood(): Flowable<List<FoodRecentSearch>>

    fun insertRecentSearchFood(search: FoodRecentSearch)
}