package com.alvayonara.mealsfood.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiService
import com.alvayonara.mealsfood.core.data.source.remote.response.DetailResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.ListDetailResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.ListFoodResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getListFood(): LiveData<ApiResponse<List<FoodResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<FoodResponse>>>()

        // Get data from remote API
        val client = apiService.getListFood()

        client.enqueue(object : Callback<ListFoodResponse> {
            override fun onResponse(
                call: Call<ListFoodResponse>,
                response: Response<ListFoodResponse>
            ) {
                val dataArray = response.body()?.foods
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListFoodResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
            }
        })

        return resultData
    }

    fun getFoodDetailById(foodId: String): LiveData<List<DetailResponse>> {
        val resultData = MutableLiveData<List<DetailResponse>>()

        val client = apiService.getFoodDetailById(foodId)

        client.enqueue(object : Callback<ListDetailResponse> {
            override fun onResponse(
                call: Call<ListDetailResponse>,
                response: Response<ListDetailResponse>
            ) {
                val dataArray = response.body()?.foods
                resultData.value = dataArray
            }

            override fun onFailure(call: Call<ListDetailResponse>, t: Throwable) {
                resultData.value = null
            }
        })

        return resultData
    }
}

