package com.alvayonara.mealsfood.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListFoodResponse(
    @SerializedName("meals")
    val foods: List<FoodResponse>? = null,
)