package com.example.perfinmanagement.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.perfinmanagement.data.database.PerFinDatabase
import com.example.perfinmanagement.data.entities.Expense
import com.example.perfinmanagement.data.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application): AndroidViewModel(application) {

    private val readAllExpense: LiveData<List<Expense>>
    private val repository: ExpenseRepository //variável do repositório

    init { //É sempre o primeiro a ser executado quando o ExpenseViewModel é chamado
        val expenseDao = PerFinDatabase.getDatabase(application)
            .expenseDao() //aceder à ExpenseDao através do perfinDatabase
        repository = ExpenseRepository(expenseDao)
        readAllExpense = repository.readAllExpense
    }

    fun addExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpense(expense)
        }
    }
}