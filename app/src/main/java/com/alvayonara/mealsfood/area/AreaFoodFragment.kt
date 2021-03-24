package com.alvayonara.mealsfood.area

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
import com.alvayonara.mealsfood.databinding.FragmentAreaFoodBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AreaFoodFragment : BaseFragment<FragmentAreaFoodBinding>(), IOnBackPressed {

    private val areaFoodViewModel: AreaFoodViewModel by viewModel()
    private lateinit var foodAdapter: FoodAdapter

    private val args: AreaFoodFragmentArgs by navArgs()
    private lateinit var food: Food

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAreaFoodBinding
        get() = FragmentAreaFoodBinding::inflate

    override fun setup() {
        setupView()
        setupRecyclerView()
        subscribeViewModel()
    }

    override fun setupView() {
        args.food.let { food = it }
        with(binding) {
            tvFoodAreaName.text = food.strArea
            ivAreaBack.setOnClickListener { navigateUp() }
        }
    }

    override fun setupRecyclerView() {
        foodAdapter = FoodAdapter(TYPE_LIST).apply {
            onItemClick = {
                val nav =
                    AreaFoodFragmentDirections.actionAreaFoodFragmentToDetailFoodFragment(it)
                navigate(nav)
            }
        }

        with(binding.rvAreaFoods) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    override fun subscribeViewModel() {
        areaFoodViewModel.setSelectedFoodArea(food.strArea.orEmpty())
        areaFoodViewModel.food.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBarAreaFoods.visible()
                    is Resource.Success -> {
                        binding.progressBarAreaFoods.gone()
                        foodAdapter.setFoods(it.data)
                    }
                    is Resource.Error -> setToast("An Error Occurred", requireActivity())
                }
            }
        })
    }

    override fun releaseData() {
        binding.rvAreaFoods.adapter = null
    }

    override fun onBackPressed(): Boolean {
        navigateUp()
        return true
    }
}