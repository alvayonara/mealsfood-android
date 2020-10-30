package com.alvayonara.mealsfood.core.data.source.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "detail")
data class DetailEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "detailId")
    var detailId: String,

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
): Parcelable