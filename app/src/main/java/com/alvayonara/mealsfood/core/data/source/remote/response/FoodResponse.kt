package com.alvayonara.mealsfood.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("idMeal")
    var id: String? = null,

    @SerializedName("strMeal")
    var name: String? = null,

    @SerializedName("strMealThumb")
    var thumb: String? = null,

    @SerializedName("strCategory")
    var category: String? = null,

    @SerializedName("strArea")
    var area: String? = null,

    @SerializedName("strTags")
    var tags: String? = null,

    @SerializedName("strInstructions")
    var instructions: String? = null,

    @SerializedName("strYoutube")
    var youtube: String? = null
)