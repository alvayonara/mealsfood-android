package com.alvayonara.mealsfood.core.domain

import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository
import com.alvayonara.mealsfood.core.domain.usecase.FoodInteractor
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import com.alvayonara.mealsfood.core.utils.DataDummy
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FoodUseCaseTest {

    private lateinit var foodUseCase: FoodUseCase

    private var food = DataDummy.generateDummyFoods().first()
    private var foodCategory = food.strCategory.orEmpty()
    private var foodArea = food.strArea.orEmpty()
    private var foodId = food.idMeal.orEmpty()
    private var foodMeal = food.strMeal.orEmpty()

    private var recentSearch = DataDummy.generateDummyFoodRecentSearch().first()

    private val foodRepository: IFoodRepository = mock()

    @Before
    fun setUp() {
        foodUseCase = FoodInteractor(foodRepository)
    }

    @Test
    fun `should get popular food from repository`() {
        foodUseCase.getPopularFood()
        verify(foodRepository).getPopularFood()
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should get list food by category from repository`() {
        foodUseCase.getListFoodByCategory(foodCategory)
        verify(foodRepository).getListFoodByCategory(foodCategory)
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should get list food by area from repository`() {
        foodUseCase.getListFoodByArea(foodArea)
        verify(foodRepository).getListFoodByArea(foodArea)
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should get food detail by id from repository`() {
        foodUseCase.getFoodDetailById(foodId)
        verify(foodRepository).getFoodDetailById(foodId)
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should get search food from repository`() {
        foodUseCase.searchFood(foodMeal)
        verify(foodRepository).searchFood(foodMeal)
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should get favorite food from repository`() {
        foodUseCase.getFavoriteFood()
        verify(foodRepository).getFavoriteFood()
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should check is favorite food from repository`() {
        foodUseCase.checkIsFavoriteFood(foodId)
        verify(foodRepository).checkIsFavoriteFood(foodId)
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should insert favorite food from repository`() {
        foodUseCase.insertFavoriteFood(food)
        verify(foodRepository).insertFavoriteFood(food)
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should delete favorite food from repository`() {
        foodUseCase.deleteFavoriteFood(food)
        verify(foodRepository).deleteFavoriteFood(food)
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should get recent search food from repository`() {
        foodUseCase.getRecentSearchFood()
        verify(foodRepository).getRecentSearchFood()
        verifyNoMoreInteractions(foodRepository)
    }

    @Test
    fun `should insert recent search food from repository`() {
        foodUseCase.insertRecentSearchFood(recentSearch)
        verify(foodRepository).insertRecentSearchFood(recentSearch)
        verifyNoMoreInteractions(foodRepository)
    }
}