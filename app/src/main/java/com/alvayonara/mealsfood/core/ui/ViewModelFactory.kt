package com.alvayonara.mealsfood.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.mealsfood.core.di.Injection
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import com.alvayonara.mealsfood.dashboard.DashboardViewModel
import com.alvayonara.mealsfood.detail.DetailFoodViewModel
import com.alvayonara.mealsfood.favorite.FavoriteViewModel

class ViewModelFactory private constructor(private val foodUseCase: FoodUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideFoodUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(foodUseCase) as T
            }
            modelClass.isAssignableFrom(DetailFoodViewModel::class.java) -> {
                DetailFoodViewModel(foodUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(foodUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}