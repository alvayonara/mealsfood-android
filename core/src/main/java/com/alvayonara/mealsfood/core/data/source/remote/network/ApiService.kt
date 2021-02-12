package com.alvayonara.mealsfood.core.data.source.remote.network

import com.alvayonara.mealsfood.core.data.source.remote.response.ListFoodDetailResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.ListFoodResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("filter.php?")
    fun getListFood(
        @Query("c") strCategory: String
    ): Flowable<ListFoodResponse>

    @GET("filter.php?")
    fun getListFoodByArea(
        @Query("a") strArea: String
    ): Flowable<ListFoodResponse>

    @GET("lookup.php?")
    fun getFoodDetailById(
        @Query("i") idMeal: String?,
    ): Flowable<ListFoodDetailResponse>
}