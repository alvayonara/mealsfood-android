package com.alvayonara.mealsfood.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealsfood.core.R
import com.alvayonara.mealsfood.core.databinding.ItemRowFoodCategoryBinding
import com.alvayonara.mealsfood.core.domain.model.FoodCategory

class FoodCategoryAdapter : RecyclerView.Adapter<FoodCategoryAdapter.FoodCategoryViewHolder>() {

    private var listFoodCategories = ArrayList<FoodCategory>()
    private var lastCheckedPosition = 0
    var onItemClick: ((FoodCategory) -> Unit)? = null

    fun setFoodCategories(foodCategories: List<FoodCategory>?) {
        if (foodCategories == null) return
        listFoodCategories.clear()
        listFoodCategories.addAll(foodCategories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodCategoryViewHolder = FoodCategoryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_food_category, parent, false)
    )

    override fun onBindViewHolder(
        holder: FoodCategoryViewHolder,
        position: Int
    ) = holder.bindItem(listFoodCategories[position], position)

    override fun getItemCount(): Int = listFoodCategories.size

    inner class FoodCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowFoodCategoryBinding.bind(itemView)

        fun bindItem(foodCategory: FoodCategory, position: Int) {
            with(itemView) {
                binding.tvFoodCategory.text = foodCategory.foodCategoryName

                if (listFoodCategories[position].isSelectedCategory) {
                    binding.llFoodCategory.background = ContextCompat.getDrawable(
                        context,
                        R.drawable.rectangle_rounded_green
                    )
                    binding.tvFoodCategory.setTextColor(
                        ContextCompat.getColor(
                            context,
                            android.R.color.white
                        )
                    )
                } else {
                    binding.llFoodCategory.background = ContextCompat.getDrawable(
                        context,
                        R.drawable.rectangle_rounded_white_outline_light_grey
                    )
                    binding.tvFoodCategory.setTextColor(
                        ContextCompat.getColor(
                            context,
                            android.R.color.black
                        )
                    )
                }

                itemView.setOnClickListener {
                    onItemClick?.invoke(listFoodCategories[adapterPosition])
                    listFoodCategories[lastCheckedPosition].isSelectedCategory = false
                    listFoodCategories[position].isSelectedCategory = true
                    lastCheckedPosition = position
                    notifyDataSetChanged()
                }
            }
        }
    }
}