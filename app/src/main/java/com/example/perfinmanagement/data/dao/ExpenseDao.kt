package com.example.perfinmanagement.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.perfinmanagement.data.entities.Expense

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Query("DELETE FROM Expense")
    fun deleteAllExpenses()

    @Query("SELECT * FROM Expense ORDER BY id ASC")
    fun readAllExpenses(): LiveData<List<Expense>>

}