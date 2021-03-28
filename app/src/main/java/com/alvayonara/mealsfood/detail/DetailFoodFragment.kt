package com.alvayonara.mealsfood.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.base.BaseFragment
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.ui.FoodIngredientsAdapter
import com.alvayonara.mealsfood.core.utils.*
import com.alvayonara.mealsfood.core.utils.GenerateIngredientList.getListIngredient
import com.alvayonara.mealsfood.core.utils.Helper.setToast
import com.alvayonara.mealsfood.databinding.FragmentDetailFoodBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFoodFragment : BaseFragment<FragmentDetailFoodBinding>(), IOnBackPressed {

    private val detailFoodViewModel: DetailFoodViewModel by viewModel()

    private val args: DetailFoodFragmentArgs by navArgs()
    private lateinit var food: Food

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailFoodBinding
        get() = FragmentDetailFoodBinding::inflate

    override fun setup() {
        setupView()
        subscribeViewModel()
        checkIsFoodFavorite()
    }

    override fun setupView() {
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener { navigateUp() }
        }

        args.food.let { food = it }
    }

    override fun subscribeViewModel() {
        detailFoodViewModel.setSelectedIdMeal(food.idMeal.orEmpty())
        detailFoodViewModel.foodDetail.onLiveDataResult {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBarFoodDetail.visible()
                    is Resource.Success -> {
                        binding.progressBarFoodDetail.gone()
                        binding.layoutDetailFood.visible()
                        it.data.orEmpty().let { foodDetail ->
                            populateDetail(foodDetail)
                        }
                    }
                    is Resource.Error -> setToast("An Error Occurred", requireActivity())
                }
            }
        }
    }

    private fun populateDetail(food: List<Food>) {
        food[0].let { foodDetail ->
            with(binding) {
                Glide.with(requireActivity())
                    .load(foodDetail.strMealThumb)
                    .into(ivFoodDetail)
                tvFoodNameDetail.text = foodDetail.strMeal

                // Set view food category
                btnFoodCategory.text = foodDetail.strCategory
                btnFoodCategory.visible()

                // Set view food area
                btnFoodArea.text = foodDetail.strArea
                btnFoodArea.visible()

                // Set tags
                tvTags.text = foodDetail.strTags

                // Init list ingredients
                initListIngredients(foodDetail)

                // Set instructions
                expandableTextView.text = foodDetail.strInstructions

                btnFoodCategory.setOnClickListener {
                    val nav =
                        DetailFoodFragmentDirections.actionDetailFoodFragmentToCategoryFoodFragment(
                            foodDetail
                        )
                    navigate(nav)
                }

                btnFoodArea.setOnClickListener {
                    val nav =
                        DetailFoodFragmentDirections.actionDetailFoodFragmentToAreaFoodFragment(
                            foodDetail
                        )
                    navigate(nav)
                }
            }
        }
    }

    private fun initListIngredients(foodDetail: Food) {
        val foodIngredientsAdapter = FoodIngredientsAdapter().apply {
            setIngredients(getListIngredient(foodDetail))
        }

        with(binding.rvFoodIngredients) {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = foodIngredientsAdapter
        }
    }

    private fun checkIsFoodFavorite() {
        var isFavoriteFood = false
        detailFoodViewModel.checkIsFoodFavorite.observe(viewLifecycleOwner, {
            it?.let { favorite ->
                isFavoriteFood = favorite > 0
                setStatusFavorite(isFavoriteFood)
            }
        })

        binding.ivFoodFavoriteButton.setOnClickListener {
            with(detailFoodViewModel) {
                if (isFavoriteFood)
                    deleteFavoriteFood(food)
                else
                    insertFavoriteFood(food)
            }
            isFavoriteFood = !isFavoriteFood
            setStatusFavorite(isFavoriteFood)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        with(binding.ivFoodFavoriteButton) {
            setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    if (statusFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                )
            )
        }
    }

    override fun onBackPressed(): Boolean {
        navigateUp()
        return true
    }

    override fun releaseData() {
        binding.rvFoodIngredients.adapter = null
    }
}