package com.alvayonara.mealsfood.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.detail.DetailFoodActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_food.view.*

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var listFoods = ArrayList<Food>()
    var onItemClick: ((Food) -> Unit)? = null

    fun setFoods(foods: List<Food>?) {
        if (foods == null) return
        listFoods.clear()
        listFoods.addAll(foods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FoodViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_food, parent, false)
        )

    override fun getItemCount(): Int = listFoods.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) =
        holder.bindItem(listFoods[position])

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(food: Food) {
            with(itemView) {
                food.let {
                    Glide.with(context)
                        .load(it.thumb)
                        .into(iv_food)

                    tv_food.text = it.name
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