package com.alvayonara.mealsfood.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListDetailResponse (
    @SerializedName("meals")
    val foods: List<DetailResponse>? = null,
)