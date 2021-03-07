package com.alvayonara.mealsfood.dashboard

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class DashboardViewModel(foodUseCase: FoodUseCase) : ViewModel() {

    val foodPopular = LiveDataReactiveStreams.fromPublisher(foodUseCase.getPopularFood())

    val foodCategory = LiveDataReactiveStreams.fromPublisher(foodUseCase.getListFood())
}