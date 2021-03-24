package com.alvayonara.mealsfood.core.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import timber.log.Timber

abstract class BaseAdapter<T, VH : BaseViewHolder<T>>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {
    protected fun setLog(message: String) = Timber.e(message)
}