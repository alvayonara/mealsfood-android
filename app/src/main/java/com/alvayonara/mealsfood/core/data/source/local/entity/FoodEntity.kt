package com.alvayonara.mealsfood.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "foodId")
    var foodId: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "thumb")
    var thumb: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable