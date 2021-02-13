package com.alvayonara.mealsfood.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealsfood.core.R
import com.alvayonara.mealsfood.core.databinding.ItemRowFoodGridBinding
import com.alvayonara.mealsfood.core.databinding.ItemRowFoodListBinding
import com.alvayonara.mealsfood.core.domain.model.Food
import com.bumptech.glide.Glide


class FoodAdapter constructor(private val typeView: Int) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var listFoods = ArrayList<Food>()
    var onItemClick: ((Food) -> Unit)? = null

    companion object {
        const val TYPE_GRID = 1
        const val TYPE_LIST = 2
    }

    fun setFoods(foods: List<Food>?) {
        if (foods == null) return
        listFoods.clear()
        listFoods.addAll(foods)
        notifyDataSetChanged()
    }

    fun clearFoods() {
        val size: Int = listFoods.size
        if (size > 0) {
            for (i in 0 until size) {
                listFoods.removeAt(0)
            }
            notifyItemRangeRemoved(0, size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val rowLayout = when (typeView) {
            TYPE_GRID -> R.layout.item_row_food_grid
            TYPE_LIST -> R.layout.item_row_food_list
            else -> throw IllegalArgumentException("Invalid view type")
        }

        val view = LayoutInflater.from(parent.context).inflate(
            rowLayout,
            parent,
            false
        )

        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int = listFoods.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) =
        holder.bindItem(listFoods[position], typeView)

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var bindingRowFoodGrid: ItemRowFoodGridBinding
        private lateinit var bindingRowFoodList: ItemRowFoodListBinding

        fun bindItem(food: Food, typeView: Int) {
            with(itemView) {
                when (typeView) {
                    TYPE_GRID -> bindingRowFoodGrid = ItemRowFoodGridBinding.bind(itemView)
                    TYPE_LIST -> bindingRowFoodList = ItemRowFoodListBinding.bind(itemView)
                }

                val foodImageView = when (typeView) {
                    TYPE_GRID -> bindingRowFoodGrid.ivFood
                    TYPE_LIST -> bindingRowFoodList.ivFoodFavorite
                    else -> throw IllegalArgumentException("Invalid view type")
                }

                val foodTextView = when (typeView) {
                    TYPE_GRID -> bindingRowFoodGrid.tvFood
                    TYPE_LIST -> bindingRowFoodList.tvFoodFavorite
                    else -> throw IllegalArgumentException("Invalid view type")
                }

                food.let {
                    Glide.with(context)
                        .load(it.strMealThumb)
                        .into(foodImageView)

                    foodTextView.text = it.strMeal
                }
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listFoods[adapterPosition])
            }
        }
    }
}