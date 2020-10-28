package com.alvayonara.mealsfood

import com.google.gson.annotations.SerializedName

data class FoodsEntity(

    @SerializedName("meals")
    val foods: List<FoodEntity>? = null,
)