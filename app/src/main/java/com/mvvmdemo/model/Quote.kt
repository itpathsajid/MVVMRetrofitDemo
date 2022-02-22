package com.mvvmdemo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "quote")
data class Quote(@PrimaryKey(autoGenerate = true) val id : Int, var text: String, var author: String) : Parcelable
