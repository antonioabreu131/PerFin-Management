package com.example.perfinmanagement.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.perfinmanagement.R
import com.example.perfinmanagement.data.entities.Expense
import com.example.perfinmanagement.data.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        view.add_expense_btn.setOnClickListener{
            insertExpenseToDatabase()
        }

        return view
    }

    private fun insertExpenseToDatabase() {
        val tmpValueExpense = add_value_expense.text.toString()
        val descriptionExpense = add_description_expense.text.toString()
        val categoryExpense = add_category_expense.text.toString()
        val valueExpense = tmpValueExpense.toFloat()

        if (inputCheck(tmpValueExpense, descriptionExpense, categoryExpense)){

            //create expense to database
            val expense = Expense(0, valueExpense, descriptionExpense, categoryExpense)

            //Add Data to Database
            expenseViewModel.addExpense(expense)
            Toast.makeText(requireContext(), getString(R.string.toast_expense_added_successfully),Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),getString(R.string.toast_fill_out_all_fields),Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(valueExpense: String, descriptionExpense: String, categoryExpense: String) : Boolean{
        return !(TextUtils.isEmpty(valueExpense) && TextUtils.isEmpty(descriptionExpense) && TextUtils.isEmpty(categoryExpense))
    }
}