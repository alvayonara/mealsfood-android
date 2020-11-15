package com.alvayonara.mealsfood.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiService
import com.alvayonara.mealsfood.core.data.source.remote.response.DetailResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getListFood(): Flowable<ApiResponse<List<FoodResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<FoodResponse>>>()

        // Get data from remote API
        val client = apiService.getListFood()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.foods!!
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getFoodDetailById(foodId: String): Flowable<List<DetailResponse>> {
        val resultData = PublishSubject.create<List<DetailResponse>>()

        val client = apiService.getFoodDetailById(foodId)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.foods!!
                resultData.onNext(dataArray)
            }, { error ->
                resultData.onNext(emptyList())
                Log.e("Error getFoodDetailById", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}

