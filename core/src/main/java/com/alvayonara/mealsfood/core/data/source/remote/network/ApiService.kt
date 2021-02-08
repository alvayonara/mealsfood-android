package com.alvayonara.mealsfood.core.data.source.remote.network

import com.alvayonara.mealsfood.core.data.source.remote.response.ListDetailResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.ListFoodResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("filter.php?c=Seafood")
    fun getListFood(
    ): Flowable<ListFoodResponse>

    @GET("lookup.php?")
    fun getFoodDetailById(
        @Query("i") foodId: String?,
    ): Flowable<ListDetailResponse>
}