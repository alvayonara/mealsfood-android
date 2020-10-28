package com.alvayonara.mealsfood.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alvayonara.mealsfood.R

class DetailFoodFragment : Fragment() {

    companion object {
        const val EXTRA_FOOD_ID = "extra_food_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail_food, container, false)
}