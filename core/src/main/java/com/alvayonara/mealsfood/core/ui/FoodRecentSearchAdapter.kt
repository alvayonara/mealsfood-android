package com.alvayonara.mealsfood.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealsfood.core.R
import com.alvayonara.mealsfood.core.databinding.ItemRowSearchFoodBinding
import com.alvayonara.mealsfood.core.domain.model.FoodRecentSearch
import com.bumptech.glide.Glide

class FoodRecentSearchAdapter :
    RecyclerView.Adapter<FoodRecentSearchAdapter.FoodRecentSearchViewHolder>() {

    private var listRecentSearchFoods = ArrayList<FoodRecentSearch>()
    var onItemClick: ((FoodRecentSearch) -> Unit)? = null

    fun setRecentSearchFoods(recentSearch: List<FoodRecentSearch>?) {
        if (recentSearch == null) return
        listRecentSearchFoods.clear()
        listRecentSearchFoods.addAll(recentSearch)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodRecentSearchViewHolder = FoodRecentSearchViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_search_food, parent, false)
    )

    override fun getItemCount(): Int = listRecentSearchFoods.size

    override fun onBindViewHolder(
        holder: FoodRecentSearchViewHolder,
        position: Int
    ) = holder.bindItem(listRecentSearchFoods[position])

    inner class FoodRecentSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowSearchFoodBinding.bind(itemView)

        fun bindItem(search: FoodRecentSearch) {
            binding.tvRecentSearchFood.text = search.strMeal
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listRecentSearchFoods[adapterPosition])
            }
        }
    }
}