package com.alvayonara.mealsfood.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.domain.model.Food
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_favorite_food.view.*
import kotlinx.android.synthetic.main.item_row_food.view.*

class FoodAdapter constructor(private val typeView: Int) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var listFoods = ArrayList<Food>()
    var onItemClick: ((Food) -> Unit)? = null

    companion object {
        const val TYPE_DASHBOARD = 1
        const val TYPE_FAVORITE = 2
    }

    fun setFoods(foods: List<Food>?) {
        if (foods == null) return
        listFoods.clear()
        listFoods.addAll(foods)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val rowLayout = when (typeView) {
            TYPE_DASHBOARD -> R.layout.item_row_food
            TYPE_FAVORITE -> R.layout.item_row_favorite_food
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
        fun bindItem(food: Food, typeView: Int) {
            with(itemView) {
                val foodImageView = when (typeView) {
                    TYPE_DASHBOARD -> iv_food
                    TYPE_FAVORITE -> iv_food_favorite
                    else -> throw IllegalArgumentException("Invalid view type")
                }

                val foodTextView = when (typeView) {
                    TYPE_DASHBOARD -> tv_food
                    TYPE_FAVORITE -> tv_food_favorite
                    else -> throw IllegalArgumentException("Invalid view type")
                }

                food.let {
                    Glide.with(context)
                        .load(it.thumb)
                        .into(foodImageView)

                    foodTextView.text = it.name
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