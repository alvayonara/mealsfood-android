package com.alvayonara.mealsfood.core.data.source.remote.network

import com.alvayonara.mealsfood.core.data.source.remote.response.FoodResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.ListDetailResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.ListFoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/json/v1/1/filter.php?c=Seafood")
    fun getListFood(
    ): Call<ListFoodResponse>

    @GET("api/json/v1/1/lookup.php?")
    fun getFoodDetailById(
        @Query("i") foodId: String?,
    ): Call<ListDetailResponse>
}