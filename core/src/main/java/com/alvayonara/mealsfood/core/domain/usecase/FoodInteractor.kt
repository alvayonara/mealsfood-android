package com.alvayonara.mealsfood.core.domain.usecase

import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository
import io.reactivex.Flowable

class FoodInteractor(private val foodRepository: IFoodRepository): FoodUseCase {

    override fun getListFood(): Flowable<Resource<List<Food>>> = foodRepository.getListFood()

    override fun getFoodDetailById(foodId: String): Flowable<List<Detail>> = foodRepository.getFoodDetailById(foodId)

    override fun getFavoriteFood(): Flowable<List<Food>> = foodRepository.getFavoriteFood()

    override fun setFavoriteFood(food: Food, state: Boolean) = foodRepository.setFavoriteFood(food, state)
}