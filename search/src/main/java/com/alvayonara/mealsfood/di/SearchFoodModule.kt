package com.alvayonara.mealsfood.di

import com.alvayonara.mealsfood.search.SearchFoodViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchFoodModule = module {
    viewModel { SearchFoodViewModel(get()) }
}