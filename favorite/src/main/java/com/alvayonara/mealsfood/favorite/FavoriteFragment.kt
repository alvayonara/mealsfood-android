package com.alvayonara.mealsfood.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.core.ui.FoodAdapter
import com.alvayonara.mealsfood.core.utils.gone
import com.alvayonara.mealsfood.core.utils.visible
import com.alvayonara.mealsfood.detail.DetailFoodActivity
import com.alvayonara.mealsfood.di.favoriteModule
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        if (activity != null) {
            val foodAdapter = FoodAdapter(FoodAdapter.TYPE_FAVORITE).apply {
                onItemClick = {
                    val intent = Intent(requireActivity(), DetailFoodActivity::class.java).putExtra(
                        DetailFoodActivity.EXTRA_FOOD_DATA, it
                    )
                    startActivity(intent)
                }
            }

            progress_bar_favorite.visible()

            favoriteViewModel.favoriteFood.observe(viewLifecycleOwner, {
                progress_bar_favorite.gone()

                foodAdapter.setFoods(it)
                view_empty_favorite_food.visibility =
                    if (it.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(rv_favorite_foods) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = foodAdapter
            }
        }
    }
}