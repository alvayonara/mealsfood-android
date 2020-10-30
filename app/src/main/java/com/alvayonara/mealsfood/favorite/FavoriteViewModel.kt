package com.alvayonara.mealsfood.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(foodUseCase: FoodUseCase) : ViewModel() {

    val favoriteFood = LiveDataReactiveStreams.fromPublisher(foodUseCase.getFavoriteFood())
}