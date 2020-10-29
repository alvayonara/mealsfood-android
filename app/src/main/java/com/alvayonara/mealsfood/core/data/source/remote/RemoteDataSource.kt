package com.alvayonara.mealsfood.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiService
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodResponse
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

    fun getFoodDetailById(foodId: String): LiveData<ApiResponse<List<FoodResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<FoodResponse>>>()

        val client = apiService.getFoodDetailById(foodId)

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
}

