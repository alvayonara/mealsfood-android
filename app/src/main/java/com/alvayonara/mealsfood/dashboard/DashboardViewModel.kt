package com.alvayonara.mealsfood.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class DashboardViewModel @ViewModelInject constructor(foodUseCase: FoodUseCase): ViewModel() {

    val food = LiveDataReactiveStreams.fromPublisher(foodUseCase.getListFood())
}