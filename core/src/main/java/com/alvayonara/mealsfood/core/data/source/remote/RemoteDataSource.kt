package com.alvayonara.mealsfood.core.data.source.remote

import android.annotation.SuppressLint
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiService
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodListResponse
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.utils.ConstFood.Companion.CATEGORY_BEEF
import com.alvayonara.mealsfood.core.utils.DataMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getListFood(): Flowable<ApiResponse<List<FoodListResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<FoodListResponse>>>()

        // Get data from remote API
        // Default: Category Beef
        val client = apiService.getListFood(CATEGORY_BEEF)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.meals!!
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getListFoodByCategory(strCategory: String): Flowable<Resource<List<Food>>> {
        val resultData = PublishSubject.create<Resource<List<Food>>>()

        // Get data from remote API
        val client = apiService.getListFood(strCategory)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.meals!!
                resultData.onNext(Resource.Success(DataMapper.mapFoodListResponsesToDomain(dataArray)))
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getListFoodByArea(strArea: String): Flowable<Resource<List<Food>>> {
        val resultData = PublishSubject.create<Resource<List<Food>>>()

        // Get data from remote API
        val client = apiService.getListFoodByArea(strArea)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.meals!!
                resultData.onNext(Resource.Success(DataMapper.mapFoodListResponsesToDomain(dataArray)))
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getFoodDetailById(idMeal: String): Flowable<Resource<List<Food>>> {
        val resultData = PublishSubject.create<Resource<List<Food>>>()

        val client = apiService.getFoodDetailById(idMeal)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.meals!!
                resultData.onNext(
                    Resource.Success(
                        DataMapper.mapFoodDetailResponsesToDomain(
                            dataArray
                        )
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun searchFood(strMeal: String): Flowable<ApiResponse<List<Food>>> {
        val resultData = PublishSubject.create<ApiResponse<List<Food>>>()

        // Get data from remote API
        val client = apiService.searchFood(strMeal)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.meals!!
                resultData.onNext(
                    ApiResponse.Success(
                        DataMapper.mapFoodListResponsesToDomain(
                            dataArray
                        )
                    )
                )
            }, { error ->
                // When search result not found, the json result -> "meals": null
                // Return as null not as List<FoodListResponse> so it will throw to error subscribe
                if (error.message == null)
                    resultData.onNext(ApiResponse.Empty)
                else
                    resultData.onNext(ApiResponse.Error(error.message.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}

