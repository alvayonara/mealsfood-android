package com.alvayonara.mealsfood.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class DetailFoodViewModel(private val foodUseCase: FoodUseCase) : ViewModel() {

    private val foodId = MutableLiveData<String>()

    fun setSelectedFood(foodId: String) {
        this.foodId.value = foodId
    }

    var foodDetail: LiveData<Resource<List<Food>>> =
        Transformations.switchMap(foodId) {
            foodUseCase.getFoodDetailById(it)
        }

    fun setFavoriteFood(food: Food, newStatus: Boolean) =
        foodUseCase.setFavoriteFood(food, newStatus)
}