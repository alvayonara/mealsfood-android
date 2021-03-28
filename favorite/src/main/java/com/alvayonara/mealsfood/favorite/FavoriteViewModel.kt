package com.alvayonara.mealsfood.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.base.BaseViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class FavoriteViewModel(foodUseCase: FoodUseCase) : BaseViewModel() {

    val favoriteFood = LiveDataReactiveStreams.fromPublisher(foodUseCase.getFavoriteFood())
}