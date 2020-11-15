package com.alvayonara.mealsfood.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food (
    val id: String,
    val name: String,
    val thumb: String,
    val isFavorite: Boolean
) : Parcelable