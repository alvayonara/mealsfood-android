package com.alvayonara.mealsfood.category

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.base.BaseViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase

class CategoryFoodViewModel(foodUseCase: FoodUseCase) : BaseViewModel() {

    private val strCategory = MutableLiveData<String>()

    fun setSelectedFoodCategory(strCategory: String) {
        this.strCategory.value = strCategory
    }

    val food = Transformations.switchMap(strCategory) {
        LiveDataReactiveStreams.fromPublisher(foodUseCase.getListFoodByCategory(it))
    }
}