package com.alvayonara.mealsfood.di

import com.alvayonara.mealsfood.favorite.FavoriteViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}