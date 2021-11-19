package com.example.perfinmanagement.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.perfinmanagement.data.entities.Expense

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addExpense(expense: Expense)

    @Query("SELECT * FROM Expense ORDER BY id ASC")
    fun readAllExpenses(): LiveData<List<Expense>>

}