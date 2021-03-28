package com.alvayonara.mealsfood.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "foodList")
data class FoodListEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idMeal") var idMeal: String,
    @ColumnInfo(name = "strMeal") var strMeal: String? = "",
    @ColumnInfo(name = "strMealThumb") var strMealThumb: String? = ""
) : Parcelable