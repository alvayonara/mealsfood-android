package com.alvayonara.mealsfood.core.utils

import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.model.FoodRecentSearch

object DataDummy {

    fun generateDummyFoods(): List<Food> {
        val foods = ArrayList<Food>()
        foods.add(
            Food(
                idMeal = "53014",
                strMeal = "Pizza Express Margherita",
                strCategory = "Miscellaneous",
                strArea = "Italian",
                strInstructions = "1 Preheat the oven to 230°C.\\r\\n\\r\\n2 Add the sugar and crumble the fresh yeast into warm water.\\r\\n\\r\\n3 Allow the mixture to stand for 10 – 15 minutes in a warm place (we find a windowsill on a sunny day works best) until froth develops on the surface.\\r\\n\\r\\n4 Sift the flour and salt into a large mixing bowl, make a well in the middle and pour in the yeast mixture and olive oil.\\r\\n\\r\\n5 Lightly flour your hands, and slowly mix the ingredients together until they bind.\\r\\n\\r\\n6 Generously dust your surface with flour.\\r\\n\\r\\n7 Throw down the dough and begin kneading for 10 minutes until smooth, silky and soft.\\r\\n\\r\\n8 Place in a lightly oiled, non-stick baking tray (we use a round one, but any shape will do!)\\r\\n\\r\\n9 Spread the passata on top making sure you go to the edge.\\r\\n\\r\\n10 Evenly place the mozzarella (or other cheese) on top, season with the oregano and black pepper, then drizzle with a little olive oil.\\r\\n\\r\\n11 Cook in the oven for 10 – 12 minutes until the cheese slightly colours.\\r\\n\\r\\n12 When ready, place the basil leaf on top and tuck in!",
                strMealThumb = "https://www.themealdb.com/images/media/meals/x0lk931587671540.jpg",
                strTags = "",
                strIngredient1 = "Water",
                strIngredient2 = "Sugar",
                strIngredient3 = "Yeast",
                strIngredient4 = "Plain Flour",
                strIngredient5 = "Salt",
                strIngredient6 = "Olive Oil",
                strIngredient7 = "Passata",
                strIngredient8 = "Mozzarella",
                strIngredient9 = "Oregano",
                strIngredient10 = "Basil",
                strIngredient11 = "Black Pepper",
                strIngredient12 = "",
                strIngredient13 = "",
                strIngredient14 = "",
                strIngredient15 = "",
                strIngredient16 = "",
                strIngredient17 = "",
                strIngredient18 = "",
                strIngredient19 = "",
                strIngredient20 = "",
                strMeasure1 = "150ml",
                strMeasure2 = "1 tsp",
                strMeasure3 = "15g",
                strMeasure4 = "225g",
                strMeasure5 = "1 1/2 tsp",
                strMeasure6 = "Drizzle",
                strMeasure7 = "80g",
                strMeasure8 = "70g",
                strMeasure9 = "Peeled and Sliced",
                strMeasure10 = "Leaves",
                strMeasure11 = "Pinch",
                strMeasure12 = "",
                strMeasure13 = "",
                strMeasure14 = "",
                strMeasure15 = "",
                strMeasure16 = "",
                strMeasure17 = "",
                strMeasure18 = "",
                strMeasure19 = "",
                strMeasure20 = ""
            )
        )
        foods.add(
            Food(
                idMeal = "52972",
                strMeal = "Tunisian Lamb Soup",
                strCategory = "Lamb",
                strArea = "Tunisian",
                strInstructions = "Add the lamb to a casserole and cook over high heat. When browned, remove from the heat and set aside.\\r\\nKeep a tablespoon of fat in the casserole and discard the rest. Reduce to medium heat then add the garlic, onion and spinach and cook until the onion is translucent and the spinach wilted or about 5 minutes.\\r\\nReturn the lamb to the casserole with the onion-spinach mixture, add the tomato puree, cumin, harissa, chicken, chickpeas, lemon juice, salt and pepper in the pan. Simmer over low heat for about 20 minutes.\\r\\nAdd the pasta and cook for 15 minutes or until pasta is cooked.",
                strMealThumb = "https://www.themealdb.com/images/media/meals/t8mn9g1560460231.jpg",
                strTags = "Soup",
                strIngredient1 = "Lamb Mince",
                strIngredient2 = "Garlic",
                strIngredient3 = "Onion",
                strIngredient4 = "Spinach",
                strIngredient5 = "Tomato Puree",
                strIngredient6 = "Cumin",
                strIngredient7 = "Chicken Stock",
                strIngredient8 = "Harissa Spice",
                strIngredient9 = "Chickpeas",
                strIngredient10 = "Lemon Juice",
                strIngredient11 = "Macaroni",
                strIngredient12 = "Salt",
                strIngredient13 = "Pepper",
                strIngredient14 = "",
                strIngredient15 = "",
                strIngredient16 = "",
                strIngredient17 = "",
                strIngredient18 = "",
                strIngredient19 = "",
                strIngredient20 = "",
                strMeasure1 = "500g",
                strMeasure2 = "2 cloves minced",
                strMeasure3 = "1",
                strMeasure4 = "300g",
                strMeasure5 = "3 tbs",
                strMeasure6 = "1 tbs",
                strMeasure7 = "1 Litre",
                strMeasure8 = "3 tsp",
                strMeasure9 = "400g",
                strMeasure10 = "1/2",
                strMeasure11 = "150g",
                strMeasure12 = "Pinch",
                strMeasure13 = "Pinch",
                strMeasure14 = "",
                strMeasure15 = "",
                strMeasure16 = "",
                strMeasure17 = "",
                strMeasure18 = "",
                strMeasure19 = "",
                strMeasure20 = ""
            )
        )
        return foods
    }

    fun generateDummyFoodRecentSearch(): List<FoodRecentSearch> {
        val foodRecentSearch = ArrayList<FoodRecentSearch>()
        foodRecentSearch.add(
            FoodRecentSearch(
                id = generateDummyFoods().first().idMeal.orEmpty().toInt(),
                strMeal = generateDummyFoods().first().strMeal.orEmpty()
            )
        )
        foodRecentSearch.add(
            FoodRecentSearch(
                id = generateDummyFoods()[1].idMeal.orEmpty().toInt(),
                strMeal = generateDummyFoods()[1].strMeal.orEmpty()
            )
        )
        return foodRecentSearch
    }
}