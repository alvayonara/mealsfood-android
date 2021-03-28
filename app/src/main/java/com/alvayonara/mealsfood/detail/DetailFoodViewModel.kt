package com.alvayonara.mealsfood.detail

import androidx.lifecycle.*
import com.alvayonara.mealsfood.core.base.BaseViewModel
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class DetailFoodViewModel(private val foodUseCase: FoodUseCase) : BaseViewModel() {

    private val idMeal = MutableLiveData<String>()

    fun setSelectedIdMeal(idMeal: String) {
        this.idMeal.value = idMeal
    }

    var foodDetail = Transformations.switchMap(idMeal) {
        LiveDataReactiveStreams.fromPublisher(foodUseCase.getFoodDetailById(it))
    }

    var checkIsFoodFavorite = Transformations.switchMap(idMeal) {
        LiveDataReactiveStreams.fromPublisher(foodUseCase.checkIsFavoriteFood(it))
    }

    fun insertFavoriteFood(food: Food) = foodUseCase.insertFavoriteFood(food)

    fun deleteFavoriteFood(food: Food) = foodUseCase.deleteFavoriteFood(food)
}