package com.alvayonara.mealsfood.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.core.base.BaseFragment
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.ui.FoodAdapter
import com.alvayonara.mealsfood.core.ui.FoodAdapter.Companion.TYPE_GRID
import com.alvayonara.mealsfood.core.ui.FoodAdapter.Companion.TYPE_POPULAR_FOOD
import com.alvayonara.mealsfood.core.ui.FoodCategoryAdapter
import com.alvayonara.mealsfood.core.utils.*
import com.alvayonara.mealsfood.core.utils.ConstFoodCategory.CATEGORY_BEEF
import com.alvayonara.mealsfood.core.utils.Helper.setToast
import com.alvayonara.mealsfood.databinding.FragmentDashboardBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<FragmentDashboardBinding>(), IOnBackPressed {

    private val dashboardViewModel: DashboardViewModel by viewModel()
    private lateinit var foodPopularAdapter: FoodAdapter
    private lateinit var foodCategoryAdapter: FoodAdapter
    private lateinit var categoryAdapter: FoodCategoryAdapter

    private var category: String = CATEGORY_BEEF

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDashboardBinding
        get() = FragmentDashboardBinding::inflate

    override fun setup() {
        setupView()
        setupRecyclerView()
        subscribeViewModel()
    }

    override fun setupView() {
        binding.edtSearchFoodDashboard.setOnClickListener {
            val nav =
                DashboardFragmentDirections.actionNavigationDashboardToSearchFoodFragment()
            navigate(nav)
        }
    }

    override fun setupRecyclerView() {
        foodPopularAdapter = FoodAdapter(TYPE_POPULAR_FOOD).apply {
            onItemClick = {
                val nav =
                    DashboardFragmentDirections.actionNavigationDashboardToDetailFoodFragment(it)
                navigate(nav)
            }
        }

        foodCategoryAdapter = FoodAdapter(TYPE_GRID).apply {
            onItemClick = {
                val nav =
                    DashboardFragmentDirections.actionNavigationDashboardToDetailFoodFragment(it)
                navigate(nav)
            }
        }

        categoryAdapter = FoodCategoryAdapter().apply {
            onItemClick = {
                category = it.foodCategoryName
                dashboardViewModel.setFoodCategory(it.foodCategoryName)
            }
        }

        with(binding.rvPopularFood) {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = foodPopularAdapter
        }

        with(binding.rvFoods) {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(SpacingItemDecoration(2, Helper.dpToPx(context, 16), true))
            setHasFixedSize(true)
            adapter = foodCategoryAdapter
        }

        categoryAdapter.setFoodCategories(dashboardViewModel.initFoodCategory())
        with(binding.rvCategory) {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }

    override fun subscribeViewModel() {
        dashboardViewModel.foodPopular.onLiveDataResult {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBarPopularFood.visible()
                    is Resource.Success -> {
                        binding.progressBarPopularFood.gone()
                        binding.rvPopularFood.visible()
                        foodPopularAdapter.setFoods(it.data)
                    }
                    is Resource.Error -> setToast("An Error Occurred", requireActivity())
                }
            }
        }

        dashboardViewModel.foodCategory.onLiveDataResult {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBarCategoryFood.visible()
                    is Resource.Success -> {
                        binding.progressBarCategoryFood.gone()
                        binding.rvFoods.visible()
                        foodCategoryAdapter.setFoods(it.data)
                    }
                    is Resource.Error -> setToast("An Error Occurred", requireActivity())
                }
            }
        }
    }

    override fun releaseData() {
        binding.rvPopularFood.adapter = null
        binding.rvFoods.adapter = null
        binding.rvCategory.adapter = null
    }

    override fun onBackPressed(): Boolean = false

    override fun onResume() {
        super.onResume()
        dashboardViewModel.setFoodCategory(category)
    }
}