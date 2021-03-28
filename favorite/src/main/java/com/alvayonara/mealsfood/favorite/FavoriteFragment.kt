package com.alvayonara.mealsfood.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.core.base.BaseFragment
import com.alvayonara.mealsfood.core.ui.FoodAdapter
import com.alvayonara.mealsfood.core.utils.IOnBackPressed
import com.alvayonara.mealsfood.core.utils.gone
import com.alvayonara.mealsfood.core.utils.navigate
import com.alvayonara.mealsfood.core.utils.visible
import com.alvayonara.mealsfood.di.favoriteModule
import com.alvayonara.mealsfood.favorite.databinding.FragmentFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), IOnBackPressed {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var foodAdapter: FoodAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate

    override fun setup() {
        loadKoinModules(favoriteModule)
        setupRecyclerView()
        subscribeViewModel()
    }

    override fun setupRecyclerView() {
        foodAdapter = FoodAdapter(FoodAdapter.TYPE_LIST).apply {
            onItemClick = {
                val nav =
                    FavoriteFragmentDirections.actionNavigationFavoriteToDetailFoodFragment(it)
                navigate(nav)
            }
        }

        with(binding.rvFavoriteFoods) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    override fun subscribeViewModel() {
        binding.progressBarFavorite.visible()
        favoriteViewModel.favoriteFood.onLiveDataResult {
            binding.progressBarFavorite.gone()
            foodAdapter.setFoods(it)
            binding.viewEmptyFavoriteFood.root.visibility =
                if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }

    override fun releaseData() {
        binding.rvFavoriteFoods.adapter = null
    }

    override fun onBackPressed(): Boolean = false
}