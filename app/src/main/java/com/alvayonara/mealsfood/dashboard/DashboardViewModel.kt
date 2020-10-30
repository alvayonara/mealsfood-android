package com.alvayonara.mealsfood.dashboard

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class DashboardViewModel @Inject constructor(foodUseCase: FoodUseCase): ViewModel() {

    val food = LiveDataReactiveStreams.fromPublisher(foodUseCase.getListFood())
}