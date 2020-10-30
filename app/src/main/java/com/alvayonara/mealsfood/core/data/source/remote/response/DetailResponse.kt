package com.alvayonara.mealsfood.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse (

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