package com.alvayonara.mealsfood.core.domain.usecase

import androidx.lifecycle.LiveData
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository

class FoodInteractor(private val foodRepository: IFoodRepository): FoodUseCase {

    override fun getListFood(): LiveData<Resource<List<Food>>> = foodRepository.getListFood()

    override fun getFoodDetailById(foodId: String): LiveData<List<Detail>> = foodRepository.getFoodDetailById(foodId)

    override fun getFavoriteFood(): LiveData<List<Food>> = foodRepository.getFavoriteFood()

    override fun setFavoriteFood(food: Food, state: Boolean) = foodRepository.setFavoriteFood(food, state)
}