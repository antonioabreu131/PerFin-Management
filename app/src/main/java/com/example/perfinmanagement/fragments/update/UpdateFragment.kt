package com.example.perfinmanagement.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.perfinmanagement.R
import com.example.perfinmanagement.data.entities.Expense
import com.example.perfinmanagement.data.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        view.update_value_expense.setText(args.currentExpense.value_expense.toString())
        view.update_description_expense.setText(args.currentExpense.description_expense)
        view.update_category_expense.setText(args.currentExpense.category_expense)

        view.update_expense_btn.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem(){
        val tmpValueExpense = update_value_expense.text.toString()
        val valueExpense =tmpValueExpense.toFloat()
        val descriptionExpense = update_description_expense.text.toString()
        val categoryExpense = update_category_expense.text.toString()

        if(inputCheck(tmpValueExpense, descriptionExpense,categoryExpense)){
            // Create user object

            val updatedExpense = Expense(args.currentExpense.id, valueExpense, categoryExpense,descriptionExpense)

            //Update current Expense
            mExpenseViewModel.updateExpense(updatedExpense)
            Toast.makeText(requireContext(), getString(R.string.expanse_updated_successfully), Toast.LENGTH_SHORT).show()

            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), getString(R.string.toast_fill_out_all_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(valueExpense: String, descriptionExpense: String, categoryExpense: String) : Boolean{
        return !(TextUtils.isEmpty(valueExpense) && TextUtils.isEmpty(descriptionExpense) && TextUtils.isEmpty(categoryExpense))
    }

}