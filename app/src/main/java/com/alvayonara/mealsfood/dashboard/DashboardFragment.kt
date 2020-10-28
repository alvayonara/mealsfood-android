package com.alvayonara.mealsfood.dashboard

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edt_dashboard_search_food.setOnClickListener{
            initSearchFragment()
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