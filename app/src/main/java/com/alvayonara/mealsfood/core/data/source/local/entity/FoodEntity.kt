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
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "thumb")
    var thumb: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "area")
    var area: String,

    @ColumnInfo(name = "tags")
    var tags: String,

    @ColumnInfo(name = "instructions")
    var instructions: String,

    @ColumnInfo(name = "youtube")
    var youtube: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable