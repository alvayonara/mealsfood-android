package com.alvayonara.mealsfood.core.data.source

import com.alvayonara.mealsfood.core.data.source.local.LocalDataSource
import com.alvayonara.mealsfood.core.data.source.remote.RemoteDataSource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodResponse
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository
import com.alvayonara.mealsfood.core.utils.AppExecutors
import com.alvayonara.mealsfood.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FoodRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFoodRepository {

    override fun getListFood(): Flowable<Resource<List<Food>>> =
        object : NetworkBoundResource<List<Food>, List<FoodResponse>>() {
            override fun loadFromDB(): Flowable<List<Food>> = localDataSource.getListFood().map {
                DataMapper.mapFoodEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Food>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<FoodResponse>>> =
                remoteDataSource.getListFood()

            override fun saveCallResult(data: List<FoodResponse>) {
                val foodList = DataMapper.mapFoodResponsesToEntities(data)
                localDataSource.insertFood(foodList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFoodDetailById(foodId: String): Flowable<List<Detail>> =
        remoteDataSource.getFoodDetailById(foodId).map {
            DataMapper.mapDetailResponsesToDomain(it)
        }

    override fun getFavoriteFood(): Flowable<List<Food>> = localDataSource.getFavoriteFood().map {
        DataMapper.mapFoodEntitiesToDomain(it)
    }

    override fun setFavoriteFood(food: Food, state: Boolean) {
        val foodEntity = DataMapper.mapFoodDomainToEntity(food)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFood(foodEntity, state) }
    }
}

