package com.alvayonara.mealsfood.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodResponse(

    @SerializedName("idMeal")
    var foodId: String? = null,

    @SerializedName("strMeal")
    var name: String? = null,

    @SerializedName("strMealThumb")
    var thumb: String? = null
)

data class ListFoodResponse(
    @SerializedName("meals")
    val foods: List<FoodResponse>? = null,
)