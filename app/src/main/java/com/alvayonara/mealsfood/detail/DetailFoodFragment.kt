package com.alvayonara.mealsfood.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.ui.FoodIngredientsAdapter
import com.alvayonara.mealsfood.core.utils.*
import com.alvayonara.mealsfood.core.utils.GenerateIngredientList.getListIngredient
import com.alvayonara.mealsfood.core.utils.Helper.setDefaultStatusBarColor
import com.alvayonara.mealsfood.core.utils.Helper.setLightStatusBar
import com.alvayonara.mealsfood.core.utils.Helper.setToast
import com.alvayonara.mealsfood.databinding.FragmentDetailFoodBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_food.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFoodFragment : Fragment(), IOnBackPressed {

    private val detailFoodViewModel: DetailFoodViewModel by viewModel()

    private var _binding: FragmentDetailFoodBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFoodFragmentArgs by navArgs()
    private lateinit var food: Food

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            initToolbar()
            initView()
            subscribeVm()
            checkIsFoodFavorite()
        }
    }

    private fun initToolbar() {
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener { navigateUp() }
        }
        setDefaultStatusBarColor(requireActivity())
        setLightStatusBar(requireActivity())
    }

    private fun initView() {
        args.food.let { food = it }
    }

    private fun subscribeVm() {
        detailFoodViewModel.setSelectedIdMeal(food.idMeal.orEmpty())
        detailFoodViewModel.foodDetail.observe(viewLifecycleOwner, {
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
        })
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
                _expandableTextView.text = foodDetail.strInstructions

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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvFoodIngredients.adapter = null
        _binding = null
    }
}