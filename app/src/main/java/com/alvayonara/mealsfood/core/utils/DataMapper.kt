package com.alvayonara.mealsfood.core.utils

import com.alvayonara.mealsfood.core.data.source.local.entity.FoodEntity
import com.alvayonara.mealsfood.core.data.source.remote.response.FoodResponse
import com.alvayonara.mealsfood.core.domain.model.Food

object DataMapper {

    fun mapResponsesToEntities(input: List<FoodResponse>): List<FoodEntity> {
        val foodList = ArrayList<FoodEntity>()
        input.map {
            val food = FoodEntity(
                id = it.id ?: "-",
                name = it.name ?: "-",
                thumb = it.thumb ?: "-",
                category = it.category ?: "-",
                area = it.area ?: "-",
                tags = it.tags ?: "-",
                instructions = it.instructions ?: "-",
                youtube = it.youtube ?: "-",
                isFavorite = false
            )
            foodList.add(food)
        }
        return foodList
    }

    fun mapEntitiesToDomain(input: List<FoodEntity>): List<Food> =
        input.map {
            Food(
                id = it.id,
                name = it.name,
                thumb = it.thumb,
                category = it.category,
                area = it.area,
                tags = it.tags,
                instructions = it.instructions,
                youtube = it.youtube,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Food) = FoodEntity(
        id = input.id,
        name = input.name,
        thumb = input.thumb,
        category = input.category,
        area = input.area,
        tags = input.tags,
        instructions = input.instructions,
        youtube = input.youtube,
        isFavorite = input.isFavorite
    )
}