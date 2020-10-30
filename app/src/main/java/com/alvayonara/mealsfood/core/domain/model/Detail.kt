package com.alvayonara.mealsfood.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Detail (
    val category: String,
    val area: String,
    val tags: String,
    val instructions: String,
    val youtube: String,
): Parcelable