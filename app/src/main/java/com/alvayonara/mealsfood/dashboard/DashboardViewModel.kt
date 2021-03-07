package com.alvayonara.mealsfood.dashboard

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.domain.model.FoodCategory
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_BEEF
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_BREAKFAST
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_CHICKEN
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_DESSERT
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_GOAT
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_LAMB
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_MISCELLANEOUS
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_PASTA
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_PORK
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_SEAFOOD
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_SIDE
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_STARTER
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_VEGAN
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_VEGETARIAN

class DashboardViewModel(foodUseCase: FoodUseCase) : ViewModel() {

    private val category = MutableLiveData<String>()

    fun setFoodCategory(category: String) {
        this.category.value = category
    }

    val foodPopular = LiveDataReactiveStreams.fromPublisher(foodUseCase.getPopularFood())

    val foodCategory = Transformations.switchMap(category) {
        LiveDataReactiveStreams.fromPublisher(foodUseCase.getListFoodByCategory(it))
    }

    fun initFoodCategory() = listOf(
        FoodCategory(CATEGORY_BEEF, true),
        FoodCategory(CATEGORY_BREAKFAST, false),
        FoodCategory(CATEGORY_CHICKEN, false),
        FoodCategory(CATEGORY_DESSERT, false),
        FoodCategory(CATEGORY_GOAT, false),
        FoodCategory(CATEGORY_LAMB, false),
        FoodCategory(CATEGORY_MISCELLANEOUS, false),
        FoodCategory(CATEGORY_PASTA, false),
        FoodCategory(CATEGORY_PORK, false),
        FoodCategory(CATEGORY_SEAFOOD, false),
        FoodCategory(CATEGORY_SIDE, false),
        FoodCategory(CATEGORY_STARTER, false),
        FoodCategory(CATEGORY_VEGAN, false),
        FoodCategory(CATEGORY_VEGETARIAN, false),
    )
}