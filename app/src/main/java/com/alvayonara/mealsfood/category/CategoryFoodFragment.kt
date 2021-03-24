package com.alvayonara.mealsfood.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.core.base.BaseFragment
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.ui.FoodAdapter
import com.alvayonara.mealsfood.core.ui.FoodAdapter.Companion.TYPE_LIST
import com.alvayonara.mealsfood.core.utils.*
import com.alvayonara.mealsfood.core.utils.Helper.setToast
import com.alvayonara.mealsfood.databinding.FragmentCategoryFoodBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryFoodFragment : BaseFragment<FragmentCategoryFoodBinding>(), IOnBackPressed {

    private val categoryFoodViewModel: CategoryFoodViewModel by viewModel()
    private lateinit var foodAdapter: FoodAdapter

    private val args: CategoryFoodFragmentArgs by navArgs()
    private lateinit var food: Food

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoryFoodBinding
        get() = FragmentCategoryFoodBinding::inflate

    override fun setup() {
        setupView()
        setupRecyclerView()
        subscribeViewModel()
    }

    override fun setupView() {
        args.food.let { food = it }
        with(binding) {
            tvFoodCategoryName.text = food.strCategory
            ivCategoryBack.setOnClickListener { navigateUp() }
        }
    }

    override fun setupRecyclerView() {
        foodAdapter = FoodAdapter(TYPE_LIST).apply {
            onItemClick = {
                val nav =
                    CategoryFoodFragmentDirections.actionCategoryFoodFragmentToDetailFoodFragment(it)
                navigate(nav)
            }
        }

        with(binding.rvCategoryFoods) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    override fun subscribeViewModel() {
        categoryFoodViewModel.setSelectedFoodCategory(food.strCategory.orEmpty())
        categoryFoodViewModel.food.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBarCategoryFoods.visible()
                    is Resource.Success -> {
                        binding.progressBarCategoryFoods.gone()
                        foodAdapter.setFoods(it.data)
                    }
                    is Resource.Error -> setToast("An Error Occurred", requireActivity())
                }
            }
        })
    }

    override fun releaseData() {
        binding.rvCategoryFoods.adapter = null
    }

    override fun onBackPressed(): Boolean {
        navigateUp()
        return true
    }
}