package com.alvayonara.mealsfood.core.data.source

import com.alvayonara.mealsfood.core.data.source.local.LocalDataSource
import com.alvayonara.mealsfood.core.data.source.remote.RemoteDataSource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodListResponse
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.model.FoodRecentSearch
import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository
import com.alvayonara.mealsfood.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FoodRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IFoodRepository {

    override fun getPopularFood(): Flowable<Resource<List<Food>>> =
        object : NetworkBoundResource<List<Food>, List<FoodListResponse>>() {
            override fun loadFromDB(): Flowable<List<Food>> = localDataSource.getListFood().map {
                DataMapper.mapFoodListEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Food>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<FoodListResponse>>> =
                remoteDataSource.getListFood()

            override fun saveCallResult(data: List<FoodListResponse>) {
                val foodList = DataMapper.mapFoodListResponsesToEntities(data)
                localDataSource.insertFoodList(foodList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getListFood(): Flowable<Resource<List<Food>>> =
        object : NetworkBoundResource<List<Food>, List<FoodListResponse>>() {
            override fun loadFromDB(): Flowable<List<Food>> = localDataSource.getListFood().map {
                DataMapper.mapFoodListEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Food>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<FoodListResponse>>> =
                remoteDataSource.getListFood()

            override fun saveCallResult(data: List<FoodListResponse>) {
                val foodList = DataMapper.mapFoodListResponsesToEntities(data)
                localDataSource.insertFoodList(foodList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getListFoodByCategory(strCategory: String): Flowable<Resource<List<Food>>> =
        remoteDataSource.getListFoodByCategory(strCategory)

    override fun getListFoodByArea(strArea: String): Flowable<Resource<List<Food>>> =
        remoteDataSource.getListFoodByArea(strArea)

    override fun getFoodDetailById(idMeal: String): Flowable<Resource<List<Food>>> =
        remoteDataSource.getFoodDetailById(idMeal)

    override fun searchFood(meal: String): Flowable<ApiResponse<List<Food>>> =
        remoteDataSource.searchFood(meal)

    override fun getFavoriteFood(): Flowable<List<Food>> =
        localDataSource.getFavoriteFood().map {
            DataMapper.mapFoodDetailEntitiesToDomain(it)
        }

    override fun checkIsFavoriteFood(idMeal: String): Flowable<Int> =
        localDataSource.checkIsFavoriteFood(idMeal)

    override fun insertFavoriteFood(food: Food) {
        localDataSource.insertFavoriteFood(DataMapper.mapFoodDomainToEntity(food))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun deleteFavoriteFood(food: Food) {
        localDataSource.deleteFavoriteFood(DataMapper.mapFoodDomainToEntity(food))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun getRecentSearchFood(): Flowable<List<FoodRecentSearch>> =
        localDataSource.getRecentSearchFood().map {
            DataMapper.mapFoodRecentSearchEntitiesToDomain(it)
        }

    override fun insertRecentSearchFood(search: FoodRecentSearch) {
        localDataSource.insertRecentSearchFood(DataMapper.mapFoodRecentSearchDomainToEntity(search))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}

