package com.alvayonara.mealsfood.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealsfood.core.BuildConfig
import com.alvayonara.mealsfood.core.R
import com.alvayonara.mealsfood.core.databinding.ItemRowIngredientsBinding
import com.alvayonara.mealsfood.core.domain.model.FoodIngredient
import com.bumptech.glide.Glide

class FoodIngredientsAdapter :
    RecyclerView.Adapter<FoodIngredientsAdapter.FoodIngredientsViewHolder>() {

    private var listIngredients = ArrayList<FoodIngredient>()

    fun setIngredients(ingredient: List<FoodIngredient>?) {
        if (ingredient == null) return
        listIngredients.clear()
        listIngredients.addAll(ingredient)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): FoodIngredientsViewHolder = FoodIngredientsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_ingredients, parent, false)
    )

    override fun getItemCount(): Int = listIngredients.size

    override fun onBindViewHolder(
        holder: FoodIngredientsViewHolder, position: Int
    ) = holder.bindItem(listIngredients[position])

    inner class FoodIngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowIngredientsBinding.bind(itemView)

        fun bindItem(ingredient: FoodIngredient) {
            with(itemView) {
                ingredient.let {
                    with(binding) {
                        Glide.with(context)
                            .load("${BuildConfig.BASE_URL_IMAGE}${it.ingredient}-Small.png")
                            .into(ivIngredients)
                        tvIngredients.text = it.ingredient
                        tvMeasure.text = it.measure
                    }
                }
            }
        }
    }
}