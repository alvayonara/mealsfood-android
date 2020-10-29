package com.alvayonara.mealsfood.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.ui.ViewModelFactory
import com.alvayonara.mealsfood.core.ui.FoodAdapter
import com.alvayonara.mealsfood.core.utils.gone
import com.alvayonara.mealsfood.core.utils.visible
import com.alvayonara.mealsfood.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            dashboardViewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

            val foodAdapter = FoodAdapter()

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
                            Toast.makeText(context, "An Error Occured", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(rv_foods) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = foodAdapter
            }

            edt_dashboard_search_food.setOnClickListener {
                initSearchFragment()
            }
        }
    }

    private fun initSearchFragment() {
        val mSearchFragment = SearchFragment()
        val mFragmentManager = fragmentManager
        mFragmentManager?.beginTransaction()?.apply {
            replace(R.id.frame_dashboard, mSearchFragment, SearchFragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }
}