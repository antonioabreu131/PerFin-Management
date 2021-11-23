package com.example.perfinmanagement.data.repository

import androidx.lifecycle.LiveData
import com.example.perfinmanagement.data.dao.ExpenseDao
import com.example.perfinmanagement.data.entities.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val readAllExpense: LiveData<List<Expense>> = expenseDao.readAllExpenses()

    suspend fun addExpense(expense: Expense){
        expenseDao.addExpense(expense)
    }

    suspend fun updateExpense(expense: Expense){
        expenseDao.updateExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense){
        expenseDao.deleteExpense(expense)
    }

    suspend fun deleteAllExpenses(){
        expenseDao.deleteAllExpenses()
    }
}