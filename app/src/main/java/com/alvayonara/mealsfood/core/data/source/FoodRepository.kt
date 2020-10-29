package com.alvayonara.mealsfood.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alvayonara.mealsfood.core.data.source.local.LocalDataSource
import com.alvayonara.mealsfood.core.data.source.remote.RemoteDataSource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodResponse
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository
import com.alvayonara.mealsfood.core.utils.AppExecutors
import com.alvayonara.mealsfood.core.utils.DataMapper

class FoodRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFoodRepository {

    companion object {
        @Volatile
        private var instance: FoodRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FoodRepository =
            instance ?: synchronized(this) {
                instance ?: FoodRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getListFood(): LiveData<Resource<List<Food>>> =
        object : NetworkBoundResource<List<Food>, List<FoodResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Food>> {
                return Transformations.map(localDataSource.getListFood()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Food>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<FoodResponse>>> =
                remoteDataSource.getListFood()

            override fun saveCallResult(data: List<FoodResponse>) {
                val foodList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFood(foodList)
            }
        }.asLiveData()

    override fun getFoodDetailById(foodId: String): LiveData<Resource<List<Food>>> =
        object : NetworkBoundResource<List<Food>, List<FoodResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Food>> {
                return Transformations.map(localDataSource.getFoodDetailById(foodId)) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Food>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<FoodResponse>>> =
                remoteDataSource.getFoodDetailById(foodId)

            override fun saveCallResult(data: List<FoodResponse>) {
                val foodList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFood(foodList)
            }
        }.asLiveData()

    override fun getFavoriteFood(): LiveData<List<Food>> {
        return Transformations.map(localDataSource.getFavoriteFood()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteFood(food: Food, state: Boolean) {
        val foodEntity = DataMapper.mapDomainToEntity(food)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFood(foodEntity, state) }
    }
}

