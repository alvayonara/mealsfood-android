package com.alvayonara.mealsfood.di

import com.alvayonara.mealsfood.area.AreaFoodViewModel
import com.alvayonara.mealsfood.category.CategoryFoodViewModel
import com.alvayonara.mealsfood.core.domain.usecase.FoodInteractor
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import com.alvayonara.mealsfood.dashboard.DashboardViewModel
import com.alvayonara.mealsfood.detail.DetailFoodViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val useCaseModule = module {
    factory<FoodUseCase> { FoodInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { DetailFoodViewModel(get()) }
    viewModel { CategoryFoodViewModel(get()) }
    viewModel { AreaFoodViewModel(get()) }
}