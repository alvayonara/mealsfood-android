package com.alvayonara.mealsfood.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.ui.FoodAdapter
import com.alvayonara.mealsfood.core.ui.FoodAdapter.Companion.TYPE_LIST
import com.alvayonara.mealsfood.core.utils.*
import com.alvayonara.mealsfood.core.utils.Helper.setToast
import com.alvayonara.mealsfood.dashboard.DashboardViewModel
import com.alvayonara.mealsfood.databinding.FragmentCategoryFoodBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryFoodFragment : Fragment(), IOnBackPressed {

    private val categoryFoodViewModel: CategoryFoodViewModel by viewModel()
    private lateinit var foodAdapter: FoodAdapter

    private var _binding: FragmentCategoryFoodBinding? = null
    private val binding get() = _binding!!

    private val args: CategoryFoodFragmentArgs by navArgs()
    private lateinit var food: Food

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            initView()
            initAdapter()
            initRecyclerView()
            subscribeVm()
        }
    }

    private fun initView() {
        args.food.let { food = it }
        with(binding) {
            tvFoodCategoryName.text = food.strCategory
            ivCategoryBack.setOnClickListener { navigateUp() }
        }
    }

    private fun initAdapter() {
        foodAdapter = FoodAdapter(TYPE_LIST).apply {
            onItemClick = {
                val nav =
                    CategoryFoodFragmentDirections.actionCategoryFoodFragmentToDetailFoodFragment(it)
                navigate(nav)
            }
        }
    }

    private fun initRecyclerView() {
        with(binding.rvCategoryFoods) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    private fun subscribeVm() {
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

    override fun onBackPressed(): Boolean {
        navigateUp()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvCategoryFoods.adapter = null
        _binding = null
    }
}