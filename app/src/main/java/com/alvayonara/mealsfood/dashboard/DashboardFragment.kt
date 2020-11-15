package com.alvayonara.mealsfood.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.ui.FoodAdapter
import com.alvayonara.mealsfood.core.ui.FoodAdapter.Companion.TYPE_DASHBOARD
import com.alvayonara.mealsfood.core.utils.Helper
import com.alvayonara.mealsfood.core.utils.SpacingItemDecoration
import com.alvayonara.mealsfood.core.utils.gone
import com.alvayonara.mealsfood.core.utils.visible
import com.alvayonara.mealsfood.detail.DetailFoodActivity
import com.alvayonara.mealsfood.detail.DetailFoodActivity.Companion.EXTRA_FOOD_DATA
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val foodAdapter = FoodAdapter(TYPE_DASHBOARD).apply {
                onItemClick = {
                    val intent = Intent(requireActivity(), DetailFoodActivity::class.java).putExtra(
                        EXTRA_FOOD_DATA, it
                    )
                    startActivity(intent)
                }
            }

            dashboardViewModel.food.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> progress_bar_dashboard.visible()
                        is Resource.Success -> {
                            progress_bar_dashboard.gone()
                            foodAdapter.setFoods(it.data)
                        }
                        is Resource.Error -> {
                            progress_bar_dashboard.gone()
                            Toast.makeText(context, "An Error Occurred", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(rv_foods) {
                layoutManager = GridLayoutManager(context, 2)
                addItemDecoration(SpacingItemDecoration(2, Helper.dpToPx(context, 16), true));
                setHasFixedSize(true)
                adapter = foodAdapter
            }
        }
    }
}