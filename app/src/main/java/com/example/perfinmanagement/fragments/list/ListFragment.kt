package com.example.perfinmanagement.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perfinmanagement.R
import com.example.perfinmanagement.data.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlin.math.exp

class ListFragment : Fragment() {

    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ExpenseViewModel
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        mExpenseViewModel.ReadAllData.observe(viewLifecycleOwner, Observer { expense -> adapter.setData(expense)

        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //Add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton(getString(R.string.yes)){ _, _ ->
            mExpenseViewModel.deleteAllExpenses()
            Toast.makeText(
                requireContext(),
                "${(getString(R.string.allExpenses_removed_successfully))}",
                Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ ->}

        builder.setTitle("Delete tudo?")
        builder.setMessage("Deseja realmente apagar tudo?")
        builder.create().show()
    }
}