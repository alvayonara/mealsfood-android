package com.alvayonara.mealsfood.core.utils

import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.model.FoodIngredient

object GenerateIngredientList {

    fun getListIngredient(food: Food): List<FoodIngredient> {
        return ArrayList<FoodIngredient>().apply {
            food.let {
                if (!it.strIngredient1.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient1.orEmpty(),
                        it.strMeasure1.orEmpty()
                    )
                )
                if (!it.strIngredient2.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient2.orEmpty(),
                        it.strMeasure2.orEmpty()
                    )
                )
                if (!it.strIngredient3.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient3.orEmpty(),
                        it.strMeasure3.orEmpty()
                    )
                )
                if (!it.strIngredient4.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient4.orEmpty(),
                        it.strMeasure4.orEmpty()
                    )
                )
                if (!it.strIngredient5.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient5.orEmpty(),
                        it.strMeasure5.orEmpty()
                    )
                )
                if (!it.strIngredient6.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient6.orEmpty(),
                        it.strMeasure6.orEmpty()
                    )
                )
                if (!it.strIngredient7.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient7.orEmpty(),
                        it.strMeasure7.orEmpty()
                    )
                )
                if (!it.strIngredient8.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient8.orEmpty(),
                        it.strMeasure8.orEmpty()
                    )
                )
                if (!it.strIngredient9.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient9.orEmpty(),
                        it.strMeasure9.orEmpty()
                    )
                )
                if (!it.strIngredient10.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient10.orEmpty(),
                        it.strMeasure10.orEmpty()
                    )
                )
                if (!it.strIngredient11.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient11.orEmpty(),
                        it.strMeasure11.orEmpty()
                    )
                )
                if (!it.strIngredient12.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient12.orEmpty(),
                        it.strMeasure12.orEmpty()
                    )
                )
                if (!it.strIngredient13.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient13.orEmpty(),
                        it.strMeasure13.orEmpty()
                    )
                )
                if (!it.strIngredient14.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient14.orEmpty(),
                        it.strMeasure14.orEmpty()
                    )
                )
                if (!it.strIngredient15.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient15.orEmpty(),
                        it.strMeasure15.orEmpty()
                    )
                )
                if (!it.strIngredient16.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient16.orEmpty(),
                        it.strMeasure16.orEmpty()
                    )
                )
                if (!it.strIngredient17.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient17.orEmpty(),
                        it.strMeasure17.orEmpty()
                    )
                )
                if (!it.strIngredient18.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient18.orEmpty(),
                        it.strMeasure18.orEmpty()
                    )
                )
                if (!it.strIngredient19.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient19.orEmpty(),
                        it.strMeasure19.orEmpty()
                    )
                )
                if (!it.strIngredient20.isNullOrEmpty()) add(
                    FoodIngredient(
                        it.strIngredient20.orEmpty(),
                        it.strMeasure20.orEmpty()
                    )
                )
            }
        }
    }
}