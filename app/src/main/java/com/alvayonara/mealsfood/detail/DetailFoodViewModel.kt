package com.alvayonara.mealsfood.detail

import androidx.lifecycle.*
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class DetailFoodViewModel(private val foodUseCase: FoodUseCase) : ViewModel() {

    private val foodId = MutableLiveData<String>()

    fun setSelectedFood(foodId: String) {
        this.foodId.value = foodId
    }

    var foodDetail: LiveData<List<Detail>> =
        Transformations.switchMap(foodId) {
            LiveDataReactiveStreams.fromPublisher(foodUseCase.getFoodDetailById(it))
        }

    fun setFavoriteFood(food: Food, newStatus: Boolean) =
        foodUseCase.setFavoriteFood(food, newStatus)
}