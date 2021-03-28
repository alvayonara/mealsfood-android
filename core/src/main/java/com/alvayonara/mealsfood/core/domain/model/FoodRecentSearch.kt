package com.alvayonara.mealsfood.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodRecentSearch(
    val id: Int,
    val strMeal: String
) : Parcelable