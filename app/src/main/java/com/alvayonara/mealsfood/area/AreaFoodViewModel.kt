package com.alvayonara.mealsfood.area

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.base.BaseViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class AreaFoodViewModel(foodUseCase: FoodUseCase) : BaseViewModel() {

    private val strArea = MutableLiveData<String>()

    fun setSelectedFoodArea(strArea: String) {
        this.strArea.value = strArea
    }

    val food = Transformations.switchMap(strArea) {
        LiveDataReactiveStreams.fromPublisher(foodUseCase.getListFoodByArea(it))
    }
}