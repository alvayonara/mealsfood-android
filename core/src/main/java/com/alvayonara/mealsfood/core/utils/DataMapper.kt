package com.alvayonara.mealsfood.core.utils

import com.alvayonara.mealsfood.core.data.source.local.entity.FoodEntity
import com.alvayonara.mealsfood.core.data.source.remote.response.DetailResponse
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodResponse
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food

object DataMapper {

    fun mapFoodResponsesToEntities(input: List<FoodResponse>): List<FoodEntity> {
        val foodList = ArrayList<FoodEntity>()
        input.map {
            val food = FoodEntity(
                foodId = it.foodId ?: "-",
                name = it.name ?: "-",
                thumb = it.thumb ?: "-",
                isFavorite = false
            )
            foodList.add(food)
        }
        return foodList
    }

    fun mapDetailResponsesToDomain(input: List<DetailResponse>): List<Detail> {
        val detailList = ArrayList<Detail>()
        input.map {
            val detail = Detail(
                category = it.category ?: "-",
                area = it.area ?: "-",
                tags = it.tags ?: "-",
                instructions = it.instructions ?: "-",
                youtube = it.youtube ?: "-"
            )
            detailList.add(detail)
        }
        return detailList
    }

    fun mapFoodEntitiesToDomain(input: List<FoodEntity>): List<Food> =
        input.map {
            Food(
                id = it.foodId,
                name = it.name,
                thumb = it.thumb,
                isFavorite = it.isFavorite
            )
        }

    fun mapFoodDomainToEntity(input: Food) = FoodEntity(
        foodId = input.id,
        name = input.name,
        thumb = input.thumb,
        isFavorite = input.isFavorite
    )
}