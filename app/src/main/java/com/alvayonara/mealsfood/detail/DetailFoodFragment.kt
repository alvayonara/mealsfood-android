package com.alvayonara.mealsfood.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.domain.model.FoodIngredient
import com.alvayonara.mealsfood.core.ui.FoodIngredientsAdapter
import com.alvayonara.mealsfood.core.utils.*
import com.alvayonara.mealsfood.core.utils.GenerateIngredientList.getListIngredient
import com.alvayonara.mealsfood.core.utils.Helper.setDefaultStatusBarColor
import com.alvayonara.mealsfood.core.utils.Helper.setLightStatusBar
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
    ): View? {
        _binding = FragmentDetailFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        with((activity as AppCompatActivity).supportActionBar) {
            this?.title = ""
            this?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        setDefaultStatusBarColor(requireActivity())
        setLightStatusBar(requireActivity())
    }

    private fun initView() {
        args.food.let { food = it }
        getFoodDetail()
        checkIsFoodFavorite()
    }

    private fun getFoodDetail() {
        detailFoodViewModel.setSelectedIdMeal(food.idMeal.orEmpty())
        detailFoodViewModel.foodDetail.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBarFoodDetail.visible()
                    is Resource.Success -> {
                        binding.progressBarFoodDetail.gone()
                        it.data.orEmpty().let { foodDetail ->
                            populateDetail(foodDetail)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBarFoodDetail.gone()
                        Toast.makeText(requireActivity(), "An Error Occurred", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }

    private fun populateDetail(food: List<Food>) {
        food[0].let { foodDetail ->
            with(binding) {
                Glide.with(requireActivity())
                    .load(foodDetail.strMealThumb)
                    .into(binding.ivFoodDetail)
                binding.tvFoodNameDetail.text = foodDetail.strMeal

                // Set view food category
                btnFoodCategory.text = foodDetail.strCategory
                btnFoodCategory.visible()

                // Set view food area
                btnFoodArea.text = foodDetail.strArea
                btnFoodArea.visible()

                tvTags.text = foodDetail.strTags
                _expandableTextView.text = foodDetail.strInstructions

                initListIngredients(foodDetail)

                btnFoodCategory.setOnClickListener {
                    val nav =
                        DetailFoodFragmentDirections.actionDetailFoodFragmentToCategoryFoodFragment(
                            foodDetail
                        )
                    navigate(nav)
                }

                binding.btnFoodArea.setOnClickListener {
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
            if (statusFavorite) {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite
                    )
                )
            } else {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite_border
                    )
                )
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed(): Boolean {
        navigateUp()
        return true
    }
}