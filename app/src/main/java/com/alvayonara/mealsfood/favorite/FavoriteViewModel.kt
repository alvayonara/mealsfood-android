package com.alvayonara.mealsfood.favorite

import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class FavoriteViewModel(foodUseCase: FoodUseCase) : ViewModel() {

    val favoriteFood = foodUseCase.getFavoriteFood()
}