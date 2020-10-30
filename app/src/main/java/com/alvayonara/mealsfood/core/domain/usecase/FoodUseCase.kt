package com.alvayonara.mealsfood.core.domain.usecase

import androidx.lifecycle.LiveData
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food

interface FoodUseCase {

    fun getListFood(): LiveData<Resource<List<Food>>>

    fun getFoodDetailById(foodId: String): LiveData<List<Detail>>

    fun getFavoriteFood(): LiveData<List<Food>>

    fun setFavoriteFood(food: Food, state: Boolean)
}