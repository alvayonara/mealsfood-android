package com.alvayonara.mealsfood.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealsfood.FoodEntity
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.detail.DetailFoodFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_food.view.*


class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var listFoods = ArrayList<FoodEntity>()

    fun setFoods(foods: List<FoodEntity>?) {
        if (foods == null) return
        listFoods.clear()
        listFoods.addAll(foods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder =
        FoodViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_food, parent, false)
        )

    override fun getItemCount(): Int = listFoods.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) =
        holder.bindItem(listFoods[position])

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(food: FoodEntity) {
            with(itemView) {
                food.let {
                    Glide.with(context)
                        .load(it.foodThumb)
                        .into(iv_food)

                    tv_food.text = it.foodName

                    setOnClickListener {
                        val mBundle = Bundle().apply {
                            putString(DetailFoodFragment.EXTRA_FOOD_ID, food.foodId)
                        }

                        val mDetailFoodFragment = DetailFoodFragment().apply {
                            arguments = mBundle
                        }

                        val mFragmentManager =
                            (context as AppCompatActivity).supportFragmentManager
                        mFragmentManager.beginTransaction().apply {
                            replace(
                                R.id.frame_dashboard,
                                mDetailFoodFragment,
                                DetailFoodFragment::class.java.simpleName
                            )
                            addToBackStack(null)
                            commit()
                        }
                    }
                }
            }
        }
    }
}