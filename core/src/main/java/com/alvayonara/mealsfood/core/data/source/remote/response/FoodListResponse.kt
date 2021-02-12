package com.alvayonara.mealsfood.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodListResponse(
    @SerializedName("idMeal") var idMeal: String? = null,
    @SerializedName("strMeal") var strMeal: String? = null,
    @SerializedName("strMealThumb") var strMealThumb: String? = null
)

data class ListFoodResponse(
    @SerializedName("meals") val meals: List<FoodListResponse>? = null,
)