package com.example.perfinmanagement.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Expense")
class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val value_expense: Float,
    val category_expense: String,
    val description_expense: String
): Parcelable