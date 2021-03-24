package com.alvayonara.mealsfood.core.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindView(element: @UnsafeVariance T)
}