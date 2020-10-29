package com.alvayonara.mealsfood.dashboard

import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class DashboardViewModel(foodUseCase: FoodUseCase): ViewModel() {

    val food = foodUseCase.getListFood()
}