package com.alvayonara.mealsfood.core.domain.repository

import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food
import io.reactivex.Flowable

interface IFoodRepository {

    fun getListFood(): Flowable<Resource<List<Food>>>

    fun getFoodDetailById(foodId: String): Flowable<List<Detail>>

    fun getFavoriteFood(): Flowable<List<Food>>

    fun setFavoriteFood(food: Food, state: Boolean)
}