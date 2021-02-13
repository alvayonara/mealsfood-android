package com.alvayonara.mealsfood.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class FavoriteViewModel(private val foodUseCase: FoodUseCase) : ViewModel() {

    val favoriteFood = LiveDataReactiveStreams.fromPublisher(foodUseCase.getFavoriteFood())
}