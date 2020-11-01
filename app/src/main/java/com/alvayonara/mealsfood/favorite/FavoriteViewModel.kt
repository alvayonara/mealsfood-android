package com.alvayonara.mealsfood.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class FavoriteViewModel @ViewModelInject constructor(foodUseCase: FoodUseCase) : ViewModel() {

    val favoriteFood = LiveDataReactiveStreams.fromPublisher(foodUseCase.getFavoriteFood())
}